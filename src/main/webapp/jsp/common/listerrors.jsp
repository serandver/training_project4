<c:if test="${not empty errorList}">
    <c:forEach var="error" items="${errorList}">
        <div class="label label-danger">${error.message}</div>
    </c:forEach>
</c:if>