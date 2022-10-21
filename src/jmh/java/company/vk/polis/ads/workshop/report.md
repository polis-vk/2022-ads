| **Benchmark** | **(arraySize)** | **Mode** | **Cnt** | **Score** | **Error** | **Units** |
|---|---|---|---|---|---|---|
| SortsBench.heapSort | 100 | avgt | 3 | 0,006 � | 0,003 | ms/op |
| SortsBench.heapSort | 1000 | avgt | 3 | 0,138 � | 0,085 | ms/op |
| SortsBench.heapSort | 10000 | avgt | 3 | 1,685 � | 0,944 | ms/op |
| SortsBench.heapSort | 100000 | avgt | 3 | 28,196 � | 137,648 | ms/op |
| SortsBench.heapSort | 300000 | avgt | 3 | 145,232 � | 71,032 | ms/op |
| SortsBench.improvedInsertionSort | 100 | avgt | 3 | ? 10??  |  | ms/op |
| SortsBench.improvedInsertionSort | 1000 | avgt | 3 | 0,002 �| 0,001 | ms/op |
| SortsBench.improvedInsertionSort | 10000  | avgt | 3 | 0,023 �| 0,003 | ms/op |
| SortsBench.improvedInsertionSort | 100000 | avgt | 3 | 0,333 �| 1,067 | ms/op |
| SortsBench.improvedInsertionSort | 300000 | avgt | 3 | 4,153 �| 3,712 | ms/op |
| SortsBench.insertionSort | 100  | avgt | 3 | ? 10??  |  | ms/op |
| SortsBench.insertionSort | 1000 | avgt | 3 | 0,004 � | 0,001  | ms/op |
| SortsBench.insertionSort | 10000 | avgt | 3 | 0,045 � | 0,031  | ms/op |
| SortsBench.insertionSort | 100000 | avgt | 3 | 23975,411 � | 15678,422 | ms/op |
| SortsBench.insertionSort | 300000 | avgt | 3 | 847472,233 � | 949223,399 | ms/op |
| SortsBench.mergeSort | 100| avgt | 3 | 0,018 � | 0,010  | ms/op |
| SortsBench.mergeSort | 1000| avgt | 3 | 0,296 � | 0,242  | ms/op |
| SortsBench.mergeSort | 10000| avgt | 3 | 22,494 � | 5,945  | ms/op |
| SortsBench.mergeSort | 100000| avgt | 3 | 4184,836 � | 5123,789  | ms/op |
| SortsBench.mergeSort | 300000| avgt | 3 | 41310,593 � | 4914,169  | ms/op |
| SortsBench.quickSort | 100 | avgt | 3 | 0,009 � | 0,006 | ms/op |
| SortsBench.quickSort | 1000 | avgt | 3 | 0,117 � | 0,068 | ms/op |
| SortsBench.quickSort | 10000 | avgt | 3 | 1,542 � | 0,828 | ms/op |
| SortsBench.quickSort | 100000 | avgt | 3 | 29,824 � | 37,435 | ms/op |
| SortsBench.quickSort | 300000 | avgt | 3 | 188,116 � | 167,447 | ms/op |

Как можно заметить, наилучшим образом при всех рассмотренных длиннах массива себя показала **улучшенная сортировка слиянием**. Также стоит отметить, что _сортировка слиянием_ показала себя лучше, чем _быстрая сортировка_ и _пирамидальная_ при размерах массива 100, 1000, 10_000 и 100_000, и чем _сортировка слиянием_ при размерах 100, 1000 и 10_000. Если сравнивать сортировки со временной сложностью O(nlog(n)) (без учёта улучшенной сортировки вставками) при больших данных (в нашем случае 300_000), то здесь лидирует _пирамидальная сортировка_, далее идёт _быстрая сортировка_ и _сортировка слиянием_.