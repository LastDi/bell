# Bell Integrator study project
Данный проект выполнен в рамках онлайн-стажировки в компании "Bell Integrator". <br/>
Проект представляет собой web-сервис для сохранения, обновления и получения списка пользователей, офисов и организаций. <br/>
Проект написан с использованием Spring Boot, JDBC, JPA, REST API, JUnit<br/>
В качестве базы даннных исользуется H2. <br/>
Сборка проекта и управление зависимостями осуществляется с помощью Maven. <br/>
Версия Java - 17. <br/>

### Запуск проекта
Для того, чтобы запустить данный проект, необходимо скопировать репозиторий к себе на компьютер, 
следующей командой в терминале.
###### Ссылка на репозиторий для запуска в терминале
```bash
git clone https://github.com/LastDi/bell
```

### Примеры всех видов запросов и ответов на них
GET запрос для получения пользователя по id (Например id = 3):
```bash
/api/user/3
```
Ответ: 
<br/>
<p align="center">
  <img src="https://i.ibb.co/mqsyC8V/img.png" width="50%" height="50%"/>
</p>

POST запрос для получения списка пользователей по фильтру:
```bash
/api/user/list
```
<p align="center">
  <img src="https://i.ibb.co/RTC31hP/img-1.png" width="50%" height="50%"/>
</p>
<br/>
Ответ: 
<br/>
<p align="center">
  <img src="https://i.ibb.co/RSsfqXB/img-2.png" width="50%" height="50%"/>
</p>

POST запрос для сохранения офиса
```bash
/api/office/save
```
<p align="center">
  <img src="https://i.ibb.co/9tNY646/img-3.png" width="50%" height="50%"/>
</p>
<br/>
Ответ: 
<br/>
<p align="center">
  <img src="https://i.ibb.co/BzXWm95/img-4.png" width="50%" height="50%"/>
</p>

POST запрос для обновления данных организации
```bash
/api/organization/update
```
<p align="center">
  <img src="https://i.ibb.co/vhZyhD8/img-5.png" width="50%" height="50%"/>
</p>
<br/>
Ответ:
<br/>
<p align="center">
  <img src="https://i.ibb.co/nL0sRqL/img-6.png" width="50%" height="50%"/>
</p>
<br/>

Далее перечислены все доступные запросы: <br/>
#### GET запросы
Для отправки данных запросов можно использовать браузер.
#### для получения элемента по его id (вместо {id} указать нужное значение id)
```bash
/api/user/{id}
```
```bash
/api/office/{id}
```
```bash
/api/organization/{id}
```

#### список доступных типов документов и гражданств (стран)
```bash
/api/docs
```
```bash
/api/countries
```

#### POST запросы
Для отправки данных запросов, необходимо использовать программу, с возможностью добавления тела запроса (например Postman)
#### для получения списка по фильтру
Получить список пользователей соответсвующих следующим условиям, указанным в теле запроса
(в данном примере и последующих, обязательные для заполнения поля помечены звездочкой):
>\* "officeId" - айди офиса пользователя
> <br/>
>"firstName" - имя
> <br/>
>"lastName" - фамилия
> <br/>
>"middleName" - отчество
> <br/>
>"position" - должность
> <br/>
>"docCode" - код документа
> <br/>
>"citizenshipCode" - код гражданства
```bash
/api/user/list
```
Получить список офисов соответсвующих следующим условиям, указанным в теле запроса
>\* "orgId" - айди организации, которой принадлежит офис
> <br/>
>"name" - название офиса
> <br/>
>"phone" - телефон офиса
> <br/>
>"isActive" - действует ли офис (Здесь и далее параметры начинающиеся на is* заполняются строковыми значениями true или false)
```bash
/api/office/list
```
>\* "name" - название организации
> <br/>
>"inn" - ИНН организации
> <br/>
>"isActive" - действует ли организация
```bash
/api/organization/list
```

#### для сохранения элемента в базе
Запрос для сохранения нового пользователя в базе данных
>\* "officeId" - айди офиса пользователя
> <br/>
>\* "firstName" - имя
> <br/>
>"secondName" - фамилия
> <br/>
>"middleName" - отчество
> <br/>
>\* "position" - должность
> <br/>
>"phone" - телефон
> <br/>
>"docCode" - код документа
> <br/>
>"docName" - имя документа
> <br/>
>"docNumber" - номер документа
> <br/>
>"docDate" - дата выдачи документа в формате: "yyyy-MM-dd"
> <br/>
>"citizenshipCode" - код гражданства
> <br/>
>"isIdentified" - идентифицирован ли пользователь
```bash
/api/user/save
```

Запрос для сохранения нового офиса в базе данных
>\* "orgId" - айди организации
> <br/>
>"name" - название офиса
> <br/>
>"address" - адрес офиса
> <br/>
>"phone" - телефон офиса
> <br/>
>"isActive" - действует ли офис
```bash
/api/office/save
```

Запрос для сохранения новой организации в базе данных
>\* "name" - название организации
> <br/>
>\* "fullName" - полное название
> <br/>
>\* "inn" - ИНН
> <br/>
>\* "kpp" - КПП
> <br/>
>\* "address" - адрес организации
> <br/>
>"phone" - телефон
> <br/>
>"isActive" - действует ли организация
```bash
/api/organization/save
```
#### для обновления данных
Запрос для обновления данных пользователя в базе данных
>\* "id" - айди пользователя
> <br/>
>"officeId" - айди офиса, в котором работает пользователь
> <br/>
>\* "firstName" - имя
> <br/>
>"secondName" - фамилия
> <br/>
>"middleName" - отчество
> <br/>
>\* "position" - должность
> <br/>
>"phone" - телефон
> <br/>
>"docCode" - код документа
> <br/>
>"docName" - имя документа
> <br/>
>"docNumber" - номер документа
> <br/>
>"docDate" - дата выдачи документа в формате: "yyyy-MM-dd"
> <br/>
>"citizenshipCode" - код гражданства
> <br/>
>"isIdentified" - идентифицирован ли пользователь
```bash
/api/user/update
```

Запрос для обновления данных офиса в базе данных
>\* "id" - айди офиса
> <br/>
>"name" - название офиса
> <br/>
>"address" - адрес офиса
> <br/>
>"phone" - телефон офиса
> <br/>
>"isActive" - действует ли офис
```bash
/api/office/update
```

Запрос для обновления данных организации в базе данных
>\* "id" - айди организации
> <br/>
>\* "name" - название организации
> <br/>
>\* "fullName" - полное название
> <br/>
>\* "inn" - ИНН
> <br/>
>\* "kpp" - КПП
> <br/>
>\* "address" - адрес организации
> <br/>
>"phone" - телефон
> <br/>
>"isActive" - действует ли организация
```bash
/api/organization/update
```