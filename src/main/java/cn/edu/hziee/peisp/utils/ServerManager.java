package cn.edu.hziee.peisp.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ServerManager {

    private static Collection<MyWebSocket> servers = Collections.synchronizedCollection(new ArrayList<MyWebSocket>());

    public static void broadCast(String msg){
        for (MyWebSocket bitCoinServer : servers) {
            try {
                bitCoinServer.sendMessage(msg);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static int getTotal(){
        return servers.size();
    }
    public static void add(MyWebSocket server){
        System.out.println("有新连接加入！ 当前总连接数是："+ servers.size());
        servers.add(server);
    }
    public static void remove(MyWebSocket server){
        System.out.println("有连接退出！ 当前总连接数是："+ servers.size());
        servers.remove(server);
    }

}