
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Clientes</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Saldo</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos todos los elementos de la lista de clientes-->
                            <c:forEach var="cliente" items="${clientes}">
                                <tr>
                                    <td>${cliente.idCliente}</td>
                                    <td>${cliente.nombre} ${cliente.apellido}</td>
                                    <td>${cliente.saldo}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${cliente.idCliente}"
                                           class="btn btn-secondary"><i class="fas fa-angle-double-right"></i>Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<ul>
    <c:forEach var="cliente" items="${clientes}">
        <li>${cliente.idCliente} ${cliente.nombre} ${cliente.saldo}</li>
        </c:forEach>
</ul>
