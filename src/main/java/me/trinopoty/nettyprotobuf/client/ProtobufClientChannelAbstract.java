package me.trinopoty.nettyprotobuf.client;

import com.google.protobuf.AbstractMessage;

abstract class ProtobufClientChannelAbstract implements AutoCloseable {

    public abstract void sync() throws InterruptedException;
    public abstract AbstractMessage sendMessageSync(AbstractMessage pMessage) throws Exception;
    public abstract boolean getIsActive();
}