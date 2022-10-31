#Benchmarks for sorts

### Benchmark for Improved Insertion Sort
| Benchmark                  | (dataLength) | Mode | Cnt | Score with Error | Units |
|---------------------------:|-------------:|-----:|----:|-----------------:|------:|
| ImprovedInsertionSortBench | 100          | avgt |  3  | 0.006 ±   0.003  | ms/op |
| ImprovedInsertionSortBench | 1000         | avgt |  3  | 0.097 ±  0.024   | ms/op |
| ImprovedInsertionSortBench | 10000        | avgt |  3  | 2.777 ±  0.686   | ms/op |
| ImprovedInsertionSortBench | 10000        | avgt |  3  | 235.979 ± 11.147 | ms/op |

### Benchmark for Insertion Sort
| Benchmark          | (dataLength) | Mode | Cnt |  Score with Error   | Units |
|-------------------:|-------------:|-----:|----:|--------------------:|------:|
| InsertionSortBench | 100          | avgt |  3  | 0.005 ±   0.001     | ms/op |
| InsertionSortBench | 1000         | avgt |  3  | 0.366 ±   0.382     | ms/op |
| InsertionSortBench | 10000        | avgt |  3  | 37.139 ±   2.136    | ms/op |
| InsertionSortBench | 10000        | avgt |  3  | 11470.965 ± 287.220 | ms/op |

### Benchmark for Merge Sort
| Benchmark      | (dataLength) | Mode | Cnt | Score with Error | Units |
|---------------:|-------------:|-----:|----:|-----------------:|------:|
| MergeSortBench | 100          | avgt |  3  | 0.008 ±  0.001   | ms/op |
| MergeSortBench | 1000         | avgt |  3  | 0.105 ± 0.016    | ms/op |
| MergeSortBench | 10000        | avgt |  3  | 1.369 ± 0.094    | ms/op |
| MergeSortBench | 100000       | avgt |  3  | 17.091 ± 1.019   | ms/op |

### Benchmark for Quick Sort
| Benchmark      | (dataLength) | Mode | Cnt | Score with Error | Units |
|---------------:|-------------:|-----:|----:|-----------------:|------:|
| QSortBenchmark | 100          | avgt |  3  | 0.007 ± 0.004    | ms/op |
| QSortBenchmark | 1000         | avgt |  3  | 0.116 ± 0.126    | ms/op |
| QSortBenchmark | 10000        | avgt |  3  | 1.393 ± 0.068    | ms/op |
| QSortBenchmark | 100000       | avgt |  3  | 17.627 ± 0.637   | ms/op |

### Benchmark for Heap Sort
| Benchmark         | (dataLength) | Mode | Cnt | Score with Error | Units |
|------------------:|-------------:|-----:|----:|-----------------:|------:|
| HeapSortBenchmark | 100          | avgt |  3  | 0.006 ± 0.001    | ms/op |
| HeapSortBenchmark | 1000         | avgt |  3  | 0.099 ± 0.030    | ms/op |
| HeapSortBenchmark | 10000        | avgt |  3  | 1.651 ± 0.052    | ms/op |
| HeapSortBenchmark | 100000       | avgt |  3  | 29.969 ± 0.943   | ms/op |

## Results
> As we can see, Qsort and MergeSort are the best ways to sort array with any amount of elements, because it is the fastest sort in every situation, except case in what we can already sorted array => we need to use insertionSort for O(n)
> HeapSort showed itself not bad, but if we have more that 100_000 elements, it will lose in speed to Qsort and MergeSort
> Also we can see, that Improved Insertion Sort gave us good results in comparison with clasic insertionSort. It's clearly seen in tests with big length (100_000 elements)
