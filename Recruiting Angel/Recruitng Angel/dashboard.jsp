<%@page import="DAO.DAOFactory"%>
<%@page import="java.util.Iterator"%>
<%@page import="Bean.Company"%>
<%@page import="Bean.User"%>
<%@page import="java.util.ArrayList"%>


<%@ page language="java" session="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%!
 			ArrayList<Company> job_list;
    		Company company;
    		Iterator<Company> itr;
    		String email;
    		
    %>
     <%
 HttpSession session=request.getSession(false);
 if(session==null){
 	response.sendRedirect("login.jsp");
 }
 else{
 	User user=(User)session.getAttribute("user_info");
 	String email=(String)session.getAttribute("email");
 	DAOFactory dao = DAOFactory.getDao();
	job_list = dao.getAllcompanies();
	itr = job_list.iterator();
	int count=dao.countCompany();
 	int student=dao.countStudent();
 	int apply=dao.countApplied(email);
 	int favorite=dao.countFavorite(email);
 %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">


<head>
	<title>Dashboard</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- CSS -->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/vendor/icon-sets.css">
	<link rel="stylesheet" href="assets/css/main.min.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="assets/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- SIDEBAR -->
		<div class="sidebar">
			<div class="brand">
				<!--<a href="index.html"><img src="assets/img/logo.png" alt="Klorofil Logo" class="img-responsive logo"></a>-->
				<a href="#" style ="color:white;font-size:25px">Recruiting Angel</a>
			</div>
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="dashboard.jsp" class="active"><i class="lnr lnr-home"></i> <span>Dashboard</span></a></li>
						<li>
							<a href="#subPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>Jobs</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages" class="collapse ">
								<ul class="nav">
								 <%if(user.getRole().equals("admin") || user.getRole().equals("company")){ %>
									<li><a href="post_job.jsp" class="">Post a Job</a></li>
									<%} %>
									<li><a href="list_job.jsp" class="">Posted Job</a></li>
									
								</ul>
							</div>
						</li>
						<li>
							<a href="#students" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>Students</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="students" class="collapse ">
								<ul class="nav">
								 <%if(user.getRole().equals("admin") || user.getRole().equals("company")){ %>
									<li><a href="student_list.jsp" class="">Student_list</a></li>
									
									<%} %>
									 <%if(user.getRole().equals("admin") || user.getRole().equals("jobseeker")){ %>
						<li><a href="profile.jsp" class=""><i class="lnr lnr-code"></i> <span>My Profile</span></a></li>
						<%} %>
						 
						
								</ul>
							</div>
						</li>

						<li><a class="" data-toggle="modal" href="#myModal"><i class="lnr lnr-alarm" ></i> <span>Change Password</a></span></li>
						<li>
							<a href="#Pages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>Manage Jobs</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="Pages" class="collapse ">
								<ul class="nav">
								 <%if(user.getRole().equals("admin") || user.getRole().equals("jobseeker")){ %>
									<li><a href="applied_jobs.jsp" class="">Applied Jobs</a></li>
									<li><a href="favorite_jobs.jsp" class="">Favorite Jobs</a></li>
									<%} %>
								</ul>
							</div>
						</li>
						<li><a href="logout.jsp" class=""><i class="lnr lnr-dice"></i> <span>Logout</span></a></li>
					</ul>
				</nav>
			</div>
			<a class="footer" href="#" title="Twitter share" target="_blank"> <span style="font-size:25px">Delete Profile</span></a>
		</div>
		<!-- END SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- NAVBAR -->
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-btn">
						<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
					</div>
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-menu">
							<span class="sr-only">Toggle Navigation</span>
							<i class="fa fa-bars icon-nav"></i>
						</button>
					</div>
					<div id="navbar-menu" class="navbar-collapse collapse">
						<form class="navbar-form navbar-left hidden-xs">
							<div class="input-group">
								<input type="text" value="" class="form-control" placeholder="Search dashboard...">
								<span class="input-group-btn"><button type="button" class="btn btn-primary">Go</button></span>
							</div>
						</form>
						<ul class="nav navbar-nav navbar-right">
							
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="<%=request.getContextPath()%>/upload/<%=user.getImage()%>" class="img-circle" alt="Avatar" style="height:40px;width:40px"> <span><%=user.getFirst_name() %></span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
								<ul class="dropdown-menu">
									<li><a href="dashboard.jsp"><i class="lnr lnr-home"></i><span>Dashboard</span></a></li>
									<li><a href="#"><i class="lnr lnr-user"></i> <span>My Profile</span></a></li>
									<li><a data-toggle="modal" href="#myModal"><i class="lnr lnr-cog"></i> <span>Change Password</span></a></li>
									<li><a href="logout.jsp"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</nav>
			<!-- END NAVBAR -->
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<!-- OVERVIEW -->
					<div class="panel panel-headline">
						<div class="panel-heading">
							<h2 class="panel-title" style="color:#24ABE5;font-size:28px;padding:12px;border-bottom:1px solid lightgrey">Applications Statistics</h2>
						</div>
						 <div class="panel-body" >
							<div class="row" >
								<div class="col-md-3">
									<div class="metric">
										<p>
											<span class="number" style="text-align:center;padding:-4px;color:grey;font-weight:bold;font-size:18px">APPLIED JOBS</span><br>
											<span class="number" style="text-align:center;padding:-4px;font-weight:bold"><%=apply %></span><br>
											<span class="number" style="text-align:center;font-size:18px">to find career</span>
										</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="metric">
										<p>
											<span class="number" style="text-align:center;padding:-4px;color:grey;font-weight:bold;font-size:18px">FAVOURITE JOBS</span><br>
											<span class="number" style="text-align:center;padding:-4px;font-weight:bold"><%=favorite %></span><br>
											<span class="number" style="text-align:center;font-size:18px">against opportunities</span>
										</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="metric">
										
										<p>
											<span class="number" style="text-align:center;padding:-4px;color:grey;font-weight:bold;font-size:18px">COMPANY</span><br>
											<span class="number" style="text-align:center;padding:-4px;font-weight:bold"><%=count %></span><br>
											<span class="number" style="text-align:center;font-size:18px">to find registered companies</span>
										</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="metric">
										
										<p>
											<span class="number" style="text-align:center;padding:-4px;color:grey;font-weight:bold;font-size:18px">STUDENT</span><br>
											<span class="number" style="text-align:center;padding:-4px;font-weight:bold"><%=student %></span><br>
											<span class="number" style="text-align:center;font-size:18px">to find registered students</span>
										</p>
									</div>
								</div>

							</div>
							<div class="row">
								<div class="col-md-9">
									<div id="headline-chart" class="ct-chart"></div>
								</div>
								<div class="col-md-3">
									<div class="weekly-summary text-right">
										<span class="number"><%=apply %></span> 
										<span class="info-label">Applied Jobs</span>
									</div>
									<div class="weekly-summary text-right">
										<span class="number"><%=favorite %></span>
										<span class="info-label">Favorite Jobs</span>
									</div>
									<div class="weekly-summary text-right">
										<span class="number"><%=count %></span>
										<span class="info-label">Company Registered</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- END OVERVIEW -->
					<div class="row">
						<div class="col-md-12">
							<!-- RECENT PURCHASES -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">Recent Jobs</h3>
									<div class="right">
										<button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
										<button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
									</div>
								</div>
								<div class="panel-body no-padding">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Company_Name</th>
												<th>Job_Title</th>
												<th>Salary</th>
												<th>Industry</th>
												<th>Status</th>
											</tr>
										</thead>
										<tbody>
										<% while(itr.hasNext()){
                           		       company = itr.next();
                                     %>   
											<tr>
												<td><%=company.getCompany_name() %></td>
												<td><%=company.getJob_title() %></td>
												<td><%=company.getMin_sal()%> &mdash; <%=company.getMax_sal() %></td>
												<td><%=company.getIndustry() %></td>
												<td><span class="label label-success">COMPLETED</span></td>
											</tr>
											
											<%} %>
										</tbody>
									</table>
								</div>
								<div class="panel-footer">
									<div class="row">
										<div class="col-md-6"><span class="panel-note"><i class="fa fa-clock-o"></i> Last 24 hours</span></div>
										<div class="col-md-6 text-right"><a href="list_job.jsp" class="btn btn-primary">View All Companies</a></div>
									</div>
								</div>
							</div>
							<!-- END RECENT PURCHASES -->
						</div>
						
						</div>
					</div>
					
						</div>
						
						</div>
					</div>
					
					</div>
				</div>
			</div>
			<!-- END MAIN CONTENT -->
			<footer>
				<div class="container-fluid">
					<p class="copyright">&copy; 2016. Designed &amp; Crafted by <a href="https://themeineed.com">The Develovers</a></p>
				</div>
			</footer>
		</div>
		<!-- END MAIN -->
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets/js/jquery/jquery-2.1.0.min.js"></script>
	<script src="assets/js/bootstrap/bootstrap.min.js"></script>
	<script src="assets/js/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/js/plugins/jquery-easypiechart/jquery.easypiechart.min.js"></script>
	<script src="assets/js/plugins/chartist/chartist.min.js"></script>
	<script src="assets/js/klorofil.min.js"></script>



  <!-- The Modal -->
  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h3 class="modal-title">Change Password</h3>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">          	
		<form method="post" action="ChangePassword">
			<h4>Enter your email:</h4>
			<input type="text" name="email" class="form-control">
			<h4>Enter your old password:</h4>
			<input type="password" name="password" class="form-control">
			<h4>Enter your new password:</h4>
			<input type="password" name="npassword" class="form-control">
			<input type="submit" value="Submit" style="margin-top:20px;margin-left:250px">
		</form>		
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  

<%}%>