<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
  <form>
    <input type="hidden" id="boardId" value="${board.id}"/>
    <div class="form-group">
      <input type="text" class="form-control" placeholder="제목을 입력하세요" id="title" value="${board.title}">
    </div>

    <div class="form-group">
      <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
    </div>

  </form>
  <button id="btn-update" class="btn btn-primary">수정 완료</button>
</div>

<script>
  $('.summernote').summernote({
    tabsize: 2,
    height: 100
  });
</script>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp" %>




