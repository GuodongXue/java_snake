package com.msb.game;

import javax.swing.*;
import java.awt.*;

public class StartGame {
    public static void main(String[] args) {
        //定义两个数组， 一个是蛇的x坐标， 另外一个是y坐标


        //这段都是关于jframe的设置
        JFrame jf = new JFrame();
        jf.setTitle("这是guodong写的第一个窗口游戏");
        //窗口弹出的坐标, 对应窗口的宽高
        //int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        //int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        jf.setBounds(800, 200, 800, 800);
        //设置窗口大小不可调
        jf.setResizable(false);
        //关闭窗口程序也关闭
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //创建面板
        GamePanel gp = new GamePanel();
        jf.add(gp);
        //默认情况下窗体是隐藏, 显现的方法放在最后， 最后在进行显现
        jf.setVisible(true);
    }
}
