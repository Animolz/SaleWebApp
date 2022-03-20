<%-- 
    Document   : header
    Created on : Mar 20, 2022, 7:38:22 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="#">Phone Sale</a>

  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/" />">Trang chủ</a>
    </li>

    <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
        Danh mục
      </a>
      <div class="dropdown-menu">
        <c:forEach items="${categories}" var="c" >
        <c:url value="/" var="cateUrl" >
            <c:param name="cateId" value="${c.id}}" />
        </c:url>
          <a class="dropdown-item" href="${cateUrl}">${c.name}</a>
        </c:forEach>
      </div>
    </li>
  </ul>
    <form class="form-inline" action="">
        <input class="form-control mr-sm-2" type="text" placeholder="Nhap tu khoa...">
        <button class="btn btn-success" type="submit">Tim kiem</button>
    </form>
</nav>
