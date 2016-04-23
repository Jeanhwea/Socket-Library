package com.tencent.www;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.tencent.www.socket.SocketClient;

public class TestClient {
 
    public static void main(String[] args) 
    {
        
        SocketClient client = null;
        try {
            client = new SocketClient(InetAddress.getLocalHost(), 5556);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; ++i) {
            sb.append(args[i]+";");
        }
        client.sendStr(sb.toString());
        System.out.println(client.recvStr());
    }
}