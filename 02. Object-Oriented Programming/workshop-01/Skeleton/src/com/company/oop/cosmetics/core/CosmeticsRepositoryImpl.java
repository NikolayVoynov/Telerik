package com.company.oop.cosmetics.core;

import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.Category;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.Product;
import com.company.oop.cosmetics.models.ShoppingCart;

import java.util.ArrayList;

public class CosmeticsRepositoryImpl implements CosmeticsRepository {

    public static final String FIND_CATEGORY_ERROR = "Category %s does not exist!";
    public static final String FIND_PRODUCT_ERROR = "Product %s does not exist!";

    private final ArrayList<Product> products;
    private final ArrayList<Category> categories;
    private final ShoppingCart shoppingCart;

    public CosmeticsRepositoryImpl() {
        products = new ArrayList<>();
        categories = new ArrayList<>();

        shoppingCart = new ShoppingCart();
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public ArrayList<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    @Override
    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public Product findProductByName(String productName) {
        /**
         * Hint: You have to go through every product and see if one has name equal to productName.
         *       If not, "throw new IllegalArgumentException("Product %s does not exist!");"
         */

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)){
                return product;
            }
        }

        throw new IllegalArgumentException(String.format(FIND_PRODUCT_ERROR, productName));
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        /**
         * Hint: You have to go through every category and see if one has name equal to categoryName.
         *       If not, "throw new IllegalArgumentException("Category %s does not exist!");"
         */

        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }

        throw new IllegalArgumentException(String.format(FIND_CATEGORY_ERROR, categoryName));
    }

    @Override
    public void createCategory(String categoryName) {
        Category newCategory =new Category(categoryName);
        categories.add(newCategory);
    }

    @Override
    public void createProduct(String name, String brand, double price, GenderType gender) {
        Product newProduct = new Product(name, brand, price, gender);
        products.add(newProduct);
    }

    @Override
    public boolean categoryExist(String categoryName) {
        /**
         * Hint: You have to go through every category and see if one has name equal to categoryName.
         *       If there is one, return true. If not, return false.
         */
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean productExist(String productName) {
        /**
         * Hint: You have to go through every product and see if one has name equal to productName.
         *       If there is one, return true. If not, return false.
         */
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return true;
            }
        }

        return false;
    }
}
