package scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Trim Muharremi
 * Graphical user interface - pamja grafike e aplikacionit, dritarja kryesore.
 * Permban edhe disa metoda validimi
 */
public class GUIForm {

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JTextField textEmri;
    private JLabel labelEmri;
    private JTextField textmbiemri;
    private JLabel labelMbiemri;
    private JLabel textData;
    private JTextField textFieldDatelindja;
    private JLabel labelAdresa;
    private JTextField textFieldAdresa;
    private JLabel labelInfo;
    private JButton buttonRuaj;
    private JTextArea textAreaInfo;
    private JTextField textFieldDataTerminit;
    private JLabel labelMjeku;
    private JLabel labelData;
    private JLabel IdLabel;
    ;
    private JTextField textFieldPacientiId;
    private JButton ruajTermininButton;
    private JButton pacientetERegjistruarButton;
    private JButton terminetButton;
    private JButton buttonDPicker;
    private JButton buttonDataTerminit;
    private JComboBox comboBoxOra;

    private int idIncrement = 1;
    private static Databaza databaza = new Databaza();

    public GUIForm() {

        IdLabel.setText(databaza.nextPatientId() + 1 + "");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        String formatted = format1.format(cal.getTime());

        textFieldDatelindja.setText(formatted);
        textFieldDataTerminit.setText(formatted);

        buttonRuaj.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validoRuajtjenEPacienteve()) {
                    databaza.shtoPacient(textEmri.getText(), textmbiemri.getText(), textFieldAdresa.getText(), textAreaInfo.getText(),textFieldDatelindja.getText());
                    textEmri.setText("");
                    textmbiemri.setText("");
                    textFieldAdresa.setText("");

                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, 1);
                    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                    String formatted = format1.format(cal.getTime());

                    textFieldDatelindja.setText(formatted + "");
                    textAreaInfo.setText("");
                    IdLabel.setText(++idIncrement + "");
                    new JOptionPane().showMessageDialog(panelMain, "Pacienti u regjistrua");
                } else {
                    new JOptionPane().showMessageDialog(panelMain, "Ju lutem plotesoni te gjitha fushat");

                }

            }
        });

        textAreaInfo.setLineWrap(true);
        textAreaInfo.setWrapStyleWord(true);
        // textAreaInfo.setGrowByContent(false);

        pacientetERegjistruarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new PacientetTable(databaza.getPacientet());
            }
        });
        buttonDPicker.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                DatePicker dp = new DatePicker(panelMain);
                Point bP = buttonDPicker.getLocationOnScreen();
                dp.d.setLocation(bP.x, bP.y + buttonDPicker.getHeight());
                dp.d.setVisible(true);
                textFieldDatelindja.setText(dp.setPickedDate());
            }
        });
        buttonDataTerminit.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                DatePicker dp = new DatePicker(panelMain);
                Point bP = buttonDataTerminit.getLocationOnScreen();
                dp.d.setLocation(bP.x, bP.y + buttonDataTerminit.getHeight());
                dp.d.setVisible(true);
                textFieldDataTerminit.setText(dp.setPickedDate());
            }
        });
        ruajTermininButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validoRuajtjenETermineve()) {

                    if (validoPacientId(textFieldPacientiId.getText())) {
                        if(checkTerminet(textFieldDataTerminit.getText(), comboBoxOra.getSelectedItem().toString()))
                        {
                            databaza.shtoTermin(textFieldDataTerminit.getText(), comboBoxOra.getSelectedItem().toString(), Integer.parseInt(textFieldPacientiId.getText()));
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.DATE, 1);
                            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                            String formatted = format1.format(cal.getTime());

                            textFieldDataTerminit.setText(formatted);
                            comboBoxOra.setSelectedIndex(1);
                            textFieldPacientiId.setText("");
                            new JOptionPane().showMessageDialog(panelMain, "Termini u ruajt");
                        }
                        else{
                            new JOptionPane().showMessageDialog(panelMain, "Ky termin eshte i nxene");
                        }

                    } else {
                        new JOptionPane().showMessageDialog(panelMain, "Pacienti me Id=" + textFieldPacientiId.getText() + " nuk ekziston ne databaze");
                    }

                } else {
                    new JOptionPane().showMessageDialog(panelMain, "Ju lutem jepeni ID e pacientit");
                }
            }
        });
        terminetButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new TerminetTable(databaza.getTermini());
            }
        });
    }

    /**
     *
     * @param text id e pacientit
     * @return true nese pacienti me id=text gjendet ne liste
     */
    private boolean validoPacientId(String text) {
        int id = Integer.parseInt(text);

        if (databaza.getIdPacienteve().contains(id))
            return true;
        else
            return false;

    }

    /**
     * Kthen instancen e databazes te krijuar gjate ekzekutimit te ketij aplikacioni(konstruktori GUIForm ).
     * Nevojitet ne klaset tjera, kur deshirojme te i perdorim listat e ruajtura ne databaze.
     * @return lidhja e databzes
     */
    public static Databaza getDatabaza() {
        return databaza;
    }

    /**
     *
     * @return true nese fushat perkatese jane plotesuar, false perndryshe
     */
    public boolean validoRuajtjenEPacienteve() {
        if (textEmri.getText().equals("") || textmbiemri.getText().equals("") || textFieldAdresa.getText().equals(""))
            return false;
        else
            return true;
    }

    /**
     *
     * @return true nese ID e pacientit gjate shtimit te terminit eshte dhene, perndryshe false
     */
    public boolean validoRuajtjenETermineve() {

        if (textFieldPacientiId.getText().equals(""))
            return false;
        else
            return true;
    }

    /**
     * Kontrollo a eshte termini i nxene
     * @param data data terminit
     * @param ora ora terminit
     * @return true nese koha e terminit eshte e lire, perndryshe false
     */
    public boolean checkTerminet(String data, String ora) {
       boolean ret= true;
        for (int i = 0; i < databaza.getTermini().size(); i++) {
            if (databaza.getTermini().get(i).getData().equals(data) && databaza.getTermini().get(i).getOra().equals(ora)) {
                ret=false;
                break;

            }
        }
                return ret;
    }


    /**
     * Ekzekutimi i aplikacionit
     * @param args
     */

    public static void main(String[] args) {

              JFrame frame = new JFrame("Terminet");
                    frame.setContentPane(new GUIForm().panelMain);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //frame.pack();
                    frame.setBounds(400, 100,600,400);

                    frame.setVisible(true);
                    frame.setResizable(false);
    }
}
