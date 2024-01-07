/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.adminController;

import dao.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Order;

/**
 *
 * @author MSI PC
 */
public class ManagerOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDao orderDao = new OrderDao();
        List<Order> orders = orderDao.selectAll();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("managerOrder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("updateOrderStatus".equals(action)) {
            String orderId = req.getParameter("orderId");
            OrderDao.updateOrderStatus(orderId);
        }
        // Redirect back to doGet after updating
        resp.sendRedirect( "ManagerOrder");
    }

}
