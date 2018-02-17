# JAVAEE-Example
Simple example of Java EE application
1. Общее описание
Система представляет собой многопользовательское веб-приложение, которое позволяет пользователям заносить в базу данных набор произвольных параметров, каждый из которых имеет вид "имя_данных" - "значение". Также пользователь может запрашивать из базы данных список всех данных и данные по имени. �?мя данных представляет собой непустую строку длиной до 255 символов. Значение представляет собой целое число типа int.

2. Реализация
2.1. Реализация приложения выполняется в 3 этапа.
2.2. Первый этап включает реализацию на основе технологии сервлетов классов со следующей функциональностью:
2.2.1. Класс сервлета Registrator обеспечивает ввод одного параметра, который включает:
2.2.1.1. имя параметра - непустая строка длиной до 255 символов
2.2.1.2. значение параметра - целое число
2.2.1.3. класс показывает пользователю результат выполнения операции ввода:
2.2.1.3.1. введённый параметр добавлен как новый
2.2.1.3.2. введённый параметр заменил ранее введённый параметр в случае, когда параметр с таким именем уже присутствует в системе
2.2.1.3.3. введён неправильный параметр с указанием типа ошибки:
2.2.1.3.3.1. имя параметра пустое или превышает длину 255 символов
2.2.1.3.3.2. значение параметра не может быть корректно преобразовано к типу int
2.2.1.4. класс позволяет пользователю вызвать форму для просмотра введённых параметров (класс ViewList, см.п.2.2.2.)
2.2.2. Класс сервлета ViewList обеспечивает показ пользователю имён и значений введённых параметров:
2.2.2.1. всех параметров (по умолчанию) 
2.2.2.2. всех параметров, удовлетворяющих заданному пользователем шаблону имени
2.2.2.3. всех параметров, значение которых укладывается в заданный пользователем интервал
2.2.2.4. класс позволяет пользователю вызвать форму для ввода новых параметров (класс Registrator, см.п.2.2.1.)
2.2.3. Класс Attribute обеспечивает хранение введённых пользователем параметров в виде коллекции объектов типа Parameter (см.п.2.2.4)
2.2.3.1. класс должен быть доступен всем сервлетам приложения
2.2.3.2. класс обеспечивает выполнение запросов от класса ViewList, определённых в п.2.2.2.1 - 2.2.2.3. 
2.2.4. Класс Parameter представляет отдельный параметр, который содержит
2.2.4.1. имя параметра - непустая строка длиной до 255 символов
2.2.4.2. значение параметра - представляет собой целое число типа int
2.2.4.3. класс обеспечивает
2.2.4.3.1. задание начальных значений имени и значения параметра
2.2.4.3.2. замену значения параметра с конвертацией из типа String
2.2.4.3.3. получение имени параметра
2.2.4.3.4. получение значения параметра
2.2.4.3.5. конвертацию имени и значения параметра в строку вида "<li>имя_параметра - значение_параметра</li>"

2.3. На  втором этапе в приложение вводится класс DbManager, обеспечивающий взаимодействие с базой данных на основе технологии JPA
2.3.1.Данный класс должен представлять собой атрибут приложения, реализущий шаблон проектирования "Singleton"
2.3.2.Данный класс обеспечивает выполнение следующих функций
2.3.2.1. установку и поддержание соединения с базой данных
2.3.2.2. управление объектами вида Entity
2.3.2.3. непосредственное выполнение запросов от классов UpdateBean и SelectBean

2.3. На третьем этапе приложение расширяется за счёт подключения двух сессионных бинов со следующей функциональностью
2.3.1. Класс UpdateBean обеспечивает обработку запросов на изменение данных:
2.3.1.1. занесение нового параметра в базу данных
2.3.1.2. изменение существующего параметра в базе данных
2.3.1.3. удаление существующего параметра из базы данных
2.3.1.4. класс должен обеспечить корректировку введённых пользователем значений и проверку допустимости значений полей перед занесением их в базу данных, а именно:
2.3.1.4.1. из поля имени параметра удаляются пробельными символами в его начале и в конце
2.3.1.4.1.1. к пробельным символам относятся пробел, символ табуляции, символы начала новой строки и перевода каретки
2.3.1.4.2. поле имени параметра должно содержать не менее одного непробельного символа
2.3.1.4.3. длина поля имени параметра не должна превышать 32 символов после удаления начальных и конечных пробельных символов 
2.3.1.4.4. поле значения параметра может быть корректно преобразовано в значение типа int 
2.3.2. Класс SelectBean обеспечивает выполнение запросов от класса ViewList к базе данных на получение выборки
2.3.2.1. Класс должен обрабатывать следующие виды запросов:
2.3.2.1.1. получение всех записей
2.3.2.1.2. получение одной записи по имени параметра
2.3.2.1.3. получение всех записей, имена параметров которых удовлетворяют заданному пользователем шаблону имени
2.3.2.2. в случае получения непустой выборки класс возвращает полученные результаты классу ViewList в виде HTML-представления полученных данных
2.3.2.3. в случае получения пустой выборки формируется строка с соответствующим сообщением
