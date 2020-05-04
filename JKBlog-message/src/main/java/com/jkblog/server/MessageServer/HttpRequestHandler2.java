package com.jkblog.server.MessageServer;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
public class HttpRequestHandler2 extends SimpleChannelInboundHandler<FullHttpRequest> {
    private final String wsUri;
    private static final File INDEX;
    static {
        URL location = HttpRequestHandler2.class
                .getProtectionDomain()
                .getCodeSource().getLocation();
        try {
            String path = location.toURI() + "WebsocketChatClient.html";
            System.out.println(path);
            path = !path.contains("file:") ? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Unable to locate index.html", e);
        }
    }

    public HttpRequestHandler2(String wsUri) {
        this.wsUri = wsUri;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        if(wsUri.equalsIgnoreCase(fullHttpRequest.uri())){
            ctx.fireChannelRead(fullHttpRequest.retain());
        }else {
            if(HttpHeaders.is100ContinueExpected(fullHttpRequest)){
                send100Continue(ctx);
            }
            RandomAccessFile file=new RandomAccessFile(INDEX,"r");
            HttpResponse response=new DefaultFullHttpResponse(fullHttpRequest.getProtocolVersion(),
                    HttpResponseStatus.OK);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/html; charset=UTF-8");
            Boolean keepAlive=HttpHeaders.isKeepAlive(fullHttpRequest);
            if(keepAlive){
                response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,file);
                response.headers().set(HttpHeaders.Names.CONNECTION,HttpHeaders.Values.KEEP_ALIVE);
            }
            ctx.write(response);
            if(ctx.pipeline().get(SslHandler.class)==null){
                ctx.write(new DefaultFileRegion(file.getChannel(),0,file.length()));
            }else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            ChannelFuture future=ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            System.out.println(future);
            if(!keepAlive){
               future.addListener(ChannelFutureListener.CLOSE);
            }
        }

    }
    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
