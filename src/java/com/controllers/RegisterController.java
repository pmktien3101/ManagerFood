package com.controllers;

import dao.AccountDao;
import dao.RegionDao;
import dto.RegisterDto;
import error.ValidationError;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Region;

public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("loadRegister".equals(action)) {
            loadRegister(req, resp);
        } else if ("registerUser".equals(action)) {
            registerUser(req, resp);
        }
    }

    private void loadRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegionDao allRegion = new RegionDao();
        List<Region> allCategory = allRegion.selectAll();
        req.setAttribute("regions", allCategory);
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegisterDto dto = new RegisterDto();
        dto.setName(req.getParameter("name"));
        dto.setEmail(req.getParameter("email"));
        dto.setPhone(req.getParameter("phone"));
        dto.setRegion(req.getParameter("region"));
        dto.setTaxCode(req.getParameter("taxcode"));
        dto.setPassword(req.getParameter("password"));
        dto.setConfirmPassword(req.getParameter("confirm"));
        req.setAttribute("name", dto.getName());
        req.setAttribute("email", dto.getEmail());
        req.setAttribute("phone", dto.getPhone());
        req.setAttribute("taxcode", dto.getTaxCode());

        RegionDao allRegion = new RegionDao();
        List<Region> allCategory = allRegion.selectAll();
        req.setAttribute("regions", allCategory);

        String error = "";
        List<ValidationError> errors = dto.validate();

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else {
            boolean createAccountResult = AccountDao.createUser(dto);

            if (createAccountResult) {
                 req.getRequestDispatcher("success.jsp").forward(req, resp);
            } else {
                try {
                    if (AccountDao.isEmailExisted(dto.getEmail())
                            || AccountDao.isPhoneExisted(dto.getPhone())
                            || AccountDao.isTaxCodeExisted(dto.getTaxCode())) {
                        error += "Email, phone, or taxcode already exists";
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                }

                req.setAttribute("error", error);
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadRegister(req, resp);
    }
}
