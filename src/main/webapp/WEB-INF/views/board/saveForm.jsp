<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
  <form action="/auth/loginProc" method="post">
    <div class="form-group">
      <label for="title">제목:</label>
      <input type="text" class="form-control" placeholder="제목을 입력하세요" id="title">
    </div>

    <div class="form-group">
      <label for="content">내용:</label>
      <textarea class="form-control summernote" rows="5" id="content"></textarea>
    </div>

  </form>
  <button id="btn-save" class="btn btn-primary">작성 완료</button>
</div>

<script>
  $('.summernote').summernote({
    tabsize: 2,
    height: 100
  });
</script>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp" %>




