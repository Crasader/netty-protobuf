package me.trinopoty.nettyprotobuf.client;

import com.google.protobuf.AbstractMessage;

public abstract class ProtobufClientMessageRegistry {

    public abstract Integer getMessageIdentifierFromClass(Class<? extends AbstractMessage> messageClass);

    public abstract Class<? extends AbstractMessage> getMessageClassFromIdentifier(int identifier);
}