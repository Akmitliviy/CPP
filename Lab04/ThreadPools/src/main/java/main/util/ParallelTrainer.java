// src/main/java/main/util/ParallelTrainer.java
package main.util;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import main.model.BatchProcessor;
import main.model.ModelAggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelTrainer {
    private final int threadCount;
    private final VBox threadsBox;
    private final TextArea resultArea;
    private final ExecutorService executor;

    public ParallelTrainer(int threadCount, VBox threadsBox, TextArea resultArea) {
        this.threadCount = threadCount;
        this.threadsBox = threadsBox;
        this.resultArea = resultArea;
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    public void trainModel(double[][] dataBatches, int weightSize) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        List<Future<double[]>> futures = new ArrayList<>();
        ModelAggregator aggregator = new ModelAggregator(weightSize);

        // Створення віджетів для потоків
        List<Label> threadLabels = new ArrayList<>();
        for (int i = 0; i < dataBatches.length; i++) {
            Label label = new Label("Завдання " + i + ": Очікування...");
            Platform.runLater(() -> threadsBox.getChildren().add(label));
            threadLabels.add(label);
        }

        // Відправка завдань на виконання
        for (int i = 0; i < dataBatches.length; i++) {
            int finalI = i;
            futures.add(executor.submit(() -> {
                Platform.runLater(() -> threadLabels.get(finalI).setText("Завдання  " + finalI + ": Виконується..."));
                BatchProcessor processor = new BatchProcessor(finalI, dataBatches[finalI]);
                double[] result = processor.call();
                Platform.runLater(() -> threadLabels.get(finalI).setText("Завдання " + finalI + ": Завершено."));
                return result;
            }));
        }

        List<double[]> batchResults = new ArrayList<>();

        // Збір результатів
        for (Future<double[]> future : futures) {
            try {
                batchResults.add(future.get());
            } catch (ExecutionException e) {
                Platform.runLater(() -> resultArea.appendText("Помилка під час обробки: " + e.getMessage() + "\n"));
            }
        }

        // Агрегація результатів
        aggregator.aggregateResults(batchResults);

        long endTime = System.currentTimeMillis();
        Platform.runLater(() -> {
            resultArea.appendText("Загальний час виконання: " + (endTime - startTime) + " мс\n");
            resultArea.appendText("Глобальні ваги: " + aggregator.getGlobalWeightsAsString() + "\n");
        });

        // Завершення виконання
        executor.shutdown();
    }
}
