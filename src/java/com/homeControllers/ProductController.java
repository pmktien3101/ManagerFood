/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.homeControllers;

import com.controllers.*;
import dao.AccountDao;
import dao.BrandDao;
import dao.CategoryDao;
import dao.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Brand;
import model.Category;
import model.Product;

/**
 *
 * @author MSI PC
 */
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("cid");
        String brandId = req.getParameter("bid");

        ProductDao productDao = new ProductDao();
        List<Product> products;

        if (categoryId != null) {
            // Fetch products based on category ID
            products = productDao.getProductByCId(categoryId);
        } else if (brandId != null) {
            // Fetch products based on brand ID
            products = productDao.getProductByBId(brandId);
        } else {
            // Fetch all products if no category or brand is selected
            products = productDao.selectAll();
        }

        CategoryDao categoryDao = new CategoryDao();
        List<Category> allCategory = categoryDao.selectAll();

        BrandDao brandDao = new BrandDao();
        List<Brand> allBrand = brandDao.selectAll();

        req.setAttribute("brands", allBrand);
        req.setAttribute("products", products);
        req.setAttribute("category", allCategory);
        req.setAttribute("tag", categoryId);
        req.setAttribute("tagg", brandId);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("cid");
        String brandId = req.getParameter("bid");

        ProductDao productDao = new ProductDao();
        List<Product> products;

        if (categoryId != null) {
            // Fetch products based on category ID
            products = productDao.getProductByCId(categoryId);
        } else if (brandId != null) {
            // Fetch products based on brand ID
            products = productDao.getProductByBId(brandId);
        } else {
            // Fetch all products if no category or brand is selected
            products = productDao.selectAll();
        }

        CategoryDao categoryDao = new CategoryDao();
        List<Category> allCategory = categoryDao.selectAll();

        BrandDao brandDao = new BrandDao();
        List<Brand> allBrand = brandDao.selectAll();


        req.setAttribute("brands", allBrand);
        req.setAttribute("products", products);
        req.setAttribute("category", allCategory);
        req.setAttribute("tag", categoryId);
        req.setAttribute("tagg", brandId);

        // Forward the request to the JSP page
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

}
