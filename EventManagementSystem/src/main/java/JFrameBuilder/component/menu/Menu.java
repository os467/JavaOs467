package JFrameBuilder.component.menu;


import javax.swing.*;

public interface Menu {
    void name(Integer index, String name);

    JPanel getPage(Integer index);

    void setDefaultPage(JPanel defaultPage);
}
