package servlet;

import model.Attribute;
import service.DatabaseService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AttributesServlet", value = "/AttributesServlet")
public class AttributesServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("dbName");
        String tableName = request.getParameter("tableName");
        List<Attribute> attributes = DatabaseService.getInstance().getAttributes(databaseName, tableName);
        String htmlOutput = "";
        for (Attribute attribute : attributes) {
            htmlOutput+="<option>"+attribute.getName()+"</option>";
        }
        response.getWriter().write(htmlOutput);
    }



}
