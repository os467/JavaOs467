package JFrameBuilder;

import javax.swing.*;
import java.awt.*;

public class JFrameUtils {

    private JFrameUtils(){

    }

    public static void batchAddCenterJLabel(Container contentPane,JLabel... labels){
        for (JLabel label : labels) {
            label.setHorizontalAlignment(SwingUtilities.CENTER);
        }
        batchAdd(contentPane,labels);
    }

    public static void batchAdd(Container contentPane,Component... components){
        for (Component component : components) {
            contentPane.add(component);
        }
    }
}
