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
                        <div>
                            <div class="column is-8 is-offset-2 is-flex is-flex-wrap-wrap">
                                <c:if test="${!result.isFirst()}">
                                    <a class="button is-primary" href="<c:url value='/?q=${param.q}&p=${result.getPageable().getPageNumber()-1}' />">
                                        <i>←</i>Prev
                                    </a>
                                </c:if>
                                <c:if test="${!result.isLast()}">
                                    <a style="margin-left: auto;" class="button is-primary" href="<c:url value='/?q=${param.q}&p=${result.getPageable().getPageNumber()+1}' />">
                                        Next<i>→</i>
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </section>
                </div>
            </t:wrapper>