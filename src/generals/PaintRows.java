/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generals;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Programador 1
 */
public class PaintRows extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        int numero = (Integer) table.getValueAt(row, column);

        switch (numero) {
            case 0:
                setBackground(Color.RED);
                break;
            case 1:
                setBackground(Color.green);
                break;
            default:
                setBackground(Color.BLACK);
                break;
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
