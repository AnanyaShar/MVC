package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DAOFactory;

/**
 * Servlet implementation class MailActon
 */
@WebServlet("/MailActon")
public class MailActon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		String email,password;
		email=request.getParameter("email");
		DAOFactory dao=DAOFactory.getDao();
		password=dao.getPassword(email);
		if(password!=null) {
			Mailer.send(email,"noreply",password);
			request.setAttribute("msg", "Password sent to your email");
			rd=request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}
		else {
			request.setAttribute("Error", "Please Try Again Later");
			rd=request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}
	}

}
