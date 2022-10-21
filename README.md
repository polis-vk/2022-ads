# Алгоритмы и структуры данных

Задачи для курса "Алгоритмы и структуры данных" 2022 года [курса](https://polis.vk.company/curriculum/program/discipline/1440) в [Образовательном центре VK в Политехе](https://polis.vk.company)

## Fork
[Форкните проект](https://docs.github.com/en/get-started/quickstart/fork-a-repo), склонируйте и добавьте `upstream`:
```bash
$ git clone git@github.com:<username>/2022-ads.git
Cloning into '2022-ads'...
...
$ git remote add upstream git@github.com:polis-vk/2022-ads.git
$ git fetch upstream
From github.com:polis-vk/2022-ads
 * [new branch]      main     -> upstream/main
```

## Схема работы
В общем случае часть задач будет с [infromatics-msk](https://informatics.msk.ru/course/view.php?id=4979) и [e-olymp](https://www.e-olymp.com), и проверяться будет средствами этих систем.
Также возможны и задачи, тесты на которые будут оформлены в нашем репозитории. Либо могут быть ДЗ, где тесты к своим решениям будет нужно написать самостоятельно.

### Решения задач в тестирующей системе
Первым делом регистрируемся на [informatics-msk](https://informatics.msk.ru) и [e-olymp](https://www.e-olymp.com), на informatics-msk записываемся на [курс](https://informatics.msk.ru/course/view.php?id=4979)

Для каждого нового домашнего задания заводим новую ветку в своем репозитории. Ветка должна ответвляться от свежей `main` ветки в `upstream`. Иными словами, в ветке, относящейся к конкретному домашнему заданию, не должно быть решений задач из других дз: это облегчит их проверку.
Например, домашнему заданию после первой лекции будет соответствовать ветка `part1`.
Создаем ее в локальном репозитории
```bash
$ git checkout -b part1
``` 
* Исходники решений добавляются в java-пакет `company.vk.polis.ads.<partX>.<username>`, где `username` - логин на Github в случае e-olimp или тестов в репозитории.
* В случае с informatics решения добавляются в корень проекта, так как тестирующая система требует класс без указания пакета.

После того, как решения будут доведены до рабочего состояния (все тесты будут проходить),
можно коммитить, пушить и создавать pull request в `polis-vk/2022-ads`.
В самом PR либо в его описании, либо в комментариях к каждому классу-решению
Решение каждой задачи - отдельный Java-класс. Можно воспользоваться классом `company.vk.polis.ads.SolveTemplate`, в котором остается реализовать лишь метод `solve`.

* informatics-msk: добавлять ничего не нужно, статус решения отображается в общих результатах.
* E-olymp: В самом PR в его описании нужно добавить ссылку на submission в e-olymp, где видно, что решение прошло все тесты.
  Эти ссылки имеют вид "https://www.e-olymp.com/ru/submissions/5707028".

Все обсуждения решения происходят в рамках комментариев к PR
(в противном случае мы зафлудим общий чатик и запутаемся окончательно :))

### Решения задач с локальными тестами

Прогон тестов будет осуществляться системами [continuous integration](https://en.wikipedia.org/wiki/Continuous_integration),
например, [Github Actions](https://docs.github.com/en/actions).
Тесты в этих системах будут исполняться при созданни PR и при добавлении новых коммитов.
В итоге у PR должна появиться зеленая галочка, говорящая об успешном прохождении тестов.

## ДЗ 1. Стек, очередь, дек. Дедлайн 27.09.2022 18:29:59

Задачи с [informatics-msk](https://informatics.msk.ru/mod/statements/view.php?id=75096#1)

В задачах, где требуется реализовать структуру данных (стек, дек или очередь), использовать готовые реализации из Java нельзя. 

В "олимпиадных" задачах можно использовать стандартные реализации `java.util.Queue` какие найдете, `java.util.Stack` использовать нельзя.

## ДЗ 2. Сортировки. Дедлайн 04.10.2022 18:29:59

Задачи с [informatics-msk](https://informatics.msk.ru/mod/statements/view.php?id=75378#1)

Во всех задачах запрещается использовать готовые реализации алгоритмов из JDK.

Задачу C необходимо решить с помощью сортировки слиянием, при этом функция сортировки должна либо принимать в качестве аргумента `Comparator` помимо массива, либо принимать массив элементов, реализующих интерфейс `Comparable`.

В задаче D необходимо написать быструю сортировку.

## ДЗ 3. Куча. Дедлайн 11.10.2022 18:29:59

‼️ Ветка с решением должна ответвляться от коммита [b759305df2f671fba410b8bd21d40d8eeb6d2701](https://github.com/polis-vk/2022-ads/commit/b759305df2f671fba410b8bd21d40d8eeb6d2701):

```bash
$ git fetch upstream
$ git checkout -b <my-branch-for-part3> b759305df2f671fba410b8bd21d40d8eeb6d2701
```

Задачки с e-olymp:

* https://www.e-olymp.com/ru/problems/4039 - Хипуй
* https://www.e-olymp.com/ru/problems/4074 - Найти медиану 2
* https://www.e-olymp.com/ru/problems/3738 - Простая сортирока - реализовать HeapSort

* Реализовать методы `hasNext()` и `next()` в классе `company.vk.polis.ads.heap.MergeIterator` так, чтобы успешно выполнялись тесты в `company.vk.polis.ads.heap.MergeIteratorTest`. Время работы должно быть O(n logk), где n - суммарное количество всех элементов в k подаваемых на вход итераторах.

* Реализовать метод `company.vk.polis.ads.heap.TopK#topK`, который должен возвращать список из k максимальных элементов, отсортированных по убыванию, так, чтобы выполнялись тесты в `company.vk.polis.ads.heap.TopKTest`.

Локально тесты можно запустить с помощью `./gradlew test`.

## ДЗ 4. Динамическое программирование. Дедлайн 18.10.2022 18:29:59

Задачки с e-olymp:

* https://www.e-olymp.com/ru/problems/1087 - Скобочная последовательность
* https://www.e-olymp.com/ru/problems/15 - Мышки и зернышки
* https://www.e-olymp.com/ru/problems/1618 - Наибольшая общая подпоследовательность
* https://www.e-olymp.com/ru/problems/262 - Лесенка
* https://www.e-olymp.com/ru/problems/4261 - Количество инверсий

## Семинар. Дедлайн 01.11.2022 18:29:59

* Реализовать в `company.vk.polis.ads.workshop.CircularBufferQueue` методы, чтобы выполнялись все тесты в `company.vk.polis.ads.workshop.CircularBufferQueueTest`

* Реализовать в `company.vk.polis.ads.workshop.ImprovedInsertionSort` алгоритм "улучшенной" сортировки вставками, в котором позиция вставки находится с помощью бинарного поиска, а сдвиг элементов выполняется не по одному, а с помощью `java.lang.System#arraycopy`. Решение должно проходить тесты в `company.vk.polis.ads.workshop.ImprovedInsertionSortTest`.

* Написать [JMH](https://github.com/openjdk/jmh) бенчмарки и сравнить время работы следующих алгоритмов:
  1) обычная сортировка вставками
  2) "улучшенная" сортировка вставками
  3) сортировка слиянием
  4) быстрая сортировка
  5) пирамидальная сортировка

  Посмотреть на время работы алгоритмов на различных по длине входных массивах (например, 100, 1000, 10_000, 100_000, 1_000_000 элементов). На основе полученных результатов сделать отчет в формате [Markdown](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax). В отчёте среди прочего необходимо отметить, какие алгоритмы на каких размерах массивов ведут себя лучше других. Таблички с результатами бенчмарков вставить в отчет в виде кода (а не скриншота)

* Решить задачку "Верёвочки" на eolymp - https://www.eolymp.com/ru/problems/3967

* Реализовать поиск максимального подмассива в классе `company.vk.polis.ads.workshop.MaxSubarray` так, чтобы успешно выполнялись тесты в `company.vk.polis.ads.workshop.MaxSubarrayTest`. Время работы решения должно быть не хуже O(n log(n))
