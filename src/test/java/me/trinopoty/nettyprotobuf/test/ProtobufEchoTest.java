package me.trinopoty.nettyprotobuf.test;

import com.google.protobuf.AbstractMessage;
import io.netty.channel.*;
import me.trinopoty.nettyprotobuf.ProtobufMessageRegistry;
import me.trinopoty.nettyprotobuf.client.ProtobufClient;
import me.trinopoty.nettyprotobuf.client.ProtobufClientChannel;
import me.trinopoty.nettyprotobuf.server.ProtobufMessageHandler;
import me.trinopoty.nettyprotobuf.server.ProtobufServer;
import me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import static org.junit.Assert.*;

public class ProtobufEchoTest {

    private static final class ProtobufTestRegistry extends ProtobufMessageRegistry {

        @Override
        public Integer getMessageIdentifierFromClass(Class<? extends AbstractMessage> messageClass) {
            return (messageClass.equals(EchoMessageOuterClass.EchoMessage.class))? 1 : null;
        }

        @Override
        public Class<? extends AbstractMessage> getMessageClassFromIdentifier(int identifier) {
            return (identifier == 1)? EchoMessageOuterClass.EchoMessage.class : null;
        }

        @Override
        public ProtobufMessageHandler getMessageHandlerFromClass(Class<? extends AbstractMessage> messageClass) {
            return (messageClass.equals(EchoMessageOuterClass.EchoMessage.class))? new EchoMessageHandler() : null;
        }
    }

    private static final class EchoMessageHandler extends ProtobufMessageHandler {

        @Override
        protected void handleMessage(ChannelHandlerContext ctx, AbstractMessage message) throws Exception {
            EchoMessageOuterClass.EchoMessage echoMessage = (EchoMessageOuterClass.EchoMessage) message;
            ctx.write(echoMessage);
            ctx.flush();
        }
    }

    private ProtobufTestRegistry mProtobufTestRegistry;
    private ProtobufServer mProtobufServer;

    @Before
    public void setUp() throws Exception {
        mProtobufTestRegistry = new ProtobufTestRegistry();

        mProtobufServer = (new ProtobufServer.ProtobufServerBuilder()).setProtobufMessageRegistry(mProtobufTestRegistry).setLocalPort(8945).build();
        mProtobufServer.startServer();
    }

    @After
    public void tearDown() throws Exception {
        mProtobufServer.stopServer();
    }

    @Test
    public void testEcho1() throws Exception {
        ProtobufClient protobufClient = (new ProtobufClient.ProtobufClientBuilder()).setProtobufMessageRegistry(mProtobufTestRegistry).build();
        ProtobufClientChannel c = protobufClient.getClientChannel(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8945));
        AbstractMessage resposeMessage = c.sendMessageSync(EchoMessageOuterClass.EchoMessage.newBuilder().setMsg("Hello World").build());
        c.close();
        protobufClient.close();

        assertNotNull(resposeMessage);
        assertTrue(resposeMessage instanceof EchoMessageOuterClass.EchoMessage);
        assertEquals("Hello World", ((EchoMessageOuterClass.EchoMessage) resposeMessage).getMsg());
    }
}