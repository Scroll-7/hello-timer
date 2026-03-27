package com.devops;

import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head><title>Hello DevOps</title></head>");
        out.println("<body>");
        out.println("<h1>Hello from DevOps!</h1>");
        out.println("<p>Current time: " + new Date() + "</p>");
        out.println("<p>This app was built with Maven and deployed by Jenkins.</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
