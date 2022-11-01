###Сортировка вставками

| Benchmark                              | (dataLength) | Mode | Cnt |  Score  |  Error | Units |
|----------------------------------------|--------------|------|-----|---------|--------|-------|
| SortBench.measureInsertionSort         |          100 | avgt |  3  | 0,005   | 0,001  | ms/op |
| SortBench.measureInsertionSort         |         1000 | avgt |  3  | 0,653   | 0,746  | ms/op |
| SortBench.measureInsertionSort         |        10000 | avgt |  3  | 80,048  | 10,150 | ms/op |

###Улучшенная сортировка вставками

| Benchmark                              | (dataLength) | Mode | Cnt |  Score  |  Error | Units |
|----------------------------------------|--------------|------|-----|---------|--------|-------|
| SortBench.measureImprovedInsertionSort |          100 | avgt |  3  | 0,008   | 0,001  | ms/op |
| SortBench.measureImprovedInsertionSort |         1000 | avgt |  3  | 0,124   | 0,001  | ms/op |
| SortBench.measureImprovedInsertionSort |        10000 | avgt |  3  | 3,042   | 0,048  | ms/op |

###Сортировка слиянием

| Benchmark                              | (dataLength) | Mode | Cnt |  Score  |  Error | Units |
|----------------------------------------|--------------|------|-----|---------|--------|-------|
| SortBench.measureMergeSort             |          100 | avgt |  3  | 0,011   | 0,001  | ms/op |
| SortBench.measureMergeSort             |         1000 | avgt |  3  | 0,142   | 0,002  | ms/op |
| SortBench.measureMergeSort             |        10000 | avgt |  3  | 1,896   | 0,170  | ms/op |

###Быстрая сортировка

| Benchmark                              | (dataLength) | Mode | Cnt |  Score  |  Error | Units |
|----------------------------------------|--------------|------|-----|---------|--------|-------|
| SortBench.measureQuickSort             |          100 | avgt |  3  | 0,006   | 0,001  | ms/op |
| SortBench.measureQuickSort             |         1000 | avgt |  3  | 0,087   | 0,018  | ms/op |
| SortBench.measureQuickSort             |        10000 | avgt |  3  | 1,261   | 0,035  | ms/op |

###Пирамидальная сортировка

| Benchmark                              | (dataLength) | Mode | Cnt |  Score  |  Error | Units |
|----------------------------------------|--------------|------|-----|---------|--------|-------|
| SortBench.measureHeapSort              |          100 | avgt |  3  | 0,007   | 0,001  | ms/op |
| SortBench.measureHeapSort              |         1000 | avgt |  3  | 0,108   | 0,004  | ms/op |
| SortBench.measureHeapSort              |        10000 | avgt |  3  | 1,661   | 0,049  | ms/op |

###Выводы

Сортировка вставками показала наилучший результат на массиве из 100 элементов, но на массивах большего размера (1000 и 10000 элементов) сортировка вставками показала наихудший результат, на них лучше всего показала себя быстрая сортировка. 

Сортировка слиянием и пирамидальная сортировка показали себя в среднем чуть хуже, чем быстрая сортировка. 

Улучшенная сортировка вставками на массивах из 100 и 1000 элементов не сильно уступает пирамидальной сортировке и даже работает чуть лучше, чем сортировка слиянием. А вот на массиве из 10000 элементов уже работает значительно хуже.   