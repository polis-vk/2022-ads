# JMH Benchmark. Измерение времени работы сортировок.

## Протестированные сортировки
- обычная сортировка вставками
- "улучшенная" сортировка вставками
- сортировка слиянием
- быстрая сортировка
- пирамидальная сортировка

## Результаты

``` 
Benchmark                           (arraySize)  Mode  Cnt      Score      Error  Units
SampleBench.measureInsertionSort            100  avgt    3      0,002     0,001  ms/op
SampleBench.measureInsertionSort           1000  avgt    3      0,155     0,011  ms/op
SampleBench.measureInsertionSort          10000  avgt    3     14,711     0,515  ms/op
SampleBench.measureInsertionSort         100000  avgt    3   1444,719    13,574  ms/op
SampleBench.measureInsertionSort         300000  avgt    3  13093,272  1448,398  ms/op

SampleBench.measureImprovedInsSort          100  avgt    3      0,004     0,001  ms/op
SampleBench.measureImprovedInsSort         1000  avgt    3      0,070     0,011  ms/op
SampleBench.measureImprovedInsSort        10000  avgt    3      2,210     0,380  ms/op
SampleBench.measureImprovedInsSort       100000  avgt    3    183,507    46,948  ms/op
SampleBench.measureImprovedInsSort       300000  avgt    3   1706,198   928,592  ms/op

SampleBench.measureMergeSort                100  avgt    3      0,005     0,001  ms/op
SampleBench.measureMergeSort               1000  avgt    3      0,066     0,012  ms/op
SampleBench.measureMergeSort              10000  avgt    3      0,857     0,221  ms/op
SampleBench.measureMergeSort             100000  avgt    3     10,231     0,668  ms/op
SampleBench.measureMergeSort             300000  avgt    3     33,374     2,041  ms/op

SampleBench.measureQuickSort                100  avgt    3      0,004     0,001  ms/op
SampleBench.measureQuickSort               1000  avgt    3      0,049     0,002  ms/op
SampleBench.measureQuickSort              10000  avgt    3      0,609     0,051  ms/op
SampleBench.measureQuickSort             100000  avgt    3      7,315     0,714  ms/op
SampleBench.measureQuickSort             300000  avgt    3     24,860     4,049  ms/op

SampleBench.measureHeapSort                 100  avgt    3      0,003     0,001  ms/op
SampleBench.measureHeapSort                1000  avgt    3      0,047     0,004  ms/op
SampleBench.measureHeapSort               10000  avgt    3      0,597     0,110  ms/op
SampleBench.measureHeapSort              100000  avgt    3      7,527     1,774  ms/op
SampleBench.measureHeapSort              300000  avgt    3     28,817     1,323  ms/op
```

### Сравнение сортировок

Ниже представлено соотношение размер массива - наиболее быстрая сортировка:
| Размер массива | Тип сортировки |
| ------ | ------ |
| 100 | обычная сортировка вставками |
| 1000 | пирамидальная сортировка |
| 10'000 | пирамидальная сортировка |
| 100'000 | быстрая сортировка |
| 300'000 | быстрая сортировка |



