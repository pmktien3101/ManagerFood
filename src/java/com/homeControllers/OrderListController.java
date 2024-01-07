/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.homeControllers;

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
public class OrderListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        String action = req.getParameter("action");

        String email = account.getEmail();
        OrderDao orderDao = new OrderDao();

        if ("cancelOrder".equals(action)) {
            cancelOrder(req, resp);
        } else {
            List<Order> orders = orderDao.getOrdersByEmail(email);
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("orderList.jsp").forward(req, resp);
        }
    }

    private void cancelOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        OrderDao orderDao = new OrderDao();
        orderDao.cancelOrder(id);
        resp.sendRedirect("OrderList");
    }
}
