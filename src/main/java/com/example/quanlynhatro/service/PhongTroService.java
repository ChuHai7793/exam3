package com.example.quanlynhatro.service;

import com.example.quanlynhatro.dao.IPhongTroDAO;
import com.example.quanlynhatro.dao.PhongTroDAOImpl;
import com.example.quanlynhatro.model.PhongTro;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongTroService {
    static private PhongTroDAOImpl PhongTroDAO;

    public PhongTroService() {
        PhongTroDAO = new PhongTroDAOImpl();
    }

    public void listPhongTro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<PhongTro> listPhongTros = PhongTroDAO.selectAllPhongTros();
        request.setAttribute("listPhongTros", listPhongTros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }


    public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("phongTro/create.jsp");
        dispatcher.forward(request, response);
    }


    public void insertPhongTro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

//        ----------------------------- insertUser OR insertUserStore -------------------------

        String maPhongTro = request.getParameter("maPhongTro");
        String tenNguoiThue = request.getParameter("tenNguoiThue");
        String soDienThoai = request.getParameter("soDienThoai");
        Date ngayBatDauThue = Date.valueOf(request.getParameter("ngayBatDauThue"));
        String hinhThucThanhToan = request.getParameter("hinhThucThanhToan");
        String ghiChu = request.getParameter("ghiChu");
        PhongTro newPhongTro = new PhongTro(maPhongTro, tenNguoiThue,
                soDienThoai,ngayBatDauThue,hinhThucThanhToan,ghiChu);
        PhongTroDAO.insertPhongTro(newPhongTro);// Su dung CallableStatement
        listPhongTro(request,response);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("phongTro/create.jsp");
//        dispatcher.forward(request, response);
    }
    public void findPhongTro( HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String keyword = request.getParameter("keyword");
        List<PhongTro> listPhongTros = PhongTroDAO.findPhongTro(keyword);
        request.setAttribute("listPhongTros", listPhongTros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    public void deleteMultiplePhongTro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String[] selectedIds = request.getParameterValues("tickPhongTro");
        if (selectedIds != null) {
            int[] ids = new int[selectedIds.length];
            for (int i = 0; i < selectedIds.length; i++) {
                ids[i] = Integer.parseInt(selectedIds[i]);
            }
            PhongTroDAO.deleteMultiplePhongTro(selectedIds);
        }
        listPhongTro(request,response);
    }

//    public void insertUserTransaction(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//
////        ----------------------------- addUserTransaction -------------------------
//
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String country = request.getParameter("country");
//
//        String add = request.getParameter("add");
//        String edit = request.getParameter("edit");
//        String delete = request.getParameter("delete");
//        String view = request.getParameter("view");
//        List<Integer> permissions = new ArrayList<>();
//        if (add != null){
//            permissions.add(1);
//        }
//        if (edit != null){
//            permissions.add(2);
//        }
//        if (delete != null){
//            permissions.add(3);
//        }
//        if (view != null){
//            permissions.add(4);
//        }
//        User newUser = new User(name, email, country);
//
//        userDAO.addUserTransaction(newUser,permissions);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
//        dispatcher.forward(request, response);
//    }
//    public void updateUser(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String country = request.getParameter("country");
//
//        String message;
//        if (Validate.isValidEmail(email)){
//            message = "UPDATE SUCCESSFUL";
//            User user = new User(id, name, email, country);
//            request.setAttribute("user", user);
//            request.setAttribute("message", message);
//            userDAO.updateUser(user);
//        } else {
//            message = "INVALID EMAIL";
//            User user = new User(id, name, email, country);
//            request.setAttribute("user", user);
//            request.setAttribute("message", message);
//        }
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    public void deleteUser(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        userDAO.deleteUser(id);
//
//        List<User> listUser = userDAO.selectAllUsers();
//        request.setAttribute("listUser", listUser);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    public void listUserByCountry(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        String country = request.getParameter("country");
//        List<User> listUser = userDAO.selectAllUsersByCountry(country);
//        request.setAttribute("listUser", listUser);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    public void sortUserByName(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//
//        List<User> listUser = userDAO.sortByName();
//        request.setAttribute("listUser", listUser);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//        dispatcher.forward(request, response);
//    }
}
