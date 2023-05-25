package JFrameBuilder;

import JFrameBuilder.defaultImpl.DefaultJFrameBuilder;
import JFrameBuilder.defaultImpl.DefaultJFrameContentMaker;
import JFrameBuilder.defaultImpl.DefaultJFrameListener;

import javax.swing.*;

/**
 * build JFrame Window with this builder, you perhaps need to define a jFrameContextMaker
 */
public class WindowBuilder {

    private JFrameBuilder jFrameBuilder;

    private JFrameContentMaker jFrameContentMaker;

    private JFrameListener jFrameListener;

    private JFrame jFrame;

    public JFrame getFrame(){
        if (jFrame == null){
            jFrame = buildFrame();
        }
        return jFrame;
    }

    public WindowBuilder() {
        jFrameBuilder = new DefaultJFrameBuilder();
        jFrameContentMaker = new DefaultJFrameContentMaker();
        jFrameListener = new DefaultJFrameListener();
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
        this.jFrame = jFrameBuilder.build();
        jFrameContentMaker.make(jFrame);
        return jFrame;
    }



}
