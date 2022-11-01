Benchmark                        (dataLength)  Mode  Cnt     Score     Error  Units
SortBench.heapSort                        100  avgt    3     0,006 �   0,001  ms/op
SortBench.heapSort                       1000  avgt    3     0,088 �   0,033  ms/op
SortBench.heapSort                      10000  avgt    3     1,068 �   0,140  ms/op
SortBench.heapSort                     100000  avgt    3    22,490 �   3,158  ms/op

SortBench.improvedInsertionSort           100  avgt    3     0,013 �   0,004  ms/op
SortBench.improvedInsertionSort          1000  avgt    3     0,171 �   0,121  ms/op
SortBench.improvedInsertionSort         10000  avgt    3     3,779 �   0,127  ms/op
SortBench.improvedInsertionSort        100000  avgt    3   212,078 �  18,734  ms/op
SortBench.improvedInsertionSort       1000000  avgt    3  28422,497 � 19183,118  ms/op

SortBench.insertionSort                   100  avgt    3     0,011 �   0,001  ms/op
SortBench.insertionSort                  1000  avgt    3     0,218 �   0,012  ms/op
SortBench.insertionSort                 10000  avgt    3    21,615 �   1,056  ms/op
SortBench.insertionSort                100000  avgt    3  3048,253 � 325,223  ms/op
SortBench.heapSort                    1000000  avgt    3   1233,602 �   372,960  ms/op

SortBench.mergeSort                       100  avgt    3     0,027 �   0,004  ms/op
SortBench.mergeSort                      1000  avgt    3     0,295 �   0,144  ms/op
SortBench.mergeSort                     10000  avgt    3     3,501 �   2,017  ms/op
SortBench.mergeSort                    100000  avgt    3    39,693 �  13,276  ms/op
SortBench.mergeSort                   1000000  avgt    3    678,739 �   217,533  ms/op

SortBench.quickSort                       100  avgt    3     0,005 �   0,001  ms/op
SortBench.quickSort                      1000  avgt    3     0,070 �   0,046  ms/op
SortBench.quickSort                     10000  avgt    3     1,036 �   1,212  ms/op
SortBench.quickSort                    100000  avgt    3    17,280 �  16,160  ms/op
SortBench.quickSort                   1000000  avgt    3    450,359 �    70,167  ms/op


В ходе работы можно увидеть, что быстрая сортировка отлично работает на больших длиннах массива. 
На самых минимальных значениях быстро работают сортировки: пирамидальная и быстрая.
Исходя и результатов видно, что сортировка вставками и пирамидальная работает медленно на высоких длиннах.