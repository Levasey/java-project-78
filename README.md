### Hexlet tests and linter status:
[![Actions Status](https://github.com/Levasey/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Levasey/java-project-78/actions)
[![Java CI](https://github.com/Levasey/java-project-78/actions/workflows/build.yml/badge.svg)](https://github.com/Levasey/java-project-78/actions/workflows/build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Levasey_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Levasey_java-project-78)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Levasey_java-project-78&metric=bugs)](https://sonarcloud.io/summary/new_code?id=Levasey_java-project-78)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Levasey_java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=Levasey_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Levasey_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Levasey_java-project-78)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=Levasey_java-project-78&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=Levasey_java-project-78)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Levasey_java-project-78&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=Levasey_java-project-78)

# Валидатор данных

Проект представляет собой гибкую систему валидации данных с поддержкой различных типов (строки, числа, Map) и вложенной валидации.

## Возможности

- Валидация строк с проверками:
    - Обязательное поле (`required()`)
    - Минимальная длина (`minLength()`)
    - Содержание подстроки (`contains()`)

- Валидация чисел с проверками:
    - Обязательное поле (`required()`)
    - Положительное число (`positive()`)
    - Диапазон значений (`range()`)

- Валидация Map с проверками:
    - Обязательное поле (`required()`)
    - Точный размер (`sizeof()`)
    - Вложенная валидация значений (`shape()`)

## Установка

1. Клонируйте репозиторий:
```bash
git clone https://github.com/Levasey/java-project-78.git
```

2. Соберите проект:
```bash
cd app
./gradlew build
```
3. Добавьте как зависимость в ваш проект

## Тестирование

Запуск тестов:
```bash
cd app
./gradlew test
```
Проверка покрытия:
```bash
cd app
./gradlew jacocoTestReport
```
