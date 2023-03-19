<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="data" required="true" type="com.example.xssdemo.dto.ArticleResponseDto" %>
<div class="card article">
    <div class="card-content">
        <div class="media">
            <div class="media-content has-text-centered">
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.id" var="userID"/>
                    <c:if test="${data.user.id == userID || data.user.role == 'ROLE_ADMIN'}">
                        <div class="icon-text">
                            <a href="<c:url value='/p/${data.id}/edit' />">
                                <span class="icon has-text-success">
                                    <i class="fas fa-pen-to-square"></i>
                                </span>
                                <span>Edit</span>
                            </a>
                        </div>
                    </c:if>
                </sec:authorize>
                <p class="title article-title">
                    <a href="<c:url value='/p/${data.id}' />">
                        <c:out value='${data.title}' />
                    </a>
                </p>
                <div class="tags has-addons level-item">
                    <a href="<c:url value='/u/${data.user.id}' />">
                        <span class="tag is-rounded is-info">@<c:out value='${data.user.name}' /></span>
                    </a>
                    <span class="tag is-rounded"><fmt:formatDate type='date' value='${data.createdAt}' /></span>
                </div>
            </div>
        </div>
        <div class="content article-body">
             <!-- nosemgrep -->
            ${data.content}
        </div>
    </div>
</div>