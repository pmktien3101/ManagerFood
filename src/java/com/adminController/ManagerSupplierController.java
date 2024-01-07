/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.adminController;

import dao.InvoiceDao;
import dao.SupplierDao;
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
import model.Supplier;

/**
 *
 * @author MSI PC
 */
public class ManagerSupplierController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("deleteSupplier".equals(action)) {
            deleteSupplier(req, resp);
        }   else {
            manageSupplier(req, resp);
        }
    }
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("addSupplier".equals(action)) {
            try {
                addSupplier(req, resp);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ManagerSupplierController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ManagerSupplierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void addSupplier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {
        String name = req.getParameter("name");
        String nation = req.getParameter("nation");
        // Check if the supplier name already exists
            SupplierDao supplierDao = new SupplierDao();
            if (supplierDao.isNameExisted(name)) {
                req.setAttribute("error", "Supplier name already exists.");
                req.getRequestDispatcher("managerSupplier.jsp").forward(req, resp);
            } else {
                Supplier newSupplier = new Supplier();
                newSupplier.setName(name);
                newSupplier.setNation(nation);
                int result = supplierDao.insert(name, nation);
                
                if (result > 0) {
                    resp.sendRedirect("ManagerSupplier");
                } else {
                    req.setAttribute("error", "Failed to add the new supplier company.");
                    req.getRequestDispatcher("managerSupplier.jsp").forward(req, resp);
                }
            }

    }
    
    private void deleteSupplier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String id = req.getParameter("id");

    // Delete the association in the Invoice table
    InvoiceDao invoiceDao = new InvoiceDao();
    invoiceDao.updateSupplierId(id);

    // Now, delete the supplier itself
    SupplierDao supplierDao = new SupplierDao();
    supplierDao.deleteSupplierId(id);

    resp.sendRedirect("ManagerSupplier");
}


    private void manageSupplier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplierDao supplierDao = new SupplierDao();
        List<Supplier> suppliers = supplierDao.selectAll();

        req.setAttribute("suppliers", suppliers);
        req.getRequestDispatcher("managerSupplier.jsp").forward(req, resp);
    }
}
