package servlet;

import model.Database;
import model.Table;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 03-03-16.
 */
@WebServlet(name = "TableServlet")
public class TableServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String databaseName = request.getParameter("databaseName");
        Database database = new Database(databaseName);
        List<Table> tables = database.getTables();
    }

}
