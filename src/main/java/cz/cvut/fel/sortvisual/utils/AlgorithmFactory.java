package cz.cvut.fel.sortvisual.utils;

import cz.cvut.fel.sortvisual.model.sort.*;

import java.util.List;

public class AlgorithmFactory {
    public List<SortingAlgorithm> createAll() {
        return List.of(
                new BubbleSort(new SortingAlgoInfo(
                        "Bubble Sort",
                        "O(n^2)",
                        true,
                        "O(n^2)",
                        "reverse sorted data",
                        "O(n)",
                        "already sorted",
                        "O(1)"
                )),
                new SelectionSort(new SortingAlgoInfo(
                        "Selection Sort",
                        "O(n^2)",
                        false,
                        "O(n^2)",
                        "",
                        "O(n^2)",
                        "",
                        "O(1)"
                )),
                new InsertionSort(new SortingAlgoInfo(
                        "Insertion Sort",
                        "O(n^2)",
                        true,
                        "O(n^2)",
                        "",
                        "O(n)",
                        "",
                        "O(1)"
                )),
                // TODO - graph comparison for every algorithm with growing n, compared to n^2, n*log n etc.
                new HeapSort(new SortingAlgoInfo(
                        "Heap Sort",
                        "O(n*log(n))",
                        false,
                        "O(n*log(n))",
                        "",
                        "O(n)",
                        "",
                        "O(1)"
                )),
                new QuickSort(new SortingAlgoInfo(
                        "Quick Sort",
                        "O(n*log(n))",
                        false,
                        "O(n^2)",
                        "",
                        "O(n*log(n))",
                        "",
                        "O(log(n))"
                )),
                new MergeSort(new SortingAlgoInfo(
                        "Merge Sort",
                        "O(n*log(n))",
                        true,
                        "O(n*log(n))",
                        "",
                        "O(n*log(n))",
                        "",
                        "O(n)"
                ))

        );
    }
}
