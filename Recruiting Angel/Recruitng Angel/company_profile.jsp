<%@page import="DAO.DAOFactory"%>
<%@page import="Bean.Company"%>
<%@page import="Bean.User"%><%@ page language="java" session="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%
    HttpSession session= request.getSession(false);
    if(session==null){
    	response.sendRedirect("login.jsp");
    }
    else{
    	User user=(User)session.getAttribute("user_info");
    	int id=Integer.parseInt(request.getParameter("id"));
    	DAOFactory dao=DAOFactory.getDao();
    	Company company=dao.getCompany(id);
    %>
       
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<title>Job description</title>
	<link rel="stylesheet" href="bootstrap3/css/bootstrap.min.css">
	<script type="text/javascript" src="bootstrap3/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="bootstrap3/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="style3.css">
</head>
<body>
         
        <nav class="navbar">
	<div class="container">
	         <div class="navbar-header">
		<button type="button"  class="navbar-toggle" data-toggle="collapse" data-target="#MyNavbar">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>	
		<a href="#" class="navbar-brand"><h3>RECRUITING ANGEL</h3></a>
	         </div>
	       
	         <div class="collapse navbar-collapse" id="MyNavbar">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="dashboard.jsp"><span class="glyphicon glyphicon-home"> Dashboard</span></a></li>
			<li><a href="index.jsp"><span class="glyphicon  glyphicon-chevron-down"></span> Home</a></li>
			<li><a href="about.jsp"><span class="glyphicon  glyphicon-pencil"></span> About</a></li>
			<li><a href="logout.jsp"><span class="glyphicon  glyphicon-earphone"></span> Logout</a></li>
		</ul>
	         </div>
	</div>
        </nav>


	<div class="container">
	<div class="row">
	<div id="banner_img_5">
	
	<br><br><br><br><br><br><br><br><br>

	<div class="container">
	<div class="row">
	
	<div class="col-xs-4">
		<div class="thumbnail row2">
		<br>
		<img src="images/11.jpg" class= "img-responsive" style="border:3px dotted black">
		<h2><%=company.getCompany_name() %></h2>	
		<table class="table">
		<tr><th>Closed Date:</th><th><%=company.getDate() %></th></tr>
		<tr><th>Location:</th><th><%=company.getAddress() %><br><%=company.getState() %></th></tr>
		<tr><th>No. of Vacancies:</th><th>10</th></tr>
		</table>
		</div>

		<div class="thumbnail">
		<table class="table">
		<tr><th>Offered Salary<br></th><th><%=company.getMin_sal() %> &mdash; <%=company.getMax_sal() %></th></tr>
		<tr><th>Career Level<br></th><th><%=company.getCareer_level() %></th></tr>
		<tr><th>Job Type<br></th><th><%=company.getEmployment_type() %></th></tr>
		<tr><th>Experience<br></th><th><%=company.getExperience() %></th></tr>
		<tr><th>Gender<br></th><th><%=company.getGender() %></th></tr>
		<tr><th>Industry<br></th><th><%=company.getIndustry() %></th></tr>
		<tr><th>Qualification<br></th><th><%=company.getQualification() %></th></tr>
		</table>
		</div>
		
		<center><button type="button" class="btn btn-primary"  onclick="apply('<%=user.getEmail() %>',<%=company.getId() %>);">APPLY TO THIS JOB</button></centetr>

	</div>

	<div class="col-xs-8">
	<div class="thumbnail row2 row3">
		<h1><%=company.getJob_title() %></h1>
		<h4><%=company.getCompany_name() %> </h4>
		<h3>Job Description</h3>
		<p><%=company.getJob_description() %></p>
		<ul>
		<li><%=company.getJob_description() %>.</li>
		</ul>
		<h3>What we Do</h3>
		<p><%=company.getCompany_description() %>.</p>
		<ul>
		<li><%=company.getCompany_description() %></li>
		</ul>
		<h3>Company's head office</h3>
		<p style="font-size: 15px">Street No: <%=company.getAddress() %><br>State: <%=company.getState() %><br>Country: <%=company.getCountry() %><br>Zipcode: <%=company.getCode() %><br></p>
	</div>
	</div>

	</div>
	</div>
</div>	
	</div>
	</div>
<script type="text/javascript">
 function apply(email,id)
	{
		document.location.href="apply.jsp?email="+email + "&id="+id;
	}
	</script>
	<div class="container jumbotron">
		<div class="col-xs-2">
			<h5>&nbspJob Seeker</h5><br>
			<ul style="list-style: none">
				<li>Register</li>
				<li>Login</li>
				<li>Post Resume</li>
				<li>Search Jobs</li>
				<li>Career Tips</li>
			</ul>
		</div>

		<div class="col-xs-2">
			<h5>Top Location</h5><br>
			<ul>
				<li>Ahmedabad</li>
				<li>Bangalore</li>
				<li>Delhi</li>
				<li>Chandigarh</li>
				<li>Chennai</li>
				<li>Faridabad</li>
				<li>Gurgaon</li>
				<li>Hyderabad</li>
				<li>Kolkata</li>
				<li>Mumbai</li>		
			</ul>
		</div>

		<div class="col-xs-2">
			<h5>Job by Industry</h5><br>
			<ul>
				<li>Accessories</li>
				<li>Accounting</li>
				<li>Management</li>
				<li>Aerospace</li>
				<li>Animatiom</li>
				<li>Appliances</li>
			</ul>
		</div>

		<div class="col-xs-2">
			<h5>Job Seeker</h5><br>
			<ul>
				<li>Register</li>
				<li>Login</li>
				<li>Post Resume</li>
				<li>Search Jobs</li>
				<li>Career Tips</li>
			</ul>
		</div>

		<div class="col-xs-2">
			<h5>Quick Links</h5><br>
			<ul>
				<li>Home</li>
				<li>About Us</li>
				<li>Post Resume</li>
				<li>Post Jobs</li>
				<li>Careers With Us</li>
				<li>FAQs</li>
				<li>Forum</li>
				<li>Walk-in</li>
			</ul>
		</div>

		<div class="col-xs-2">
			<h5></h5><br>
			<ul>
				<li>Security</li>
				<li>Experts Advice</li>
				<li>Privacy Policy</li>
				<li>Refer A friend</li>
				<li>Terms Of Use</li>
				<li>Help Center</li>
				<li>Report Fraud</li>
				<li>Sitemap</li>
			</ul>
		</div>

	</div>


        <footer>
	<div class="container">
	        <center>
		<p>Copyright © Lifestyle Store. All Rights Reserved and Contact Us: +91 90000 00000</p>
	        </center>
	</div>
        </footer>
       
</body>

</html>
<%}%>