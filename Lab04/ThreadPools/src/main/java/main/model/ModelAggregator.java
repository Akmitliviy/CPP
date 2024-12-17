// src/main/java/main/model/ModelAggregator.java
package main.model;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelAggregator {
    private double[] globalWeights;

    public ModelAggregator(int weightSize) {
        this.globalWeights = new double[weightSize];
    }

    // Агрегація результатів
    public void aggregateResults(List<double[]> batchResults) {
        for (double[] batchWeights : batchResults) {
            for (int i = 0; i < globalWeights.length; i++) {
                globalWeights[i] += batchWeights[i];
            }
        }
        normalizeWeights();
    }

    // Нормалізація глобальних вагів (усереднення)
    private void normalizeWeights() {
        for (int i = 0; i < globalWeights.length; i++) {
            globalWeights[i] /= globalWeights.length;
        }
    }

    // Вивід глобальних вагів
    public void printGlobalWeights() {
        System.out.println("Global weights: " + Arrays.toString(globalWeights));
    }

    public String getGlobalWeightsAsString() {
        return Arrays.toString(globalWeights);
    }
}
