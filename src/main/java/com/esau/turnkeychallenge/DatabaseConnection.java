/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esau.turnkeychallenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esau
 */
public class DatabaseConnection {

    private Connection connection;

    public DatabaseConnection() {
        connection = null;
    }

    public Connection getConnection() {
        if (connection != null) {
            closeConnection();
        }
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "testuser", "Welcome01");
        } catch (Exception ex) {
            System.err.println("getConnection|ex:" + ex.toString());
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.err.println("closeConnection|ex:" + ex.toString());
        }
    }
}
