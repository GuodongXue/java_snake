package com.msb.game;

import javax.swing.*;
import java.net.URL;

public class Images {
    public static URL bodyURL = Images.class.getResource("/images/body.png");
    //将图片封装成一个对象
    public static ImageIcon bodyImg = new ImageIcon(bodyURL);

    public static URL downURL = Images.class.getResource("/images/down.png");
    //将图片封装成一个对象
    public static ImageIcon downImg = new ImageIcon(downURL);

    public static URL upURL = Images.class.getResource("/images/up.png");
    //将图片封装成一个对象
    public static ImageIcon upImg = new ImageIcon(upURL);

    public static URL leftURL = Images.class.getResource("/images/left.png");
    //将图片封装成一个对象
    public static ImageIcon leftImg = new ImageIcon(leftURL);

    public static URL rightURL = Images.class.getResource("/images/right.png");
    //将图片封装成一个对象
    public static ImageIcon rightImg = new ImageIcon(rightURL);

    public static URL headerURL = Images.class.getResource("/images/header.png");
    //将图片封装成一个对象
    public static ImageIcon headerImg = new ImageIcon(headerURL);
}
