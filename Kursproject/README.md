# Управление заявками склада

## Описание

Консольное приложение на **Java** для учёта **складских заявок**: приём товаров от поставщика и отгрузка получателям. Пользователь через меню создаёт заявки, просматривает список, меняет статус и может вывести текстовый «документ» по заявке.

Проект выполнен в учебных целях и демонстрирует **объектно-ориентированное программирование**: классы и объекты, инкапсуляцию, наследование, полиморфизм, интерфейс и абстрактный класс, обработку исключений.

## Используемые технологии

- Язык **Java** (рекомендуется JDK **8** или новее)
- Стандартная библиотека: `java.time`, коллекции, консольный ввод/вывод
- Система контроля версий **Git**, публикация на **GitHub**

## Структура проекта (кратко)

| Элемент | Назначение |
|--------|------------|
| `Documentable` | Интерфейс с методом `buildDocumentText()` |
| `StorageRequest` | Абстрактный класс общей заявки |
| `InboundRequest` / `OutboundRequest` | Наследники: приёмка и отгрузка |
| `RequestStatus` | Перечисление статусов заявки |
| `Warehouse` | Сервис: хранение и поиск заявок |
| `RequestNotFoundException` | Своё исключение «заявка не найдена» |
| `WarehouseApp` | Точка входа и меню |

## Как запустить проект

1. Установите **JDK** (не только JRE), чтобы была команда `javac`.
2. Склонируйте репозиторий или скопируйте папку проекта.
3. В корне проекта выполните компиляцию и запуск:

**Windows (PowerShell или cmd), из папки `Kursproject`:**

```bat
javac -encoding UTF-8 -d out -sourcepath src ^
  src\com\course\warehouse\Documentable.java ^
  src\com\course\warehouse\WarehouseApp.java ^
  src\com\course\warehouse\exception\RequestNotFoundException.java ^
  src\com\course\warehouse\model\RequestStatus.java ^
  src\com\course\warehouse\model\StorageRequest.java ^
  src\com\course\warehouse\model\InboundRequest.java ^
  src\com\course\warehouse\model\OutboundRequest.java ^
  src\com\course\warehouse\service\Warehouse.java
java -classpath out com.course.warehouse.WarehouseApp
```

Либо откройте проект в **IntelliJ IDEA** / **Eclipse**: укажите папку `src` как source root и запустите класс `WarehouseApp`.

## Примеры использования

1. Запустите программу — появится демонстрационный набор из двух заявок.
2. Выберите `3` — отобразится список всех заявок с типом, номером, товаром, количеством и статусом.
3. Выберите `1`, введите товар, количество (> 0), поставщика и комментарий — создана заявка на приём.
4. Выберите `4`, укажите номер заявки и код нового статуса (1 — новая, 2 — в работе, 3 — выполнена).
5. Выберите `6` — демонстрация **полиморфизма**: для каждой заявки один и тот же код вызывает разные реализации (приёмка / отгрузка).

При неверном вводе числа или несуществующем номере заявки сообщения обрабатываются через `try-catch`; при завершении программы вызывается `finally` (закрытие `Scanner`).

## Связь с требованиями курсовой

- **Инкапсуляция:** поля в классах заявок и склада — `private`; доступ через геттеры/сеттеры.
- **Наследование:** `InboundRequest` и `OutboundRequest` расширяют `StorageRequest`.
- **Полиморфизм:** переменная типа `StorageRequest`; переопределены `getRequestTypeLabel()`, `buildDocumentText()`.
- **Абстракция:** абстрактный класс `StorageRequest`; интерфейс `Documentable`.
- **Исключения:** проверяемое `RequestNotFoundException`, `IllegalArgumentException`, `try-catch-finally` в `WarehouseApp`.

## Автор

Курсовой проект по дисциплине Java (ООП).
