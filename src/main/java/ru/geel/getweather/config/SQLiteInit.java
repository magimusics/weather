package ru.geel.getweather.config;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQLiteInit {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize(){
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(
                    "create table if not exists stats(" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "username varchar(30) not null," +
                            "request varchar(30) not null," +
                            "city varchar(30) not null," +
                            "dates datetime," +
                            "description varchar(30) not null," +
                            "temp varchar(30) not null," +
                            "humidity varchar(30) not null," +
                            "pressure varchar(30) not null)"
            );
            //statement.executeUpdate("insert into UserLogin (username,password) VALUES('ivan', '123123')");
            //statement.executeUpdate("insert into UserLogin (username,password) VALUES('geel', '111')");
            //statement.executeUpdate("insert into UserLogin (username,password) VALUES('john', '123456')");
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
