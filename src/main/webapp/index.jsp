<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="css/style_index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .modal {
            display: none;
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: white;
            margin: 10% auto;
            padding: 20px;
            width: 60%;
            border-radius: 8px;
        }

        .close-btn {
            float: right;
            font-size: 20px;
            cursor: pointer;
        }

        table {
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>

</head>

<body>
<nav class="navbar navbar-expand-lg" style="background-color: #007bff;">
    <div class="container-fluid">
        <!-- User Management on the left -->
        <a class="nav-link text-light" href="http://localhost:8080">Quan ly phong tro</a>

        <!-- Toggler for mobile view -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>

<%--<button>--%>
<%--    <a href="?action=create">Add New PhongTro</a>--%>
<%--</button>--%>
<!-- Nút mở Modal -->
<button id="openModal">Tạo thông tin thuê trọ</button>
<form action="" method="get">
    <input type="hidden" name="action" value="search">
    <input type="text" name="keyword" placeholder="Nhập từ khóa...">
    <input type="submit" value="SEARCH">
</form>

<form action="?action=deleteMultiple" method="post" class="mb-3">
<div class="text-center">
    <h2>List of PhongTro</h2>
</div>
<div >
    <table >
        <tr>
            <th>STT</th>
            <th>Ma Phong Tro</th>
            <th>Ten nguoi thue tro</th>
            <th>Sdt</th>
            <th>Ngay bat dau thue</th>
            <th>Hinh thuc thanh toan</th>
            <th>Ghi chu</th>
            <th><input type="checkbox" id="selectAll"></th>

        </tr>
        <c:set var="stt" value="0" />
        <c:forEach var="phongTro" items="${listPhongTros}">
            <c:set var="stt" value="${stt + 1}" />
            <tr>
                <td> ${stt}</td>
                <td><c:out value="${phongTro.maPhongTro}"/></td>
                <td><c:out value="${phongTro.tenNguoiThue}"/></td>
                <td><c:out value="${phongTro.soDienThoai}"/></td>
                <td><c:out value="${phongTro.ngayBatDauThue}"/></td>
                <td><c:out value="${phongTro.hinhThucThanhToan}"/></td>
                <td><c:out value="${phongTro.ghiChu}"/></td>
                <td>
<%--                    <a href="/?action=delete&id=${user.id}">Delete</a>--%>
                    <input type="checkbox" name="tickPhongTro" value="${phongTro.maPhongTro}" />
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

    <button type="submit" class="btn btn-danger" onclick="return confirm('Bạn có chắc muốn xóa các phòng trọ đã chọn?')">Xóa các phòng trọ đã chọn</button>
</form>

<!-- Modal Popup -->
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close-btn">&times;</span>
        <form method="post">
            <input type="hidden" name="action" value="create">
            <table>
                <caption>
                    <h2>Tạo thông tin thuê trọ</h2>
                </caption>
                <tr>
                    <th>Mã phòng trọ:</th>
                    <td><input type="text" name="maPhongTro" id="maPhongTro" size="45" /></td>
                </tr>
                <tr>
                    <th>Tên người thuê:</th>
                    <td><input type="text" name="tenNguoiThue" id="tenNguoiThue" size="45" /></td>
                </tr>
                <tr>
                    <th>Sdt:</th>
                    <td><input type="text" name="soDienThoai" id="soDienThoai" size="15" /></td>
                </tr>
                <tr>
                    <th>Ngày bắt đầu thuê:</th>
                    <td><input type="date" name="ngayBatDauThue" id="ngayBatDauThue" size="15" /></td>
                </tr>
                <tr>
                    <th>Hình thức thanh toán:</th>
                    <td><input type="text" name="hinhThucThanhToan" id="hinhThucThanhToan" size="15" /></td>
                </tr>
                <tr>
                    <th>Ghi chú:</th>
                    <td><input type="text" name="ghiChu" id="ghiChu" size="15" /></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<button type="submit" class="btn btn-danger" onclick="return confirm('Bạn có chắc muốn xóa các phòng trọ đã chọn?')">Xóa các phòng trọ đã chọn</button>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const modal = document.getElementById("myModal");
    const btn = document.getElementById("openModal");
    const closeBtn = document.querySelector(".close-btn");

    btn.onclick = function() {
        modal.style.display = "block";
    }

    closeBtn.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }

    document.getElementById("selectAll").onclick = function () {
        let checkboxes = document.getElementsByName("tickPhongTro");
        for (let checkbox of checkboxes) {
            checkbox.checked = this.checked;
        }
    };
</script>
</body>
</html>