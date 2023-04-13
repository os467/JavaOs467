package JFrameBuilder;

import JFrameBuilder.defaultImpl.DefaultJFrameBuilder;
import JFrameBuilder.defaultImpl.DefaultJFrameContentMaker;

import javax.swing.*;
import java.awt.*;

public class WindowBuilder {

    private JFrameBuilder jFrameBuilder;

    private JFrameContentMaker jFrameContentMaker;

    public JFrame getFrame(){
        return buildFrame();
    }

    public WindowBuilder() {
        jFrameBuilder = new DefaultJFrameBuilder();
        jFrameContentMaker = new DefaultJFrameContentMaker();
    }

    /**
     * register builder
     * @param jFrameBuilder
     * @return
     */
    public WindowBuilder register(JFrameBuilder jFrameBuilder){
        this.jFrameBuilder = jFrameBuilder;
        return this;
    }

    /**
     * register contentMaker
     * @param jFrameContentMaker
     * @return
     */
    public WindowBuilder register(JFrameContentMaker jFrameContentMaker){
        this.jFrameContentMaker = jFrameContentMaker;
        return this;
    }

    /**
     * build the frame
     * @return
     */
    private JFrame buildFrame() {
        JFrame frame = jFrameBuilder.build();
        jFrameContentMaker.make(frame);
        return frame;
    }

}
