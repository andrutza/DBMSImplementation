package servlet;

import mapdb.MapDbException;
import model.Record;
import service.DatabaseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "RecordDeleteServlet", value = "/RecordDeleteServlet")
public class RecordDeleteServlet extends HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("dbName");
        String tableName = request.getParameter("tableName");
        String primaryKey = request.getParameter("primaryKey");
        try {
            DatabaseService.getInstance().deleteRecord(databaseName, tableName, primaryKey);
        } catch (MapDbException e) {
            request.setAttribute("errorMsg", e.getMessage());
        }
        request.getRequestDispatcher(request.getContextPath() + "/table.jsp?dbName=" + databaseName).forward(request, response);
    }
}
