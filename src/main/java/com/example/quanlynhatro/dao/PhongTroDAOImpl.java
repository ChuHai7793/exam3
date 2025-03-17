package com.example.quanlynhatro.dao;


import com.example.quanlynhatro.model.PhongTro;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhongTroDAOImpl implements IPhongTroDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/quanlyphongtro?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "7793";

    private static final String INSERT_PHONGTROS_SQL = "INSERT INTO phong_tro (ma_phong_tro, ten_nguoi_thue, so_dien_thoai,ngay_bat_dau,hinh_thuc_tt_id,ghi_chu) VALUES (?, ?, ?,?,?,?);";
    private static final String SELECT_ALL_PHONGTROS = "select * from phong_tro";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";

    public PhongTroDAOImpl() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public PhongTro selectPhongTro(int id) {
        return null;
    }

    @Override
    public List<PhongTro> selectAllPhongTrosUsingCallableStatement() {
        return Collections.emptyList();
    }



    @Override
    public boolean updatePhongTro(PhongTro PhongTro) throws SQLException {
        return false;
    }

    @Override
    public List<PhongTro> selectAllPhongTrosByCountry(String country) throws SQLException {
        return Collections.emptyList();
    }

    @Override
    public List<PhongTro> sortByName() {
        return Collections.emptyList();
    }

    @Override
    public PhongTro getPhongTroById(int id) {
        return null;
    }

//    @Override
//    public void insertUser(User user) throws SQLException {
//        System.out.println(INSERT_USERS_SQL);
//        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getEmail());
//            preparedStatement.setString(3, user.getCountry());
//            System.out.println(preparedStatement);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//    }
//    @Override
//    public User selectUser(int id) {
//        User user = null;
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
//            preparedStatement.setInt(1, id);
//            System.out.println(preparedStatement);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String country = rs.getString("country");
//                user = new User(id, name, email, country);
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return user;
//    }
    @Override
    public List<PhongTro> selectAllPhongTros() {

        List<PhongTro> phongTros = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PHONGTROS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String ma_phong_tro = rs.getString("ma_phong_tro");
                String ten_nguoi_thue = rs.getString("ten_nguoi_thue");
                String so_dien_thoai = rs.getString("so_dien_thoai");
                String ngay_bat_dau = rs.getString("ngay_bat_dau");
                String hinh_thuc_tt_id = rs.getString("hinh_thuc_tt_id");
                String ghi_chu = rs.getString("ghi_chu");
                phongTros.add(new PhongTro(ma_phong_tro, ten_nguoi_thue, so_dien_thoai,
                        ngay_bat_dau,hinh_thuc_tt_id,ghi_chu));
            }

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return phongTros;
    }

    @Override
    public boolean deletePhongTro(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public void deleteMultiplePhongTro(String[] ids) {
        String sql = "DELETE FROM phong_tro WHERE ma_phong_tro = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (String id : ids) {
                ps.setString(1, id);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void insertPhongTro(PhongTro phongTro) throws SQLException {
        System.out.println(INSERT_PHONGTROS_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PHONGTROS_SQL)) {
            preparedStatement.setString(1, phongTro.getMaPhongTro());
            preparedStatement.setString(2, phongTro.getTenNguoiThue());
            preparedStatement.setString(3, phongTro.getSoDienThoai());
            preparedStatement.setDate(4, Date.valueOf(phongTro.getNgayBatDauThue()));
            preparedStatement.setInt(5, Integer.parseInt( phongTro.getHinhThucThanhToan()));
            preparedStatement.setInt(5, Integer.parseInt( phongTro.getHinhThucThanhToan()));
            preparedStatement.setString(6, phongTro.getGhiChu());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<PhongTro> findPhongTro(String keyword) throws SQLException {
        List<PhongTro> phongTros = new ArrayList<>();
        try (Connection connection = getConnection(); CallableStatement stmt = connection.prepareCall("{CALL TimKiemPhongTro(?)}")) {

            stmt.setString(1, keyword);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String ma_phong_tro =  rs.getString("ma_phong_tro");
                String ten_nguoi_thue =  rs.getString("ten_nguoi_thue");
                String so_dien_thoai =  rs.getString("so_dien_thoai");
                String ngay_bat_dau =  rs.getString("ngay_bat_dau");
                String ghi_chu =  rs.getString("ghi_chu");
                String hinh_thuc_tt_id =  rs.getString("hinh_thuc_tt_id");
                PhongTro phongTro = new PhongTro(ma_phong_tro,ten_nguoi_thue,so_dien_thoai,
                        ngay_bat_dau,ghi_chu,hinh_thuc_tt_id);
                phongTros.add(phongTro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phongTros;
    }
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
@Override
public void addPhongTroTransaction(PhongTro phongTro) {
        Connection conn = null;
        // for insert a new user
        PreparedStatement pstmt = null;

        // for assign permision to user
        PreparedStatement pstmtAssignment = null;

        // for getting user id
        ResultSet rs = null;
        try {
            conn = getConnection();

            // set auto commit to false
            conn.setAutoCommit(false);

            // Insert user
            pstmt = conn.prepareStatement(INSERT_PHONGTROS_SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, phongTro.getMaPhongTro());
            pstmt.setString(2, phongTro.getTenNguoiThue());
            pstmt.setString(3, phongTro.getSoDienThoai());
            pstmt.setDate(4, Date.valueOf(phongTro.getNgayBatDauThue()));
            pstmt.setString(5, phongTro.getHinhThucThanhToan());
            pstmt.setString(6, phongTro.getGhiChu());
            int rowAffected = pstmt.executeUpdate();

//            // get user id
//            rs = pstmt.getGeneratedKeys();
//
//            int userId = 0;
//            if (rs.next())
//                userId = rs.getInt(1);
//
//            // in case the insert operation successes, assign permision to user
//            if (rowAffected == 1) {
//                // assign permision to user
//                String sqlPivot = "INSERT INTO user_permision(user_id,permision_id) "
//                        + "VALUES(?,?)";
//                pstmtAssignment = conn.prepareStatement(sqlPivot);
//
//                for (int permisionId : permissions) {
//                    pstmtAssignment.setInt(1, userId);
//                    pstmtAssignment.setInt(2, permisionId);
//                    pstmtAssignment.executeUpdate();
//                }
//                conn.commit();
//            } else {
//                conn.rollback();
//            }

        } catch (SQLException ex) {
            // roll back the transaction
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (pstmtAssignment != null) pstmtAssignment.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
