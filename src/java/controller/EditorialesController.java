package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Editorial;
import model.EditorialDAO;
import utils.Validaciones;


@WebServlet(name = "EditorialesController", urlPatterns = {"/editoriales.do"})
public class EditorialesController extends HttpServlet {

    EditorialDAO edao = new EditorialDAO();
    /* Lo ocupamos para almacenar los errores */
    ArrayList<String> listaErrores = new ArrayList<>();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameter("op") == null){
                listar(request, response);
                return;
            }
            String operacion = request.getParameter("op");
            switch(operacion){
                case "listar":
                    listar(request, response);
                    break;
                case "nuevo":
                    request.getRequestDispatcher("/editoriales/nuevoEditorial.jsp").forward(request, response);
                    break;
                case "agregar":
                    agregar(request, response);
                    break;
                case "obtener":
                    obtener(request, response);
                    break;
                case "modificar":
                    modificar(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/error404.jsp").forward(request, response);
                    break;
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {    
            //request.setAttribute("listaEditoriales", model.listarEditoriales());
            request.setAttribute("listaEditoriales", edao.listar());
            // Redirecciones en el Cliente
            // response.sendRedirect("ruta"); 
            // Redirecciones en el Servidor
            request.getRequestDispatcher("/editoriales/listaEditoriales.jsp").forward(request, response); 
            
        } catch (ServletException ex) {
            Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void agregar(HttpServletRequest request, HttpServletResponse response) {
        
        try{
            Editorial editorial = new Editorial();
            listaErrores.clear();
        // recibimos los datos form the view
        editorial.setCodigoEditorial(request.getParameter("codigo"));
        editorial.setNombreEditorial(request.getParameter("nombre"));
        editorial.setContacto(request.getParameter("contacto"));
        editorial.setTelefono(request.getParameter("telefono"));
        
        // validations
        if(Validaciones.isEmpty(editorial.getCodigoEditorial())){
            listaErrores.add("el código del editorial es obligatorio.");
        }
        else if(!Validaciones.esCodigoEditorial(editorial.getCodigoEditorial())){
            listaErrores.add("el código del editorial debe tener el formato EDI000");
        }
        if(Validaciones.isEmpty(editorial.getNombreEditorial())){
            listaErrores.add("el nombre del editorial es obligatorio.");
        }
        if(Validaciones.isEmpty(editorial.getContacto())){
            listaErrores.add("el nombre del contacto es obligatorio.");
        }
        if(Validaciones.isEmpty(editorial.getTelefono())){
            listaErrores.add("el teléfono es obligatorio.");
        }
        // verify el formato del tefono
        else if(!Validaciones.esTelefono(editorial.getTelefono())){
            listaErrores.add("el teléfono no cumple el formato 0000-0000");                    
        }       
          //
        if(listaErrores.size() > 0){ // Hay errores de validacion
            request.setAttribute("editorial", editorial);
            request.setAttribute("listaErrores", listaErrores);
            request.getRequestDispatcher("/editoriales.do?op=nuevo").forward(request, response);
        }
        else { // No hay errores de validación 
            // 1 = se insertó correctamente, 0 = no se insertó
            if(edao.agregarEditorial(editorial) > 0 ){ // se guardó correctamente
                request.getSession().setAttribute("exito", "Editorial registrado exitosamente!");
                //request.setAttribute("exito", "Editorial registrado exitosamente!");
                // contextPath hace referencia a la raiz del proyecto
                response.sendRedirect(request.getContextPath() + "/editoriales.do?op=listar");
                //request.getRequestDispatcher("/editoriales.do?op=listar").forward(request, response); //  ya no lo necesito
            }else{
                //request.setAttribute("fracaso", "No se puedo guardar este editoria. "
                request.getSession().setAttribute("fracaso", "No se puedo guardar este editorial. "
                        + "Ya existe otro editorial con este código.");
                response.sendRedirect(request.getContextPath() + "/editoriales.do?op=listar");                
                    //request.getRequestDispatcher("/editoriales.do?op=listar").forward(request, response);                
            }
        }
            
        } catch (ServletException ex) {
            System.out.println("info: "+ ex.toString());
            Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // final method agregar

    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        try {
            // receive the parameter codigoEditorial
            String codigo = request.getParameter("id");
            Editorial editorial = edao.obtenerEditorial(codigo);
            
            if(editorial != null ){
            request.setAttribute("editorial", editorial);// send to the view
            request.getRequestDispatcher("/editoriales/editar.jsp").forward(request, response);                
            } else {
                response.sendRedirect(request.getContextPath()+"/error404.jsp");
            }

        } catch (ServletException | IOException ex) {
            Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Método modificar datos de la tabla Editoriales */
    private void modificar(HttpServletRequest request, HttpServletResponse response) {
        try{
            Editorial editorial = new Editorial();
            listaErrores.clear();
        // recibimos los datos form the view
        editorial.setCodigoEditorial(request.getParameter("codigo"));
        editorial.setNombreEditorial(request.getParameter("nombre"));
        editorial.setContacto(request.getParameter("contacto"));
        editorial.setTelefono(request.getParameter("telefono"));
        
        // validations
        if(Validaciones.isEmpty(editorial.getCodigoEditorial())){
            listaErrores.add("el código del editorial es obligatorio.");
        }
        else if(!Validaciones.esCodigoEditorial(editorial.getCodigoEditorial())){
            listaErrores.add("el código del editorial debe tener el formato EDI000");
        }
        if(Validaciones.isEmpty(editorial.getNombreEditorial())){
            listaErrores.add("el nombre del editorial es obligatorio.");
        }
        if(Validaciones.isEmpty(editorial.getContacto())){
            listaErrores.add("el nombre del contacto es obligatorio.");
        }
        if(Validaciones.isEmpty(editorial.getTelefono())){
            listaErrores.add("el teléfono es obligatorio.");
        }
        // verify el formato del tefono
        else if(!Validaciones.esTelefono(editorial.getTelefono())){
            listaErrores.add("el teléfono no cumple el formato 0000-0000");                    
        }       
          //
        if(listaErrores.size() > 0){ // Hay errores de validacion
            request.setAttribute("editorial", editorial);
            request.setAttribute("listaErrores", listaErrores);
            request.getRequestDispatcher("/editoriales/editar.jsp").forward(request, response);
        }
        else { // No hay errores de validación 
            // 1 = se insertó correctamente, 0 = no se insertó
            if(edao.actualizar(editorial) > 0 ){ // Se actualizó correctamente
                request.getSession().setAttribute("exito", "Editorial actualizado exitosamente!");
                //request.setAttribute("exito", "Editorial registrado exitosamente!");
                // contextPath hace referencia a la raiz del proyecto
                response.sendRedirect(request.getContextPath() + "/editoriales.do?op=listar");
                //request.getRequestDispatcher("/editoriales.do?op=listar").forward(request, response); //  ya no lo necesito
            }else{
                //request.setAttribute("fracaso", "No se puedo guardar este editoria. "
                request.getSession().setAttribute("fracaso", "No se pudo actualizar los datos del editorial.");
                response.sendRedirect(request.getContextPath() + "/editoriales.do?op=listar");                
                    //request.getRequestDispatcher("/editoriales.do?op=listar").forward(request, response);                
            }
        }
            
        } catch (ServletException ex) {
            System.out.println("info: "+ ex.toString());
            Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// fin método modificar

    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String codigo = request.getParameter("id");
            if ( edao.eliminar(codigo) > 0 ){// 1: hizo la eliminacion
                request.setAttribute("exito", "Editorial eliminado exitosamente!");
            }else{
                request.setAttribute("fracaso", "No se puede eliminar este Editorial.");
            }
            request.getRequestDispatcher("/editoriales.do?op=listar").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditorialesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
