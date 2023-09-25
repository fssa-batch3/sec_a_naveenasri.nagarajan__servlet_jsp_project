<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page
	import="com.fssa.dynamicdesign.service.exception.ServiceException"%>
<%@ page import="com.fssa.dynamicdesign.model.Design"%>
<%@ page import="com.fssa.dynamicdesign.service.DesignService"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Designs List</title>
<link rel="stylesheet" href="./assets/css/universe.css" />
<link rel="stylesheet" href="./assets/css/design.css">
<link rel="stylesheet" href="./assets/css/index.css" />

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	
	<div class="unique">
	<form action="SearchDesignServlet" method="get">
    <div>
        <input type="text" id="search" name="search" placeholder="Search">
        <button type="submit"> Search</button>
    </div>
</form>
<form action="FilterDesignsServlet" method="Get">
    <div class="filter">
        <select class="selectColor" id="selectColor" name="selectrooms">
            <option value="Livingroom">Livingroom</option>
            <option value="Kitchen">Kitchen</option>
            <option value="Bedroom">Bedroom</option>
            <option value="Bathroom">Bathroom</option>
            <option value="others">Others</option>
        </select>
        <button type="submit">Filter</button>
    </div>
</form>

    </div>
	<div class="designs">
	
		<%
		List<Design> listDesigns = (List<Design>) request.getAttribute("designs");
		if (listDesigns != null && !listDesigns.isEmpty()) {
			for (Design design : listDesigns) {
			System.out.println(design.getDesignUrls());
			System.out.println();
		
		%>
		
		
		
		<div class="card">
			<img class="free_img" src="<%= design.getDesignUrls().get(0) %>"
				alt="Design Image">
			<div class="info">
				<h3><%=design.getDesignName()%></h3>
				<p>
					Price:
					<%= design.getPricePerSqFt() * design.getSquareFeet() %></p>
				<p>
					<%=design.getBio()%>
				</p>

				<a href="UserDesignDetailServlet?uniqueId=<%=design.getUniqueId() %>" class="btn" >More details</a>
			</div>
		</div>
		<%
		}
		} else {
		%>
		<img alt="interior design" class="free_img" Style="width: auto;height: auto;" src="./assets/image/common/nodesign.png">
		<%
		}
		%>
	</div>
</body>
</html>