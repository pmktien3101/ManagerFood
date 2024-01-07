/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controllers;

import dao.AccountDao;
import dto.ChangePassDto;
import error.ValidationError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class ChangePassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPass = request.getParameter("oldpass");
        String newPass = request.getParameter("newpass");
        String confirm = request.getParameter("confirm");

        String oldPass_Sha1 = Encode.toSHA1(oldPass);

        String error = "";

        // Pass old password, new password, and confirm to the ChangePassDto constructor
        ChangePassDto changePassDto = new ChangePassDto(oldPass, newPass, confirm);
        List<ValidationError> errors = changePassDto.validate();

        // Kiem tra mat khau cu co giong hay khong
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("account");
        Account account = null;
        if (obj != null) {
            account = (Account) obj;
        }
        if (account == null) {
            error = "You are not logged into the system!";
        } else {
            // Neu khach hang da dang nhap
            if (!oldPass_Sha1.equals(account.getPassword())) {
                error = "Old password does not correct!";
            } else {
                if (!errors.isEmpty()) {
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("changePass.jsp").forward(request, response);
                } else {
                    
                        String newPass_Sha1 = Encode.toSHA1(newPass);
                        account.setPassword(newPass_Sha1);
                        AccountDao khd = new AccountDao();
                        if (khd.changePassword(account)) {
                            error = "Change password successfully!";
                        } else {
                            error = "Cannot change password!";
                        }
                    
                }
            }
        }

        request.setAttribute("error", error);
        request.getRequestDispatcher("changePass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
