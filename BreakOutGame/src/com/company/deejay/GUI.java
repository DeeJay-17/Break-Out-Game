package com.company.deejay;
import java.util.*;
import javax.swing.*;
import javax.sound.*;

public class GUI {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay gameobj = new Gameplay();
        obj.setBounds(10, 10, 709, 600);
        obj.setTitle("BreakOut");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameobj);
    }

    public static void music() {
        AudioP
    }
}
