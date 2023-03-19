<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="title" required="true" type="String" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <sec:csrfMetaTags />
    <title><c:out value='${title}' /></title>
    <link rel="icon" type="image/png" href="/img/logo.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.9.4/css/bulma.min.css"
        integrity="sha512-HqxHUkJM0SYcbvxUw5P60SzdOTy/QVwA1JJrvaXJv4q7lmbDZCmZaqz01UPOaQveoxfYRv1tHozWGPMcuTBuvQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
        integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.9.1/css/OverlayScrollbars.min.css"
        integrity="sha512-KsmAN0XOiG+TO0kNormPyohqUVGqAdG9RGNEr9yINp6HoBK2HyAT1FxxuXv8ZyK5dJ2tF3xtuPguAS9UrrMy3A=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
    <script>
        var data = Object.create(null)
        data.logedin = false
    </script>
</head>
<sec:authentication property="principal" var="user"/>
<body data-loggedin="${!user.equals('anonymousUser')}">
    <jsp:doBody/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.9.1/js/OverlayScrollbars.min.js"
        integrity="sha512-UkjGNPZ9Z4+gDXx2jDWxHV03SXY7d58PfZnzojPtcif7L8GuVpufOFfDYg6uAk43VeGCt5ySNbgFs+8JjsziGg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script async src="<c:url value='/js/main.js' />"></script>
</body>

</html>