/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.adminController;

import dao.AccountDao;
import dao.RegionDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ManagerAccountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("deleteRegion".equals(action)) {
            deleteRegion(req, resp);
        } else {
            AccountDao accountDao = new AccountDao();
            List<Account> accounts = accountDao.selectAll();
            RegionDao regionDao = new RegionDao();
            List<Region> regions = regionDao.selectAll();

            req.setAttribute("regions", regions);
            req.setAttribute("accounts", accounts);
            req.getRequestDispatcher("managerAccount.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addRegion".equals(action)) {
            addRegion(request, response);
        }
    }

    private void addRegion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String regionName = request.getParameter("name");
            
            // Check if the region name already exists
            RegionDao regionDao = new RegionDao();
            if (regionDao.isNameExisted(regionName)) {
                request.setAttribute("error", "Region name already exists.");
                request.getRequestDispatcher("managerAccount.jsp").forward(request, response);
            } else {
                Region newRegion = new Region();
                newRegion.setName(regionName);
                
                int result = regionDao.insert(regionName);
                
                if (result > 0) {
                    response.sendRedirect("ManagerAccount");
                } else {
                    request.setAttribute("error", "Failed to add the new region.");
                    request.getRequestDispatcher("managerAccount.jsp").forward(request, response);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagerAccountController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagerAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteRegion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        // Delete associated accounts first
        AccountDao accountDao = new AccountDao();
        List<Account> accountsInRegion = accountDao.getAccountByRId(id);

        for (Account account : accountsInRegion) {
            accountDao.deleteAccountByRId(account.getRegion().getId());
        }

        // Delete the region itself
        RegionDao regionDao = new RegionDao();
        regionDao.deleteRegionId(id);

        resp.sendRedirect("ManagerAccount");
    }

}
