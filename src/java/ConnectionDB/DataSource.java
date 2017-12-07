package ConnectionDB;

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
    public Connection conexion=null;
    
    public Connection iniciaConexion(){
        
        try{
            System.out.println("pasa1");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("pasa2");
            //String url = "jdbc:mysql://148.204.86.36:3306/BasePT?"+"user=root&password=ipnUPIITA145&?autoReconnect=true&useSSL=false";
            String url = "jdbc:mysql://localhost:3306/basept?"+"user=root&password=jorzet94&?autoReconnect=true&useSSL=false";
            System.out.println("pasa3");
            conexion= DriverManager.getConnection(url);
            System.out.println("pasa4");
        }
        catch(ClassNotFoundException ex){
            System.err.println(ex+ "Error1 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(SQLException ex){
            System.err.println(ex+ "Error2 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        catch(Exception ex){
            System.err.println(ex+ "Error3 en la Conexión con la BD "+ex.getMessage());
            conexion=null;
        }
        finally{
            return conexion;
        }
    }
    
    public void cerrarConexion() throws SQLException{
        if(conexion != null){
            conexion.close();
        }
    }
}
