import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI calculator = new GUI();
            calculator.setVisible(true);
        });
    }
}