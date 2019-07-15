package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Bean.User;
import DAO.DAOFactory;

/**
 * Servlet implementation class RegisterAction
 */
@WebServlet("/RegisterAction")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "upload";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024*1024*50;
	
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd;
		DAOFactory dao=DAOFactory.getDao();
		User user=new User();
		PrintWriter writer=response.getWriter();
		boolean flag=false;
		String image=request.getParameter("upload");
		if (!ServletFileUpload.isMultipartContent(request)) 
		{
			writer = response.getWriter();
			writer.println("Request does not contain upload data");
			writer.flush();
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);

		String uploadPath = getServletContext().getRealPath("")
				+ File.separator + UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) 
		{
			uploadDir.mkdir();
		}

		try {
			List formItems = upload.parseRequest(request);
			Iterator iter = formItems.iterator();

			while (iter.hasNext()) 
			{
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) 
				{
					String fileName = new File(item.getName()).getName();
					user.setStatus("active");
		            user.setImage(image);
					user.setImage(fileName);
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);
					item.write(storeFile);
					
				}
				
				else
				{
					String fname=item.getFieldName();
					String fvalue=item.getString();
					if(fname.equals("Fname"))
						user.setFirst_name(fvalue);
					else if(fname.equalsIgnoreCase("Lname"))
						user.setLast_name(fvalue);
					else if(fname.equalsIgnoreCase("MNumber"))
						user.setContact_no(fvalue);
					else if(fname.equalsIgnoreCase("email"))
				        user.setEmail(fvalue);
					else if(fname.equalsIgnoreCase("password"))
						user.setPassword(fvalue);
					else if(fname.equalsIgnoreCase("gender"))
						user.setSex(fvalue);
					else if(fname.equalsIgnoreCase("role"))
						user.setRole(fvalue);
				}
			}
			flag=dao.registration(user);
			if(flag) {
				HttpSession session=request.getSession(true);
				
				session.setAttribute("user_info", user);
				request.setAttribute("msg", "Registration Successful");
				rd=request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("Error", "Registraton Failed");
				rd=request.getRequestDispatcher("registration.jsp");
				rd.include(request, response);
			}
			request.setAttribute("message", "Upload has been done successfully!");
		    rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
			
		} catch (Exception ex) {
			ex.printStackTrace();
	}
	
		if(dao.registration(user))
		{
			request.setAttribute("message", "Image inserted");
			
		}
	}			


}
