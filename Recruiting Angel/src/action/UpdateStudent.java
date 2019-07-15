package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Student;
import DAO.DAOFactory;

/**
 * Servlet implementation class UpdateStudent
 */
@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		String name,gender,url,phone,email,sector,about,qualification,master,bachelor,master_about,bachelor_about,senior_secondary,
		senior_secondary_about,secondary,secondary_about,company_name,
		designation,description,company_name2,designation2,description2,
		company_name3,designation3,description3,industry,language,
		project,topic,project_description,reference_name,reference_designation,reference_description,reference_name2,reference_designation2,
		reference_description2,country,state,city,address;
		int age,zip;
		name=request.getParameter("name");
		gender=request.getParameter("gender");
		url=request.getParameter("url");
		phone=request.getParameter("phone");
		email=request.getParameter("email");
		sector=request.getParameter("sector");
		about=request.getParameter("about");
		qualification=request.getParameter("qualification");
		master=request.getParameter("master");
		bachelor=request.getParameter("bachelor");
		master_about=request.getParameter("master_about");
		bachelor_about=request.getParameter("bachelor_about");
		senior_secondary=request.getParameter("senior_secondary");
		senior_secondary_about=request.getParameter("senior_secondary_about");
		secondary=request.getParameter("secondary");
		secondary_about=request.getParameter("secondary_about");
		company_name=request.getParameter("company_name");
		designation=request.getParameter("designation");
		description=request.getParameter("description");
		company_name2=request.getParameter("company_name2");
		designation2=request.getParameter("designation2");
		description2=request.getParameter("description2");
		company_name3=request.getParameter("company_name3");
		designation3=request.getParameter("designation3");
		description3=request.getParameter("description3");
		industry=request.getParameter("industry");
		language=request.getParameter("language");
		project=request.getParameter("project");
		topic=request.getParameter("topic");
		project_description=request.getParameter("project_description");
		reference_name=request.getParameter("reference_name");
		reference_designation=request.getParameter("reference_designation");
		reference_description=request.getParameter("reference_description");
		reference_name2=request.getParameter("reference_name2");
		reference_designation2=request.getParameter("reference_designation2");
		reference_description2=request.getParameter("reference_description2");
		country=request.getParameter("country");
		state=request.getParameter("state");
		city=request.getParameter("city");
		address=request.getParameter("address");
		age=Integer.parseInt(request.getParameter("age"));
		zip=Integer.parseInt(request.getParameter("zip"));
		Student student=new Student();
		student.setName(name);
		student.setAbout(about);
		student.setAddress(address);
		student.setAge(age);
		student.setBachelor(bachelor);
		student.setBachelor_about(bachelor_about);
		student.setCity(city);
		student.setCompany_name(company_name);
		student.setCompany_name2(company_name2);
		student.setCompany_name3(company_name3);
		student.setCountry(country);
		student.setDescription(description);
		student.setDescription2(description2);
		student.setDescription3(description3);
		student.setDesignation(designation);
		student.setDesignation2(designation2);
		student.setDesignation3(designation3);
		student.setEmail(email);
		student.setGender(gender);
		student.setIndustry(industry);
		student.setLanguage(language);
		student.setMaster(master);
		student.setMaster_about(master_about);
		student.setPhone(phone);
		student.setProject(project);
		student.setProject_description(project_description);
		student.setQualification(qualification);
		student.setReference_description(reference_description);
		student.setReference_description2(reference_description2);
		student.setReference_designation(reference_designation);
		student.setReference_designation2(reference_designation2);
		student.setReference_name(reference_name);
		student.setReference_name2(reference_name2);
		student.setSecondary(secondary);
		student.setSecondary_about(secondary_about);
		student.setSenior_secondary(senior_secondary);
		student.setSenior_secondary_about(senior_secondary_about);
		student.setState(state);
		student.setTopic(topic);
		student.setUrl(url);
		student.setZip(zip);
		student.setSector(sector);
		DAOFactory dao=DAOFactory.getDao();
		boolean flag=dao.updateProfile(student);
		if(flag) {
			HttpSession session=request.getSession(true);
			session.setAttribute("student_info", student);
			request.setAttribute("msg", "Profile updated");
			rd=request.getRequestDispatcher("student_list.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("Error", "Registration Failed");
			rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		
	}

}
