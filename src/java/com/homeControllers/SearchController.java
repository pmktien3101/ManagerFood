/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.homeControllers;

import dao.BrandDao;
import dao.CategoryDao;
import dao.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Brand;
import model.Category;
import model.Product;

/**
 *
 * @author MSI PC
 */
public class SearchController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String textSearch = req.getParameter("txt");
        ProductDao productDao = new ProductDao();
        List<Product> list = productDao.getProductByName(textSearch);
        CategoryDao categoryDao = new CategoryDao();
        List<Category> allCategory = categoryDao.selectAll();
        BrandDao brandDao = new BrandDao();
        List<Brand> allBrand = brandDao.selectAll();
        
        req.setAttribute("category", allCategory);
        req.setAttribute("brands", allBrand);
        req.setAttribute("products", list);
        req.getRequestDispatcher("home.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("home.jsp").forward(req, resp);

    }

}
