# **Measurement of sorting operation time**

## InsertionSort

```
Benchmark                               (arrLength)  Mode  Cnt      Score       Error  Units
InsertionSortBench.measureSort                  100  avgt    3      0,007 �     0,001  ms/op
InsertionSortBench.measureSort                 1000  avgt    3      0,942 �     5,292  ms/op
InsertionSortBench.measureSort                10000  avgt    3    135,436 �    58,476  ms/op
InsertionSortBench.measureSort               100000  avgt    3  23027,393 � 21033,921  ms/op
```
## InsertionSort(Improved)
> The improvement is to find the desired index using binary search
> and insert the element via *System.copy*

```
Benchmark                               (arrLength)  Mode  Cnt      Score        Error  Units
ImprovedInsertionSortBench.measureSort          100  avgt    3      0,005 �      0,001  ms/op
ImprovedInsertionSortBench.measureSort         1000  avgt    3      0,087 �      0,001  ms/op
ImprovedInsertionSortBench.measureSort        10000  avgt    3      1,676 �      0,153  ms/op
ImprovedInsertionSortBench.measureSort       100000  avgt    3    505,010 �     12,418  ms/op
ImprovedInsertionSortBench.measureSort      1000000  avgt    3  40196,442 � 316403,687  ms/op
```

## MergeSort

```
Benchmark                   (arrLength)  Mode  Cnt    Score      Error  Units
MergeSortBench.measureSort          100  avgt    3    0,007 �    0,007  ms/op
MergeSortBench.measureSort         1000  avgt    3    0,111 �    0,040  ms/op
MergeSortBench.measureSort        10000  avgt    3    1,386 �    0,456  ms/op
MergeSortBench.measureSort       100000  avgt    3   20,651 �   62,988  ms/op
MergeSortBench.measureSort      1000000  avgt    3  872,065 � 1554,254  ms/op
```

## QuickSort

```
Benchmark                   (arrLength)  Mode  Cnt    Score     Error  Units
QuickSortBench.measureSort          100  avgt    3    0,005 �   0,004  ms/op
QuickSortBench.measureSort         1000  avgt    3    0,144 �   1,892  ms/op
QuickSortBench.measureSort        10000  avgt    3    1,083 �   0,101  ms/op
QuickSortBench.measureSort       100000  avgt    3   16,567 �   6,449  ms/op
QuickSortBench.measureSort      1000000  avgt    3  350,473 � 328,981  ms/op
```

## HeapSort

```
Benchmark                  (arrLength)  Mode  Cnt    Score     Error  Units
HeapSortBench.measureSort          100  avgt    3    0,007 �   0,001  ms/op
HeapSortBench.measureSort         1000  avgt    3    0,121 �   0,034  ms/op
HeapSortBench.measureSort        10000  avgt    3    3,043 �  44,182  ms/op
HeapSortBench.measureSort       100000  avgt    3   25,316 �  10,347  ms/op
HeapSortBench.measureSort      1000000  avgt    3  766,703 � 166,998  ms/op

```

Из полученных данных можно сделать вывод, что при увелечении количества элементов, самая эффективная сортировка - QuickSort
Сортировки, работающие за O(nlogn) до 10000 элементов работают приблизительно одинаково(данные каждый раз генерировали рандомно
, мог попасться плохой вариант для той или иной сортировки, при котором она деградировала).