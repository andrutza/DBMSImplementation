package servlet;

import service.DatabaseService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "AttributeAddServlet", value = "/AttributeAddServlet")
public class AttributeAddServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("dbName");
        String tableName = request.getParameter("tableName");
        String attributeName = request.getParameter("attributeName");
        String attributeType = request.getParameter("attributeType");
        if(request.getParameter("cancelButton")!=null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        DatabaseService.getInstance().addAttribute(databaseName, tableName, attributeName, attributeType);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }



}
