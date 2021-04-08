package com.msb.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;

public class GamePanel extends JPanel {
    //继承了jpanel才是面板
    //自动调用
    int length;
    int[] snakeX = new int[200];
    int[] snakeY = new int[200];

    //默认游戏停止
    boolean isStart = false;
    //加入一个定时器
    Timer timer;
    //定义蛇的行走方向
    String direction;
    public void init(){
        length =3;
        //初始化蛇头坐标
        snakeX[0] = 175;
        snakeY[0] = 275;
        //初始化第一个身子的坐标
        snakeX[1] = 150;
        snakeY[1] = 275;
        //初始化第二个身子的坐标
        snakeX[2] = 125;
        snakeY[2] = 275;
        //初始化方向
        direction = "R";
    }
    public GamePanel(){
        init();
        //jiao dian ding zai chuang kou
        this.setFocusable(true);
        //加入监听
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //监听键盘
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                System.out.println(keyCode);
                if(keyCode== 32){
                //   System.out.print("Clicked the Space Key");
                    isStart = !isStart;
                    repaint();
                }
                //监听向上

                //监听向下
                if(keyCode==38){
                    direction = "U";
                }
                if(keyCode==40){
                    direction = "D";
                }
                //监听向左
                if(keyCode==37){
                    direction = "L";
                }
                //监听向右
                if(keyCode==39){
                    direction = "R";
                }
            }
        });
        //对定时器初始化
        timer = new Timer(300, new ActionListener() {
            //事件监听相当于每一百毫秒监听一次
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStart){
                    //游戏开始状态蛇才动， 后一节到前一节
                    for(int i = length-1; i>0; i--){
                        snakeX[i] = snakeX[i-1];
                        snakeY[i] = snakeY[i-1];
                    }
                    if("R".equals(direction)){
                        snakeX[0]+=25;
                    }
                    if("L".equals(direction)){
                        snakeX[0]=-25;
                    }
                    if("U".equals(direction)){
                        snakeY[0]-=25;
                    }
                    if("D".equals(direction)){
                        snakeY[0]+=25;
                    }
                    //防止蛇超出
                    if(snakeX[0]>765){
                        snakeX[0] = 10;
                    }
                    if(snakeX[0]<10){
                        snakeX[0] = 765;
                    }
                    if(snakeY[0]<150){
                        snakeY[0] = 725;
                    }
                    if(snakeY[0]>725){
                        snakeY[0] = 150;
                    }
                    repaint();//重绘

                }
            }
        });
        //定时器要启动
        timer.start();
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //填充背景颜色
        this.setBackground(new Color(0, 0, 0));

        //画头部的图片

        Images.headerImg.paintIcon(this, g, 150, 20);


        //画个矩形
        g.fillRect(10, 140, 765, 610);

        //画小蛇
        if("R".equals(direction)){
           Images.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if("L".equals(direction)){
            Images.leftImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if("U".equals(direction)){
            Images.upImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if("D".equals(direction)){
            Images.downImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        //头
        Images.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);

        //Images.bodyImg.paintIcon(this, g, snakeX[1], snakeY[1]);
        //Images.bodyImg.paintIcon(this, g, snakeX[2], snakeY[2]);

        //优化画身子的代码
        for(int i=1; i<length; i++){
            Images.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        //如果暂停， 就有提示语
        if(isStart == false){
            g.setColor(new Color(127, 127, 133));
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("点击空格开始游戏",255, 330);
        }
    }

}
