package by.chaika19.hibernate.starter;

import by.chaika19.hibernate.starter.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {

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