package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.User;
import DAO.DAOFactory;
/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		PrintWriter out=response.getWriter();
		boolean flag;
		String email,password,npassword;
		email=request.getParameter("email");
		password=request.getParameter("password");
		npassword=request.getParameter("npassword");
		
		User user=new User();
		user.setEmail(email);
		user.setPassword(npassword);
		DAOFactory dao=DAOFactory.getDao();
		flag=dao.changePassword(user);
		if(flag)
		{
			out.println("<script type=\"text/javascript\">");
			   out.println("alert('Updated successfully');");
			   out.println("location='dashboard.jsp';");
			   out.println("</script>");

		}
		else {
			   out.println("<script type=\"text/javascript\">");
			   out.println("alert('User or password incorrect');");
			   out.println("location='dashboard.jsp';");
			   out.println("</script>");
		}
		
		
	}

}
