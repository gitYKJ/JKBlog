package com.jkblog.server.websocketchat;

import com.jkblog.entity.massgefactory.MessageFactory;
import com.jkblog.server.webSocketServer.MessageHandler;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * HttpRequest Handler.
 * 学习
 * @since
 * @author
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> { // （1）
	private final String wsUri;
	private static final File INDEX;
	private MessageHandler mh=new MessageHandler();
	static {
		URL location = HttpRequestHandler.class.getProtectionDomain().getCodeSource().getLocation();
		try {
			String path = location.toURI() + "WebsocketChatClient.html";
			//String path = location.toURI() + "index.html";
			path = !path.contains("file:") ? path : path.substring(5);
			INDEX = new File(path);
		} catch (URISyntaxException e) {
			throw new IllegalStateException("Unable to locate WebsocketChatClient.html", e);
		}
	}

	public HttpRequestHandler(String wsUri) {
		this.wsUri = wsUri;
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		if (wsUri.equalsIgnoreCase(request.getUri())) {
			ctx.fireChannelRead(request.retain()); // （2）
		} else {
			if (HttpHeaders.is100ContinueExpected(request)) {
				send100Continue(ctx); // （3）
			}

			RandomAccessFile file = new RandomAccessFile(INDEX, "r");// （4）

			HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
			response.headers().set(HttpHeaders.Names.CONTENT_TYPE,  "text/html; charset=UTF-8");
			//response.headers().set(HttpHeaders.Names.ACCESS_CONTROL_ALLOW_ORIGIN,"*");


			boolean keepAlive = HttpHeaders.isKeepAlive(request);
			if (keepAlive) { // （5）
				response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, file.length());
				response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
			}
			ctx.write(response); // （6）

			if (ctx.pipeline().get(SslHandler.class) == null) { // （7）
				ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
				/*Channel channel=ctx.channel();
				mh.setChannel(channel);
				channel=mh.getChannel();
				System.out.println("channel"+channel);*/
			} else {
				ctx.write(new ChunkedNioFile(file.getChannel()));

			}
			ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);// （8）
			System.out.println(future);
			if (!keepAlive) {
				future.addListener(ChannelFutureListener.CLOSE); // （9）
			}

			file.close();
		}
	}

	private static void send100Continue(ChannelHandlerContext ctx) {
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
		ctx.writeAndFlush(response);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("Client:" + incoming.remoteAddress() + "异常");
		// 当出现异常就关闭连接
		cause.printStackTrace();
		ctx.close();
	}
	public MessageFactory pushMsg(){
		return null;
	}
}
