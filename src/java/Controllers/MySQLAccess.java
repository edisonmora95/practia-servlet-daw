/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.sql.*;

/**
 *
 * @author Ivan Mera
 */
public class MySQLAccess {
    //URL's de JDBC y la base de datos
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost/practicaconferencias";
    //Credenciales
    private final String USER = "root";
    private final String PASS = "";

    private Connection conn = null;
    private Statement stmt = null;
    private CallableStatement stmt2 = null;

    public boolean connection() {
        try {
            //1: Registrar JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //2: Abrir coneccion
            System.out.println("Conectando a la base de datos...");
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
            this.stmt = conn.createStatement();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (this.conn != null) {
                this.conn.close();
            }
            if (this.stmt != null) {
                this.stmt.close();
            }
            System.out.println("Cerrando coneccion a la base de datos...");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ResultSet query(String q) throws SQLException {
        ResultSet rs = this.stmt.executeQuery(q);
        return rs;
    }

    public ResultSet query2(String call) throws SQLException {
        this.stmt2 = this.conn.prepareCall(call);
        this.stmt2.execute();
        return this.stmt2.getResultSet();
    }

    public void write(String q) throws SQLException {
        this.stmt.executeUpdate(q);
    }

    public void write2(String call) throws SQLException {
        this.stmt2 = this.conn.prepareCall(call);
        this.stmt2.execute();
    }

    public Connection getConn() {
        return this.conn;
    }
}
