<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>


<body>


<div >
  <form method="post">
    <table>
      <caption>
        <h2>Tạo thông tin thuê trọ </h2>
      </caption>
      <tr>
        <th>Mã phòng trọ:</th>
        <td>
          <input type="text" name="maPhongTro" id="maPhongTro" size="45"/>
        </td>
      </tr>
      <tr>
        <th>Tên người thuê:</th>
        <td>
          <input type="text" name="tenNguoiThue" id="tenNguoiThue" size="45"/>
        </td>
      </tr>
      <tr>
        <th>Sdt:</th>
        <td>
          <input type="text" name="soDienThoai" id="soDienThoai" size="15"/>
        </td>
      </tr>
      <tr>
        <th>Ngày bắt đầu thuê:</th>
        <td>
          <input type="date" name="ngayBatDauThue" id="ngayBatDauThue" size="15"/>
        </td>
      </tr>
      <tr>
        <th>Hình thức thanh toán:</th>
        <td>
          <input type="text" name="hinhThucThanhToan" id="hinhThucThanhToan" size="15"/>
        </td>
      </tr>
      <tr>
        <th>Ghi chú:</th>
        <td>
          <input type="text" name="ghiChu" id="ghiChu" size="15"/>
        </td>
      </tr>

      <tr>
        <td colspan="2" >
          <input type="submit" value="Save"/>
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>