package com;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientFrame{
    private JLabel imageLab;
    private JFrame jFrame = new JFrame();
    private ImageIcon icon;
    private ObjectOutputStream ous ;

    ClientFrame(ObjectOutputStream os) {
        imageLab = new JLabel();
        jFrame.add(imageLab);
        jFrame.setSize(1500, 900);
        jFrame.setLocation(100, 100);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        //这里是不需要启线程去监听的，直接传过来反序列化流就可以，
        // awt里的监听会自动监听到控制机，然后将监听事件
        // 序列化输入进去 在被控制那边写循环等待事件即可
        // 问题一直卡在这里了，逻辑没有搞清楚
        this.ous = os;
        addLis();

    }
    public void setOus(ObjectOutputStream ous1){
        this.ous=ous1;
    }
    public void addLis(){
        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                sendEventObject(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                sendEventObject(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                sendEventObject(e);
            }
        });
        jFrame.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                sendEventObject(e);
            }
        });
        jFrame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                sendEventObject(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                sendEventObject(e);
            }
        });
        jFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sendEventObject(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                sendEventObject(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                sendEventObject(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sendEventObject(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sendEventObject(e);
            }
        });
    }


    private void sendEventObject(InputEvent e)  {
        try {
            ous.writeObject(e);
            ous.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void setIcon(ImageIcon icon){
        this.icon=icon;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                imageLab.setIcon(icon);
                imageLab.repaint();
            }
        });
        t.start();
    }

}
