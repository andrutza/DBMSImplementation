package servlet;

import model.Database;
import service.DatabaseService;
import xml.Reader;

import java.io.IOException;
import java.util.List;

/**
 * Created by User on 03-03-16.
 */
@javax.servlet.annotation.WebServlet(name = "DatabaseServlet", value = "/DatabaseServlet")
public class DatabaseServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("databaseName");
        DatabaseService.getInstance().addDatabase(databaseName);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }



}
