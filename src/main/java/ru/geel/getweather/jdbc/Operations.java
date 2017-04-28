package ru.geel.getweather.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.geel.getweather.model.Stats;
import ru.geel.getweather.model.Weather;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ivangeel on 27.04.17.
 */

@Service
public class Operations {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getUser(String username){
        String string = jdbcTemplate.query("select * from UserLogin where username='"+username+"'", new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.getString("username");
            }
        });
        return string;
    }

    public void addStat(Weather weather, String username, String request, String date){
        String INSERT_SQL = "insert into stats (username, request, city, dates, description, temp, humidity, pressure) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, request);
                preparedStatement.setString(3, weather.getName());
                preparedStatement.setString(4, date);
                preparedStatement.setString(5, (String) weather.getWeather().get("description"));
                preparedStatement.setString(6, (String) weather.getMain().get("temp"));
                preparedStatement.setString(7, weather.getMain().get("humidity")+"");
                preparedStatement.setString(8, weather.getMain().get("pressure")+"");
                return preparedStatement;
            }
        });
    }

    public List<Stats> getStats(String username){
        List<Stats> stats = jdbcTemplate.query("select * from stats where username='"+username+"'", new RowMapper<Stats>() {
            @Override
            public Stats mapRow(ResultSet resultSet, int i) throws SQLException {
                Stats stat = new Stats();
                stat.setUsername(resultSet.getString("username"));
                stat.setRequest(resultSet.getString("request"));
                stat.setCity(resultSet.getString("city"));
                stat.setDate(resultSet.getString("dates"));
                stat.setDescription(resultSet.getString("description"));
                stat.setTemp(resultSet.getString("temp"));
                stat.setHumidity(resultSet.getString("humidity"));
                stat.setPressure(resultSet.getString("pressure"));
                return stat;
            }
        });
        return stats;
    }

    public boolean checkUser(String username, String password){
        String usr = jdbcTemplate.query("select username from UserLogin where username='" + username + "' and password='" + password + "'", new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return resultSet.getString("username");
            }
        });
        if(username.equals(usr)){
            return true;
        }
        return false;
    }

    public void addUser(String username, String pass){
        String INSERT_SQL = "insert into UserLogin (username,password) VALUES(?,?)";
        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, pass);
                    return preparedStatement;
                }
            });
        }
        catch (DataAccessException e){
            throw new RuntimeException(e);
        }
    }
}
