package by.chaika19.hibernate.starter;

import by.chaika19.hibernate.starter.entity.*;
import by.chaika19.hibernate.starter.util.HibernateUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {

    @Test
    public void checkManyToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Chat chat = session.get(Chat.class, 2L);

        User user = session.get(User.class, 2L);
        UserChat userChat = UserChat.builder()
                .createdAt(Instant.now())
                .createdBy("Vovan")
                .build();

        userChat.setChat(chat);
        userChat.setUser(user);

        session.save(userChat);

        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        User user = User.builder()
                .username("Vovan1@gmail.com")
                .build();
        Profile profile = Profile.builder()
                .language("BY")
                .street("Krid 2009")
                .build();

        session.save(user);
        profile.setUser(user);
        session.save(profile);

        session.getTransaction().commit();
    }

    @Test
    public void checkOrphalRemoval() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = session.get(Company.class, 1);
        company.getUsers().removeIf(user -> user.getId() == 5);

        session.getTransaction().commit();
    }

    @Test
    public void addNewUserAndCompany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = Company.builder()
                .name("Mail")
                .build();

        User user = User.builder()
                .username("Egor_Krid2009@gmail.com")
                .build();

        company.addUser(user);

        session.saveOrUpdate(company);

        session.getTransaction().commit();
    }


    @Test
    public void checkOneToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 1);
        System.out.println(company.getUsers());

        session.getTransaction().commit();
    }



    @Test
  public void testHibetnateApi() throws SQLException, IllegalAccessException {
        /*
        var user = User.builder()
                .username("a1@gmail.com")
                .firstname("Ivan")
                .lastname("Ivanovich")
                .birthDay(LocalDate.of(2001, 9, 11))
                .age(52)
                .build();
        var sql = """
                  insert into
                  %s
                  (%s)
                  values
                  (%s)
                  """;

        var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();

        var columnNames = Arrays.stream(fields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName())
                ).collect(Collectors.joining(", "));

        var columnValues = Arrays.stream(fields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hibernate",
                "postgres", "123");
        PreparedStatement preparedStatement = connection
                .prepareStatement(sql.formatted(tableName, columnNames, columnValues));

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            preparedStatement.setObject(i + 1, fields[i].get(user));
        }

        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        */
  }
}