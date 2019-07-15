package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Bean.Visitor;
import DAO.DAOFactory;

/**
 * Servlet implementation class ContactInfo
 */
@WebServlet("/ContactInfo")
public class ContactInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		String Name,Email,Message;
		Name=request.getParameter("Name");
		Email=request.getParameter("Email");
		Message=request.getParameter("Message");
		
		Visitor visitor=new Visitor();
		visitor.setName(Name);
		visitor.setEmail(Email);
		visitor.setMessage(Message);
		
		DAOFactory dao=DAOFactory.getDao();
		boolean flag=dao.contact_info(visitor);
		if(flag) {
			request.setAttribute("alertMsg", "Message sent");
			rd=request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("Error", "Email already in use");
			rd=request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

}
