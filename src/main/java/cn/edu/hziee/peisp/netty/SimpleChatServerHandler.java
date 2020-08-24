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

import cn.edu.hziee.peisp.contoller.LiveController;
import cn.edu.hziee.peisp.entity.Position;
import cn.edu.hziee.peisp.service.WebSocketServer;
import cn.edu.hziee.peisp.utils.PositionCounter;
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

import java.io.IOException;

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
        if (stringMessage.startsWith("_")){
            //删去首个"_" 收到数据格式为[_距离_距离_距离]
            String [] str = stringMessage.split("_");
            double [] distance = new double[str.length-1];
            //转换为double数组
            for (int i = 1; i < str.length; i++) {
                System.out.println(str[i]+"1");
                distance[i-1] = Double.valueOf(str[i]);
            }
            Position ans = PositionCounter.count(distance[0],distance[1],distance[2]);
            if (ans==null||ans.equals(null)){
                //无法计算出正确位置
                return;
            }
            System.out.println(ans.toString());
            try {
                WebSocketServer.BroadCastInfo(ans.getX()+"&"+ans.getY());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //传回给前端
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
