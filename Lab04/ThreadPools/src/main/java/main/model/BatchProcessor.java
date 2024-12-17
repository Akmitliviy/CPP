// src/main/java/main/model/BatchProcessor.java
package main.model;

import java.util.Random;
import java.util.concurrent.Callable;

// Симуляція обробки одного батчу
public class BatchProcessor implements Callable<double[]> {
    private final int batchId;
    private final double[] batchData;

    public BatchProcessor(int batchId, double[] batchData) {
        this.batchId = batchId;
        this.batchData = batchData;
    }

    @Override
    public double[] call() throws Exception {
        System.out.println("Thread " + Thread.currentThread().getName() + " processing batch " + batchId);

        // Симуляція "навчання" — обчислення
        double[] updatedWeights = new double[batchData.length];
        Random random = new Random();
        for (int i = 0; i < batchData.length; i++) {
            updatedWeights[i] = batchData[i] + random.nextDouble(); // Оновлення вагів
        }

        // Симуляція часу навчання
        Thread.sleep(1000 + random.nextInt(500));

        System.out.println("Thread " + Thread.currentThread().getName() + " completed batch " + batchId);
        return updatedWeights;
    }
}
