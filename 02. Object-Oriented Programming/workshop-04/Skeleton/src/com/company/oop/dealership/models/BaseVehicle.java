package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.FormattingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseVehicle implements Vehicle {
    private final static String COMMENTS_HEADER = "--COMMENTS--";
    private final static String NO_COMMENTS_HEADER = "--NO COMMENTS--";

    private static final int MAKE_NAME_LEN_MIN = 2;
    private static final int MAKE_NAME_LEN_MAX = 15;
    private static final String MAKE_NAME_LEN_ERR = String.format(
            "Make must be between %s and %s characters long!",
            MAKE_NAME_LEN_MIN,
            MAKE_NAME_LEN_MAX);

    public static final int MODEL_NAME_LEN_MIN = 1;
    public static final int MODEL_NAME_LEN_MAX = 15;
    private static final String MODEL_NAME_LEN_ERR = String.format(
            "Model must be between %s and %s characters long!",
            MODEL_NAME_LEN_MIN,
            MODEL_NAME_LEN_MAX);

    public static final double PRICE_VAL_MIN = 0;
    public static final double PRICE_VAL_MAX = 1000000;
    private static final String PRICE_VAL_ERR = String.format(
            "Price must be between %.1f and %.1f!",
            PRICE_VAL_MIN,
            PRICE_VAL_MAX);

    private String make;
    private String model;
    private double price;
    private final int wheelsCount;
    private List<Comment> comments;

    public BaseVehicle(String make, String model, double price, int wheelsCount) {
        setMake(make);
        setModel(model);
        setPrice(price);
        this.wheelsCount = wheelsCount;
        this.comments = new ArrayList<>();
    }

    @Override
    public int getWheels() {
        return wheelsCount;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public abstract VehicleType getType();

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public String toString() {

        return String.format("%s:%n" +
                        "Make: %s%n" +
                        "Model: %s%n" +
                        "Wheels: %d%n" +
                        "Price: $%s%n" +
                        "%s%n" +
                        "%s",
                getType(),
                getMake(),
                getModel(),
                getWheels(),
                FormattingHelpers.removeTrailingZerosFromDouble(price),
                printAdditionalInfoAsPerVehicleType(),
                printComments());
    }

    protected abstract String printAdditionalInfoAsPerVehicleType();

    private String printComments() {
        StringBuilder result = new StringBuilder();

        if (comments.isEmpty()) {
            result.append(NO_COMMENTS_HEADER);
        } else {
            result.append(COMMENTS_HEADER);
            result.append(System.lineSeparator());

            for (Comment comment : comments) {
                result.append(comment.toString());
            }

            result.append(COMMENTS_HEADER);
        }

        return result.toString().trim();
    }

    private void setMake(String make) {
        ValidationHelpers.validateIntRange(make.length(), MAKE_NAME_LEN_MIN, MAKE_NAME_LEN_MAX, MAKE_NAME_LEN_ERR);

        this.make = make;
    }

    private void setModel(String model) {
        ValidationHelpers.validateIntRange(model.length(), MODEL_NAME_LEN_MIN, MODEL_NAME_LEN_MAX, MODEL_NAME_LEN_ERR);

        this.model = model;
    }

    private void setPrice(double price) {
        ValidationHelpers.validateDecimalRange(price, PRICE_VAL_MIN, PRICE_VAL_MAX, PRICE_VAL_ERR);

        this.price = price;
    }

}
