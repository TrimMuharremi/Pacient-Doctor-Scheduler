package scheduler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Trim Muharremi
 * Paraqet terminet ne tabele.
 */

public class TerminetTable extends JFrame {

    DefaultTableModel model;
    JTable table;
    JButton buttonDeleteRow;

    public TerminetTable(ArrayList<Termini> data) {
        String[] columnNames = new String[]{"Data", "Ora", "Pacienti ID"};


        final Class[] columnClass = new Class[] {
                String.class, String.class, String.class
        };
        //create table model with data
         model = new DefaultTableModel(convertListToArray(data), columnNames) {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClass[columnIndex];
            }
        };

        table = new JTable(model);
        buttonDeleteRow = new JButton();
        buttonDeleteRow.setText("Fshij rreshtin e selektuar");
        buttonDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Delete_RowActionPerformed(evt);
            }
        });

        BorderLayout bLayout = new BorderLayout();
        this.setLayout(bLayout);

        //add the table to the frame
        this.add(new JScrollPane(table));
        this.add(buttonDeleteRow, BorderLayout.SOUTH);

        this.add(new JScrollPane(table));

        this.setTitle("Terminet");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();
        this.setBounds(400, 200,300,200);
        this.setVisible(true);

    }

    // button delete row
    private void jButton_Delete_RowActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // get selected row index

        try{

            int SelectedRowIndex = table.getSelectedRow();
            GUIForm.getDatabaza().deleteTermin(SelectedRowIndex);

            model.removeRow(SelectedRowIndex);
        }catch(Exception ex)
        {
           // JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * converton listen ne varg 2D
     * @param data lista me termine
     * @return vargu 2D me termine
     */

    public String[][] convertListToArray(ArrayList<Termini> data) {
        int h = data.size();

        String[][] terminData = new String[h][6];
        for (int i = 0; i < h; i++) {
            terminData[i][0] = data.get(i).getData();
            terminData[i][1] = data.get(i).getOra();
            terminData[i][2] = data.get(i).getPacienti()+"";
        }
        return terminData;
    }


}
