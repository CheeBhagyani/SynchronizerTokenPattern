/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import net.sf.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author Chee Bhagyani
 */
public class CheckToken extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String csrfToken = MemoryStorage.getStorage().getId(cookies[0].getValue());
        resp.setContentType("application/json");
        JSONObject member = new JSONObject();
        member.put("csrfToken",csrfToken);
        PrintWriter pw =resp.getWriter();
        pw.print(member.toString());
        pw.flush();
        pw.close();
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        serviceVerify(req, resp);
    }


    protected void serviceVerify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        
        try {
            String code = req.getParameter("code");
            String enKey = req.getParameter("enKey");
            String CSRFTokenRecieved = req.getParameter("tokentxt");
            
            resp.getWriter().println("Subject code :" + code);
            resp.getWriter().println("Enrollment key :" + enKey);
            resp.getWriter().println("Token :" + CSRFTokenRecieved);
            
            Cookie[] cookies = req.getCookies();
            
            String CSRFToken = MemoryStorage.getStorage().getId(cookies[0].getValue());
            
            if (CSRFTokenRecieved.equals(CSRFToken)) {
                resp.getWriter().println("CSRF token is verfied !!");
            } else {
                resp.getWriter().println("CSRF token is not verified !!");
            }
        }finally {
            resp.getWriter().close();
        }

    }
}
