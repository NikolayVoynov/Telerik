package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactor) {
        this.sessionFactory = sessionFactor;
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("from User", User.class)
                    .list();

        }
    }

    @Override
    public User getUserById(int id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);

            if (user == null) {
                throw new EntityNotFoundException("User", id);
            }

            return user;
        }

    }

    @Override
    public User getUserByName(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);

            List<User> users = query.list();

            if (users.isEmpty()) {
                throw new EntityNotFoundException("User", "username", username);
            }

            return users.get(0);
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        }

    }

    @Override
    public void createUser(User user) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }
}
