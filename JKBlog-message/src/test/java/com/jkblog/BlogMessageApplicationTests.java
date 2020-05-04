package com.jkblog;

import com.jkblog.entity.massgefactory.GzMessage;
import com.jkblog.entity.massgefactory.MessageFactoryImpl;
import com.jkblog.server.webSocketServer.MessageHandler;
import com.jkblog.server.websocketchat.TextWebSocketFrameHandler;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Map;

@RunWith(JUnit4.class)
class BlogMessageApplicationTests extends TextWebSocketFrameHandler{
    MessageFactoryImpl mf = new MessageFactoryImpl();
    @Test
    void contextLoads() {
       GzMessage msg= (GzMessage) mf.product(GzMessage.class);
        System.out.println(msg);
    }
    @Test
    void statusQueue(){
        MessageHandler mh=new MessageHandler();
        Map map= TextWebSocketFrameHandler.getUserChannels();
        System.out.println(map);
        mh.newMsg();
    }
}
