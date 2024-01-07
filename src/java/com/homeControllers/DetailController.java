///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package com.homeControllers;
//
//import dao.ProductDao;
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import model.Product;
//
///**
// *
// * @author MSI PC
// */
//public class DetailController extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        Object obj = session.getAttribute("account");
//        
//        String id = req.getParameter("pid");
//        ProductDao dao = new ProductDao();
//        Product product = dao.getProductById(id);
//        req.setAttribute("detail", product);
//        req.getRequestDispatcher("productDetail.jsp").forward(req, resp);
//
//    }
// @Override
//    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("productDetail.jsp").forward(req, resp);
//
//    }
//}
