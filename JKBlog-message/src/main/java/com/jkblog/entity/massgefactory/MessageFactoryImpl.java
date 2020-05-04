package com.jkblog.entity.massgefactory;


public class MessageFactoryImpl<T> implements MessageFactory<T> {
    @Override
    public Message product(Class<Message> msg) {

        try {
            return (Message) msg.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}