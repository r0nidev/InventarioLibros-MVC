package model;

/**
 * Esta clase lo ocupamos para mapear los campos de las tabla de la bd. 
 * La clase Autor debe mapear los campos de la tabla Autor.
 * según el teacher, esta clase es un bean
 */
public class Autor {
    private String codigoAutor;
    private String nombreAutor;
    private String nacionalidad;
    
    // constructor, le damos valores iniciales a los atributos
    public Autor(){
        this.codigoAutor = "";
        this.nombreAutor = "";
        this.nacionalidad = "";
    }
    
    // getters and setters

    public String getCodigoAutor() {
        return codigoAutor;
    }

    public void setCodigoAutor(String codigoAutor) {
        this.codigoAutor = codigoAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
}
