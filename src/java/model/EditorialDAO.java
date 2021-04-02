package model;

import configbd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLException;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * En esta clase vamos hacer uso de List.
 * List es una interface de la clase Collection.
 */
public class EditorialDAO { //extends Conexion {
    
    Connection con; // demo conexion standar JDBC
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;    
    
    //public ArrayList<Editorial> => NO recomendable. 
    
    /* Framework de Persistencia utitlizan List. 
       retornar una Lista de tipo Editorial. */
    /* Método para listar Editoriales */
    public List<Editorial> listar() {
        List<Editorial> editoriales = new ArrayList<>(); // => best practice of programming OOP. 
        try {
          //  ArrayList<Editorial> lista = new ArrayList<Editorial>(); // => NO RECOMENDABLE. 
            String sql = "SELECT * FROM editoriales";
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(); 
            /*this.conectar();
            st = conexion.prepareStatement(sql);
            rs = st.executeQuery();*/
            while(rs.next()){
                Editorial editorial = new Editorial();
                editorial.setCodigoEditorial(rs.getString("codigo_editorial"));
                editorial.setNombreEditorial(rs.getString("nombre_editorial"));
                editorial.setContacto(rs.getString("contacto"));
                editorial.setTelefono(rs.getString("telefono"));
                editoriales.add(editorial);
            }
            //this.desconectar();
           // return editoriales;
        //} catch (SQLException ex) {
        } catch (Exception ex) {
            System.out.println("(EditorialDAO - listar) Exception: "+ ex.toString());
            //Logger.getLogger(EditorialDAO.class.getName()).log(Level.SEVERE, null, ex);
           // this.desconectar();
            //return null;
        }
        return editoriales;
    }
    
    /* Método para insertar Editoriales */
    public int agregarEditorial(Editorial ed){
        int r = 0;
        String sql = "INSERT INTO editoriales VALUES(?,?,?,?)";
        try{
            con = cn.conectar();
            ps = con.prepareStatement(sql);                        
            ps.setString(1, ed.getCodigoEditorial());
            ps.setString(2, ed.getNombreEditorial());
            ps.setString(3, ed.getContacto());
            ps.setString(4, ed.getTelefono());
            r = ps.executeUpdate();
            if ( r == 1){
                r = 1;
            } else {
                r = 0;
            }
        }catch(Exception e){
              System.out.println("(EditorialDAO)-addEditorial-Exception:  "+ e.toString());
        }
        return r;
    }
    
    /* method que permite obtener un Editorial por medio de su codigo,
    devuelve el objeto Editorial by medio de codigo.*/
    public Editorial obtenerEditorial(String codigo){
        System.out.println("codigo: "+ codigo);
        String sql = "SELECT *FROM editoriales WHERE codigo_editorial= ?"; 
        Editorial ed = new Editorial();        
        try{
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                ed.setCodigoEditorial(rs.getString(1));
                ed.setNombreEditorial(rs.getString(2));
                ed.setContacto(rs.getString(3));
                ed.setTelefono(rs.getString(4));
                /*ed.setCodigoEditorial(rs.getString("codigo_editorial"));
                ed.setNombreEditorial(rs.getString("nombre_editorial"));
                ed.setContacto(rs.getString("contacto"));
                ed.setTelefono(rs.getString("telefono"));*/
            }
        }catch(Exception e){
            System.out.println("EditorialDAO (obtenerEditorial) Exception: "+ e.toString());
        }
        return ed;
    }
    
    /* metodo que permite Actualizar/Modificar los datos del Editorial */
    public int actualizar(Editorial ed){
        int r = 0;
        String sql = "UPDATE editoriales SET nombre_editorial=?, contacto=?, telefono=?"
                + " WHERE codigo_editorial=?";
        try{
            con = cn.conectar();
            ps = con.prepareStatement(sql);          
            /* tener muy encuenta el orden de los parámetros.
            Es el mismo que en la sentencia SQL de arriba */
            ps.setString(1, ed.getNombreEditorial());
            ps.setString(2, ed.getContacto());
            ps.setString(3, ed.getTelefono());
            ps.setString(4, ed.getCodigoEditorial());
            r = ps.executeUpdate();
            if ( r == 1){
                r = 1;
            } else {
                r = 0;
            }
        }catch(Exception e){
              System.out.println("EditorialDAO -> actualizar - Exception: "+ e.toString());
        }
        return r;
    }
    
    /* metodo eliminar editorial */
    public int eliminar(String codigo){
        int r = 0;
        try {
            String sql = "DELETE FROM editoriales WHERE codigo_editorial=?";
            con = cn.conectar();
            ps =  con.prepareStatement(sql);
            ps.setString(1, codigo);
            r = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EditorialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
