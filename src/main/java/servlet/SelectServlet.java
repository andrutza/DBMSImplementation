package servlet;

import model.Attribute;
import model.Filter;
import model.Record;
import service.DatabaseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "SelectServlet", value = "/SelectServlet")
public class SelectServlet extends HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String databaseName = request.getParameter("dbName");
        String tableName = request.getParameter("tableName");
        //where = atr + op + val
        String[] attributes = request.getParameterValues("selectedAttribute");
        List<Attribute> selectedAttributes = new ArrayList<>();
        for (String attribute : attributes) {
            selectedAttributes.add(new Attribute(attribute));
        }
        String[] filterAttributes = request.getParameterValues("filter_attributes");
        String[] filterOperationTypes = request.getParameterValues("filter_operation_types");
//        List<Filter> filters = request.getP
//        List<Record> records = DatabaseService.getInstance().projectionAndSelection(databaseName, tableName, selectedAttributes);   //? filter - takes List<Where> rets List<Record>
//        String htmlOutput = "";
//        for (Filter filter : filters) {
//            htmlOutput += "<option>" + filter.getName() + "</option>";
//        }
//        response.getWriter().write(htmlOutput);
    }


}

