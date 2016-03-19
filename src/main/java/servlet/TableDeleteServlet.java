package servlet;

import service.DatabaseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(name = "TableDeleteServlet",value = "/TableDeleteServlet")
public class TableDeleteServlet extends HttpServlet {


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("dbName");
        String tableName = request.getParameter("tableName");
        DatabaseService.getInstance().deleteTable(databaseName, tableName);
        response.sendRedirect(request.getContextPath() + "/table.jsp?dbName=" + databaseName);
    }
}
