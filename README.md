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

Для каждого нового домашнего задания заводим новую ветку в своем репозитории.
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
Решение каждой задачи - отдельный Java-класс. Можно воспользоваться классом `company.vk.polis.ads.homework.SolveTemplate`, в котором остается реализовать лишь метод `solve`.

* informatics-msk: добавлять ничего не нужно, статус решения отображается в общих результатах.
* E-olymp: В самом PR либо в его описании, либо в комментариях к каждому классу-решению нужно добавить ссылку на submission в e-olymp, где видно, что все решение прошло все тесты.
  Эти ссылки имеют вид "https://www.e-olymp.com/ru/submissions/5707028".

Все обсуждения решения происходят в рамках комментариев к PR
(в противном случае мы зафлудим общий чатик и запутаемся окончательно :))

### Решения задач с локальными тестами

Прогон тестов будет осуществляться системами [continuous integration](https://en.wikipedia.org/wiki/Continuous_integration),
например, [Github Actions](https://docs.github.com/en/actions).
Тесты в этих системах будут исполняться при созданни PR и при добавлении новых коммитов.
В итоге у PR должна появиться зеленая галочка, говорящая об успешном прохождении тестов.

## ДЗ 1. Дедлайн 27.09.2022 18:29:59

Задачи с informatics-msk: https://informatics.msk.ru/mod/statements/view.php?id=75096#1

В задачах, где требуется реализовать структуру данных (стек, дек или очередь), использовать готовые реализации из Java нельзя. 

В "олимпиадных" задачах можно использовать стандартные реализации `java.util.Queue` какие найдете, `java.util.Stack` использовать нельзя.
