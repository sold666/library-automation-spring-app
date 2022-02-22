package jdbc;

import jdbc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        try (Connection connection = JDBCUtils.getNewConnection()) {

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}