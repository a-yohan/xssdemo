<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar is-info">
    <div class="container">
        <div class="navbar-brand">
            <a class="navbar-item" href="/">
                <img src="/img/logo.png" alt="Logo">
            </a>
            <span class="navbar-burger burger" data-target="navbarMenu">
                <span></span>
                <span></span>
                <span></span>
            </span>
        </div>
        <div id="navbarMenu" class="navbar-menu">
            <div class="navbar-end">
                <div class=" navbar-item">
                    <div class="control has-icons-left">
                        <input id="search" class="input is-rounded" value="${param.q}" placeholder="Search">
                        <span class="icon is-left">
                            <i class="fa fa-search"></i>
                        </span>
                    </div>
                </div>
                <sec:authorize access="isAuthenticated()">
                
                <sec:authentication property="principal.name" var="name"/>
                <a class="navbar-item" href="/new-article">
                    <i class="fa-solid fa-pen-to-square"></i>
                </a>
                <div class="navbar-item has-dropdown is-hoverable">
                    <a class="navbar-link">
                        <span class="icon">
                            <i class="fa-solid fa-circle-user"></i>
                        </span>
                        <span>${name}</span> 
                    </a>
                    <div class="navbar-dropdown">
                        <a class="navbar-item">
                            Dashboard
                        </a>
                        <a class="navbar-item">
                            Profile
                        </a>
                        <hr class="navbar-divider">
                        <form action="/logout" method="post">
                            <input type="hidden" name="<c:out value='${_csrf.parameterName}'/>"
                                value="<c:out value='${_csrf.token}'/>" />
                            <button type="submit" class="button is-white is-justify-content-start is-fullwidth navbar-item">
                                Logout
                            </button>
                        </form>
                    </div>
                </div>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                <a class="navbar-item" href="<c:url value='/login' />">
                    Login
                </a>
                </sec:authorize>
            </div>
        </div>
    </div>
</nav>