<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de editoriales</title>
        <jsp:include page="/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container">
            <div class="row">
                <h3>Lista de editoriales</h3>
            </div>
            <div class="row">
                <div class="col-md-10">
                    <a type="button" class="btn btn-primary btn-md" 
                       href="${pageContext.request.contextPath}/editoriales.do?op=nuevo"> Nuevo editorial</a>
                <br><br>
                <table class="table table-striped table-bordered table-hover" id="tabla">
                    <thead>
                        <tr>
                            <th>Codigo del editorial</th>
                            <th>Nombre del editorial</th>
                            <th>Contacto</th>
                            <th>Telefono</th>
                            <th>Operaciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaEditoriales}" var="editorial">
                            <tr>
                                <td>${editorial.codigoEditorial}</td>
                                <td>${editorial.nombreEditorial}</td>
                                <td>${editorial.contacto}</td>
                                <td>${editorial.telefono}</td>
                                <td>
                                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/editoriales.do?op=obtener&id=${editorial.codigoEditorial}"><span class="glyphicon glyphicon-edit"></span> Editar</a>
                                    
                                    <a  class="btn btn-danger" href="javascript:eliminar('${editorial.codigoEditorial}')"><span class="glyphicon glyphicon-trash"></span> Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                            
                   
                    </tbody>
                </table>
                </div>
                
            </div>                    
        </div> 
        <!-- -->
        
        <!-- Codigo jQuery -->
        <script> 
        $(document).ready(function (){
            $('#tabla').DataTable();
        });
        // alertify
            <c:if test="${not empty exito}">
                alertify.success('${exito}');
                // una vez que muestra message de exito, que deje empty la variable de session
                <c:set var="exito" value="" scope="session"/>
            </c:if>
            
            <c:if test="${not empty fracaso}">
                alertify.error('${fracaso}');
                <c:set var="fracaso" value="" scope="session"/>
            </c:if>
                
        // Delete function 
        function eliminar(id){
            //alert(id);
            alertify.confirm('¿Realmente desea eliminar este editorial?', function(e){
                if(e){
                    location.href="editoriales.do?op=eliminar&id="+id;
                }
            });
        }
        </script> 
        <!-- -->
    </body>
</html>


