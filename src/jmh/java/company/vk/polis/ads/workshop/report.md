## Бенчмарки
|          Sort         |   Size  | Mode | Cnt |    Score    |   Error   | Units |
|:---------------------:|:-------:|:----:|:---:|:-----------:|:---------:|:-----:|
|        HeapSort       |   100   | avgt |  3  |   0,006 �   |   0,001   | ms/op |
|        HeapSort       |   1000  | avgt |  3  |   0,099 �   |   0,005   | ms/op |
|        HeapSort       |  10000  | avgt |  3  |   1,479 �   |   0,391   | ms/op |
|        HeapSort       |  100000 | avgt |  3  |   28,888 �  |   33,945  | ms/op |
|        HeapSort       | 1000000 | avgt |  3  |  778,582 �  |  788,162  | ms/op |
|     InsertionSort     |   100   | avgt |  3  |   0,006 �   |   0,003   | ms/op |
|     InsertionSort     |   1000  | avgt |  3  |   0,695 �   |   0,731   | ms/op |
|     InsertionSort     |  10000  | avgt |  3  |   75,108 �  |   10,015  | ms/op |
|     InsertionSort     |  100000 | avgt |  3  | 10307,465 � |  4943,785 | ms/op |
| ImprovedInsertionSort |   100   | avgt |  3  |   0,007 �   |   0,001   | ms/op |
| ImprovedInsertionSort |   1000  | avgt |  3  |   0,112 �   |   0,064   | ms/op |
| ImprovedInsertionSort |  10000  | avgt |  3  |   2,878 �   |   0,483   | ms/op |
| ImprovedInsertionSort |  100000 | avgt |  3  |  227,195 �  |   46,855  | ms/op |
| ImprovedInsertionSort | 1000000 | avgt |  3  | 37171,598 � | 38044,893 | ms/op |
|       MergeSort       |   100   | avgt |  3  |   0,013 �   |   0,005   | ms/op |
|       MergeSort       |   1000  | avgt |  3  |   0,160 �   |   0,263   | ms/op |
|       MergeSort       |  10000  | avgt |  3  |   1,883 �   |   0,476   | ms/op |
|       MergeSort       |  100000 | avgt |  3  |   23,401 �  |   2,715   | ms/op |
|       MergeSort       | 1000000 | avgt |  3  |  424,451 �  |  105,435  | ms/op |
|       QuickSort       |   100   | avgt |  3  |   0,007 �   |   0,001   | ms/op |
|       QuickSort       |   1000  | avgt |  3  |   0,093 �   |   0,007   | ms/op |
|       QuickSort       |  10000  | avgt |  3  |   1,312 �   |   0,219   | ms/op |
|       QuickSort       |  100000 | avgt |  3  |   19,441 �  |   9,232   | ms/op |
|       QuickSort       | 1000000 | avgt |  3  |  385,516 �  |  126,852  | ms/op |
## Анализ
На больших объемах данных лучше всего показал себя QuickSort. При небольших же объемах данных (100 - 1000) все сортировки работали примерно одинаково.<br>
Хуже всего показала себя сортировка вставками без модификации, для нее получить результаты на выборке из 1000000 элементов за адекватное время не получилось.