##Бенчмарки сортировок

###_Heapsort:_
size | mode | cnt | score | error | units
:----|:----:|:---:|:-----:|:----:|-----:
100 | arg | 3 | 0,005 | 0,001 | ms/op
1000 | arg | 3 | 0,082 | 0,006 | ms/op
10000 | arg | 3 | 1,327 | 0,082 | ms/op
100000 | arg | 3 | 19,168 | 1,571 | ms/op

###_ImprovedInsertionSort:_
size | mode | cnt | score | error | units
:----|:----:|:---:|:-----:|:----:|-----:
100 | arg | 3 | 0,006 | 0,001 | ms/op
1000 | arg | 3 | 0,097 | 0,011 | ms/op
10000 | arg | 3 | 1,912 | 0,312 | ms/op
100000 | arg | 3 | 156,703 | 3,933 | ms/op

###_InsertionSort:_
size | mode | cnt | score | error | units
:----|:----:|:---:|:-----:|:----:|-----:
100 | arg | 3 | 0,004 | 0,001 | ms/op
1000 | arg | 3 | 0,508 | 1,883 | ms/op
10000 | arg | 3 | 62,974 | 48,144 | ms/op
100000 | arg | 3 | 6437,592 | 3912,594 | ms/op

###_MergeSort:_
size | mode | cnt | score | error | units
:----|:----:|:---:|:-----:|:----:|-----:
100 | arg | 3 | 0,082 | 2,262 | ms/op
1000 | arg | 3 | 0,126 | 0,070 | ms/op
10000 | arg | 3 | 1,715 | 0,329 | ms/op
100000 | arg | 3 | 21,367 | 5,706 | ms/op

###_QuickSort:_
size | mode | cnt | score | error | units
:----|:----:|:---:|:-----:|:----:|-----:
100 | arg | 3 | 0,006 | 0,003 | ms/op
1000 | arg | 3 | 0,087 | 0,004 | ms/op
10000 | arg | 3 | 4,880 | 25,777 | ms/op
100000 | arg | 3 | 32,853 | 89,904 | ms/op

---

##Вывод:
При 100 все сортировки показывают себя примерно одинаково. Из них самая быстрая InsertionSort. При 1000 QuickSort и HeapSort оказываются самыми быстрыми. При 10000 и 10000 лидируют HeapSort и MergeSort, а самая медленная InsertionSort. Расчёты для 1000000 проводить не стал, так как для InsertionSort они будут очень долгими.
