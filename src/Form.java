import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Form extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton bodyButton;
    private JButton trianglButton;
    private JPanel mainPanel;

    JFileChooser chooser = new JFileChooser();

    public Form() {
        setContentPane(mainPanel);


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu soubor = new JMenu("Soubor");
        menuBar.add(soubor);
        JMenu akce = new JMenu("Akce");
        menuBar.add(akce);
        JMenuItem nacti = new JMenuItem("Načti");
        soubor.add(nacti);
        JMenuItem uloz = new JMenuItem("Ulož");
        soubor.add(uloz);
        JMenuItem body = new JMenuItem("A -> (B a C)");
        akce.add(body);
        JMenuItem triangl = new JMenuItem("lze sestrojit troúhelník?");
        akce.add(triangl);



        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField2.setText(textField1.getText());
                textField3.setText(textField1.getText());
            }
        };


        bodyButton.addActionListener(listener);
        body.addActionListener(listener);

        ActionListener listenerTriangl = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(textField1.getText());
                int b = Integer.parseInt(textField2.getText());
                int c = Integer.parseInt(textField3.getText());

                if(a+b>c && a+c>b && b+c>a){
                    JOptionPane.showMessageDialog(mainPanel, "Trojúhelník lze sestrojit" );
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "trojúhelník nelze sestrojit");
                }
            }
        };

        trianglButton.addActionListener(listenerTriangl);
        triangl.addActionListener(listenerTriangl);


        nacti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vypis(chooser());
            }
        });

        uloz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapis(chooser());
            }
        });




    }
    private File chooser(){
        int vysledek = chooser.showSaveDialog(mainPanel);
        if(vysledek == chooser.APPROVE_OPTION){
            //zapis(chooser.getSelectedFile());
            return chooser.getSelectedFile();
        }
        return null;
    }
    private void zapis(File soubor){ //funkce která zapíše do souboru

        try {
            FileWriter writer = new FileWriter(soubor);

            writer.write(textField1.getText()+"\n"+textField2.getText()+"\n"+textField3.getText());

            writer.close(); //je potřeba kvuli uzavření paměti při zápisu

        } catch (IOException e) {
            chyba();
        }
    }
    public void chyba(){
        JOptionPane.showMessageDialog(mainPanel, "chyba v uložení");
    }

    private void vypis(File soubor){
        try {
            Scanner scanner = new Scanner(soubor);
            while(scanner.hasNextLine()) {
                textField1.setText(scanner.nextLine());
                textField2.setText(scanner.nextLine());
                textField3.setText(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}
