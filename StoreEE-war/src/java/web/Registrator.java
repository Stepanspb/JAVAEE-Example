package web;


import EJBPack.DbManagerLocal;
import EJBPack.UpdateBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Registrator extends HttpServlet {
    
@EJB
UpdateBeanLocal updateBean; 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Attribute atr;
        boolean newparam = false;
        boolean numFormatExc = false;
        String buttonpress, dbbutton;        
        response.setContentType("text/html;charset=UTF-8");        
        HttpSession hs = request.getSession();
        Object obj = hs.getAttribute("Attribute");
        if (obj != null && (obj instanceof Attribute)) {
            atr = (Attribute) obj;
        } else {
            atr = new Attribute();
            hs.setAttribute("Attribute", atr);
        }
        
        buttonpress = request.getParameter("add");
        int length = atr.list.size();
        numFormatExc = checkexception(request.getParameter("value"));
        if ((buttonpress != null) && !numFormatExc &&!cheakName(request.getParameter("name")) ) {
            if (atr.addParameter(new Parameter(request.getParameter("name"), request.getParameter("value")))) {
                if (length != atr.list.size()) {
                    newparam = true;
                }
            }
        }  
dbbutton = request.getParameter("dbadd");     
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registrator</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Add new Parameter   </h1>");            
            out.println("<div style = 'width: 50%; float: left; border-width: 0 1px 0 0; border-style: solid; padding-right: 10px;'>");
            out.println(getform());
            out.println("</div>");
            out.println("<div style = 'float: left;  margin-left:15px; '>");
            out.println(getformDb());
            out.println("</div><br>");
            out.println("<div style = 'width: 50%; float: left;'>");            
            if ((buttonpress != null) && (cheakName(request.getParameter("name")))) {
                out.println("<p><h4> имя параметра пустое или его длина превышает 255 символов </p></h4>");
            } else {
                if (newparam && (buttonpress != null) && !numFormatExc) {
                    out.println("<p><h4> New parameter was added </p></h4>");
                }
                if (!newparam && (buttonpress != null) && !numFormatExc) {
                    out.println("<p><h4> Parameter was rerecorded </p></h4>");
                }
            }
            if (numFormatExc && (buttonpress != null)) {
                out.println("<p><h4> значение параметра не может быть корректно преобразовано к типу int </p></h4>");
            }            
            out.println("</div><br>");
            
            // DATABASE 
            out.println("<div style = 'float: left;  margin-left:15px; '>");
            if (dbbutton != null){
            out.println(updateBean.ParameterProcessing(request.getParameter("dbname"), request.getParameter("dbvalue")));
            }
            out.println("</div><br>");
            // END DATABASE 
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registrator</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registrator at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String getform() {
        StringBuilder sb = new StringBuilder();
        sb.append("<form action='Registrator' method = 'Get'>\n")
                .append("<p><h2>Input Parameter:<h2> <br> <input type ='text' name='name' size='60'/><br>")
                .append("<p><input type ='text' name='value' size='10'/><br>")
                .append("\n<br>\n<input type='submit' name='add' value='Add Parameter' />")
                .append("\n</p>\n</form >")
                .append("\n <form action='ViewList' method = 'GET'>\n <button>back</button>\n </form>")
                .append("\n<br>\n <hr> ");
        return sb.toString();
    }
    
    
    private String getformDb() {
        StringBuilder sb = new StringBuilder();
        sb.append("<form action='Registrator' method = 'Get'>\n")
                .append("<p><h2>Input Parameter to database:<h2> <br> <input type ='text' name='dbname' size='60'/><br>")
                .append("<p><input type ='text' name='dbvalue' size='10'/><br>")
                .append("\n<br>\n<input type='submit' name='dbadd' value='Add Parameter to DB' />")
                .append("\n</p>\n</form >")
                .append("\n <form action='ViewList' method = 'GET'>\n <button>back</button>\n </form>")
                .append("\n<br>\n <hr> ");
        return sb.toString();
    }
    

    private boolean checkexception(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }
    
    private boolean cheakName (String s) {
    return  (s == null || s.trim().isEmpty()
                    || s.length() > 255);               
    }


}