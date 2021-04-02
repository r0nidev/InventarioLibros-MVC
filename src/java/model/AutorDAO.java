package model;

import configbd.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * DAO : Data Access Object.
 * 
 */
public class AutorDAO {
    PreparedStatement ps;
    ResultSet rs;
    Conexion c = new Conexion();
    Connection con;
    //private CallableStament cs; // fort work with SP (Stored Procedure)

    /*public List<Autor> listar(){
       String sql = "CAL sp_listAuthor()"; // así se llama mi Procedimiento Almaceando
       
       con = c.conectar();
       // Vamos a trabajar con SP, we need CallableStatement
       
    }*/
    
}
