package servlet;

import service.DatabaseService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "DatabaseAddServlet", value = "/DatabaseAddServlet")
public class DatabaseAddServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("databaseName");
        DatabaseService.getInstance().addDatabase(databaseName);
        response.sendRedirect(request.getContextPath() + "/database.jsp");
    }



}
