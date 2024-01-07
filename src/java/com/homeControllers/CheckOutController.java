package com.homeControllers;

import dao.OrderDao;
import dao.PaymentDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Cart;
import model.Order;
import model.Payment;
import model.ShoppingCart;

public class CheckOutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("loadPayment".equals(action)) {
            loadPayment(req, resp);
        } else if ("addOrder".equals(action)) {
            addOrder(req, resp);
        }
    }

    private void loadPayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        // Get payment list from the database
        PaymentDao dao = new PaymentDao();
        List<Payment> allPayment = dao.selectAll();
        req.setAttribute("paymentList", allPayment);
        req.getRequestDispatcher("myOrders.jsp").forward(req, resp);
    }

    private void addOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        String error = "";

        if (account != null && cart != null) {
            String email = account.getEmail();
            // Get total directly from the session attribute
            double total = cart.getTotal();
            String address = req.getParameter("address");
            String paymentId = req.getParameter("payment");

            OrderDao.insert(total, address, paymentId, email);
            cart.clear();
            req.getRequestDispatcher("orderSuccess.jsp").forward(req, resp);
        } else {
            error = "Cannot order products";
        }
        req.setAttribute("error", error);
        req.getRequestDispatcher("myOrders.jsp").forward(req, resp);

    }
}
