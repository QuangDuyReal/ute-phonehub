<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quản lý Danh mục - UTE Admin</title>

  <!-- Favicon -->
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/static/favicon.png">

  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

  <!-- Google Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;600;700&display=swap" rel="stylesheet">

  <!-- Admin CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pages/admin.css">
</head>
<body class="admin-body">
  <div class="admin-layout">
    <%@ include file="/WEB-INF/views/common/admin-sidebar.jspf" %>

    <main class="admin-main">
      <div class="admin-topbar">
        <div class="topbar-left">
          <button class="btn-toggle-sidebar" id="toggleSidebar">
            <i class="fas fa-bars"></i>
          </button>
          <h1 class="page-title">Quản lý Danh mục</h1>
        </div>
        <div class="topbar-right">
          <button class="btn-action btn-primary" id="btnAddCategory">
            <i class="fas fa-plus"></i> Thêm danh mục
          </button>
        </div>
      </div>

      <div class="admin-content">
        <div class="dashboard-card">
          <div class="card-body">
            <div class="table-responsive">
              <table class="admin-table">
                <thead>
                  <tr>
                    <th style="width: 80px;">ID</th>
                    <th>Tên danh mục</th>
                    <th>Mô tả</th>
                    <th>Số sản phẩm</th>
                    <th style="width: 150px;">Thao tác</th>
                  </tr>
                </thead>
                <tbody id="categoriesTableBody">
                  <tr>
                    <td colspan="5" class="text-center">
                      <div class="loading-spinner">
                        <i class="fas fa-spinner fa-spin"></i>
                        <span>Đang tải...</span>
                      </div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>

  <!-- Category Modal -->
  <div id="categoryModal" class="modal">
    <div class="modal-dialog">
      <div class="modal-header">
        <h3 class="modal-title" id="modalTitle">Thêm danh mục</h3>
        <button class="btn-close" id="btnCloseModal">&times;</button>
      </div>
      <div class="modal-body">
        <form id="categoryForm">
          <input type="hidden" id="categoryId">
          <div class="form-group">
            <label for="categoryName">Tên danh mục <span style="color: red;">*</span></label>
            <input type="text" id="categoryName" class="form-control" required>
          </div>
          <div class="form-group">
            <label for="categoryDescription">Mô tả</label>
            <textarea id="categoryDescription" class="form-control" rows="3"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button class="btn-action btn-secondary" id="btnCancelModal">Hủy</button>
        <button class="btn-action btn-primary" id="btnSaveCategory">Lưu</button>
      </div>
    </div>
  </div>

  <script src="${pageContext.request.contextPath}/static/js/utils.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/api.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/auth.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/pages/admin-categories.js"></script>

  <script>
    document.getElementById('toggleSidebar')?.addEventListener('click', () => {
      document.querySelector('.admin-sidebar')?.classList.toggle('show');
    });
  </script>
</body>
</html>

