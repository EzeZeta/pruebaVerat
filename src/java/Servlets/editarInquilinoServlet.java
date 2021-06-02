/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Gestores.GestorInquilinos;
import Modelos.Consorcio;
import Modelos.Persona;
import Modelos.Ph;
import Modelos.TipoDocu;
import Modelos.TipoPersona;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "editarInquilinoServlet", urlPatterns = {"/editarInquilinoServlet"})
public class editarInquilinoServlet extends HttpServlet {

    
   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id_persona = Integer.parseInt(request.getParameter("id_persona"));
        
        GestorInquilinos gi = new GestorInquilinos();
        
       Persona p = gi.getInquilinoEdicion(id_persona);

       request.setAttribute("editInqui", p );
       
       RequestDispatcher rc = request.getServletContext().getRequestDispatcher("/editarInquilino.jsp");
       rc.forward(request, response);
       
       
       
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
        
        int id_persona = Integer.parseInt(request.getParameter("txtId_persona"));
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String documento =  request.getParameter("txtNroDNI");
        String telefono = request.getParameter("txtTelefono");
        String mail = request.getParameter("txtMail");
        
        
        TipoPersona tp = new TipoPersona(0, "");
        TipoDocu td = new TipoDocu(0, "");
        Ph ph = new Ph(0,"" ,0 , 0, true , true);
        Consorcio con = new Consorcio(0,"", "", "", "", "", "", "",true,0);
        
        
        Persona nuevaPersona = new Persona(id_persona, tp, nombre, apellido, td, documento, telefono, mail, ph, true, con, "");
        
               
        
        GestorInquilinos gi = new GestorInquilinos();
        gi.editarInquilino(nuevaPersona);
        
        RequestDispatcher rc = request.getServletContext().getRequestDispatcher("/listaInquilinosServlet");
        rc.forward(request, response);
        
        
        
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
