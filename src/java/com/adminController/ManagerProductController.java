/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.adminController;

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
public class ManagerProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("deleteProduct".equals(action)) {
            deleteProduct(req, resp);
        } else {
            manageProduct(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("loadProduct".equals(action)) {
            loadProduct(req, resp);
        } else if ("editProduct".equals(action)) {
            updateProduct(req, resp);
        } else if ("loadProductA".equals(action)) {
            loadProductA(req, resp);
        }if ("addProduct".equals(action)) {
            addProduct(req, resp);
        }

    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ProductDao productDao = new ProductDao();
        productDao.deleteProduct(id);
        resp.sendRedirect("ManagerProduct");
    }

    private void loadProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("pid");
        ProductDao dao = new ProductDao();
        Product product = dao.getProductById(id);
        CategoryDao categoryDao = new CategoryDao();
        List<Category> allCategory = categoryDao.selectAll();
        BrandDao brandDao = new BrandDao();
        List<Brand> allBrand = brandDao.selectAll();
        req.setAttribute("category", allCategory);
        req.setAttribute("brands", allBrand);
        req.setAttribute("detail", product);
        req.getRequestDispatcher("editProduct.jsp").forward(req, resp);
    }

    private void loadProductA(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        CategoryDao categoryDao = new CategoryDao();
        List<Category> allCategory = categoryDao.selectAll();
        BrandDao brandDao = new BrandDao();
        List<Brand> allBrand = brandDao.selectAll();
        req.setAttribute("category", allCategory);
        req.setAttribute("brands", allBrand);
        req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve data from the form
        String productId = req.getParameter("id");
        String name = req.getParameter("name");
        String image = req.getParameter("image");
        String price = req.getParameter("price");
        String category = req.getParameter("category");
        String description = req.getParameter("description");
        String brand = req.getParameter("brand");

        // Update the product in the database
        ProductDao productDao = new ProductDao();
        productDao.updateProduct(name, image, price, category, description, brand, productId);
        resp.sendRedirect("ManagerProduct");
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String image = req.getParameter("image");
        String price = req.getParameter("price");
        String category = req.getParameter("category");
        String description = req.getParameter("description");
        String brand = req.getParameter("brand");

        ProductDao productDao = new ProductDao();
        productDao.insert(name, image, price, category, description, brand);
        resp.sendRedirect("ManagerProduct");
    }

    private void manageProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao ProductDao = new ProductDao();
        List<Product> products = ProductDao.selectAll();
        CategoryDao categoryDao = new CategoryDao();
        List<Category> allCategory = categoryDao.selectAll();
        BrandDao brandDao = new BrandDao();
        List<Brand> allBrand = brandDao.selectAll();

        req.setAttribute("category", allCategory);
        req.setAttribute("brands", allBrand);
        req.setAttribute("products", products);
        req.getRequestDispatcher("managerProduct.jsp").forward(req, resp);
    }

}
