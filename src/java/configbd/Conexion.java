package configbd;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Engineer
 */
public class Conexion {
    // Accounts MySQL on Windows 10    
    private String  URL = "jdbc:mysql://127.0.0.1:3306/inventariolibros?useSSL=false";    
    private String USER = "nic"; 
    private String PASSWD = "engineer";       
    
    // Método conectar a la bd.
    public  Connection conectar(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");            
            //Class.forName("com.mysql.cj.jdbc.Driver");            

            con = (Connection) DriverManager.getConnection(URL, USER, PASSWD);                   
            System.out.println("Conexión establecida a la BD!!");
        }catch(Exception e){
            System.out.println("EXCEPTION(Conexion): "+ e.toString() );   
        }
        return con;
    }
}
