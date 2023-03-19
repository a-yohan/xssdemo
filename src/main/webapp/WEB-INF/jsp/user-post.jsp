<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Home">
    <t:navbar></t:navbar>

    <div class="container">
        <section class="articles">
        <c:forEach items="${result.content}" var="article">
            <div class="column is-8 is-offset-2">
                <t:article data="${article}"></t:article>
            </div>
        </c:forEach>
        </section>
    </div>
</t:wrapper>