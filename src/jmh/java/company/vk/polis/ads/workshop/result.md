| **Benchmark**                       | **(dataLength)**   | **Mode**   | **Cnt**   | **Score**     | **Error**    | **Units**   |
|-------------------------------------|--------------------|------------|-----------|---------------|--------------|-------------|
| SampleBench.heapSort                | 100                | avgt       | 3         | 0,003 ±       | 0,001        | ms/op       |
| SampleBench.heapSort                | 1000               | avgt       | 3         | 0,065 ±       | 0,005        | ms/op       |
| SampleBench.heapSort                | 10000              | avgt       | 3         | 0,911 ±       | 0,080        | ms/op       |
| SampleBench.heapSort                | 100000             | avgt       | 3         | 11,099 ±      | 6,067        | ms/op       |
| SampleBench.heapSort                | 200000             | avgt       | 3         | 42,701 ±      | 36,599       | ms/op       |
| ----------------------------------- | ------------------ | ---------- | --------- | ------------- | ------------ | ----------- |
| SampleBench.improvedInsertionSort   | 100                | avgt       | 3         | ≈ 10⁻⁴        |              | ms/op       |
| SampleBench.improvedInsertionSort   | 1000               | avgt       | 3         | 0,001 ±       | 0,001        | ms/op       |
| SampleBench.improvedInsertionSort   | 10000              | avgt       | 3         | 0,020 ±       | 0,001        | ms/op       |
| SampleBench.improvedInsertionSort   | 100000             | avgt       | 3         | 0,155 ±       | 0,001        | ms/op       |
| SampleBench.improvedInsertionSort   | 200000             | avgt       | 3         | 0,603 ±       | 0,299        | ms/op       |
|                                     |                    |            |           |               |              |             |
| SampleBench.insertionSort           | 100                | avgt       | 3         | ≈ 10⁻³        |              | ms/op       |
| SampleBench.insertionSort           | 1000               | avgt       | 3         | 0,005 ±       | 0,040        | ms/op       |
| SampleBench.insertionSort           | 10000              | avgt       | 3         | 0,048 ±       | 0,374        | ms/op       |
| SampleBench.insertionSort           | 100000             | avgt       | 3         | 13275,788 ±   | 47670,505    | ms/op       |
| SampleBench.insertionSort           | 200000             | avgt       | 3         | 55206,860 ±   | 101312,699   | ms/op       |
|                                     |                    |            |           |               |              |             |
| SampleBench.mergeSort               | 100                | avgt       | 3         | 0,013 ±       | 0,001        | ms/op       |
| SampleBench.mergeSort               | 1000               | avgt       | 3         | 1,051 ±       | 0,024        | ms/op       |
| SampleBench.mergeSort               | 10000              | avgt       | 3         | 94,583 ±      | 3,931        | ms/op       |
| SampleBench.mergeSort               | 100000             | avgt       | 3         | 10118,385 ±   | 788,408      | ms/op       |
| SampleBench.mergeSort               | 200000             | avgt       | 3         | 89855,522 ±   | 125079,271   | ms/op       |
|                                     |                    |            |           |               |              |             |
| SampleBench.quickSort               | 100                | avgt       | 3         | 0,003 ±       | 0,001        | ms/op       |
| SampleBench.quickSort               | 1000               | avgt       | 3         | 0,054 ±       | 0,160        | ms/op       |
| SampleBench.quickSort               | 10000              | avgt       | 3         | 0,616 ±       | 0,047        | ms/op       |
| SampleBench.quickSort               | 100000             | avgt       | 3         | 9,767 ±       | 19,560       | ms/op       |

##Отчёт работы на массиве 200к элементов
Наилучшей по времени оказалась "улучшенная сортировка слиянием" (независимо от длины массива)
На втором месте quickSort, на объёме 100000 элементов время < 10 ms/op
Далее heapSort с результатом 42,701 ms/op для массива на 200000 и 11,099 (для 100к), что чуть хуже qSort
Хуже всего себя проявила обычная сортировка вставками и mergeSort
(что особенно заметно на массиве 200000)



| **Benchmark**                     | **(dataLength)** | **Mode** | **Cnt** | **Score**       | **Error**  | **Units** |
|-----------------------------------|------------------|----------|---------|-----------------|------------|-----------|
| SampleBench.heapSort              | 1M               | avgt     | 3       | 307,132 ±       | 1046,585   | ms/op     |
| SampleBench.improvedInsertionSort | 1M               | avgt     | 3       | 24772,138 ±     | 68057,694  | ms/op     |
| SampleBench.mergeSort             | 1M               | avgt     | 3       | 2_173_535,242 ± | 797912,357 | ms/op     |
| SampleBench.quickSort             | 1M               | avgt     | 3       | 347,137 ±       | 87,771     | ms/op     |


| **Benchmark**                     | **(dataLength)** | **Mode** | **Cnt** | **Score**   | **Error**  | **Units** |
|-----------------------------------|------------------|----------|---------|-------------|------------|-----------|
| SampleBench.heapSort              | 200k             | avgt     | 3       | 42,701 ±    | 36,599     | ms/op     |
| SampleBench.improvedInsertionSort | 200k             | avgt     | 3       | 0,603 ±     | 0,299      | ms/op     |
| SampleBench.mergeSort             | 200k             | avgt     | 3       | 89855,522 ± | 125079,271 | ms/op     |
| SampleBench.quickSort             | 200k             | avgt     | 3       | 9,767 ±     | 19,560     | ms/op     |

##Отчет работы на массиве 1M элементов
При увеличении объёма массива в 5 раз (200к -> 1M) можно заметить, что время работы изменилось следующим образом:
`heapsort: ` 42,701 -> 307,132 (разница в 7.3 раза). Погрешность стала превосходить результат.
`improvedInsertionSort: ` 0,603 -> 24772,138 (разница в 41_081 раз). Видно аномальное уменьшение скорости работы.
Погрешность стала превосходить результат.
`mergeSort: ` 89855,522 -> 2_173_535,242 (разница в 24 раза). Погрешность, напротив, стала
меньше результата.
`quickSort: ` 9,767 -> 347,137 (разница в 36 раз). Погрешность, напротив, стала
меньше результата.

Итак, при увеличении массива наилучшим образом показывает себя `heapsort`, так как увеличение массива примерно
соответствует увеличению времени работы. `MergeSort и QuickSort` также показывают относительно неплохой результат.
У `improvedInsertionSort` аномально замедляется скорость работы, однако, даже так она остается не худшим выбором, 
так как работает быстрее mergeSort.





