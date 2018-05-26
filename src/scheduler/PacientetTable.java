package scheduler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Trim Muharremi
 *
 * JFrame e cila paraqet Pacientet e regjistuar ne tabele, pas selektimit te rreshtit perkates pacienti mund te fshihet nga databaza(lista)
    E nevojshme eshte qe ne konstruktor parameter te jete lista me pacienta, pasi qe ndryshe nuk mund ti qasemi databazes(ose duhet te jete Databaza si parameter hyres.)
 */
public class PacientetTable extends JFrame {

    JTable table;
    JButton buttonDeleteRow;
    DefaultTableModel model;
    public PacientetTable(ArrayList<Pacienti> data) {

        String[] columnNames = new String[]{"Id", "Emri", "Mbiemri", "Datelindja","Adresa","Informata"};

        final Class[] columnClass = new Class[] {
                Integer.class, String.class, String.class, String.class,String.class,String.class
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
        //create table with data
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
        this.setTitle("Pacientet e Regjistruar");
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400, 200,600,200);

       // this.pack();
        this.setVisible(true);

    }

    // button delete row
    private void jButton_Delete_RowActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // get selected row index

        try{

            int SelectedRowIndex = table.getSelectedRow();
            GUIForm.getDatabaza().deletePacient(SelectedRowIndex);

            model.removeRow(SelectedRowIndex);
        }catch(Exception ex)
        {
           // JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * e Konverton listen e pacienteve ne varg 2D per arsye se JTable si parameter kerkon nje varg 2D
     * @param data lista qe do te konvertohet
     * @return vargu 2D
     */
    public String[][] convertListToArray(ArrayList<Pacienti> data) {
        int h = data.size();

        String[][] pacientData = new String[h][6];
        for (int i = 0; i < h; i++) {
            pacientData[i][0] = data.get(i).getPacientId() + "";
            pacientData[i][1] = data.get(i).getEmri();
            pacientData[i][2] = data.get(i).getMbiemri();
            pacientData[i][3] = data.get(i).getDatelindja();
            pacientData[i][4] = data.get(i).getAdresa();
            pacientData[i][5] = data.get(i).getInformata();
        }
        return pacientData;
    }

}


