package com.os467.project.init;

import JFrameBuilder.WindowBuilder;
import JFrameBuilder.defaultImpl.DefaultJFrameBuilder;
import com.os467.project.jFrameBuild.JFrameContentMakerImpl;

public class Init {

    public static void main(String[] args) {

        new WindowBuilder().
                register(new DefaultJFrameBuilder())
                .register(new JFrameContentMakerImpl())
                .getFrame();
    }

}
