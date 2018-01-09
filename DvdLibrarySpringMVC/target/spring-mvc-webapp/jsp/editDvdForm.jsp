<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dvd Library</title>
 
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Edit Dvd</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayDvdsPage">
                            Dvds
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySearchPage">
                            Search
                        </a>
                    </li>
                </ul>    
            </div>
            <sf:form class="form-horizontal" role="form" modelAttribute="dvd"
                     action="editDvd" method="POST">
                <div class="form-group">
                    <label for="add-title" class="col-md-4 control-label">Title:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-title"
                                  path="title" placeholder="Title"/>
                        <sf:errors path="title" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-release-year" class="col-md-4 control-label">Release Year:</label>
                        <div class="col-md-8">
                        <sf:input type="number" class="form-control" id="add-release-year"
                                  path="releaseYear" placeholder="Release Year"/>
                        <sf:errors path="releaseYear" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-director" class="col-md-4 control-label">Director:</label>                          
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-director"
                                  path="director" placeholder="Director"/>
                        <sf:errors path="director" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-rating" class="col-md-4 control-label">Rating:</label>
                        <div class="col-md-8">
                        <sf:input type="rating" class="form-control" id="add-rating"
                                  path="rating" placeholder="Rating"/>
                        <sf:errors path="rating" cssclass="error"></sf:errors>
                        <sf:hidden path="dvdId"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-notes" class="col-md-4 control-label">Notes:</label>
                        <div class="col-md-8">
                        <sf:input type="notes" class="form-control" id="add-notes"
                                  path="notes" placeholder="Notes"/>
                        <sf:errors path="notes" cssclass="error"></sf:errors>
                        <sf:hidden path="dvdId"/>
                        </div>
                    </div>

                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Dvd"/>
                    </div>
                </div>
            </sf:form>                
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>