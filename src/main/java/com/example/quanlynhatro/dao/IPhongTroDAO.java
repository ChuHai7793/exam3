package com.example.quanlynhatro.dao;



import com.example.quanlynhatro.model.PhongTro;

import java.sql.SQLException;
import java.util.List;

public interface IPhongTroDAO {
    public void insertPhongTro(PhongTro PhongTro) throws SQLException;

    public PhongTro selectPhongTro(int id);

    public List<PhongTro> selectAllPhongTros();
    public List<PhongTro> selectAllPhongTrosUsingCallableStatement();

    public boolean deletePhongTro(int id) throws SQLException;

    public boolean updatePhongTro(PhongTro PhongTro) throws SQLException;

    public List<PhongTro> selectAllPhongTrosByCountry(String country) throws SQLException;

    public List<PhongTro> sortByName();

    PhongTro getPhongTroById(int id);

    //    @Override
    //    public void insertPhongTroStore(PhongTro phongTro) throws SQLException {
    //        String query = "{CALL InsertPhongTro(?,?,?,?,?,?)}";
    //
    //        try (Connection connection = getConnection();
    //             CallableStatement callableStatement = connection.prepareCall(query);) {
    //            callableStatement.setString(1, phongTro.getMaPhongTro());
    //            callableStatement.setString(2, phongTro.getTenNguoiThue());
    //            callableStatement.setString(3, phongTro.getSoDienThoai());
    //            callableStatement.setDate(4, phongTro.getNgayBatDauThue());
    //            callableStatement.setString(5, phongTro.getHinhThucThanhToan());
    //            callableStatement.setString(6, phongTro.getGhiChu());
    //            System.out.println(callableStatement);
    //            callableStatement.executeUpdate();
    //        } catch (SQLException e) {
    //            e.printStackTrace();
    //        }
    //    }
    void addPhongTroTransaction(PhongTro phongTro);

//    void insertPhongTroStore(PhongTro PhongTro) throws SQLException;

//    void addPhongTroTransaction(PhongTro PhongTron);
}