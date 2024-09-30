import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class GUI extends JFrame {

    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField[] workStartFields;
    private JTextField[] workEndFields;
    private Map<JCheckBox, Integer> includeDayCheckBoxes;
    private JTextArea resultArea;

    // Робочий графік (день тижня -> [початок роботи, кінець роботи])
    private Map<DayOfWeek, LocalTime[]> workSchedule;

    public GUI() {
        setTitle("Калькулятор робочих годин");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Панель для введення дат
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new GridLayout(2, 2));
        datePanel.add(new JLabel("Початкова дата (yyyy-MM-dd):"));
        startDateField = new JTextField();
        datePanel.add(startDateField);
        datePanel.add(new JLabel("Кінцева дата (yyyy-MM-dd):"));
        endDateField = new JTextField();
        datePanel.add(endDateField);

        add(datePanel, BorderLayout.NORTH);

        // Панель для введення робочих годин для кожного дня тижня
        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(new GridLayout(7, 4));
        workStartFields = new JTextField[7];
        workEndFields = new JTextField[7];
        includeDayCheckBoxes = new HashMap<>();

        DayOfWeek[] daysOfWeek = DayOfWeek.values();
        for (int i = 0; i < 7; i++) {
            schedulePanel.add(new JLabel(daysOfWeek[i].toString()));
            workStartFields[i] = new JTextField("09:00");
            workEndFields[i] = new JTextField("17:00");

            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(true);
            checkBox.addActionListener(new DayIsCheckedListener());
            includeDayCheckBoxes.put(checkBox, i);

            schedulePanel.add(workStartFields[i]);
            schedulePanel.add(workEndFields[i]);
            schedulePanel.add(checkBox);
        }

        add(schedulePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1));

        JButton openExceptionDaysButton = new JButton("Святкові дні");
        openExceptionDaysButton.addActionListener(new OpenHolidaysListener());
        bottomPanel.add(openExceptionDaysButton);


        // Кнопка для обчислення
        JButton calculateButton = new JButton("Обчислити");
        calculateButton.addActionListener(new CalculateListener());
        bottomPanel.add(calculateButton);

        // Панель для виведення результатів
        resultArea = new JTextArea();
        bottomPanel.add(resultArea);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private class OpenHolidaysListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HolidaysUI holidaysUI = new HolidaysUI();
        }
    }

    private class DayIsCheckedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            int index = includeDayCheckBoxes.get(checkBox);
            if(checkBox.isSelected()){
                workStartFields[index].setEnabled(true);
                workEndFields[index].setEnabled(true);
            }else{
                workStartFields[index].setEnabled(false);
                workEndFields[index].setEnabled(false);
            }
        }
    }
    private class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Зчитуємо введені дати
                LocalDate startDate = LocalDate.parse(startDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate endDate = LocalDate.parse(endDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);

                // Створюємо графік роботи
                workSchedule = new HashMap<>();
                DayOfWeek[] daysOfWeek = DayOfWeek.values();

                for (int i = 0; i < 7; i++) {
                    if(!workStartFields[i].isEnabled()) continue;

                    LocalTime startTime = LocalTime.parse(workStartFields[i].getText());
                    LocalTime endTime = LocalTime.parse(workEndFields[i].getText());
                    workSchedule.put(daysOfWeek[i], new LocalTime[]{startTime, endTime});
                }

                // Обчислюємо кількість робочих годин
                long totalWorkHours = WorkHoursCalculator.calculateWorkHours(startDate, endDate, workSchedule);

                // Виводимо результат
                resultArea.setText("Загальна кількість робочих годин: " + totalWorkHours);

            } catch (Exception ex) {
                resultArea.setText("Помилка: " + ex.getMessage());
            }
        }
    }
}
