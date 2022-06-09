package com.my_website.test;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyWindow extends Frame {

    public MyWindow(String title){
        super(title);
        setSize(500, 140);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0) ;
            }
        } )  ;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Font sansSerifLarge = new Font("SansSerif", Font.BOLD, 18) ;
        Font sansSerifSmall = new Font("SansSerif", Font.BOLD, 14) ;

       g.setFont(sansSerifLarge);
       g.drawString("My First Java Window Project!", 60, 70);
       g.setFont(sansSerifSmall);
       g.drawString("By Aashraya Krishnani", 60, 100);
    }

}
