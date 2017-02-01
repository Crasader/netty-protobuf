package me.trinopoty.nettyprotobuf.server;

import com.google.protobuf.AbstractMessage;
import me.trinopoty.nettyprotobuf.client.ProtobufClientMessageRegistry;

public abstract class ProtobufServerMessageRegistry extends ProtobufClientMessageRegistry {

    public abstract ProtobufMessageHandler getMessageHandlerFromClass(Class<? extends AbstractMessage> messageClass);

    public final ProtobufMessageHandler getMessageHandlerFromIdentifier(int identifier) {
        return getMessageHandlerFromClass(getMessageClassFromIdentifier(identifier));
    }
}