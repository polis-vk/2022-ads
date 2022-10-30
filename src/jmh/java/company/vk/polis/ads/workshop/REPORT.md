## Результаты бенчмарка:

Для массива длины **100**:

| **Benchmark**                         | **Score** | **Error**   | **Units** |
|----------------------------------------|--------|-------------|---|
| SortsBench.measureHeapSort             | 0,021| ±0,056      | ms/op |
| SortsBench.measureImprovedInsertionSort | 0,017 | ± 0,042     | ms/op |
| SortsBench.measureMergeSort            | 0,022 | ± 0,105     | ms/op |
| SortsBench.measureQuickSort            | 0,016 | ± 0,003     | ms/op |
| SortsBench.measureInsertionSort        |0,007 | ±     0,007 | ms/op |

Для массива длины **1000**:

| **Benchmark** | **Score** | **Error**   | **Units** |
|---|--------|-------------|---|
| SortsBench.measureHeapSort| 0,349 | ±    0,064  | ms/op |
| SortsBench.measureImprovedInsertionSort| 0,304 | ±    0,149  | ms/op |
| SortsBench.measureMergeSort| 0,340 | ±   0,080   | ms/op |
| SortsBench.measureQuickSort | 0,230 | ±    0,080  | ms/op |
| SortsBench.measureInsertionSort | 0,625 | ±     0,771 | ms/op |

Для массива длины **10000**:

| **Benchmark** | **Score**         | **Error**    | **Units** |
|---|-------------------|--------------|---|
| SortsBench.measureHeapSort| 3,623             | ±   29,179   | ms/op |
| SortsBench.measureImprovedInsertionSort| 5,905             | ±    0,569   | ms/op |
| SortsBench.measureMergeSort| 5,260             | ±    5,815   | ms/op |
| SortsBench.measureQuickSort | 3,382 | ±    1,400   | ms/op |
| SortsBench.measureInsertionSort | 63,958|  ±   146,978 | 71,032      | ms/op |

Для массива длины **100000**:

| **Benchmark**                           | **Score**              | **Error**  | **Units** |
|-----------------------------------------|------------------------|------------|---|
| SortsBench.measureHeapSort              | 28,807                 | ±   19,115 | ms/op |
| SortsBench.measureImprovedInsertionSort | 511,602                | ±   62,797 | ms/op |
| SortsBench.measureMergeSort             | 65,033                 | ±   25,197 | ms/op |
| SortsBench.measureQuickSort             | 49,420                 | ±   10,399 | ms/op |
| SortsBench.measureInsertionSort         | 20747,110 | ± 55225,854| ms/op |

Для массива длины **300000**:

| **Benchmark** | **Score** | **Error**  | **Units** |
|---|-----------|------------|---|
| SortsBench.measureHeapSort| 174,910   | ±  501,205 | ms/op |
| SortsBench.measureImprovedInsertionSort| 4459,113  | ±  660,160 | ms/op |
| SortsBench.measureMergeSort| 223,912   | ± 1442,963 | ms/op |
| SortsBench.measureQuickSort | 230,075   | ±   74,417 | ms/op |
| SortsBench.measureInsertionSort | -         | -          | ms/op |

**По результатам бенчмарка можно сделать следующий вывод:**
на относительно маленьких массивах (длина 100)
лучше использовать сортировку вставками и ее улучшенную версию.
Для массивов длины 1000 лидером среди 3-х оставшихся сортировок
является QuickSort, но важно отметить, что при увеличении количества
элементов (например, до 300000) она показывает эффективность аналогичную MergeSort, и 
немного уступающую HeapSort.