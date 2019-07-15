<%@page import="DAO.DAOFactory"%>
<%@page import="java.util.Iterator"%>
<%@page import="Bean.Company"%>
<%@page import="Bean.User"%>
<%@page import="Bean.Apply"%>
<%@page import="Bean.favorite"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" session="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%!
 			ArrayList<Apply> job_list;
    		Apply apply;
    		Iterator<Apply> itr;
    		
    %>
    <%
    HttpSession session= request.getSession(false);
    if(session==null){
    	response.sendRedirect("login.jsp");
    }
    else{
    	User user=(User)session.getAttribute("user_info");
    	String email=(String)session.getAttribute("email");
    	DAOFactory dao = DAOFactory.getDao();
    	job_list = dao.getApplyDetails(email);
    	itr = job_list.iterator();	
    	int count=dao.countCompany();
     	int student=dao.countStudent();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html lang="en">

<head>
	<title>Dashboard | Recruiting Angel</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- CSS -->
	<link rel="stylesheet" href="assets3/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets3/css/vendor/icon-sets.css">
	<link rel="stylesheet" href="assets3/css/main.min.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="assets3/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="assets3/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets3/img/favicon.png">
	<link href="assets3/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
	
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- SIDEBAR -->
		<div class="sidebar">
			<div class="brand">
				<a href="index.jsp"><h1 style="color: #FFFFFF; margin-top: -20px;padding-top: 10px;font-family: Consolas, Andale Mono, Lucida Console, Lucida Sans Typewriter, Monaco, Courier New, monospace;font-size:24px">Recruiting Angel</h1></a>
			</div>
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
					<li><a href="dashboard.jsp"><i class="lnr lnr-home"></i> <span>Dashboard</span></a></li>
					 <%if(user.getRole().equals("admin") || user.getRole().equals("jobseeker")){ %>
					<li><a href="updateprofile.jsp?email=<%=user.getEmail() %>"><i class="lnr lnr-home"></i> <span>Edit profile</span></a></li>
						<%} %>
						<li>
							<a href="#company" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>Jobs</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="company" class="collapse ">
								<ul class="nav">
								 <%if(user.getRole().equals("admin") || user.getRole().equals("company")){ %>
									<li><a href="post_job.jsp" class="">Post a job</a></li>
									<%} %>
									<li><a href="list_job.jsp" class="">Posted Jobs</a></li>
								</ul>
							</div>
						</li>
						
						<li><a class="" data-toggle="modal" href="#myModal"><i class="lnr lnr-alarm" ></i> <span>Change Password</a></span></li>
						 <%if(user.getRole().equals("admin") || user.getRole().equals("jobseeker")){ %>
						<li>
							<a href="#jobseeker" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>Manage Jobs</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="jobseeker" class="collapse ">
								<ul class="nav">
									<li><a href="applied_jobs.jsp" class="">Applied Jobs</a></li>
									<li><a href="favorite_jobs.jsp" class="">Favorite Jobs</a></li>
									
								</ul>
							</div>
						</li>
						<%} %>
						<li><a href="logout.jsp" class=""><i class="lnr lnr-cog"></i> <span>Logout</span></a></li>
						
					</ul>
				</nav>
			</div>
			
		</div>
		<!-- END SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			
			</nav>
			<!-- END NAVBAR -->
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<!-- OVERVIEW -->
					<div class="panel panel-headline">
						<div class="panel-heading">
							<h3 class="panel-title">Dashboard</h3>
							<!--<p class="panel-subtitle">Period: Oct 14, 2016 - Oct 21, 2016</p>-->
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3">
								</div>
								 <div class="col-md-3" style="width: 400px;margin-left: -150px">
									<div class="metric">
								 	 <span class="icon"><i class="fa fa-eye"></i></span> 
										<p>
											<span class="number"><%=count %></span>
											 <span class="title">Companies</span> 
										</p>
									</div>
								</div>
								<div class="col-md-3" style="width: 400px;">
									<div class="metric">
									 <span class="icon"><i class="fa fa-user"></i></span>
										<p>
											<span class="number"><%=student %></span>
											<span class="title">Students</span>
											</p>
									</div>
								</div>
								
								
							</div>
							
						</div>
					</div>
					<!-- END OVERVIEW -->
					<div style="width: 100%">
						<div >
							<!-- RECENT PURCHASES -->
							<div class="panel" style="margin-top: -20px;">
								<div class="panel-heading">
									<h3 class="panel-title" >All Jobs</h3>
									<!--<div class="right">
										<button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
										<button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
									</div>-->
								</div>
								<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!--<div class="panel-heading">
                            DataTables Advanced Tables
                        </div>-->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                    <tr>
 	                                           <th>Id</th>
                                            <th>Job_title</th>
                                            <th>Salary</th>
                                            <th>Industry</th>
                                            <th>Sector</th>
                                            <th>Deadline</th>
                                            <th>Email</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                     <% while(itr.hasNext()){
                           		       apply = itr.next();
                                     %>   
                                    <tr>
                                 <td><%=apply.getId() %></td>
                                 <td><%=apply.getJob_title() %></td>
                                 <td><%=apply.getMin_salary()  %> &mdash; <%=apply.getMax_salary() %></td>
                                 <td><%=apply.getIndustry() %></td>
                                 <td><%=apply.getSector() %></td>
                                 <td><%=apply.getDate() %></td>
                                 <td><%=apply.getEmail() %></td>
                                  
                                  
                                 
                                  
                                  </tr>
                                  <%}%>
                                  
                                   </tbody>
                                </table>
                            </div>
                           
                        </div>
                    </div>
                </div>
            </div>
							</div>
							<!-- END RECENT PURCHASES -->
						</div>
					</div>
					<div class="row">
						<div class="col-md-7">
						</div>
						<div >
						</div>
					</div>
					
				</div>
			</div>
			<!-- END MAIN CONTENT -->
			<!-- <footer>
				<div class="container-fluid">
					<p class="copyright">&copy; 2018. Designed &amp; Crafted by <a href="#">Dream Job</a></p>
				</div>
			</footer> -->
		</div>
		<!-- END MAIN -->
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="assets3/js/jquery/jquery-2.1.0.min.js"></script>
	<script src="assets3/js/bootstrap/bootstrap.min.js"></script>
	<script src="assets3/js/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets3/js/plugins/jquery-easypiechart/jquery.easypiechart.min.js"></script>
	<script src="assets3/js/plugins/chartist/chartist.min.js"></script>-
	<script src="assets3/js/klorofil.min.js"></script>-->
	<script src="assets3/dataTables/jquery.dataTables.js"></script>
    <script src="assets3/dataTables/dataTables.bootstrap.js"></script>
     <script>
         $(document).ready(function () {
             $('#dataTables-example').dataTable();
         });
    </script>
      <script type="text/javascript">
	function deleteCompany(date)
	{
		v = confirm("Are You sure you want to Delete company");
	    if(v)
	    	document.location.href="delete_favcompany.jsp?date="+date;
	 }	


	</script>
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
</body>

</html>
<%}%>