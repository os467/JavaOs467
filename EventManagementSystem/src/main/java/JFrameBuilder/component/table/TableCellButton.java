package JFrameBuilder.component.table;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TableCellButton{

    private Integer rowCount;

    private JTable jTable;

    private TableCellRenderer tableCellRenderer;

    private TableCellEditor tableCellEditor;

    private List<JButton> jButtonList;

    private List<JPanel> jPanelList;

    private ActionListener actionListener;

    private String operation;

    private Color color;

    private Integer columnIndex;

    public TableCellRenderer getTableCellRenderer() {
        return tableCellRenderer;
    }

    public TableCellEditor getTableCellEditor() {
        return tableCellEditor;
    }

    /**
     * Creating a form column button builder requires providing form information
     * and specifying the generated column index and the number of form rows
     * @param columnIndex
     * @param rowCount
     * @param jTable
     */
    public TableCellButton(int columnIndex, int rowCount, JTable jTable) {
        this.jTable = jTable;
        this.columnIndex = columnIndex;
        this.rowCount = rowCount;
        jButtonList = new ArrayList<>();
        jPanelList = new ArrayList<>();
    }

    /**
     * Provide basic information about the button and the listener,
     * and the button command is bound to the table row index by default
     * @param operation
     * @param actionListener
     * @param color
     */
    public void registerOperation(String operation,ActionListener actionListener,Color color){
        this.color = color;
        this.operation = operation;
        this.actionListener = actionListener;
        register();
    }

    public void registerOperation(String operation,ActionListener actionListener){
        this.color = new Color(232, 232, 232);
        this.operation = operation;
        this.actionListener = actionListener;
        register();
    }

    /**
     * Register button directives and bind table columns
     */
    private void register(){
        for (int i = 0; i < rowCount; i++) {
            JButton jButton = new JButton(operation);
            jButton.setActionCommand(i+"");
            jButton.setBackground(color);
            jButton.setBounds(2, 3, 80, 30);
            jButton.addActionListener(actionListener);
            JPanel jPanel = new JPanel();
            jPanel.setLayout(null);
            jPanel.add(jButton);
            jPanelList.add(jPanel);
            jButtonList.add(jButton);
        }

        tableCellEditor = new TableCellEditorButton(jButtonList);
        tableCellRenderer = new TableCellRenderButton(jButtonList);

        jTable.getColumnModel().getColumn(columnIndex).setCellRenderer(tableCellRenderer);
        jTable.getColumnModel().getColumn(columnIndex).setCellEditor(tableCellEditor);
    }




}

class TableCellRenderButton implements TableCellRenderer{

    private List<JButton> jButtonList;


    public TableCellRenderButton(List<JButton> jButtonList) {
        this.jButtonList = jButtonList;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return jButtonList.get(row);
    }
}

class TableCellEditorButton extends DefaultCellEditor {

    private List<JButton> jButtonList;

    public TableCellEditorButton(List<JButton> jButtonList) {
        super(new JTextField());
        this.jButtonList = jButtonList;
        //click one time to start
        this.setClickCountToStart(1);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return jButtonList.get(row);
    }


}