package com.bingo.gui.components;

import com.bingo.BingoConstants;
import com.bingo.core.BingoBall;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Represents table of numbers available in bingo.
 */
public class BingoTable extends JTable {

    private static final long serialVersionUID = -5220316784478212341L;

    public static final String[] COLUMN_NAMES = { "B", "I", "N", "G", "O" };

    public BingoTable(final List<BingoBall> bingoNumbers)
    {
        super(convertListNumbersToArray(bingoNumbers), COLUMN_NAMES);

        setOpaque(false);
        setShowGrid(false);
        setRowHeight(40);

        getTableHeader().setDefaultRenderer(new BingoTableHeaderCellRender());

        Enumeration<TableColumn> columns = getColumnModel().getColumns();
        while (columns.hasMoreElements())
        {
            TableColumn tableColumn = (TableColumn)columns.nextElement();
            tableColumn.setCellRenderer(new BingoNumberCellRender());
        }

    }


    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public static final class BingoNumberCellRender extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 5185792895683313498L;

        BingoNumberCellRender() {}

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (!(value instanceof BingoBall)) {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
            BingoBall number = (BingoBall)value;

            JLabel label = new JLabel(value.toString());
            label.setFont(new Font("Arial", 1, 30));
            if (number.isRaffled())
            {
                label.setFont(new Font("Arial", 1, 40));
                label.setForeground(Color.RED);
            }
            label.setHorizontalAlignment(0);
            label.setOpaque(true);
            return label;
        }
    }

    public static class BingoTableHeaderCellRender extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 5334288080098668990L;

        BingoTableHeaderCellRender() {}

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            JLabel label = new JLabel(value.toString());
            label.setFont(new Font(BingoConstants.FONT_NAME, 1, 42));
            label.setForeground(Color.GRAY);
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            label.setHorizontalAlignment(0);
            return label;
        }
    }


    private static BingoBall[][] convertListNumbersToArray(final List<BingoBall> bingoNumbers) {
        final int lines = bingoNumbers.size() / COLUMN_NAMES.length;
        final BingoBall[][] numbersArray = new BingoBall[lines][COLUMN_NAMES.length];
        final List<BingoBall> bingoNumbersCopy = new ArrayList<>(bingoNumbers);

        int i = 0;
        do {
            final List<BingoBall> subList = bingoNumbersCopy.subList(0, Math.min(lines, bingoNumbersCopy.size()));
            for (int l = 0; l < lines; l++) {
                numbersArray[l][i] = subList.get(l);
            }
            bingoNumbersCopy.removeAll(subList);
            i++;
        } while (!bingoNumbersCopy.isEmpty());

        return numbersArray;
    }
}
