package servlet;

import service.DatabaseService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "TableAddServlet", value = "/TableAddServlet")
public class TableAddServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("dbName");
        String tableName = request.getParameter("tableName");
        DatabaseService.getInstance().addTable(databaseName, tableName);
        response.sendRedirect(request.getContextPath() + "/table.jsp?dbName=" + databaseName);
    }



}
