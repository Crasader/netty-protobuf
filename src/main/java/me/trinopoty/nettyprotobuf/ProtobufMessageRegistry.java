package me.trinopoty.nettyprotobuf;

import com.google.protobuf.AbstractMessage;
import me.trinopoty.nettyprotobuf.server.ProtobufMessageHandler;

public abstract class ProtobufMessageRegistry {

    public abstract Integer getMessageIdentifierFromClass(Class<? extends AbstractMessage> messageClass);

    public abstract Class<? extends AbstractMessage> getMessageClassFromIdentifier(int identifier);

    public abstract ProtobufMessageHandler getMessageHandlerFromClass(Class<? extends AbstractMessage> messageClass);

    public final ProtobufMessageHandler getMessageHandlerFromIdentifier(int identifier) {
        return getMessageHandlerFromClass(getMessageClassFromIdentifier(identifier));
    }
}