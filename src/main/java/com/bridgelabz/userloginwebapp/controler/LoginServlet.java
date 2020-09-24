package com.bridgelabz.userloginwebapp.controler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {
}
