package com.bridgelabz.userloginwebapp.controler;

import com.bridgelabz.userloginwebapp.exception.UserLoginException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //get request parameter from userId and Password
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");

        try {
            if (userService.loginUser(email, pwd)) {
                req.setAttribute("user", email);
                req.getRequestDispatcher("LoginSuccess.jsp").forward(req, res);
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                PrintWriter out = res.getWriter();
                out.println("<font color=red>Either user name or password is wrong. </font>");
                rd.include(req, res);
            }
        } catch (UserLoginException | SQLException e) {
            e.printStackTrace();
        }
    }
}
