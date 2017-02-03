package me.trinopoty.nettyprotobuf.client;

import com.google.protobuf.AbstractMessage;

class ProtobufClientChannelProxyImpl extends ProtobufClientChannel {

    private ProtobufClientChannel mClientChannel;
    private ProtobufClientChannelPool mClientChannelPool;

    ProtobufClientChannelProxyImpl(ProtobufClientChannel clientChannel, ProtobufClientChannelPool clientChannelPool) {
        mClientChannel = clientChannel;
        mClientChannelPool = clientChannelPool;
    }

    @Override
    public synchronized AbstractMessage sendMessageSync(AbstractMessage message) throws Exception {
        return mClientChannel.sendMessageSync(message);
    }

    @Override
    public synchronized void sync() throws InterruptedException {
        mClientChannel.sync();
    }

    @Override
    public synchronized void close() throws Exception {
        // Return to the pool
        mClientChannelPool.returnResource(this);
    }

    @Override
    public synchronized boolean getIsActive() {
        return mClientChannel.getIsActive();
    }

    void realClose() throws Exception {
        mClientChannel.close();
    }
}