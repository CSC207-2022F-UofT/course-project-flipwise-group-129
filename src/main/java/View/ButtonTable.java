package View;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

class ButtonTable implements TableCellRenderer, TableCellEditor
{
    private JButton purchase;
    private int row;
    ButtonTable(JTable table) {
        purchase = new JButton("Purchase");
        purchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(row);
            }
        });
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object
            element, boolean selected, boolean hasFocus, int row, int column)
    {
        return purchase;
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object
            element, boolean selected, int row, int column)
    {
        this.row = row;
        return purchase;
    }
    @Override
    public Object getCellEditorValue() { return true; }
    @Override
    public boolean isCellEditable(EventObject e) { return true; }
    @Override
    public boolean shouldSelectCell(EventObject e) { return true; }
    @Override
    public boolean stopCellEditing() { return true; }
    @Override
    public void cancelCellEditing() {}
    @Override
    public void addCellEditorListener(CellEditorListener l) {}
    @Override
    public void removeCellEditorListener(CellEditorListener l) {}

    public JButton getPurchase() {
        return purchase;
    }
}
