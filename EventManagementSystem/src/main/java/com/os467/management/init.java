package com.os467.management;

import JFrameBuilder.WindowBuilder;

public class init {

    public static void main(String[] args) {

        new WindowBuilder().
                register(new JFrameMakerImpl())
                .getFrame();
    }

}
