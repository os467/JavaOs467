package JFrameBuilder.defaultImpl;

import JFrameBuilder.JFrameBuilder;

import javax.swing.*;
import java.awt.*;

public class DefaultJFrameBuilder implements JFrameBuilder {

    protected JFrame jFrame;

    @Override
    public JFrame build() {
        jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setResizable(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        jFrame.setBounds((int)(width*0.2),(int)(height*0.2),(int)(width*0.618),(int)(height*0.618));
        jFrame.setBackground(new Color(0,0,0));
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return jFrame;
    }
}
