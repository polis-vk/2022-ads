# Сводная таблица по сотрировкам

| dataLength | Insertion | Improved Insertion | Merge  | Quick  | Heap   |
|------------|-----------|--------------------|--------|--------|--------|
| 100        | 0,005     | 0,007              | 0,007  | 0,005  | 0,006  |
| 1000       | 0,317     | 0,104              | 0,092  | 0,079  | 0,092  |
| 10000      | 29,413    | 2,651              | 1,182  | 1,166  | 1,690  |
| 100000     | 9120,368  | 205,365            | 15,314 | 15,089 | 29,085 |

По результатам, приведенным в таблице выше, можно сказать,\
что хуже всего работает сортировка вставками без улучшений,\
показывая сильное отставание уже на массиве из 1000 элементов.\
Улучшенная сортировка вставками справляется значительно лучше,\
но все ёщё отставая от продвинутых сортировок начиная с 10_000\
элемнтов. Продвинутые сортировки на используемых массивах\
до 100_000 элементов показывают себя практически одинаково,\
начиная с 100_000 быстрая сортировка показывает себя чуть лучше,\
а пирамидальная сортировка начинает заметно оставать(в ~2 раза) 

## Сортировка вставками

### Benchmark SortBench.measureInsertionSort

Warmup: 3 iterations, 10 s each\
Measurement: 3 iterations, 10 s each\
Benchmark mode: Average time, time/op

| dataLength | result         | error    |
|------------|----------------|----------|
| 100        | 0,005 ms/op    | 0,001    |
| 1000       | 0,317 ms/op    | 0,313    |
| 10000      | 29,413 ms/op   | 1,137    |
| 100000     | 9120,368 ms/op | 7585,572 |

## Улучшенная сортировка вставками

### Benchmark SortBench.measureImprovedInsertionSort

Warmup: 3 iterations, 10 s each\
Measurement: 3 iterations, 10 s each\
Benchmark mode: Average time, time/op

| dataLength | result        | error  |
|------------|---------------|--------|
| 100        | 0,007 ms/op   | 0,003  |
| 1000       | 0,104 ms/op   | 0,010  |
| 10000      | 2,651 ms/op   | 0,573  |
| 100000     | 205,365 ms/op | 10,659 |

## Сортировка слиянием

### Benchmark SortBench.measureMergeSort

Warmup: 3 iterations, 10 s each\
Measurement: 3 iterations, 10 s each\
Benchmark mode: Average time, time/op

| dataLength | result       | error |
|------------|--------------|-------|
| 100        | 0,007 ms/op  | 0,001 |
| 1000       | 0,092 ms/op  | 0,004 |
| 10000      | 1,182 ms/op  | 0,145 |
| 100000     | 15,314 ms/op | 0,619 |

## Быстрая сортировка

### Benchmark SortBench.measureQuickSort

Warmup: 3 iterations, 10 s each\
Measurement: 3 iterations, 10 s each\
Benchmark mode: Average time, time/op

| dataLength | result       | error |
|------------|--------------|-------|
| 100        | 0,005 ms/op  | 0,001 |
| 1000       | 0,079 ms/op  | 0,013 |
| 10000      | 1,166 ms/op  | 0,220 |
| 100000     | 15,089 ms/op | 2,373 |

## Пирамидальная сортировка

### Benchmark SortBench.measureHeapSort

Warmup: 3 iterations, 10 s each\
Measurement: 3 iterations, 10 s each\
Benchmark mode: Average time, time/op

| dataLength | result       | error  |
|------------|--------------|--------|
| 100        | 0,006 ms/op  | 0,002  |
| 1000       | 0,092 ms/op  | 0,004  |
| 10000      | 1,690 ms/op  | 0,205  |
| 100000     | 29,085 ms/op | 10,798 |
