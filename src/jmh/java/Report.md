## Data from the benchmark for sorting
#### Improved Insertion Sort
| Benchmark                                      | (dataLength)  |  Mode   |  Cnt   |  Score  |  Error  | Units  |
|------------------------------------------------|:-------------:|:-------:|:------:|:-------:|:-------:|:------:|
| BenchmarkForImprovedInsertionSort.measureSort  | 100 |  avgt   |   3    | 0,005 � |  0,001  | ms/op  |
| BenchmarkForImprovedInsertionSort.measureSort  |   1000    |  avgt   |   3    | 0,082 � |  0,004  | ms/op  |
| BenchmarkForImprovedInsertionSort.measureSort  |   10000    |  avgt   |   3    | 2,481 � |  0,587  | ms/op  |
| BenchmarkForImprovedInsertionSort.measureSort  | 100000 |  avgt   |   3    | 180,682 � |  4,284  | ms/op  |
| BenchmarkForImprovedInsertionSort.measureSort  |   1000000    |  avgt   |   3    | 18715,576 � |  2458,529  | ms/op  |
#### Merge Sort
| Benchmark                                      | (dataLength)  |  Mode   |  Cnt   |  Score  |  Error  | Units  |
|------------------------------------------------|:-------------:|:-------:|:------:|:-------:|:-------:|:------:|
| BenchmarkForMergeSort.measureSort  | 100 |  avgt   |   3    | 0,008 � |  0,001  | ms/op  |
| BenchmarkForMergeSort.measureSort  |   1000    |  avgt   |   3    | 0,105 � |  0,002  | ms/op  |
| BenchmarkForMergeSort.measureSort  |   10000    |  avgt   |   3    | 1,313 � |  0,027  | ms/op  |
| BenchmarkForMergeSort.measureSort  | 100000 |  avgt   |   3    | 16,814 � |  1,143  | ms/op  |
| BenchmarkForMergeSort.measureSort  |   1000000    |  avgt   |   3    | 275,640 � |  93,651  | ms/op  |
#### Quick Sort
| Benchmark                                      | (dataLength)  |  Mode   |  Cnt   |  Score  |  Error  | Units  |
|------------------------------------------------|:-------------:|:-------:|:------:|:-------:|:-------:|:------:|
| BenchmarkForQuickSort.measureSort  | 100 |  avgt   |   3    | 0,004 � |  0,001  | ms/op  |
| BenchmarkForQuickSort.measureSort  |   1000    |  avgt   |   3    | 0,059 � |  0,001  | ms/op  |
| BenchmarkForQuickSort.measureSort  |   10000    |  avgt   |   3    | 0,826 � |  0,020  | ms/op  |
| BenchmarkForQuickSort.measureSort  | 100000 |  avgt   |   3    | 11,447 � |  0,266  | ms/op  |
| BenchmarkForQuickSort.measureSort  |   1000000    |  avgt   |   3    | 209,426 � |  55,537  | ms/op  |
#### Insertion Sort
| Benchmark                             | (dataLength)  |  Mode   |  Cnt   |  Score   | Error | Units  |
|---------------------------------------|:-------------:|:-------:|:------:|:--------:|:-----:|:------:|
| BenchmarkForInsertionSort.measureSort | 100 |  avgt   |   3    | 0,004 �  | 0,001 | ms/op  |
| BenchmarkForInsertionSort.measureSort |   1000    |  avgt   |   3    | 0,565 �  | 2,362 | ms/op  |
| BenchmarkForInsertionSort.measureSort                                     |   10000    |  avgt   |   3    | 67,079 �  | 4,446 | ms/op  |
| BenchmarkForInsertionSort.measureSort     | 100000 |  avgt   |   3    | 11819,358 � | 28764,992 | ms/op  |
#### Pyramid Sort
| Benchmark                                      | (dataLength)  |  Mode   |  Cnt   |   Score   |  Error  | Units  |
|------------------------------------------------|:-------------:|:-------:|:------:|:---------:|:-------:|:------:|
| BenchmarkForHeapSort.measureSort  | 100 |  avgt   |   3    |  0,002 �  |  0,001  | ms/op  |
| BenchmarkForHeapSort.measureSort  |   1000    |  avgt   |   3    |  0,049 �  |  0,003  | ms/op  |
| BenchmarkForHeapSort.measureSort  |   10000    |  avgt   |   3    |  0,703 �  |  0,097  | ms/op  |
| BenchmarkForHeapSort.measureSort  | 100000 |  avgt   |   3    | 8,139 �  |  8,177  | ms/op  |
| BenchmarkForHeapSort.measureSort  |   1000000    |  avgt   |   3    | 184,372 � |  826,995  | ms/op  |

#### Вывод
В итоге я получил время работы сортировок для 100, 1000, 10000, 100000 и для 1000000 данных. Из экспериментальных данных следует, что пирамидальная сортировка - самая быстрая сортировка. Обычная сортировка вставками оказалась самой медленной.(В обыной сортировке вставками параметр 1_000_000 был удалён, так как он слишком долго работает)