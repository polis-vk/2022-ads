# Домашняя работа номер 5, измерение времени работы различных соритровок.

## В этой домашней работе использовались следующие сортировки
##### 1. Сортировка вставками
##### 2. Сортировка вставками улучшенная версия
##### 3. Сортировка слиянием
##### 4. Сортировка кучей
##### 5. Быстрая сортировка

## Результаты работы после ожидания 25 минут в режиме runtime
```
  Benchmark                                 (dataLength) | Mode  Cnt      Score     Error  Units
  SampleBench.measureInsertionSort                   100   | avgt    3      0,003 ±   0,001  ms/op
  SampleBench.measureInsertionSort                  1000 | avgt    3      0,163 ±   0,052  ms/op
  SampleBench.measureInsertionSort                 10000 | avgt    3     15,778 ±   0,397  ms/op
  SampleBench.measureInsertionSort                100000 | avgt    3   1577,627 ±  77,725  ms/op
  SampleBench.measureInsertionSort                300000 | avgt    3  14359,544 ±  31,515  ms/op

  SampleBench.measureImprovedInsertionSort           100 | avgt    3      0,005 ±   0,001  ms/op
  SampleBench.measureImprovedInsertionSort          1000 | avgt    3      0,082 ±   0,047  ms/op
  SampleBench.measureImprovedInsertionSort         10000 | avgt    3      2,175 ±   0,108  ms/op
  SampleBench.measureImprovedInsertionSort        100000 | avgt    3    215,057 ±   3,597  ms/op
  SampleBench.measureImprovedInsertionSort        300000 | avgt    3   2299,280 ± 152,564  ms/op

  SampleBench.measureMergeSort                       100 | avgt    3      0,006 ±   0,001  ms/op
  SampleBench.measureMergeSort                      1000 | avgt    3      0,083 ±   0,005  ms/op
  SampleBench.measureMergeSort                     10000 | avgt    3      1,033 ±   0,051  ms/op
  SampleBench.measureMergeSort                    100000 | avgt    3     13,924 ±  23,024  ms/op
  SampleBench.measureMergeSort                    300000 | avgt    3     41,003 ±   1,544  ms/op

  SampleBench.measureHeapSort                        100 | avgt    3      0,004 ±   0,007  ms/op
  SampleBench.measureHeapSort                       1000 | avgt    3      0,057 ±   0,004  ms/op
  SampleBench.measureHeapSort                      10000 | avgt    3      0,776 ±   0,041  ms/op
  SampleBench.measureHeapSort                     100000 | avgt    3     10,122 ±   0,910  ms/op
  SampleBench.measureHeapSort                     300000 | avgt    3     36,824 ±  56,675  ms/op

  SampleBench.measureQuickSort                       100 | avgt    3      0,004 ±   0,002  ms/op
  SampleBench.measureQuickSort                      1000 | avgt    3      0,062 ±   0,049  ms/op
  SampleBench.measureQuickSort                     10000 | avgt    3      0,741 ±   0,174  ms/op
  SampleBench.measureQuickSort                    100000 | avgt    3      8,605 ±   0,292  ms/op
  SampleBench.measureQuickSort                    300000 | avgt    3     28,047 ±   0,537  ms/op
```
## Итоги

##### Подводя итог можно сказать следующее:
Быстрая сортировка ялвяется абсолютным лидером в забеге на большие дистанции - 10000, 100000 и 300000 были отсортированы за рекордное время 28,047 и 8,605 и 0,741 но учитывая погрешность возможно на 10000 она в среднем будет хуже сортировки кучей с результатом 0,776. На массиве размером всего в 1000 элементов уже точно лидирует сортировка кучей с резултатом 0,057 и наконец массив размером в 100 элементов отсортируется быстрее всего обычной сортировкой вставками. Хочу отметить что сортировка вставками в улучшенном виде все таки не смогла бороться с мастодонтами в виде быстрой сортировки и сортировки кучей но она показала невероятный результат по сравнению со своей обыкновенной версией на больших обьемах работы. 