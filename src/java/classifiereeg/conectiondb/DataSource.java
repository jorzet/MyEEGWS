package classifiereeg.conectiondb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class DataSource {
    public Connection con = null;
    String driver = "com.mysql.jdbc.Drive";
    
    public boolean iniciaConexion(){
        boolean conection = false;
        String url = "jdbc:mysql://148.204.86.36:3306/BasePT&user=root&password=ipnUPIITA145&";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            conection = true;
            System.out.println("Conexion Exitosa");
        } catch(ClassNotFoundException ex)
        {
            System.err.println(ex+ "Error1 en la Conexión con la BD "+ex.getMessage());
            conection = false;
        }
        catch(SQLException ex)
        {
            System.err.println(ex+ "Error2 en la Conexión con la BD "+ex.getMessage());
            conection = false;
        }
        catch(Exception ex)
        {
            System.err.println(ex+ "Error3 en la Conexión con la BD "+ex.getMessage());
            conection = false;
        }
        finally
        {
            return conection;
        }
        
    }
    
    public void cerrarConexion() throws SQLException{
        if(con != null){
            con.close();
        }
}
}
