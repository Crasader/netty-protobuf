package me.trinopoty.nettyprotobuf.client;

import com.google.protobuf.AbstractMessage;

public abstract class ProtobufClientChannel implements AutoCloseable {

    ProtobufClientChannel() {
    }

    public abstract void sync() throws InterruptedException;
    public abstract AbstractMessage sendMessageSync(AbstractMessage pMessage) throws Exception;
    public abstract boolean getIsActive();
}