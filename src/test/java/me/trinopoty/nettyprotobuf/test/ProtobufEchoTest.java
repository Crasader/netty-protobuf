package me.trinopoty.nettyprotobuf.test;

import com.google.protobuf.AbstractMessage;
import io.netty.channel.*;
import me.trinopoty.nettyprotobuf.client.ProtobufClient;
import me.trinopoty.nettyprotobuf.client.ProtobufClientChannel;
import me.trinopoty.nettyprotobuf.client.ProtobufClientChannelPool;
import me.trinopoty.nettyprotobuf.client.ProtobufClientChannelPoolConfig;
import me.trinopoty.nettyprotobuf.server.ProtobufMessageHandler;
import me.trinopoty.nettyprotobuf.server.ProtobufServer;
import me.trinopoty.nettyprotobuf.server.ProtobufServerExceptionHandler;
import me.trinopoty.nettyprotobuf.server.ProtobufServerMessageRegistry;
import me.trinopoty.nettyprotobuf.test.message.EchoMessageOuterClass;
import org.junit.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import static org.junit.Assert.*;

public class ProtobufEchoTest {

    private static final class ProtobufTestRegistry extends ProtobufServerMessageRegistry {

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

        @Override
        public ProtobufServerExceptionHandler getExceptionHandler() {
            return null;
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

    private static ProtobufTestRegistry sProtobufTestRegistry;
    private static ProtobufServer sProtobufServer;

    @BeforeClass
    public static void setUp() throws Exception {
        sProtobufTestRegistry = new ProtobufTestRegistry();

        sProtobufServer = (new ProtobufServer.ProtobufServerBuilder()).setProtobufMessageRegistry(sProtobufTestRegistry).setLocalPort(8945).build();
        sProtobufServer.startServer();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        sProtobufServer.stopServer();
    }

    @Test
    public void testEcho1() throws Exception {
        ProtobufClient protobufClient = (new ProtobufClient.ProtobufClientBuilder()).setProtobufMessageRegistry(sProtobufTestRegistry).build();
        ProtobufClientChannel c = protobufClient.getClientChannel(new InetSocketAddress(InetAddress.getLoopbackAddress(), 8945));
        AbstractMessage resposeMessage = c.sendMessageSync(EchoMessageOuterClass.EchoMessage.newBuilder().setMsg("Hello World").build());
        c.close();
        protobufClient.close();

        assertNotNull(resposeMessage);
        assertTrue(resposeMessage instanceof EchoMessageOuterClass.EchoMessage);
        assertEquals("Hello World", ((EchoMessageOuterClass.EchoMessage) resposeMessage).getMsg());
    }

    @Test
    public void techEcho2() throws Exception {
        ProtobufClientChannelPoolConfig poolConfig = new ProtobufClientChannelPoolConfig();
        poolConfig.setMaxIdle(5);
        poolConfig.setMaxTotal(5);
        poolConfig.setMinIdle(1);
        poolConfig.setMaxWaitMillis(3000);

        ProtobufClient protobufClient = (new ProtobufClient.ProtobufClientBuilder()).setProtobufMessageRegistry(sProtobufTestRegistry).build();
        ProtobufClientChannelPool channelPool = protobufClient.getClientChannelPool(poolConfig, new InetSocketAddress(InetAddress.getLoopbackAddress(), 8945));
        ProtobufClientChannel c1 = channelPool.getResource();
        ProtobufClientChannel c2 = channelPool.getResource();
        ProtobufClientChannel c3 = channelPool.getResource();

        assertNotEquals(System.identityHashCode(c1), System.identityHashCode(c2));
        assertNotEquals(System.identityHashCode(c2), System.identityHashCode(c3));
        assertNotEquals(System.identityHashCode(c1), System.identityHashCode(c3));

        AbstractMessage resposeMessage1 = c1.sendMessageSync(EchoMessageOuterClass.EchoMessage.newBuilder().setMsg("Hello World 1").build());
        AbstractMessage resposeMessage2 = c2.sendMessageSync(EchoMessageOuterClass.EchoMessage.newBuilder().setMsg("Hello World 2").build());
        AbstractMessage resposeMessage3 = c3.sendMessageSync(EchoMessageOuterClass.EchoMessage.newBuilder().setMsg("Hello World 3").build());

        c1.close();
        c2.close();
        c3.close();
        channelPool.close();

        assertNotNull(resposeMessage1);
        assertTrue(resposeMessage1 instanceof EchoMessageOuterClass.EchoMessage);
        assertEquals("Hello World 1", ((EchoMessageOuterClass.EchoMessage) resposeMessage1).getMsg());
        assertNotNull(resposeMessage2);
        assertTrue(resposeMessage2 instanceof EchoMessageOuterClass.EchoMessage);
        assertEquals("Hello World 2", ((EchoMessageOuterClass.EchoMessage) resposeMessage2).getMsg());
        assertNotNull(resposeMessage3);
        assertTrue(resposeMessage3 instanceof EchoMessageOuterClass.EchoMessage);
        assertEquals("Hello World 3", ((EchoMessageOuterClass.EchoMessage) resposeMessage3).getMsg());
    }
}