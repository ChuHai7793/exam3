package com.example.quanlynhatro.service;


import com.example.quanlynhatro.dao.PhongTroDAOImpl;
import com.example.quanlynhatro.model.PhongTro;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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
        String ngayBatDauThue = request.getParameter("ngayBatDauThue");
        String hinhThucThanhToan = request.getParameter("hinhThucThanhToan");
        if (Objects.equals(hinhThucThanhToan, "theo nam")){
            hinhThucThanhToan = "3";
        } else if (Objects.equals(hinhThucThanhToan, "theo quy")){
            hinhThucThanhToan = "2";
        } else if (Objects.equals(hinhThucThanhToan, "theo thang")){
            hinhThucThanhToan = "1";
        }
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
            PhongTroDAO.deleteMultiplePhongTro(selectedIds);
        }
        listPhongTro(request,response);
    }


}
