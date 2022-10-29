# Отчёт по семинару

## Курс "Алгоритмы и структуры данных" - Образовательный центр VK в Политехе

* [Задача](#)
* [Результаты](#)
    * [Heap sort](#heap-sort)
    * [Insertion sort](#insertion-sort)
    * [Improved insertion sort](#improved-insertion-sort)
    * [Quick sort](#quick-sort)
    * [Merge sort](#merge-sort)
* [Итог](#)

## Задача

Написать JMH бенчмарки и сравнить время работы следующих алгоритмов:

* Обычная сортировка вставками
* "Улучшенная" сортировка вставками
* Сортировка слиянием
* Быстрая сортировка
* Пирамидальная сортировка

Посмотреть на время работы алгоритмов на различных по длине входных массивах
(например, 100, 1000, 10000, 100000, 1000000 элементов).
На основе полученных результатов сделать отчет в формате Markdown.
В отчёте среди прочего необходимо отметить, какие алгоритмы на каких размерах массивов ведут себя лучше других.
Таблички с результатами бенчмарков вставить в отчет в виде кода (а не скриншота).

## Результаты

> Перед каждым тестом 3 раза выполнялся "прогрев"

> Каждый тест прогонялся 3 раза и вычислял среднее арифметическое.

### Heap sort

| Benchmark                      | (dataLength) | Mode | Cnt | Score      | Error   | Units |
|--------------------------------|--------------|------|-----|------------|---------|-------|
| SortBenchmarks.measureHeapSort | 100          | avgt | 3   | 0,010 ±    | 0,002   | ms/op |
| SortBenchmarks.measureHeapSort | 1000         | avgt | 3   | 0,111 ±    | 0,003   | ms/op |
| SortBenchmarks.measureHeapSort | 10000        | avgt | 3   | 2,164 ±    | 0,122   | ms/op |
| SortBenchmarks.measureHeapSort | 100000       | avgt | 3   | 38,019 ±   | 1,632   | ms/op |
| SortBenchmarks.measureHeapSort | 1000000      | avgt | 3   | 1110,718 ± | 424,146 | ms/op |

### Insertion sort

Тест с 1000000 для данной сортировки превысил время ожидания.

| Benchmark                           | (dataLength) | Mode | Cnt | Score       | Error    | Units |
|-------------------------------------|--------------|------|-----|-------------|----------|-------|
| SortBenchmarks.measureInsertionSort | 100          | avgt | 3   | 0,010 ±     | 0,011    | ms/op |
| SortBenchmarks.measureInsertionSort | 1000         | avgt | 3   | 1,267 ±     | 0,341    | ms/op |
| SortBenchmarks.measureInsertionSort | 10000        | avgt | 3   | 74,563 ±    | 67,628   | ms/op |
| SortBenchmarks.measureInsertionSort | 100000       | avgt | 3   | 18323,005 ± | 8213,618 | ms/op |

### Improved insertion sort

| Benchmark                                   | (dataLength) | Mode | Cnt | Score       | Error      | Units |
|---------------------------------------------|--------------|------|-----|-------------|------------|-------|
| SortBenchmarks.measureImprovedInsertionSort | 100          | avgt | 3   | 0,011 ±     | 0,006      | ms/op |
| SortBenchmarks.measureImprovedInsertionSort | 1000         | avgt | 3   | 0,148 ±     | 0,039      | ms/op |
| SortBenchmarks.measureImprovedInsertionSort | 10000        | avgt | 3   | 4,845 ±     | 1,523      | ms/op |
| SortBenchmarks.measureImprovedInsertionSort | 100000       | avgt | 3   | 451,493 ±   | 92,559     | ms/op |
| SortBenchmarks.measureImprovedInsertionSort | 1000000      | avgt | 3   | 51696,326 ± | 189392,704 | ms/op |

### Quick sort

| Benchmark                       | (dataLength) | Mode | Cnt | Score     | Error    | Units |
|---------------------------------|--------------|------|-----|-----------|----------|-------|
| SortBenchmarks.measureQuickSort | 100          | avgt | 3   | 0,010 ±   | 0,010    | ms/op |
| SortBenchmarks.measureQuickSort | 1000         | avgt | 3   | 0,093 ±   | 0,007    | ms/op |
| SortBenchmarks.measureQuickSort | 10000        | avgt | 3   | 1,280 ±   | 0,094    | ms/op |
| SortBenchmarks.measureQuickSort | 100000       | avgt | 3   | 15,380 ±  | 0,823    | ms/op |
| SortBenchmarks.measureQuickSort | 1000000      | avgt | 3   | 511,788 ± | 1025,691 | ms/op |

### Merge sort

| Benchmark                       | (dataLength) | Mode | Cnt | Score     | Error    | Units |
|---------------------------------|--------------|------|-----|-----------|----------|-------|
| SortBenchmarks.measureMergeSort | 100          | avgt | 3   | 0,013 ±   | 0,005    | ms/op |
| SortBenchmarks.measureMergeSort | 1000         | avgt | 3   | 0,134 ±   | 0,001    | ms/op |
| SortBenchmarks.measureMergeSort | 10000        | avgt | 3   | 1,613 ±   | 0,160    | ms/op |
| SortBenchmarks.measureMergeSort | 100000       | avgt | 3   | 20,248 ±  | 1,925    | ms/op |
| SortBenchmarks.measureMergeSort | 1000000      | avgt | 3   | 654,019 ± | 1309,933 | ms/op |

## Итог

Исходя из полученных результатов, видно, что с большим количеством данных лучше всех справилась быстрая
сортировка `Quick sort`. Самой эффективной для малого количества данных стала пирамидальная
сортировка `Heap sort` стоит отметить, что число `Error` у этого алгоритма тоже заметно меньше.
