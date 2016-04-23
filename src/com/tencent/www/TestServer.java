package com.tencent.www;

import com.tencent.www.socket.Listener;
import com.tencent.www.socket.SocketServer;

public class TestServer {

    public static void main(String [] args) 
    {
        SocketServer server = new SocketServer(5556);
        Listener lis = new Listener(server);
        lis.start();
    }

}
