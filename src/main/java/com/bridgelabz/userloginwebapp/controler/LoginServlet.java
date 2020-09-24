package com.bridgelabz.userloginwebapp.controler;

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

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    UserService userService;
    User user;

    public LoginServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getServletPath();
        if (action.equals("/login")){
            try {
                this.userLogin(req, res);
            } catch (UserLoginException | SQLException e) {
                e.printStackTrace();
            }
        }
        super.doGet(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/register" :
                try {
                    this.userRegistration(req, res);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/edit" :
                try {
                    this.forwardToUpdatePage(req, res);
                } catch (UserLoginException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/update" :
                try {
                    this.updateUser(req, res);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
            case "/delete" :
                try {
                    this.deleteUser(req, res);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                this.getLoginPage(req, res);
                break;
        }
    }

    private void getLoginPage(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("views/login.jsp");
        rd.forward(req, res);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException {
        userObject(req);
        userService.updateUser(user);
        res.sendRedirect("views/editOrDelete.jsp");
    }

    private void userObject(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phoneNo = req.getParameter("phoneNo");
        this.user = new User(firstName, lastName, email, password, phoneNo);
    }

    private void forwardToUpdatePage(HttpServletRequest req, HttpServletResponse res)
            throws UserLoginException, SQLException, ServletException, IOException {
        String email = req.getParameter("email");
        this.user = userService.getUsetDetail(email);
        req.setAttribute("user", this.user);
        req.getRequestDispatcher("views/register.jsp").forward(req, res);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
        String email = req.getParameter("email");
        userService.deleteUser(email);
        res.sendRedirect("views/login.jsp");
    }

    private void userRegistration(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException, ServletException {
        userObject(req);
        if (userService.checkInput(user).equals("success")){
            userService.registerUser(user);
            res.sendRedirect("./login.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("./register.jsp");
            PrintWriter out = res.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('"+userService.checkInput(user)+"');");
            out.println("</script>");
            rd.include(req, res);
        }
    }

    private void userLogin(HttpServletRequest req, HttpServletResponse res)
            throws UserLoginException, SQLException, IOException {
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        this.user = userService.getUsetDetail(email);

        try {
            if (this.user != null) {
                if (this.user.password.equals(pwd)){
                    req.setAttribute("email", email);
                    req.getRequestDispatcher("views/editOrDelete.jsp").forward(req, res);
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("views/login.jsp");
                    PrintWriter out = res.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('wrong password');");
                    out.println("</script>");
                    rd.include(req, res);
                }
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("./login.jsp");
                PrintWriter out = res.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('wrong email Id');");
                out.println("</script>");
                rd.include(req, res);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
