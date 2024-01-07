/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controllers;

import dao.AccountDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import utils.Encode;

/**
 *
 * @author MSI PC
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String passWord = req.getParameter("password");
        HttpSession session = req.getSession();

        if ("admin@gmail.com".equals(email) && "admin".equals(passWord)) {
            // Create a dummy account for admin
            Account adminAccount = new Account();
            adminAccount.setEmail(email);
            adminAccount.setPassword(passWord);
            session.setAttribute("account", adminAccount);
            resp.sendRedirect("adminHome.jsp");
        } else {
            passWord = Encode.toSHA1(passWord);
            Account account = new Account();
            account.setEmail(email);
            account.setPassword(passWord);

            AccountDao ad = new AccountDao();
            Account ac = ad.selectByEmailAndPass(account);
            if (ac != null) {
                session.setAttribute("account", ac);
                resp.sendRedirect("./Home");

            } else {
                req.setAttribute("error", "Wrong email or password");
                req.getRequestDispatcher("login.jsp").forward(req, resp);

            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);

    }
}
