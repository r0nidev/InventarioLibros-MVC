package configbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Engineer
 */
public class ConexionPool {
/*
    protected static Connection conexion = null;
    protected PreparedStatement st;
    protected ResultSet rs;

    public ConexionPool() {
       
        this.st = null;
        this.rs = null;
    }

    public void conectar() {
        // Ojo, la conexion no se hace de manera Stándar, como en JDBC.
        // Se toma de un pool de conexiones.
        try {
            if(conexion == null || conexion.isClosed()){
            //Obtenemos la conexion del pool de conexiones
            Context init = new InitialContext();
            Context context = (Context) init.lookup("java:comp/env");
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
            conexion = dataSource.getConnection();
            }

        } catch (NamingException ex) {
            System.out.println("NamingException: "+ ex.toString());
          //  Logger.getLogger(model.Conexion.class.getName()).log(Level.SEVERE, null, ex);
            //Logger.getLogger(configbd.ConexionPool.conexion .Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("SQLException: "+ ex.toString());
            //Logger.getLogger(model.Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconectar() throws SQLException {
        //Cierro los objetos en el orden inverso del que se crean
        // es decir: primero el resulset, luego el statement
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }

    }*/
}
