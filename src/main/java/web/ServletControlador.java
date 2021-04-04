package web;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar"://acion del boton >>Editar del form principal
                    this.editarCliente(request, response);
                    break;
                case "eliminar"://acion del boton >>Editar del form principal
                    this.eliminarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
                    break;
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        //System.out.println("clientes = "+clientes);
        HttpSession sesion = request.getSession();//se pondran los datos en el alcanze de sesion
        sesion.setAttribute("clientes", clientes);//tenian request.
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", calcularSaldoTotal(clientes));
        /* Al reenviar el formulario no cambia la url y duplica el dato insertado
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
         */
        response.sendRedirect("clientes.jsp");
    }

    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperar el idCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    insertarCliente(request, response);
                    break;
                case "modificar":
                    modificarCliente(request, response);
                    break;

                default:
                    this.accionDefault(request, response);
                    break;
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //REcuperamos datos del formulario agregarCliente
        String nombre = request.getParameter("nombre"),
                apellido = request.getParameter("apellido"),
                email = request.getParameter("email"),
                telefono = request.getParameter("telefono"),
                saldoString = request.getParameter("saldo");
        double saldo = 0;
        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }
        //Crear el objeto cliente
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);
        //Insertar en la base de datos
        int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
        System.out.println("Registris modificados: " + registrosModificados);
        //Redirigir hacia la accion default
        this.accionDefault(request, response);
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //REcuperamos datos del formulario editarCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre"),
                apellido = request.getParameter("apellido"),
                email = request.getParameter("email"),
                telefono = request.getParameter("telefono"),
                saldoString = request.getParameter("saldo");
        double saldo = 0;

        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }
        //Crear el objeto cliente
        Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
        //Modificar en la base de datos
        int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);
        System.err.println("Registros modificados: " + registrosModificados);
        //Redirigir hacia la accion default
        this.accionDefault(request, response);
    }
    
        private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //REcuperamos datos del formulario editarCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        //Crear el objeto cliente
        Cliente cliente = new Cliente(idCliente);
        //Eliminar en la base de datos
        int registrosModificados = new ClienteDaoJDBC().eliminar(cliente);
        System.err.println("Registros modificados: " + registrosModificados);
        //Redirigir hacia la accion default
        this.accionDefault(request, response);
    }
}
