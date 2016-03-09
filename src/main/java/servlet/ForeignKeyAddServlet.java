package servlet;

import service.DatabaseService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "ForeignKeyAddServlet", value = "/ForeignKeyAddServlet")
public class ForeignKeyAddServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("dbName");
        String tableName = request.getParameter("tableName");
        String attribute = request.getParameter("attribute");
        String refTable = request.getParameter("refTable");
        String refAttribute = request.getParameter("refAttribute");
        DatabaseService.getInstance().addForeignKey(databaseName, tableName, attribute, refTable, refAttribute);
        response.sendRedirect(request.getContextPath() + "/database.jsp");
    }



}
