<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Edit Article">
    <t:navbar></t:navbar>
    <div class="container">
        <section class="articles">
            <div class="column is-8 is-offset-2">
                <div class="card article">
                    <form:form class="card-content" modelAttribute="article" method="post" action=""
                        enctype="application/x-www-form-urlencoded">
                        <div class="field">
                            <div class="control">
                                <form:input path="title" cssErrorClass="input is-danger" cssClass="input" placeholder="Title" autofocus="" autocomplete="off" />
                                <form:errors path="title" cssClass="help is-danger" element="p"/>
                            </div>
                        </div>
                        <form:textarea id="content" path="content" class="content article-body" />
                        
                        <button type="submit" class="button is-block is-info is-fullwidth">
                            Submit
                        </button>
                    </form:form>
                </div>
            </div>

        </section>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/6.2.0/tinymce.min.js"
        integrity="sha512-tofxIFo8lTkPN/ggZgV89daDZkgh1DunsMYBq41usfs3HbxMRVHWFAjSi/MXrT+Vw5XElng9vAfMmOWdLg0YbA=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="<c:url value='/js/editor.js' />"></script>
</t:wrapper>