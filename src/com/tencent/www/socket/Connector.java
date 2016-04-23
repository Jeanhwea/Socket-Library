package com.tencent.www.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Connector extends Thread {
    private Socket socket;
    private boolean running;

    public Connector(Socket socket)
    {
        this.socket = socket;
        running = true;
    }

    @Override
    public void run()
    {
        while (running) {
            if (socket.isClosed()) {
                running = false;
                break;
            }

            try {
                onReceive(socket.getInputStream(), socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            running = false;
        }
    }
    
    private void onReceive(InputStream in, OutputStream out) throws IOException
    {
        BufferedReader reader;
        PrintWriter writer;
        reader = new BufferedReader(new InputStreamReader(in));
        writer = new PrintWriter(new OutputStreamWriter(out), true);
        String msg = reader.readLine();
        if (null != msg) {
            msg = "S: " + msg;
            System.out.println(msg);
            writer.println(msg);
        }
    }

    public void stopRunning()
    {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        running = false;
    }
}