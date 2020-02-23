package com.loloara.genreisromance;

import com.loloara.genreisromance.common.util.Gender;
import com.loloara.genreisromance.model.User;
import com.loloara.genreisromance.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DatabaseTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Autowired
    UserRepository userRepository;

    @Test
    public void dataSourcePropertiesTest() {
        try(Connection con = dataSource.getConnection()) {
            System.out.println("Database Connected: " + con);
        }catch (Exception e) {
            e.printStackTrace();
        }

        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String jdbcUrl = "jdbc:mysql://localhost:3306/genreisromance?serverTimezone=Asia/Seoul&userUnicode=true&characterEncoding=utf-8";
        String username = "root";

        System.out.println("DataSource.toString(): " + dataSource.toString());
        assertEquals(driverClassName, dataSourceProperties.getDriverClassName());
        assertEquals(jdbcUrl, dataSourceProperties.getUrl());
        assertEquals(username, dataSourceProperties.getUsername());
    }

    @Test
    public void userSaveTest() {
        userRepository.save(
                User.builder()
                        .userName("Lucas")
                        .age(27)
                        .birthDate(LocalDate.of(1994,4,10))
                        .email("test@gmail.com")
                        .gender(Gender.MALE)
                        .height(173)
                        .phone("01012345678")
                        .build()
        );
        List<User> users = (List<User>) userRepository.findAll();

        int index = 0;
        System.out.println("===================USER SAVE TEST===================");
        for(User user : users) {
            System.out.println(index + " : " + user.toString());
        }

        assertEquals(users.size(), 1);
    }
}
