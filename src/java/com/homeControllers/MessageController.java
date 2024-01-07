/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.homeControllers;

import dao.AccountDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author MSI PC
 */
public class MessageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");

        if (account != null) {
            String email = account.getEmail();
            String subject = req.getParameter("subject");
            String body = req.getParameter("body");
            AccountDao accountDao = new AccountDao();
            accountDao.insertM(email, subject, body);

            req.setAttribute("msg", "valid");
            req.getRequestDispatcher("messageUs.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "invalid");
            req.getRequestDispatcher("messageUs.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("messageUs.jsp").forward(req, resp);

    }

}
