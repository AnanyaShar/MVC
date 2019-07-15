<%@page import="DAO.DAOFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int id=Integer.parseInt(request.getParameter("idStudent"));
	DAOFactory dao = DAOFactory.getDao();
	boolean f = dao.deleteStudent(id);
	if(f)
		response.sendRedirect("student_list.jsp");
	else
		out.print("Not Delete");
%>