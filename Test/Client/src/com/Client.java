package com;

import java.awt.image.BufferedImage;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class Client {

    private volatile ClientFrame frame;
    private String host;
    private int port;
    private static BufferedInputStream in;
    private static BufferedImage image;
    private static Socket socket;
    private static ObjectOutputStream ous;
    private static ObjectInputStream ois;
    Client(){
        readpro();


        try {
            socket = new Socket(host,port);
            ous = new ObjectOutputStream(socket.getOutputStream());
            frame = new ClientFrame(ous);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new PicReci(socket,frame)).start();
//        new Thread(new RobotSend(socket,frame)).start();
    }

    private void readpro() {
        try{
            Properties p = new Properties();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Client/pro/config.properties"));
            p.load(bufferedReader);
            host=p.getProperty("host");
            String x=p.getProperty("port");
            port = Integer.valueOf(x);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
