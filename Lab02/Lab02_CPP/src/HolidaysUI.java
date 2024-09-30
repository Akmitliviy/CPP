import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

public class HolidaysUI extends JFrame {

    private String datesFile = "Dates.txt";
    private JTextArea datesText = new JTextArea();
    public HolidaysUI() {

        setTitle("Святкові дні");
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        datesText = new JTextArea(GetDatesFromFileAsString());

        JScrollPane datesScrollPane = new JScrollPane(datesText);
        datesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(datesScrollPane);
        add(textPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));

        JButton save = new JButton();
        save.setText("Save");
        save.addActionListener(new SaveButtonClickedListener());
        buttonsPanel.add(save);

        JButton cancel = new JButton();
        cancel.setText("Cancel");
        cancel.addActionListener(new CancelButtonClickedListener());
        buttonsPanel.add(cancel);

        add(buttonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class SaveButtonClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileHandler fileHandler = new FileHandler(datesFile);

            try{
                fileHandler.writeFile(datesText.getText());
            }catch(IOException ioe){
                System.out.println(ioe);
            }

            dispose();
        }
    }

    private class CancelButtonClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    private String GetDatesFromFileAsString() {
        FileHandler fileHandler = new FileHandler(datesFile);
        String dates = "";

        try {
            dates = fileHandler.readFile();
        }catch (IOException e) {
            dates = e.toString();
        }

        return dates;
    }


}
