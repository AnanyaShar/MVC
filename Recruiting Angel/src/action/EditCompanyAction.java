package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Company;
import DAO.DAOFactory;

/**
 * Servlet implementation class EditCompanyAction
 */
@WebServlet("/EditCompanyAction")
public class EditCompanyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		boolean flag;
		Company company;
		String job_title,company_name,employment_type,job_sector,company_description,job_description,country,state,address,career_level,experience,gender,qualification,industry,date;
		int code,min_sal,max_sal,id;
		job_title=request.getParameter("job_title");
		id=Integer.parseInt(request.getParameter("id"));
		company_name=request.getParameter("company_name");
		employment_type=request.getParameter("employment_type");
		job_sector=request.getParameter("job_sector");
		company_description=request.getParameter("company_description");
		min_sal=Integer.parseInt(request.getParameter("min_sal"));
		max_sal=Integer.parseInt(request.getParameter("max_sal"));
		job_description=request.getParameter("job_description");
		country=request.getParameter("country");
		state=request.getParameter("state");
		address=request.getParameter("address");
		career_level=request.getParameter("career_level");
		experience=request.getParameter("experience");
		gender=request.getParameter("gender");
		qualification=request.getParameter("qualification");
		industry=request.getParameter("industry");
		date=request.getParameter("date");
		code=Integer.parseInt(request.getParameter("code"));
		company=new Company();
		company.setId(id);
		company.setJob_title(job_title);
		company.setCompany_name(company_name);
		company.setEmployment_type(employment_type);
		company.setJob_sector(job_sector);
		company.setCompany_description(company_description);
		company.setCountry(country);
		company.setJob_description(job_description);
		company.setMin_sal(min_sal);
		company.setMax_sal(max_sal);
		company.setState(state);
		company.setAddress(address);
		company.setCareer_level(career_level);
		company.setExperience(experience);
		company.setGender(gender);
		company.setQualification(qualification);
		company.setIndustry(industry);
		company.setDate(date);
		company.setCode(code);
		DAOFactory dao=DAOFactory.getDao();
		flag=dao.updateCompany(company);
		if(flag)
		{
			request.setAttribute("msg", "Details updated successfully");
			rd=request.getRequestDispatcher("list_job.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("msg", "Try again later");
			rd=request.getRequestDispatcher("list_job.jsp");
			rd.forward(request, response);
		}
				
	}
	

}
