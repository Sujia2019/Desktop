package com;

import java.net.*;

public class ServerManager {

    private ServerSocket ss;
    private Socket s;


    public ServerManager(){
        try {
            ss = new ServerSocket(9999);

            s=ss.accept();
            System.out.println("开始监听");
            new Thread(new PicSend(s)).start();
            new Thread(new RobotEvent(s)).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ServerManager serverManager = new ServerManager();


    }
}
