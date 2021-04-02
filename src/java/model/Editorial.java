package model;

/**
 *
 * @author Engineer
 */
public class Editorial {

    // atributos 
    private String codigoEditorial;
    private String nombreEditorial;
    private String contacto;
    private String telefono; // check, 'cause no debe ser string.
    /*private String codigoEditorial;
    private String nombreEditorial;
    private String contacto;
    private String telefono;*/
    
    // constructor por defecto
    public Editorial(){
        this.codigoEditorial = "";
        this.nombreEditorial = "";
        this.contacto = "";
        this.telefono = "";
    }
    
    // setter and getter

    public String getCodigoEditorial() {
        return codigoEditorial;
    }

    public void setCodigoEditorial(String codigoEditorial) {
        this.codigoEditorial = codigoEditorial;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
