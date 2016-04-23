package com.tencent.www.socket;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Listener extends Thread {
    private SocketServer socketServer;
    private List<Connector> connectors;
    private boolean running;

    public Listener(SocketServer socketServer)
    {
        this.socketServer = socketServer;
        this.connectors = new ArrayList<Connector>();
        running = true;
    }

    @Override
    public void run()
    {
        while (running) {
            if (socketServer.isClosed()) {
                running = false;
                break;
            }

            Socket socket;
            socket = socketServer.accept();
            Connector con = new Connector(socket);
            connectors.add(con);
            con.start();
        }
    }

    public void stopRunning()
    {
        for (Connector con : connectors) {
            con.stopRunning();
            try {
                con.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        running = false;
    }
}