# Unit Testing for Cosmetics Shop

## Description
You are given a software system for managing a cosmetics shop. The system is already implemented and works. It supports one type of products that can be grouped in categories. The user can:
- Create a category;
- Create a product;
- Add a product to a category;
- Show a category;

## Task
Your **task** is to cover the functionality with **unit tests**. You should cover all **models**, **commands** and the **ProductRepositoryImpl** class.

### 1. Start with the models
- `CategoryImpl` tests cases are defined.
- Continue with `ProductImpl` tests. First define the test cases.
- Target code coverage is 75%

### 2. Next test the commands
- CreateCategoryCommand test cases are defined.
- Consider using `@BeforeEach` to simplify your code.
- Continue with `CreateProductCommand`, `AddProductToCategoryCommand` and `ShowCategoryCommand`.
- Test cases are based on the functionality in the respective classes.
- Target code coverage is 90%

### 3. Test the `ProductRepositoryImpl` class
- Test cases are defined.
- Target code coverage is 100%
