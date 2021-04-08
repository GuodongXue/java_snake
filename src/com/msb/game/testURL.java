package com.msb.game;

import java.net.URL;

public class testURL {
    public static void main(String[] args) {
        URL url = Images.class.getResource("/");
        System.out.println(url);
    }
}
