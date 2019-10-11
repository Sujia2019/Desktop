package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class PicReci implements Runnable{
    private Socket socket;
    private ObjectInputStream ois;
    private BufferedImage image;
    private ClientFrame frame;
    PicReci(Socket s,ClientFrame frame1){
        socket=s;
        frame = frame1;
    }

    @Override
    public void run() {
        try {

            while (!socket.isClosed()){
                ois = new ObjectInputStream(socket.getInputStream());
                Message g = (Message)ois.readObject();
                FileOutputStream FOS = new FileOutputStream("/home/sj/Desktop/pic/"+g.getFileName());
                FOS.write(g.getFileContent(),0,(int)g.getFileLength());
                FOS.flush();
                FileInputStream FIS= new FileInputStream("/home/sj/Desktop/pic/"+g.getFileName());
                image= ImageIO.read(FIS);

                if (image!=null){
                    ImageIcon ic = new ImageIcon(image);
                    frame.setIcon(ic);
                }
            }
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
