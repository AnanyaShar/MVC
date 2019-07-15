<%@page import="DAO.DAOFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String job_title=(request.getParameter("date"));
	DAOFactory dao = DAOFactory.getDao();
	boolean f = dao.deletefavcompany(job_title);
	if(f)
		response.sendRedirect("list_job.jsp");
	else
		out.print("Not Delete");
%>