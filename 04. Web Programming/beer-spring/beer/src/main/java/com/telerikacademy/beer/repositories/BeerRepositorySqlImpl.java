package com.telerikacademy.beer.repositories;

import com.telerikacademy.beer.exceptions.EntityNotFoundException;
import com.telerikacademy.beer.models.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Repository
@PropertySource("classpath:application.properties")
public class BeerRepositorySqlImpl implements BeerRepository {
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;

//    @Autowired
    public BeerRepositorySqlImpl(Environment env) {
        dbUrl = env.getProperty("database.url");
        dbUsername = env.getProperty("database.username");
        dbPassword = env.getProperty("database.password");
    }

    @Override
    public List<Beer> getAllBeers() {
        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT beer_id, name, abv FROM beers")
        ) {
            return readBeersData(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Beer getBeerById(int id) {
        String query = "SELECT beer_id, name, abv " +
                "FROM beers " +
                "WHERE beer_id = ?";

        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);

            try (
                    ResultSet resultSet = statement.executeQuery();
            ) {
                List<Beer> beers = readBeersData(resultSet);

                if (beers.isEmpty()) {
                    throw new EntityNotFoundException("Beer", id);
                }

                return beers.get(0);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Beer getBeerByName(String name) {
        String query = "SELECT beer_id, name, abv " +
                "FROM beers " +
                "WHERE name = ?";

        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setString(1, name);
            try (
                    ResultSet resultSet = statement.executeQuery();
            ) {
                List<Beer> beers = readBeersData(resultSet);

                if (beers.isEmpty()) {
                    throw new EntityNotFoundException("Beer", "name", name);
                }

                return beers.get(0);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void createBeer(Beer beer) {
        String query = "INSERT INTO beers (beer_id, name, abv) " +
                "VALUES ('" + beer.getId() +
                "', '" + beer.getName() +
                "', '" + beer.getAbv() + "')";

        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateBeer(Beer beer) {
        String query = "UPDATE beers SET name = ?, abv = ? WHERE beer_id = ?";

        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, beer.getName());
            statement.setDouble(2, beer.getAbv());
            statement.setInt(3, beer.getId());

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM beers WHERE beer_id = ?";

        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    private List<Beer> readBeersData(ResultSet beersData) throws SQLException {
        List<Beer> beers = new ArrayList<>();

        while (beersData.next()) {
            Beer beer = new Beer(
                    beersData.getInt("beer_id"),
                    beersData.getString("name"),
                    beersData.getDouble("abv")
            );

            beers.add(beer);
        }

        return beers;
    }
}
