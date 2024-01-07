/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controllers;

import dao.AccountDao;
import dto.ForgetPassDto;
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
public class ForgetPassController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String newPass = request.getParameter("password");
        String confirm = request.getParameter("confirm");

        String error = "";
        ForgetPassDto forgetPassDto = new ForgetPassDto(email, newPass, confirm);
        List<ValidationError> errors = forgetPassDto.validate();
        AccountDao khd = new AccountDao();

        // Initialize the Account object
        Account account = new Account();
        account.setEmail(email);
        Account ac = khd.selectByEmail(account);

        // Check if the email exists in the database
        if (ac==null) {
            error = "Email does not exist!";
        } else {
            // Check for validation errors
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
            } else {
                // Update the password
                String newPass_Sha1 = Encode.toSHA1(newPass);
                ac.setPassword(newPass_Sha1);
                if (khd.forgetPassword(ac)) {
                    error = "Change password successfully!";
                } else {
                    error = "Cannot change password!";
                }
            }
        }

        request.setAttribute("error", error);
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
