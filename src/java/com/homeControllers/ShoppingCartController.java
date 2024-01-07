/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.homeControllers;

import dao.AccountDao;
import dao.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Cart;
import model.Product;
import model.ShoppingCart;

/**
 *
 * @author MSI PC
 */
public class ShoppingCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addtocart".equals(action)) {
            addToCart(req, resp);
        } else if ("updatecartproduct".equals(action)) {
            updateCartProduct(req, resp);
        } else if ("removecartproduct".equals(action)) {
            removeCartProduct(req, resp);
        }
        req.getRequestDispatcher("myCart.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("myCart.jsp").forward(req, resp);

    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        String error = "";
        if (cart == null) {
            cart = new ShoppingCart();
        }
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        // Kiem tra coi product co ton tai k
        Product product = ProductDao.getProduct(productId);
        if (product != null) {
            // Kiem tra coi product co trong cart chua
            Cart cartProduct = null;
            int i = 0;
            for (Cart cp : cart) {
                if (cp.getProduct().getId() == productId) {
                    cartProduct = cp;
                    break;
                }
                i++;
            }
            if (cartProduct != null) {
                cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
                cart.set(i, cartProduct);
                error = "Product available in cart. This quantity of product increased!";
            } else {
                cartProduct = new Cart(product, quantity);
                cart.add(cartProduct);
                error = "Add product to cart successfully!";
            }
            cart.recalculateTotal();

        } else {
            error = "Something went wrong";

        }
        req.setAttribute("error", error);
        session.setAttribute("cart", cart);
    }

    private void updateCartProduct(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        String error = "";
        if (cart != null) {

            int index = Integer.parseInt(req.getParameter("index"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            Cart cartProduct = cart.get(index);
            cartProduct.setQuantity(quantity);
            error = "update quantity of product sucessfully!";
            cart.recalculateTotal();
            session.setAttribute("cart", cart);
        } else {
            error = "Something went wrong";

        }
        req.setAttribute("error", error);
    }

    private void removeCartProduct(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        String error = "";
        if (cart != null) {

            int index = Integer.parseInt(req.getParameter("index"));
            cart.remove(index);
            cart.recalculateTotal();
            error = "delete product out of cart sucessfully!";
            session.setAttribute("cart", cart);
        } else {
            error = "Something went wrong";

        }
    }

}
