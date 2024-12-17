// src/main/java/main/javafx/Controller.java
package main.javafx.threadpools;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import main.util.ParallelTrainer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController {
    @FXML private TextField threadCountField;
    @FXML private VBox threadsBox;
    @FXML private TextArea resultArea;

    private ExecutorService executor;

    @FXML
    public void startProcessing() {
        // Отримання кількості потоків
        int threadCount;
        try {
            threadCount = Integer.parseInt(threadCountField.getText());
        } catch (NumberFormatException e) {
            resultArea.setText("Помилка: Введіть правильне число потоків.");
            return;
        }

        // Очищення перед запуском
        threadsBox.getChildren().clear();
        resultArea.clear();

        // Ініціалізація обробки
        executor = Executors.newFixedThreadPool(threadCount);
        double[][] dataBatches = generateDataBatches(5, 10); // 5 батчів по 10 елементів
        int weightSize = 10; // Розмір глобальних вагів

        // Запуск тренування у фоновому потоці
        new Thread(() -> {
            try {
                ParallelTrainer trainer = new ParallelTrainer(threadCount, threadsBox, resultArea);
                trainer.trainModel(dataBatches, weightSize);
            } catch (InterruptedException e) {
                Platform.runLater(() -> resultArea.setText("Тренування було перервано."));
            } finally {
                executor.shutdown();
            }
        }).start();
    }

    // Генерація даних для навчання
    private double[][] generateDataBatches(int batchCount, int batchSize) {
        double[][] data = new double[batchCount][batchSize];
        for (int i = 0; i < batchCount; i++) {
            for (int j = 0; j < batchSize; j++) {
                data[i][j] = Math.random();
            }
        }
        return data;
    }
}
