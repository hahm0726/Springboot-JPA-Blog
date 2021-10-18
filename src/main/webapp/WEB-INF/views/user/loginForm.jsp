<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
  <form action="/auth/loginProc" method="post">
    <div class="form-group">
      <label for="username">Username:</label>
      <input name="username" type="text" class="form-control" placeholder="Username 입력" id="username">
    </div>

    <div class="form-group">
      <label for="password">비밀번호:</label>
      <input name="password" type="password" class="form-control" placeholder="비밀번호 입력" id="password">
    </div>

<%--    <div class="form-group form-check">--%>
<%--      <label class="form-check-label">--%>
<%--        <input name="remember" class="form-check-input" type="checkbox"> Remember me--%>
<%--      </label>--%>
<%--    </div>--%>

    <button id="btn-login" class="btn btn-primary">로그인</button>
  </form>
</div>

<!-- <script src="/js/user.js"></script>> -->
<%@include file="../layout/footer.jsp" %>




