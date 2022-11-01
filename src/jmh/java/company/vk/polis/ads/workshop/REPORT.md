# Бенчмарки для сортировок

### Insertion Sort
| Benchmark          | (dataLength) | Mode | Cnt |  Score with Error    | Units |
|-------------------:|-------------:|-----:|----:|---------------------:|------:|
| InsertionSortBench | 100          | avgt |  3  | 0,006 ±   0.001      | ms/op |
| InsertionSortBench | 1000         | avgt |  3  | 0,672 ±   0,899      | ms/op |
| InsertionSortBench | 10000        | avgt |  3  | 83,270 ±   30,438    | ms/op |
| InsertionSortBench | 100000       | avgt |  3  | 12828,549 ± 16031,346| ms/op |

### Improved Insertion Sort
| Benchmark                  | (dataLength) | Mode | Cnt | Score with Error | Units |
|---------------------------:|-------------:|-----:|----:|-----------------:|------:|
| ImprovedInsertionSortBench | 100          | avgt |  3  | 0,015 ±   0.003  | ms/op |
| ImprovedInsertionSortBench | 1000         | avgt |  3  | 0,189 ±  0,015   | ms/op |
| ImprovedInsertionSortBench | 10000        | avgt |  3  | 4,1817 ±  0,129  | ms/op |
| ImprovedInsertionSortBench | 100000       | avgt |  3  | 379,820 ± 12,460 | ms/op |

### Merge Sort
| Benchmark      | (dataLength) | Mode | Cnt | Score with Error | Units |
|---------------:|-------------:|-----:|----:|-----------------:|------:|
| MergeSortBench | 100          | avgt |  3  | 0,010 ± 0,004    | ms/op |
| MergeSortBench | 1000         | avgt |  3  | 0,144 ± 0,009    | ms/op |
| MergeSortBench | 10000        | avgt |  3  | 1,724 ± 0,458    | ms/op |
| MergeSortBench | 100000       | avgt |  3  | 26,260 ± 6,637   | ms/op |

### Quick Sort
| Benchmark      | (dataLength) | Mode | Cnt | Score with Error | Units |
|---------------:|-------------:|-----:|----:|-----------------:|------:|
| QSortBenchmark | 100          | avgt |  3  | 0.007 ± 0.002    | ms/op |
| QSortBenchmark | 1000         | avgt |  3  | 0,107 ± 0,014    | ms/op |
| QSortBenchmark | 10000        | avgt |  3  | 1,509 ± 0,158    | ms/op |
| QSortBenchmark | 100000       | avgt |  3  | 21,544 ± 2,206   | ms/op |

### Heap Sort
| Benchmark         | (dataLength) | Mode | Cnt | Score with Error | Units |
|------------------:|-------------:|-----:|----:|-----------------:|------:|
| HeapSortBenchmark | 100          | avgt |  3  | 0,008 ± 0,004    | ms/op |
| HeapSortBenchmark | 1000         | avgt |  3  | 0,1209 ± 0,034   | ms/op |
| HeapSortBenchmark | 10000        | avgt |  3  | 1,848 ± 0,413    | ms/op |
| HeapSortBenchmark | 100000       | avgt |  3  | 29,792 ± 13,769  | ms/op |

## Результаты

Полученные результаты говорят о том, что самыми эффективными сортировками являются быстрая сортировка и сортировка слиянием, при этом пирамидальная сортировка немного менее эффективна. Обычная сортировка вставками не сильно отличается по эффективности от "улучшенной" при малом количестве входных данных, но при его увеличении становится заметен явный прирост по эффективности при использовании "улучшенной" сортировки вставками.
