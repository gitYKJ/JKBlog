package com.jkblog.server.websocketchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TextWebSocketFrame Handler
 * 学习
 * @since
 * @author
 */
@ChannelHandler.Sharable
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	private static Map<String,Channel> map= new ConcurrentHashMap();
	/*public static void addUserChannel(String id, SocketChannel user_channel){
		map.put(id,user_channel);
	}*/
	public static Map<String,Channel> getUserChannels(){
		return map;
	}
	int a=0;
	Map<Integer,Channel> clients= new HashMap<Integer,Channel>();
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception { // (1)
		Channel incoming = ctx.channel();
        String message=msg.toString();
        String username="admin";
        map.put(username,incoming);
        System.out.println(incoming);
		for (Channel channel : channels) {
		//从msg中获取用户、标签、etynId
			//System.out.println("channel"+channel);
			if (channel != incoming) {
				//channel.writeAndFlush(new TextWebSocketFrame("[aa" + incoming.remoteAddress() + "bb]" + msg.text()));
				channel.writeAndFlush(new TextWebSocketFrame("[" + incoming.remoteAddress() + "]" + msg.text().toString()));
                List<String> users=RelUserChannels.selectLableUser(username);
                for (String labUser:users ) {
                    Channel labChannel=map.get(labUser);
                    System.out.println("--------");
                    System.out.println(labChannel);
                    System.out.println("--------");
                    if(labChannel!=null){
                        labChannel.writeAndFlush(new TextWebSocketFrame("您关注的用户发表了新的文章"));
                        System.out.println("success");
                    }
                }
			} else {

				//从数据库获取用户

				//List<User> usersLableUserChannels.selectRelUser(lable);
				/*for (User relUser:users ) {
					Channel relChannel=map.get(relUser);
					if(channel!=null){
						channel.writeAndFlush("您喜欢的标签有了新的文章");
					}
				}*/
				channel.writeAndFlush(new TextWebSocketFrame("[you]" + msg.text().toString()));
			}

			//ctx.fireChannelRead(msg);
		}
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
		Channel incoming = ctx.channel();
		// 广播
		channels.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 加入"));
		channels.add(incoming);
		System.out.println("Client:" + incoming.remoteAddress() + "加入");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
		Channel incoming = ctx.channel();
		// 广播
		channels.writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 离开"));
		System.out.println("Client:" + incoming.remoteAddress() + "离开");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
		String uuid = ctx.channel().id().asLongText();
		//System.out.println("Client:" + incoming.remoteAddress() + "在线");
	}
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
		Channel incoming = ctx.channel();
		System.out.println("Client:" + incoming.remoteAddress() + "掉线");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) // (7)
			throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("Client:" + incoming.remoteAddress() + "异常");
		// 当出现异常就关闭连接
		cause.printStackTrace();
		ctx.close();
	}
}
