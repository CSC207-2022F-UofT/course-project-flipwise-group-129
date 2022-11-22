package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;



public class ButtonColumn extends AbstractCellEditor implements ActionListener, TableCellRenderer, TableCellEditor {
    JTable table;
    JButton r;
    JButton e;
    String text;

    public ButtonColumn(JTable table, int column)
    {
        this.table = table;
        r = new JButton();

        e = new JButton();
        e.setFocusPainted( false );
        e.addActionListener( this );

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
    }

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (hasFocus)
        {
            r.setForeground(table.getForeground());
        }
        else if (isSelected)
        {
            r.setForeground(table.getSelectionForeground());
            r.setBackground(table.getSelectionBackground());
        }
        else
        {
            r.setForeground(table.getForeground());
        }

        r.setText( (value == null) ? "" : value.toString() );
        return r;
    }

    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
    {
        text = (value == null) ? "" : value.toString();
        e.setText( text );
        return e;
    }

    public Object getCellEditorValue()
    {
        return text;
    }

    public void actionPerformed(ActionEvent e)
    {
        fireEditingStopped();

    }
}

