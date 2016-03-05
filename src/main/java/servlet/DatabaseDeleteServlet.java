package servlet;

import service.DatabaseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(name = "DatabaseDeleteServlet",value = "/DatabaseDeleteServlet")
public class DatabaseDeleteServlet extends HttpServlet {


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("databaseName");
        DatabaseService.getInstance().deleteDatabase(databaseName);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
