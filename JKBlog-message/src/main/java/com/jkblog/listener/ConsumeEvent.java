package com.jkblog.listener;

import com.jkblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsumeEvent {

    @Autowired
    private MessageService msService;
    public void consumeEvent(){
        String username=null;
        Integer jinE=null;
        //msService.recive(username,jinE);

    }
}
