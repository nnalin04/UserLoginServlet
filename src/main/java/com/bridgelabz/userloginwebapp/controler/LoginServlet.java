package com.bridgelabz.userloginwebapp.controler;

import com.bridgelabz.userloginwebapp.Repositories.UserRepo;
import com.bridgelabz.userloginwebapp.exception.UserLoginException;
import com.bridgelabz.userloginwebapp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/"})
public class LoginServlet extends HttpServlet {

    UserService userService;
    User user;

    public LoginServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/register")){
            try {
                this.userObject(req);
                if (userService.checkInput(user).equals("success")){
                    userService.registerUser(user);
                    res.sendRedirect("./views/login.jsp");
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("./views/register.jsp");
                    PrintWriter out = res.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('"+userService.checkInput(user)+"');");
                    out.println("</script>");
                    rd.include(req, res);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        String path = req.getServletPath();
        if (path.equals("/login")){
            UserRepo loginDao = new UserRepo();

            String username = req.getParameter("email");
            String password = req.getParameter("pwd");
            User user = new User();
            user.email = username;
            user.password = password;
            if (loginDao.loginUser(user))
            {
                res.sendRedirect("./loginSuccess.jsp");
            }
            else
            {
                res.sendRedirect("./login.jsp");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/edit")){
            String email = req.getParameter("email");
            try {
                this.user = userService.getUsetDetail(email);
            } catch (UserLoginException | SQLException e) {
                e.printStackTrace();
            }
            req.getSession().getAttribute("user");
            req.getSession().setAttribute("user", this.user);
            req.getRequestDispatcher("./views/register.jsp").forward(req, res);
        }
        if (path.equals("/update")){
            userObject(req);
            try {
                userService.updateUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            res.sendRedirect("./views/editOrDelete.jsp");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        String path = req.getServletPath();
        if (path.equals("/delete")){
            String email = req.getParameter("email");
            try {
                userService.deleteUser(email);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            res.sendRedirect("./views/login.jsp");
        }
    }

    private void getLoginPage(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("./login.jsp");
        rd.forward(req, res);
    }

    private void userObject(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phoneNo = req.getParameter("phoneNo");
        this.user = new User(firstName, lastName, email, password, phoneNo);
    }
}
