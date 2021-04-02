<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Nuevo editorial</title>
        <jsp:include page="/cabecera.jsp"/>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container">
            <div class="row">
                <h3>Nuevo editorial</h3>
            </div>
            <div class="row">
                <div class=" col-md-7">
                    <!-- SI NO ESTA Vacía -->
                    
                    <div class="alert alert-danger"> <!-- show errores -->
                        <ul>
                            <c:forEach items="${requestScope.listaErrores}" var="error">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                    
                    <form role="form" action="${pageContext.request.contextPath}/editoriales.do" method="POST">
                        <input type="hidden" name="op" value="agregar" />
                        <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>
                        <div class="form-group">
                            <label for="codigo">Codigo del editorial:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="codigo" id="codigo"
                                       value="${editorial.codigoEditorial}" placeholder="Ingresa el codigo del genero" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nombre">Nombre del editorial:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="nombre" 
                                       value="${editorial.nombreEditorial}" id="nombre"  placeholder="Ingresa el nombre del editorial" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="contacto">Contacto:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="contacto" 
                                       value="${editorial.contacto}" name="contacto"  placeholder="Ingresa el nombre del contacto">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="telefono">Telefono:</label>
                            <div class="input-group">
                                <input type="tel" class="form-control" id="telefono"
                                       value="${editorial.telefono}" name="telefono"  placeholder="Ingresa el telefono del contacto" >
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/editoriales.do?op=listar">Cancelar</a>
                    </form>
                </div>
            </div>  
        </div>
    </body>
</html>



