package com.tencent.www.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 封装Java的 ServerSocket 类的相关操作
 * @author hujh
 *
 */
public class SocketServer {
    private ServerSocket serverSocket;

    public SocketServer(int port)
    {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close()
    {
        try {
            if (null!=serverSocket && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket()
    {
        return serverSocket;
    }


    public void setServerSocket(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }
    
    public boolean isClosed()
    {
        return serverSocket.isClosed();
    }
    
    public Socket accept()
    {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}