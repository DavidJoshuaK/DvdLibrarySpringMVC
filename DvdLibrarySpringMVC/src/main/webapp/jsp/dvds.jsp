<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dvds</title>
 
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Dvds</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"> 
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                    </li>
                    <li role="presentation"
                        class="active">
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
          
            <div class="row">
             
                <div class="col-md-6">
                    <h2>My Dvds</h2>
                    <table id="dvdTable" class="table table-hover">
                        <tr>
                            <th width="40%">Title</th>
                            <th width="30%">Release Year</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentDvd" items="${currentDvd}">
                            <tr>
                                <td>
                                    <a href="displayDvdDetails?dvdId=${currentDvd.dvdId}">
                                        <c:out value="${currentDvd.title}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentDvd.releaseYear}"/>
                                </td>
                                <td>
                                    <a href="displayEditDvdForm?dvdId=${currentDvd.dvdId}">
                                        Edit
                                </td>
                                <td>
                                    <a href="deleteDvd?dvdId=${currentDvd.dvdId}">
                                        Delete
                                </td>
                                </a>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div> 
            
                <div class="col-md-6">
                    <h2>Add New Dvd</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createDvd">
                        <div class="form-group">
                            <label for="add-title" class="col-md-4 control-label">Title:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="title" placeholder="Title"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-release-year" class="col-md-4 control-label">Release Year:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="releaseYear" placeholder="Release Year"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-director" class="col-md-4 control-label">Director:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="director" placeholder="Director"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-rating" class="col-md-4 control-label">Rating:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="rating" placeholder="Rating"/>
                            </div>
                        </div>
                         <div class="form-group">
                            <label for="add-notes" class="col-md-4 control-label">Notes:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="notes" placeholder="Notes"/>
                            </div>
                        </div>
                        

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="createDvd"/>
                            </div>
                        </div>
                    </form>

                </div> 

            </div>   
        </div>
  
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>