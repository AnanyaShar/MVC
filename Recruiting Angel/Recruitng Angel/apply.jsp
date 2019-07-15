<%@page import="Bean.Company"%>
<%@page import="DAO.DAOFactory"%>
<%@page import="Bean.User"%>


<%@ page language="java" session="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%
 HttpSession session=request.getSession(false);
 if(session==null){
 	response.sendRedirect("login.jsp");
 }
 else{
 	User user=(User)session.getAttribute("user_info");
 	int id=Integer.parseInt(request.getParameter("id"));
 	Company company=new Company();
    company.setId(id);
    String email=request.getParameter("email");
    user.setEmail(email);
 	DAOFactory dao=DAOFactory.getDao();
 	boolean b=dao.apply(user,company);
 	if(b){
 		response.sendRedirect("list_job.jsp");
 	}
 }
 	
 %>