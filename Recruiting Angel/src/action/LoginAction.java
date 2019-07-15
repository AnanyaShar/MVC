package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.User;
import DAO.DAOFactory;


@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		String email,password;
		User user;
		email=request.getParameter("email");
		password=request.getParameter("password");
		if(email.trim().length() == 0||password.trim().length() == 0) {
			request.setAttribute("Error", "Invalid credentials");
			rd=request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
		
		user=new User();
		user.setEmail(email);
		user.setPassword(password);
		DAOFactory dao = DAOFactory.getDao();
		user=dao.checkLogin(user);
		if(user!=null)
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("user_info",user);
			session.setAttribute("email", user.getEmail());
			rd=request.getRequestDispatcher("dashboard.jsp");
			rd.include(request, response);
		}
		else
		{
			request.setAttribute("Error", "Please enter valid credentials!!");
			rd=request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}
				
	}

}
