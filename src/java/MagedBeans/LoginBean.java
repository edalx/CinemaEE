/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

    private String userName;
    private String password;
    private String admin;
    private String dbuserName;
    private String dbAdmin;
    private String dbpassword;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getDbAdmin() {
        return dbAdmin;
    }

    public void setDbAdmin(String dbAdmin) {
        this.dbAdmin = dbAdmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbuserName() {
        return dbuserName;
    }

    public void setDbuserName(String dbuserName) {
        this.dbuserName = dbuserName;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    public void dbData(String userName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cinemaEEv3", "root", "ctrlwd771");
            statement = connection.createStatement();
            SQL = "Select * from Usuario where username like ('" + userName + "')";
            resultSet = statement.executeQuery(SQL);
            resultSet.next();
            dbuserName = resultSet.getString(4).toString();
            dbpassword = resultSet.getString(10).toString();
            dbAdmin = resultSet.getString(9).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occured in the process :" + ex);
        }
    }

    public String checkValidUser() {
        dbData(userName);

        if (userName.equalsIgnoreCase(dbuserName)) {

            if (password.equals(dbpassword)) {
                if (dbAdmin.equals("1")) {
                    return "AdminSucess";
                }
                return "sucess";
            } else {
                return "failure";
            }
        } else {
            return "failure";
        }
    }
}
