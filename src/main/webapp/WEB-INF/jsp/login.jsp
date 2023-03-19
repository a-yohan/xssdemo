<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Login">
    <section class="hero is-fullheight">
    <div class="hero-body">
        <div class="container has-text-centered">
            <div class="column is-6-desktop is-offset-3-desktop">
                <h3 id="title" class="title has-text-black">Login</h3>
                <c:if test='${param.error != null}'>
                <div id="error" class="notification has-background-danger has-text-white">
                    <c:choose>
                        <c:when test='${param.error == 10}'>Maximum sessions of 1 for this user exceeded</c:when>
                        <c:otherwise>Invalid Credential</c:otherwise>
                    </c:choose>
                </div>
                </c:if>
                <form id="login-form" class="box" method="post" action="/login"
                    enctype="application/x-www-form-urlencoded">
                    <input type="hidden" name="${_csrf.parameterName}"
                        value="${_csrf.token}" />
                    <div class="field">
                        <div class="control">
                            <input name="username" class="input is-large" placeholder="Username" autofocus="" autocomplete="off">
                        </div>
                    </div>
                    
                    <div class="field">
                        <div class="control">
                            <input name="password" class="input is-large" type="password" placeholder="Password" autocomplete="off">
                        </div>
                    </div>

                    <button type="submit" class="button is-block is-info is-large is-fullwidth">
                        Login <i class="fa fa-sign-in"></i>
                    </button>
                </form>
                <p class="has-text-grey">
                    <a href="<c:url value='/'/>">Home</a> &nbsp;·&nbsp; 
                    <a href="<c:url value='/register'/>">Register</a>
                </p>
            </div>
        </div>
    </div>
    </section>
</t:wrapper>