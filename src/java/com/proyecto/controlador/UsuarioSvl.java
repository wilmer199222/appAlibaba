
package com.proyecto.controlador;

import com.proyecto.dao.UsuarioDaoImpl;
import com.proyecto.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ESTUDIANTES
 */
@WebServlet(name = "UsuarioSvl", urlPatterns = {"/usuarioSvl"})
public class UsuarioSvl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Usuario usuario = new Usuario();
            UsuarioDaoImpl usDao= new UsuarioDaoImpl();
            List<Usuario> listaUsuario = new ArrayList();
            String respuesta = null;
            RequestDispatcher rd= null;
            try {
                if (request.getParameter("btnLogin")!=null) {
                 String user = request.getParameter("usuario");
                 String clave = request.getParameter("pass");
                 
                 listaUsuario = usDao.existeUsuario(user, clave);
                    if (listaUsuario.size()>0) {
                        request.setAttribute("listaUsuario", listaUsuario);
                        rd= request.getRequestDispatcher("index.html");
                        
                    }else{
                        respuesta= "El usuario no existe";
                        request.setAttribute("respuesta", respuesta);
                        rd= request.getRequestDispatcher("login.jsp");
                    }
                    
                }
            } catch (Exception e) {
                out.println("Error en el usuario servlet: " +e.toString());
                e.printStackTrace();
            }
            rd.forward(request, response);
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

}
