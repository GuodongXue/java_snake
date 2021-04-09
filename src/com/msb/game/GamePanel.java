package com.msb.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.Random;

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
    //定义食物的变量
    int foodX;
    int foodY;
    int score;

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

        foodX = 300;
        foodY = 200;

    }


    public GamePanel(){
        init();
        //把焦点定在窗口
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
                if(keyCode==38){
                    direction = "U";
                }
                //监听向下
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
        timer = new Timer(100, new ActionListener() {
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
                        snakeX[0]-=25;
                    }
                    if("U".equals(direction)){
                        snakeY[0]-=25;
                    }
                    if("D".equals(direction)){
                        snakeY[0]+=25;
                    }
                    //防止蛇超出
                    if(snakeX[0]>725){
                        snakeX[0] = 25;
                    }
                    if(snakeX[0]<25){
                        snakeX[0] = 725;
                    }
                    if(snakeY[0]<150){
                        snakeY[0] = 725;
                    }
                    if(snakeY[0]>725){
                        snakeY[0] = 150;
                    }
                    //吃到了的动作
                    if(snakeX[0] == foodX && snakeY[0] == foodY){
                        //代表他们碰到了一起
                        length++;
                        //食物坐标要发生改变, 随机生成一个坐标

                        // 先生成一个随机数

                        foodX = ((int)(Math.random()*28)+1)*25;

                        //[4, 29]*25
                        //new Random().nextInt()左边包含0， 右边不包含26
                        foodY = ((int)(Math.random()*23)+6)*25;
                        //吃上食物积分加10
                        score+=10;
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
        g.fillRect(25, 150, 725, 600);

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
        //Images.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
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
        //画食物
        Images.foodImg.paintIcon(this, g, foodX, foodY);

        //画积分
        g.setColor(new Color(170, 143, 0));
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        g.drawString("Score: "+ score,680, 120);

    }

}
