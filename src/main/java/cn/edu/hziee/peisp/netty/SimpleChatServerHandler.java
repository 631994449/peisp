/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.edu.hziee.peisp.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * event handler to process receiving messages
 *
 * @author Jibeom Jung akka. Manty
 */
@Component
@Slf4j
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class SimpleChatServerHandler extends ChannelInboundHandlerAdapter{
    private final ChannelRepository channelRepository;

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private double count = 0;
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Assert.notNull(this.channelRepository, "[Assertion failed] - ChannelRepository is required; it must not be null");

        ctx.fireChannelActive();
        if (log.isDebugEnabled()) {
            log.debug(ctx.channel().remoteAddress() + "");
        }
        String remoteAddress = ctx.channel().remoteAddress().toString();

        ctx.writeAndFlush("Your remote address is " + remoteAddress + ".\r\n");

        if (log.isDebugEnabled()) {
            log.debug("Bound Channel Count is {}", this.channelRepository.size());
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String stringMessage = (String) msg;
        System.out.println("msg:"+msg);
        if (stringMessage.startsWith("A")){
            //开始计算相应的位置
            System.out.println(count++);
        }else if(stringMessage.equals("a")){
            //发送对应的电机控制信号
            ctx.channel().writeAndFlush("a" );
            System.out.println("电机正转");
        }else if(stringMessage.equals("b")){
            //发送对应的电机控制信号
            ctx.channel().writeAndFlush("b" );
            System.out.println("电机反转");
        }else if(stringMessage.equals("c")){
            //发送对应的电机控制信号
            ctx.channel().writeAndFlush("c" );
            System.out.println("电机启动");
        }else if(stringMessage.equals("d")){
            //发送对应的电机控制信号
//            ctx.channel().writeAndFlush("d" );
//            System.out.println("电机停止");
        }else if(stringMessage.equals("e")){
            //发送对应的电机控制信号
            ctx.channel().writeAndFlush("e" );
            System.out.println("电机开始自动巡航");
        }else if(stringMessage.equals("f")){
            //发送对应的电机控制信号
            ctx.channel().writeAndFlush("f" );
            System.out.println("电机手动巡航");
        }else{

            return;
        }
    }
    public static void sendMessage(String msg){
        for (Channel channel:channels){
            channel.writeAndFlush(msg.toString());
        }
    }
}
