package JFrameBuilder.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class LeftSelectMenu extends JPanel {

    private Integer routerNum;

    private List<JPanel> pageList;

    private JPanel selector;

    private Color selectorColor;

    private JPanel defaultPage;


    public LeftSelectMenu(JFrame jFrame, Integer routerNum){
        if (routerNum >=8){
            routerNum = 8;
        }
        jFrame.setResizable(false);
        this.routerNum = routerNum;
        setLayout(null);
        this.setSize(jFrame.getSize());

        buildSelector(routerNum,0.2);
        buildPage();

        add(selector);

        //add FirstPage
        defaultPage = new JPanel();
        defaultPage.setLayout(new BorderLayout());
        defaultPage.setBackground(new Color(0,0,0));
        defaultPage.setBounds(selector.getWidth(),0,(int)(getWidth()*0.8),getHeight());
        add(defaultPage);

        Container contentPane = jFrame.getContentPane();
        contentPane.add(this);
    }

    protected List<JPanel> getPageList() {
        return this.pageList;
    }

    private void buildPage() {
        pageList = new ArrayList<>();
        for (int i = 0; i < routerNum; i++) {
            JPanel page = new JPanel();
            page.setLayout(null);
            page.setBounds(selector.getWidth(),0,(int)(getWidth()*0.8),getHeight());
            pageList.add(page);
        }
    }

    /**
     * build selectorJPanel
     * @param routerNum
     * @param leftPercent
     */
    private void buildSelector(Integer routerNum, double leftPercent) {
        selectorColor = new Color(237, 235, 235);
        selector = new JPanel();
        selector.setLayout(null);
        selector.setBackground(selectorColor);

        setLeftPercent(0.2);

        int width = selector.getWidth();
        int height = selector.getHeight();

        ActionListener monitor = new SelectorChangeMonitor(this);
        //build selectMenu
        for (int i = 0; i < routerNum; i++) {
            JButton button = new JButton();
            button.setBackground(selectorColor);
            button.setBounds(0,(int)(height/8)*i,width,(int)(height/8));
            button.setName(""+i);
            button.addActionListener(monitor);
            selector.add(button);
        }

        //build selectorContent by os467
        JPanel selectorContent = new JPanel();
        selectorContent.setLayout(new BorderLayout());
        selectorContent.add(new JLabel("made by os467"),BorderLayout.CENTER);
        selectorContent.setBounds(0,(height/8)*routerNum,width,getHeight()-(height/8)*routerNum);
        selectorContent.setBackground(new Color(77, 77, 77));
        selector.add(selectorContent);
    }

    /**
     * name the selectorButton
     * @param index
     * @param name
     */
    public void name(Integer index, String name){
        if (index >= routerNum){
            return;
        }
        JButton button = (JButton)selector.getComponent(index);
        button.setText(name);
    }

    /**
     * set Selector leftPercent
     * @param leftPercent
     */
    public void setLeftPercent(double leftPercent){
        selector.setSize((int)(getWidth()*leftPercent),getHeight());
    }

    /**
     * get Page
     * @param index
     * @return
     */
    public JPanel getPage(Integer index){
        if (index >= routerNum){
            return null;
        }
        return this.pageList.get(index);
    }

    /**
     * set default page
     * @param defaultPage
     */
    public void setDefaultPage(JPanel defaultPage) {
        remove(this.defaultPage);
        this.defaultPage = defaultPage;
        this.defaultPage.setBounds(selector.getWidth(),0,(int)(getWidth()*0.8),getHeight());
        add(this.defaultPage);
    }
}

class SelectorChangeMonitor implements ActionListener{

    private LeftSelectMenu leftSelectMenu;

    public SelectorChangeMonitor(LeftSelectMenu leftSelectMenu){
        this.leftSelectMenu = leftSelectMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        leftSelectMenu.remove(1);
        List<JPanel> pageList = leftSelectMenu.getPageList();
        JPanel panel = pageList.get(Integer.parseInt(btn.getName()));
        leftSelectMenu.add(panel);
        SwingUtilities.updateComponentTreeUI(leftSelectMenu);
    }
}
