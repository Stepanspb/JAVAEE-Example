package web;

import EJBPack.SelectBeanLocal;
import EJBPack.UpdateBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewList extends HttpServlet {

    @EJB
    UpdateBeanLocal ubl;
    @EJB
    SelectBeanLocal sbl;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name, val, val2, buttonPress, deletPress, dbname;
        Attribute atr;
        HttpSession hs = request.getSession(true);
        Object obj = hs.getAttribute("Attribute");
        if (obj != null && (obj instanceof Attribute)) {
            atr = (Attribute) obj;
        } else {
            atr = new Attribute();
            hs.setAttribute("Attribute", atr);
        }
        name = request.getParameter("name");
        dbname = request.getParameter("dbname");
        val = request.getParameter("value1");
        val2 = request.getParameter("value2");
        buttonPress = request.getParameter("submitbutton");
        deletPress = request.getParameter("deletbutton");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewList </h1>");
            out.println("<div style = 'width: 50%; float: left; border-width: 0 1px 0 0; border-style: solid; padding-right: 10px;'>");
            out.println(getform());
            out.println("</div>");
            out.println("<div style = 'float: left;  margin-left:15px; '>");
            out.println(getformdB());
            out.println("</div>");
            out.println("<div style = 'width: 100%; float: right;'>");
            out.println("\n <p> <hr></p>");
            out.println("</div>");
            out.println("<div style = 'width: 100%; float: left;'>");
            if (buttonPress != null) {
                if ((name.trim().isEmpty()) && (val.trim().isEmpty())) {
                    out.println(atr.showAllParameters());
                }
                if (!(name.trim().isEmpty()) && (val.trim().isEmpty())) {
                    out.println(atr.showNameParameters(name));
                }
                if ((name.trim().isEmpty()) && !(val.trim().isEmpty())) {
                    out.println(atr.showIntParameters(val, val2));
                }
            }
            // ejb
            if (deletPress != null) {
                out.println(ubl.ParameterDel(dbname));
            }
            if (request.getParameter("dbsubmitbutton") != null) {
                out.println(sbl.showResults(dbname));
            }

            // ejb end
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String getform() {
        StringBuilder sb = new StringBuilder();
        sb.append("<form action = 'Registrator' method = 'Get'>\n")
                .append("<p><h2> Add new parameter</h2> \n")
                .append("<input type='submit' name='newparameter' value='add' />\n<br>\n")
                .append("\n</p>\n</form>\n<br><hr>\n")
                .append("<form action='ViewList' method = 'Get'>\n<br>")
                .append("<p><h2> Search</h2> \n")
                .append("<p>Parameter's name: ")
                .append("<input type = 'text' name ='name' size = '25' />")
                .append("\n<br>\n \n<br>\n set range of values: ")
                .append("<input type='text' name = 'value1' size= '5' />")
                .append("\n - <input type = 'text' name = 'value2' size= '5' />")
                .append("\n<br><br>\n<input type='submit' name='submitbutton' value='show Parameters' />")
                .append("\n</p>\n</form>")
                .append("\n<br>");
        return sb.toString();
    }

    private String getformdB() {
        StringBuilder sb = new StringBuilder();        
        sb.append("<form action='ViewList' method = 'Get'>\n")
                .append("<h1>Database search  </h1>")
                .append("<p>Parameter's name: ")
                .append("<input type = 'text' name ='dbname' size = '25' />")
                .append("\n<br><br>\n<input type='submit' name='deletbutton' value='delete' />")
                .append("\n<br><br>\n<input type='submit' name='dbsubmitbutton' value='show Parameters' />")
                .append("\n</p>\n</form>")
                .append("\n<br>\n");
        return sb.toString();
    }

}
