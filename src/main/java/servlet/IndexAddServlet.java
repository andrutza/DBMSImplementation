package servlet;

import service.DatabaseService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "IndexAddServlet", value = "/IndexAddServlet")
public class IndexAddServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("dbName");
        String tableName = request.getParameter("tableName");
        String attribute = request.getParameter("attribute");
        DatabaseService.getInstance().addIndex(databaseName, tableName, attribute);
        response.sendRedirect(request.getContextPath() + "/table.jsp?dbName=" + databaseName);
    }



}
