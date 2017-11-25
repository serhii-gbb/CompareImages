package com.task.image_comparison;

import javax.swing.*;
import java.awt.*;

public class PrintResultImg extends JFrame{

    public PrintResultImg(WriteImage image1, int width, int height) throws HeadlessException {
        getContentPane().add(image1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);
    }
}
