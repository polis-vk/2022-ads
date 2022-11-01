#Отчёт

### ImprovedInsertion Sort
| Benchmark                  | (dataLength) | Mode | Cnt | Score with Error | Units |
|---------------------------:|-------------:|-----:|----:|-----------------:|------:|
| ImprovedInsertionSort      | 100          | avgt |  3  | 0.013 ±   0.001  | ms/op |
| ImprovedInsertionSort      | 1000         | avgt |  3  | 0,161 ±  0.002   | ms/op |
| ImprovedInsertionSort      | 10000        | avgt |  3  | 3,381 ±  0,054   | ms/op |
| ImprovedInsertionSort      | 100000       | avgt |  3  | 204,984 ± 21,250 | ms/op |

### InsertionSort
| Benchmark          | (dataLength) | Mode | Cnt |  Score with Error   | Units |
|-------------------:|-------------:|-----:|----:|--------------------:|------:|
| InsertionSort      | 100          | avgt |  3  | 0.007 ±   0.001     | ms/op |
| InsertionSort      | 1000         | avgt |  3  | 0,625 ±   0,009     | ms/op |
| InsertionSort      | 10000        | avgt |  3  | 24,273 ±   0,915    | ms/op |
| InsertionSort      | 100000       | avgt |  3  | 8739,594 ± 13756,992| ms/op |

### MergeSort
| Benchmark      | (dataLength) | Mode | Cnt | Score with Error | Units |
|---------------:|-------------:|-----:|----:|-----------------:|------:|
| MergeSort      | 100          | avgt |  3  | 0,006 ±  0,001   | ms/op |
| MergeSort      | 1000         | avgt |  3  | 0,084 ± 0,001    | ms/op |
| MergeSort      | 10000        | avgt |  3  | 1,164 ± 0,006    | ms/op |
| MergeSort      | 100000       | avgt |  3  | 14,980 ± 1,662   | ms/op |
| MergeSort      | 1000000      | avgt |  3  | 279,874 ± 101,520| ms/op |

### QuickSort
| Benchmark      | (dataLength) | Mode | Cnt | Score with Error | Units |
|---------------:|-------------:|-----:|----:|-----------------:|------:|
| Quick Sort     | 100          | avgt |  3  | 0,005 ± 0,001    | ms/op |
| Quick Sort     | 1000         | avgt |  3  | 0,065 ± 0,001    | ms/op |
| Quick Sort     | 10000        | avgt |  3  | 0,867 ± 0,014    | ms/op |
| Quick Sort     | 100000       | avgt |  3  | 10,653 ± 0,014   | ms/op |
| Quick Sort     | 1000000      | avgt |  3  | 165,075 ± 42,196 | ms/op |

### HeapSort
| Benchmark         | (dataLength) | Mode | Cnt | Score with Error | Units |
|------------------:|-------------:|-----:|----:|-----------------:|------:|
| HeapSort          | 100          | avgt |  3  | 0,006 ± 0.001    | ms/op |
| HeapSort          | 1000         | avgt |  3  | 0,095 ± 0,003    | ms/op |
| HeapSort          | 10000        | avgt |  3  | 1,873 ± 0.92     | ms/op |
| HeapSort          | 100000       | avgt |  3  | 36,869 ± 16,381  | ms/op |
| HeapSort          | 1000000      | avgt |  3  | 869,047 ± 89,035 | ms/op |

## Результат
> В процессе работы над бенчмарками, стало понятно, что InsertionSort очень долго будет работать на массиве размером 1000000, поэтому для InsertionSort и ImprovedInsertionSort решили ограничиться размером в 100000
> Для всех остальных сортировок прогнали и на массиве размером 1000000
> Очевидно, самая медленная сортировка InsertionSort, самая быстрая QuickSort, как видно HeapSort все таки проигрывает QuickSort и MergeSort в скорости.
> Но ImprovedInsertionSort, т.е. улучшенная сортировка вставками показывает себя значительно лучше чем обычная InsertionSort