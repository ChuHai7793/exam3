package com.example.quanlynhatro.controller;

import com.example.quanlynhatro.dao.PhongTroDAOImpl;
import com.example.quanlynhatro.service.PhongTroService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PhongTroServlet", urlPatterns = "")
public class PhongTroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PhongTroDAOImpl userDAO;

    private PhongTroService phongTroService;

    //    public void init() {
//        userDAO = new UserDAOImpl();
//    }
    public void init() {
        phongTroService = new PhongTroService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    phongTroService.insertPhongTro(request, response);
                    break;
                case "edit":
                    break;

            }
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    phongTroService.showNewForm(request, response);
                    break;
                case "edit":
//                    userService.showEditForm(request, response);
                    break;
                case "search":
                    phongTroService.findPhongTro(request, response);
                    break;
                case "delete":
                    phongTroService.deleteMultiplePhongTro(request, response);
                    break;

                default:
                    phongTroService.listPhongTro(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            userService.deleteUser(req, resp);
//        } catch (SQLException e) {
//            throw new ServletException(e);
//        }
//    }
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            userService.showEditForm(req, resp);
//        } catch (SQLException e) {
//            throw new ServletException(e);
//        }
//    }

}