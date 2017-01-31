package me.trinopoty.nettyprotobuf.server;

import com.google.protobuf.AbstractMessage;
import io.netty.channel.ChannelHandlerContext;

public abstract class ProtobufMessageHandler {

    protected abstract void handleMessage(ChannelHandlerContext ctx, AbstractMessage message) throws Exception;
}