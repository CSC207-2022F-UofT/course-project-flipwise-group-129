package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

// If you want to add a table with button compatibility, copy-paste the following:


//        TableCellRenderer tableRenderer;
//                table = new JTable(new JTableButtonModel(data, header)); beware of rows and columns, those are parametes
//                tableRenderer = table.getDefaultRenderer(JButton.class);
//        table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));
//        scrollPane = new JScrollPane(table);
//
//        add(scrollPane);
class JTableButtonRenderer implements TableCellRenderer {
    private TableCellRenderer defaultRenderer;
    public JTableButtonRenderer(TableCellRenderer renderer) {
        defaultRenderer = renderer;
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value instanceof Component)
            return (Component)value;
        return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

class JTableButtonModel extends AbstractTableModel {
    private Object[][] rows;
    private String[] columns;

    public JTableButtonModel(Object[][] new_rows, String[] new_columns) {
        rows = new_rows;
        columns = new_columns;

    }

    public String getColumnName(int column) {
        return columns[column];
    }
    public int getRowCount() {
        return rows.length;
    }
    public int getColumnCount() {
        return columns.length;
    }
    public Object getValueAt(int row, int column) {
        return rows[row][column];
    }
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
}
