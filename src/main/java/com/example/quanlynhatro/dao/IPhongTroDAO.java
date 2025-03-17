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

//    void insertPhongTroStore(PhongTro PhongTro) throws SQLException;

//    void addPhongTroTransaction(PhongTro PhongTron);
}