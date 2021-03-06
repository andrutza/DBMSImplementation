package servlet;

import mapdb.MapDbException;
import model.Record;
import service.DatabaseService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RecordAddServlet", value = "/RecordAddServlet")
public class RecordAddServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("dbName");
        String tableName = request.getParameter("tableName");

        try {
            DatabaseService.getInstance().addRecord(databaseName, tableName, request.getParameterMap());
        } catch (MapDbException e) {
            request.setAttribute("errorMsg", e.getMessage());
        }
        request.getRequestDispatcher(request.getContextPath() + "/table.jsp?dbName=" + databaseName).forward(request, response);
    }



}
