<%--
  Created by IntelliJ IDEA.
  User: Jun
  Date: 2021-06-19
  Time: 오후 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp"%>

<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <button id="btn-update" class="btn btn-warning">수정</button>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
    <br />
    <div>
        <h3>${board.title}</h3>
    </div>
    <hr />
    <div>
        <div>${board.content}</div>
    </div>
    <hr />
</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


