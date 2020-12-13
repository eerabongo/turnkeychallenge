/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esau.turnkeychallenge;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Esau
 */
@RestController
public class DemoController {

    DatabaseConnection databaseConnection;

    @RequestMapping("/api/v1/employee_id_search/{id}")
    public String employeeIDSearch(@PathVariable("id") int id) {
        System.out.println("PathVariable id|" + id);
        String employee_details = "Error. Please try again later";
        try {
            databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{? = call HR.EMPLOYEE_ID_SEARCH(?)}");
            callableStatement.clearParameters();
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setInt(2, id);
            callableStatement.executeUpdate();
            employee_details = callableStatement.getString(1);
            System.out.println("employee_details|" + employee_details);
        } catch (SQLException ex) {
            System.err.println("employeeIDSearch|ex:" + ex.toString());
            Logger.getLogger(DemoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee_details;
    }
}
