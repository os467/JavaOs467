package JFrameBuilder;

import javax.swing.*;

public interface JFrameContentMaker {

    /**
     *         jFrame.getContentPane();
     *
     *         leftSelectMenu = new LeftSelectMenu(jFrame, 3);
     *
     *         leftSelectMenu.setDefaultPage(getDefaultPage());
     *
     *         leftSelectMenu.name(0,"page0");
     *         leftSelectMenu.name(1,"page1");
     *         leftSelectMenu.name(2,"page2");
     *
     *         page1 = leftSelectMenu.getPage(1);
     *         page2 = leftSelectMenu.getPage(2);
     *
     * @param jFrame
     */
    void make(JFrame jFrame);

}
