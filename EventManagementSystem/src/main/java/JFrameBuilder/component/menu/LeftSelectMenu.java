package JFrameBuilder.component.menu;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class LeftSelectMenu extends JPanel implements Menu {

    private Integer routerNum;

    private List<JPanel> pageList;

    private JPanel selector;

    private Color selectorColor;

    private JPanel defaultPage;


    public LeftSelectMenu(JFrame jFrame, Integer routerNum){
        if (routerNum >=8){
            routerNum = 8;
        }
        this.routerNum = routerNum;
        setLayout(new BorderLayout());
        this.setSize(jFrame.getSize());

        buildSelector(routerNum);
        buildPage();

        add(selector,BorderLayout.WEST);

        //add FirstPage
        JLabel jLabel = new JLabel("Loading.......");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        defaultPage = new JPanel();
        defaultPage.setBackground(new Color(233, 233, 233));
        defaultPage.setLayout(new BorderLayout());
        defaultPage.add(jLabel,BorderLayout.CENTER);
        add(defaultPage,BorderLayout.CENTER);

        jFrame.add(this);
    }

    protected List<JPanel> getPageList() {
        return this.pageList;
    }

    private void buildPage() {
        pageList = new ArrayList<>();
        for (int i = 0; i < routerNum; i++) {
            JPanel JPanel = new JPanel();
            JPanel.setBounds(selector.getWidth(),0,(int)(getWidth()*0.8),getHeight());
            pageList.add(JPanel);
        }
    }

    /**
     * build selectorJPanel
     * @param routerNum
     */
    private void buildSelector(Integer routerNum) {
        selectorColor = new Color(237, 235, 235);
        selector = new JPanel();
        selector.setLayout(new GridLayout(routerNum+1,1));
        selector.setBackground(selectorColor);

        ActionListener monitor = new SelectorChangeMonitor(this);
        //build selectMenu
        for (int i = 0; i < routerNum; i++) {
            JButton button = new JButton();
            button.setBackground(selectorColor);
            button.setName(""+i);
            button.addActionListener(monitor);
            selector.add(button);
        }

        //build selectorContent by os467
        JPanel selectorContent = new JPanel();
        selectorContent.setLayout(new BorderLayout());
        selectorContent.add(new JLabel("made by os467"),BorderLayout.CENTER);
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
     * get JPanel
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
     * set default JPanel
     * @param defaultPage
     */
    public void setDefaultPage(JPanel defaultPage) {
        remove(1);
        this.defaultPage = defaultPage;
        add(defaultPage,BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
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
        List<JPanel> pageList = leftSelectMenu.getPageList();
        JPanel panel = pageList.get(Integer.parseInt(btn.getName()));
        Component component = leftSelectMenu.getComponent(1);
        if (component == panel){
            return;
        }
        leftSelectMenu.remove(1);
        leftSelectMenu.add(panel,BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(leftSelectMenu);
    }
}
