package com.jkblog.listener;

public class ConsumeListener {
    private ConsumeEvent consumeEvent;

    public ConsumeListener(ConsumeEvent consumeEvent){
        this.consumeEvent=consumeEvent;
    }
    public boolean listener(){
        consumeEvent.consumeEvent();
        return true;
    }
}




