# Lambdas & Stream API - In-Class Activity - Movie Theater CMS

## 1. Description

You are presented with the task of refactoring a Movie Theater Customer Management System. The system already works fine but the codebase is a hot mess. The more `for` loops there are, the higher the chance for a mistake is. 

## 2. Goals

Refactor all methods in the `DataProcessor` class to use Stream API.

## 3. Project Structure

### Customer

A class that represents a single customer of the movie theatre. Each customer has: `id`, `age`, `name`, a list of `likedMovies` and a list of `dislikedMovies`.

### Genre

An enum for the possible movie genres.

### Movie

A class that represents a single movie. Each movie has a `name` and `genre`.

### Database

A class with a single static method - `generateData()` that returns a list of customers, with each one having a list of `likedMovies` and a list of `dislikedMovies`.

> *Note:* You do not have to modify any of the above classes. Just use them as they are.

### DataProcessor

A class that produces statistic data for the customers of the movie theater. It has a several methods that query the customers list and return a result. Right now all the methods use complex `for` loops. Refactor them so that they all use Stream API.

## 4. Tests

There are also tests to verify your methods work as they should.
## 5. Optional Tasks

Try to think of more methods that exercise the different Stream API methods (like `takeWhile`, `dropWhile`, `mapToDouble`, `mapToInt`, `intStream`, etc...). Implement them with a `for` loop first and then refactor them to use Stream API.

