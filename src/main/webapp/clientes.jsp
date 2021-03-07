
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/4aba0d4f1c.js" crossorigin="anonymous"></script>

        <title>Control de Clientes </title>
    </head>
    <body>
        <!--HEADER-->
        <jsp:include page="WEB-INF/paginas/comunes/cabecero.jsp"/>

        <!-- BOTONES DE NAVEGACION-->
        <jsp:include page="WEB-INF/paginas/comunes/botonesNavegacion.jsp"/>

        <!-- LISTADO CLIENTES-->
        <jsp:include page="WEB-INF/paginas/cliente/listadoClientes.jsp"/>

        <!--PIE DE PAGINA-->
        <jsp:include page="WEB-INF/paginas/comunes/piePagina.jsp"/>



        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>

    </body>
</html>

