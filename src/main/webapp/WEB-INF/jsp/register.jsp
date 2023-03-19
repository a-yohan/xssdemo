<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Register">
    <section class="hero is-fullheight">
        <div class="hero-body">
            <div class="container has-text-centered">
                <div class="column is-6-desktop is-offset-3-desktop">
                    <h3 id="title" class="title has-text-black">Register</h3>
                    <form:form class="box" modelAttribute="user" method="post" action="/register"
                        enctype="application/x-www-form-urlencoded">
                        <input type="hidden" name="<c:out value='${_csrf.parameterName}'/>" value="<c:out value='${_csrf.token}'/>" />
                        
                        <div class="field">
                            <div class="control">
                                <form:input path="name" cssErrorClass="input is-large is-danger" cssClass="input is-large" placeholder="Full Name" autofocus="" autocomplete="off" />
                                <form:errors path="name" cssClass="help is-danger" element="p"/>
                            </div>
                        </div>
                        <div class="field">
                            <div class="control">
                                <form:input path="username" cssErrorClass="input is-large is-danger" cssClass="input is-large" placeholder="Username" autocomplete="off" />
                                <form:errors path="username" cssClass="help is-danger" element="p"/>
                            </div>
                        </div>
                        <div class="field">
                            <div class="control">
                                <form:input path="password" cssErrorClass="input is-large is-danger" cssClass="input is-large" type="password" placeholder="Password" autocomplete="off" />
                                <form:errors path="password" cssClass="help is-danger" element="p" />
                            </div>
                        </div>
                        <div class="field">
                            <div class="control">
                                <input class="input is-large" type="password" placeholder="Confirm Password" autocomplete="off">
                            </div>
                        </div>
                        <button type="submit" class="button is-block is-info is-large is-fullwidth">
                            Submit
                        </button>
                    </form:form>
                    <p class="has-text-grey">
                        <a href="<c:url value='/'/>">Home</a> &nbsp;·&nbsp; 
                        <a href="<c:url value='/login'/>">Login</a>
                    </p>
                </div>
            </div>
        </div>
    </section>
</t:wrapper>
