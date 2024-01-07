/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controllers;

import dao.AccountDao;
import dao.RegionDao;
import dto.ChangeNameDto;
import error.ValidationError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Region;

/**
 *
 * @author MSI PC
 */
public class ChangeNameController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("changeName.jsp").forward(req, resp);
    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String oldName = request.getParameter("name");
    String newName = request.getParameter("newName");
    String email = ((Account) request.getSession().getAttribute("account")).getEmail();
    String error = "";

    // Validate input using ChangeNameDto
    ChangeNameDto dto = new ChangeNameDto(oldName, newName);
    List<ValidationError> errors = dto.validate();

    if (!errors.isEmpty()) {
        // If validation errors are present, set them in the request attributes
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("changeName.jsp").forward(request, response);
        return;
    }

    // If validation is successful, proceed with updating the name
    AccountDao accountDao = new AccountDao();
    boolean success = accountDao.changeInfor(newName, email);

    if (success) {
        // Set the new name to the Account object in the session
        Account account = (Account) request.getSession().getAttribute("account");
        account.setName(newName);

        error = "Change information successfully";
    } else {
        error = "Cannot change information";
    }

    request.setAttribute("error", error);
    request.getRequestDispatcher("changeName.jsp").forward(request, response);
}


}
