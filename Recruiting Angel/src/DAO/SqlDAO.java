package DAO;

import java.sql.*;
import java.util.ArrayList;

import Bean.Apply;
import Bean.Company;
import Bean.Student;
import Bean.User;
import Bean.Visitor;
import Bean.favorite;

public class SqlDAO extends DAOFactory {
	
	public User checkLogin(User user)
	{
		Connection con;
		Statement st;
		String query;
		ResultSet rs;
		
		try
		{
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="select * from user where email='"+user.getEmail()+"' and password ='"+user.getPassword()+"' and status ='active'";
			rs=st.executeQuery(query);
			if(rs.next())
			{
				user = new User();
				user.setId(rs.getInt("id"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setContact_no(rs.getString("contact_no"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setRole(rs.getString("role"));
				user.setStatus(rs.getString("status"));
				user.setImage(rs.getString("image"));
			}
			else {
				user=null;
				con.close();
				st.close();
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return user;
		
	}

	@Override
	public boolean registration(User user) {
		Connection con;
		Statement st;
		String query;
		boolean flag=false;
		int res;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="insert into user(first_name,last_name,contact_no,password,email,sex,role,status,image)values('"+user.getFirst_name()+"','"+user.getLast_name()+"','"+user.getContact_no()+"','"+user.getPassword()+"','"+user.getEmail()+"','"+user.getSex()+"','"+user.getRole()+"','"+user.getStatus()+"','"+user.getImage()+"')";
		    res=st.executeUpdate(query);
		    if(res>0) {
		    	flag=true;
		    }
		    con.close();
		    st.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean postJob(Company company) {
		Connection con;
		Statement st;
		String query;
		boolean flag=false;
		int res;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="insert into company(job_title,company_name,employment_type,job_sector,company_description,benefits,job_description,country,state,address,career_level,experience,gender,qualification,industry,date,code,min_salary,max_salary)values('"+company.getJob_title()+"','"+company.getCompany_name()+"','"+company.getEmployment_type()+"','"+company.getJob_sector()+"','"+company.getCompany_description()+"','"+company.getBenefits()+"','"+company.getJob_description()+"','"+company.getCountry()+"','"+company.getState()+"','"+company.getAddress()+"','"+company.getCareer_level()+"','"+company.getExperience()+"','"+company.getGender()+"','"+company.getQualification()+"','"+company.getIndustry()+"','"+company.getDate()+"',"+company.getCode()+","+company.getMin_sal()+","+company.getMax_sal()+")";
		    res=st.executeUpdate(query);
		    if(res>0) {
		    	flag=true;
		    }
		    con.close();
		    st.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean changePassword(User user) {
		Connection con;
		Statement st;
		int res;
		String query;
		boolean flag=false;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="update user set password='"+user.getPassword()+"' where email='"+user.getEmail()+"'";
			res=st.executeUpdate(query);
			if(res>0) 
				flag=true;
			} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ArrayList<Company> getAllcompanies() 
	{ 
		ArrayList<Company> company_list=new ArrayList<Company>();
		Connection con;
		Statement st;
		String query;
		ResultSet rs;
		try
		{
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="select * from company order by company_name desc";
			rs=st.executeQuery(query);
			while(rs.next())
			{
				Company company=new Company();
				company.setId(rs.getInt("id"));
				company.setJob_title(rs.getString("job_title"));
				company.setCompany_name(rs.getString("company_name"));
				company.setEmployment_type(rs.getString("employment_type"));
				company.setJob_sector(rs.getString("job_sector"));
				company.setCompany_description(rs.getString("company_description"));
				company.setBenefits(rs.getString("benefits"));
				company.setJob_description(rs.getString("job_description"));
				company.setCountry(rs.getString("country"));
				company.setState(rs.getString("state"));
				company.setAddress(rs.getString("address"));
				company.setCareer_level(rs.getString("career_level"));
				company.setExperience(rs.getString("experience"));
				company.setGender(rs.getString("gender"));
				company.setQualification(rs.getString("qualification"));
				company.setIndustry(rs.getString("industry"));
				company.setDate(rs.getString("date"));
				company.setCode(rs.getInt("code"));
				company.setMin_sal(rs.getInt("min_salary"));
				company.setMax_sal(rs.getInt("max_salary"));
				company_list.add(company);
			}
			
		} catch(Exception e)
			{
			e.printStackTrace();
			}
		return company_list;
		
	}

	@Override
	public boolean contact_info(Visitor visitor) {
		Connection con;
		Statement st;
		String query;
		boolean flag=false;
		int res;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="insert into contact_info(Name,Email,Message)values('"+visitor.getName()+"','"+visitor.getEmail()+"','"+visitor.getMessage()+"')";
		    res=st.executeUpdate(query);
		    if(res>0) {
		    	flag=true;
		    }
		    con.close();
		    st.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public ArrayList<Company> getCompanyInfo() {
		
		ArrayList<Company> company_list=new ArrayList<Company>();
		Connection con;
		Statement st;
		String query;
		ResultSet rs;
		try
		{
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="select job_title,employment_type,job_sector,job_description,min_salary,max_salary,country from company order by company_name desc";
			rs=st.executeQuery(query);
			while(rs.next())
			{
				Company company=new Company();
				company.setJob_title(rs.getString("job_title"));
				company.setEmployment_type(rs.getString("employment_type"));
				company.setJob_sector(rs.getString("job_sector"));
				company.setJob_description(rs.getString("job_description"));
				company.setCountry(rs.getString("country"));
				company.setMin_sal(rs.getInt("min_salary"));
				company.setMax_sal(rs.getInt("max_salary"));
				company_list.add(company);
			}
			
		} catch(Exception e)
			{
			e.printStackTrace();
			}
		return company_list;
	}

	@Override
	public boolean deletecompany(int id) {
		
			Connection con;
			Statement st;
			String query;
			boolean flag=false;
			try {
				con = DBConnection.getConnection();
				st = con.createStatement();
				query="delete from company where id="+id+"";
				int res = st.executeUpdate(query);
				if(res>0)
					flag = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return flag;
		}

	@Override
	public boolean updateCompany(Company company) {
		Connection con;
		Statement st;
		int res;
		String query;
		boolean flag=false;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="update company set job_title='"+company.getJob_title()+"',company_name='"+company.getCompany_name()+"',employment_type='"+company.getEmployment_type()+"',job_sector='"+company.getJob_sector() +"',company_description='"+company.getCompany_description()+"', benefits='"+company.getBenefits()+"',job_description='"+company.getJob_description()+"',country='"+company.getCountry()+"',state='"+company.getState()+"',address='"+company.getAddress()+"',career_level='"+company.getCareer_level()+"',experience='"+company.getExperience()+"',gender='"+company.getGender()+"',qualification='"+company.getQualification()+"',industry='"+company.getIndustry()+"',date='"+company.getDate()+"',code="+company.getCode()+",min_salary="+company.getMin_sal()+",max_salary="+company.getMax_sal()+" where id="+company.getId()+" ";
			res=st.executeUpdate(query);
			if(res>0) 
				flag=true;
			} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Company getCompany(int id) {
		Company company=null;
		Connection con;
		Statement st;
		String query;
		ResultSet rs;
		try {
			con = DBConnection.getConnection();
	    	st = con.createStatement();
	    	query="select * from company where id="+id+"";
	    	rs = st.executeQuery(query);
	    	if(rs.next()) {
	    		company=new Company();
				company.setId(rs.getInt("id"));
				company.setJob_title(rs.getString("job_title"));
				company.setCompany_name(rs.getString("company_name"));
				company.setEmployment_type(rs.getString("employment_type"));
				company.setJob_sector(rs.getString("job_sector"));
				company.setCompany_description(rs.getString("company_description"));
				company.setBenefits(rs.getString("benefits"));
				company.setJob_description(rs.getString("job_description"));
				company.setCountry(rs.getString("country"));
				company.setState(rs.getString("state"));
				company.setAddress(rs.getString("address"));
				company.setCareer_level(rs.getString("career_level"));
				company.setExperience(rs.getString("experience"));
				company.setGender(rs.getString("gender"));
				company.setQualification(rs.getString("qualification"));
				company.setIndustry(rs.getString("industry"));
				company.setDate(rs.getString("date"));
				company.setCode(rs.getInt("code"));
				company.setMin_sal(rs.getInt("min_salary"));
				company.setMax_sal(rs.getInt("max_salary"));
			
	    	}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public boolean apply(User user,Company company) {
		Connection con;
		Statement st;
		String query;
		int res;
		boolean flag=false;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="insert into apply(id,email)values("+company.getId()+",'"+user.getEmail()+"')";
			
			res=st.executeUpdate(query);
			
			if(res>0) {
				flag=true;
			}
			con.close();
			st.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean favorite(User user,Company company) {
		Connection con;
		Statement st;
		String query;
		boolean flag=false;
		int res;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="insert into favorite(job_title,company_name,job_sector,state,industry,date,min_salary,max_salary,email)values('"+company.getJob_title()+"','"+company.getCompany_name()+"','"+company.getJob_sector()+"','"+company.getState()+"','"+company.getIndustry()+"','"+company.getDate()+"',"+company.getMin_sal()+","+company.getMax_sal()+",'"+user.getEmail()+"')";
		    res=st.executeUpdate(query);
		    if(res>0) {
		    	flag=true;
		    }
		    con.close();
		    st.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public ArrayList<favorite> getFavoriteCompany(String email) {
		ArrayList<favorite> company_list=new ArrayList<favorite>();
		Connection con;
		Statement st;
		String query;
		ResultSet rs;
		try
		{
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="select distinct * from favorite order by date desc";
			rs=st.executeQuery(query);
			while(rs.next())
			{
				favorite fav=new favorite();
				fav.setJob_title(rs.getString("job_title"));
				fav.setCompany_name(rs.getString("company_name"));
				fav.setJob_sector(rs.getString("job_sector"));
				fav.setState(rs.getString("state"));
				fav.setIndustry(rs.getString("industry"));
				fav.setDate(rs.getString("date"));
				fav.setMin_salary(rs.getInt("min_salary"));
				fav.setMax_salary(rs.getInt("max_salary"));
				company_list.add(fav);
			}
			
		} catch(Exception e)
			{
			e.printStackTrace();
			}
		return company_list;
	}

	@Override
	public boolean deletefavcompany(String date) {
		Connection con;
		Statement st;
		String query;
		boolean flag=false;
		try {
			con = DBConnection.getConnection();
			st = con.createStatement();
			query="delete from favorite where date='"+date+"'";
			int res = st.executeUpdate(query);
			if(res>0)
				flag = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}

	@Override
	public ArrayList<Apply> getApplyDetails(String email) {
		ArrayList<Apply> company_list=new ArrayList<Apply>();
		Connection con;
		Statement st;
		String query;
		ResultSet rs;
		try
		{
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="select distinct * from apply natural join company where apply.email='"+email+"'";
			rs=st.executeQuery(query);
			while(rs.next())
			{
				Apply apply=new Apply();
				apply.setJob_title(rs.getString("job_title"));
				apply.setEmail(rs.getString("email"));
				apply.setSector(rs.getString("job_sector"));
				apply.setId(rs.getInt("id"));
				apply.setIndustry(rs.getString("industry"));
				apply.setDate(rs.getString("date"));
				apply.setMin_salary(rs.getInt("min_salary"));
				apply.setMax_salary(rs.getInt("max_salary"));
				company_list.add(apply);
			}
			
		} catch(Exception e)
			{
			e.printStackTrace();
			}
		return company_list;
	}

	@Override
	public boolean student_profile(Student student) {
		Connection con;
		Statement st;
		String query;
		boolean flag=false;
		int res;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="insert into student(name, gender, url, phone, email, sector, about, qualification, master, bachelor, master_about, bachelor_about, senior_secondary, senior_secondary_about, secondary, secondary_about, company_name, designation, description, company_nametwo, designationtwo, descriptiontwo, company_namethree, designationthree, descriptionthree, industry, language, project, topic, project_description, reference_name, reference_designation, reference_description, reference_nametwo, reference_designationtwo, reference_descriptiontwo, country, state, city, address, age, zip)values('"+student.getName()+"','"+student.getGender()+"','"+student.getUrl()+"','"+student.getPhone()+"','"+student.getEmail()+"','"+student.getSector()+"','"+student.getAbout()+"','"+student.getQualification()+"','"+student.getMaster()+"','"+student.getBachelor()+"','"+student.getMaster_about()+"','"+student.getBachelor_about()+"','"+student.getSenior_secondary()+"','"+student.getSenior_secondary_about()+"','"+student.getSecondary()+"','"+student.getSecondary_about()+"','"+student.getCompany_name()+"','"+student.getDesignation()+"','"+student.getDescription()+"','"+student.getCompany_name2()+"','"+student.getDesignation2()+"','"+student.getDescription2()+"','"+student.getCompany_name3()+"','"+student.getDesignation3()+"','"+student.getDescription3()+"','"+student.getIndustry()+"','"+student.getLanguage()+"','"+student.getProject()+"','"+student.getTopic()+"','"+student.getProject_description()+"','"+student.getReference_name()+"','"+student.getReference_designation()+"','"+student.getReference_description()+"','"+student.getReference_name2()+"','"+student.getReference_designation2()+"','"+student.getReference_description2()+"','"+student.getCountry()+"','"+student.getState()+"','"+student.getCity()+"','"+student.getAddress()+"',"+student.getAge()+","+student.getZip()+")";
			res=st.executeUpdate(query);
		    if(res>0) {
		    	flag=true;
		    }
		    st.close();
		    con.close();
		}catch (Exception ex) {
			
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public ArrayList<Student> getAllStudent() {
		ArrayList<Student> student_list=new ArrayList<Student>();
		Connection con;
		Statement st;
		String query;
		ResultSet rs;
		try
		{
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="select * from student order by idStudent desc";
			rs=st.executeQuery(query);
			while(rs.next())
			{
				Student student=new Student();
				student.setIdStudent(rs.getInt("idStudent"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setUrl(rs.getString("url"));
				student.setPhone(rs.getString("phone"));
				student.setEmail(rs.getString("email"));
				student.setSector(rs.getString("sector"));
				student.setAbout(rs.getString("about"));
				student.setQualification(rs.getString("qualification"));
				student.setMaster(rs.getString("master"));
				student.setBachelor(rs.getString("bachelor"));
				student.setMaster_about(rs.getString("master_about"));
				student.setBachelor_about(rs.getString("bachelor_about"));
				student.setSenior_secondary(rs.getString("senior_secondary"));
				student.setSenior_secondary_about(rs.getString("senior_secondary_about"));
				student.setSecondary(rs.getString("secondary"));
				student.setSecondary_about(rs.getString("secondary_about"));
				student.setCompany_name(rs.getString("company_name"));
				student.setDesignation(rs.getString("designation"));
				student.setDescription(rs.getString("description"));
				student.setCompany_name2(rs.getString("company_nametwo"));
				student.setDesignation2(rs.getString("designationtwo"));
				student.setDescription2(rs.getString("descriptiontwo"));
				student.setCompany_name3(rs.getString("company_namethree"));
				student.setDesignation3(rs.getString("designationthree"));
				student.setDescription3(rs.getString("descriptionthree"));
				student.setIndustry(rs.getString("industry"));
				student.setLanguage(rs.getString("language"));
				student.setProject(rs.getString("project"));
				student.setTopic(rs.getString("topic"));
				student.setProject_description(rs.getString("project_description"));
				student.setReference_name(rs.getString("reference_name"));
				student.setReference_designation(rs.getString("reference_designation"));
				student.setReference_description(rs.getString("reference_description"));
				student.setReference_name2(rs.getString("reference_nametwo"));
				student.setReference_designation2(rs.getString("reference_designationtwo"));
				student.setReference_description2(rs.getString("reference_descriptiontwo"));
				student.setCountry(rs.getString("country"));
				student.setState(rs.getString("state"));
				student.setCity(rs.getString("city"));
				student.setAddress(rs.getString("address"));
				student.setAge(rs.getInt("age"));
				student.setZip(rs.getInt("zip"));
				student_list.add(student);

			}
			
		} catch(Exception e)
			{
			e.printStackTrace();
			}
		return student_list;
		
	}

	@Override
	public boolean updateProfile(Student student) {
		Connection con;
		Statement st;
		int res;
		String query;
		boolean flag=false;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="update company set idStudent='"+student.getIdStudent()+"',name='"+student.getName()+"',gender='"+student.getGender()+"',url='"+student.getUrl() +"',phone='"+student.getPhone()+"', email='"+student.getEmail()+"',sector='"+student.getSector()+"',about='"+student.getAbout()+"',qualification='"+student.getQualification()+"',master='"+student.getMaster()+"',bachelor='"+student.getBachelor()+"',master_about='"+student.getMaster_about()+"',bachelor_about='"+student.getBachelor_about()+"',senior_secondary='"+student.getSenior_secondary()+"',senior_secondary_about='"+student.getSenior_secondary_about()+"',secondary='"+student.getSecondary()+"',secondary_about="+student.getSecondary_about()+",company_name="+student.getCompany_name()+",designation="+student.getDesignation()+",description='"+student.getDescription()+"',company_nametwo='"+student.getCompany_name2()+"',designationtwo='"+student.getDesignation2()+"',descriptiontwo='"+student.getDescription2()+"',company_namethree='"+student.getCompany_name3()+"',designationthree='"+student.getDesignation3()+"',descriptionthree='"+student.getDescription3()+"',industry='"+student.getIndustry()+"',language='"+student.getLanguage()+"',project='"+student.getProject()+"',topic='"+student.getTopic()+"',project_description='"+student.getProject_description()+"',reference_name='"+student.getReference_name()+"',reference_designation='"+student.getReference_designation()+"',reference_description='"+student.getReference_description()+"',reference_nametwo='"+student.getReference_name2()+"',reference_designationtwo='"+student.getReference_designation2()+"',reference_descriptiontwo='"+student.getReference_description2()+"',country='"+student.getCountry()+"',state='"+student.getState()+"',city='"+student.getCity()+"',address='"+student.getAddress()+"',age="+student.getAge()+",zip="+student.getZip()+" where idStudent="+student.getIdStudent()+" ";
			res=st.executeUpdate(query);
			if(res>0) 
				flag=true;
			} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteStudent(int id) {

		Connection con;
		Statement st;
		String query;
		boolean flag=false;
		try {
			con = DBConnection.getConnection();
			st = con.createStatement();
			query="delete from student where id="+id+"";
			int res = st.executeUpdate(query);
			if(res>0)
				flag = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}

	@Override
	public Student getStudent(int id) {
		Student student=null;
		Connection con;
		Statement st;
		String query;
		ResultSet rs;
		try {
			con = DBConnection.getConnection();
	    	st = con.createStatement();
	    	query="select * from student where idStudent="+id+"";
	    	rs = st.executeQuery(query);
	    	if(rs.next()) {
	    		student=new Student();
				student.setIdStudent(rs.getInt("idStudent"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setUrl(rs.getString("url"));
				student.setPhone(rs.getString("phone"));
				student.setEmail(rs.getString("email"));
				student.setSector(rs.getString("sector"));
				student.setAbout(rs.getString("about"));
				student.setQualification(rs.getString("qualification"));
				student.setMaster(rs.getString("master"));
				student.setBachelor(rs.getString("bachelor"));
				student.setMaster_about(rs.getString("master_about"));
				student.setBachelor_about(rs.getString("bachelor_about"));
				student.setSenior_secondary(rs.getString("senior_secondary"));
				student.setSenior_secondary_about(rs.getString("senior_secondary_about"));
				student.setSecondary(rs.getString("secondary"));
				student.setSecondary_about(rs.getString("secondary_about"));
				student.setCompany_name(rs.getString("company_name"));
				student.setDesignation(rs.getString("designation"));
				student.setDescription(rs.getString("description"));
				student.setCompany_name2(rs.getString("company_nametwo"));
				student.setDesignation2(rs.getString("designationtwo"));
				student.setDescription2(rs.getString("descriptiontwo"));
				student.setCompany_name3(rs.getString("company_namethree"));
				student.setDesignation3(rs.getString("designationthree"));
				student.setDescription3(rs.getString("descriptionthree"));
				student.setIndustry(rs.getString("industry"));
				student.setLanguage(rs.getString("language"));
				student.setProject(rs.getString("project"));
				student.setTopic(rs.getString("topic"));
				student.setProject_description(rs.getString("project_description"));
				student.setReference_name(rs.getString("reference_name"));
				student.setReference_designation(rs.getString("reference_designation"));
				student.setReference_description(rs.getString("reference_description"));
				student.setReference_name2(rs.getString("reference_nametwo"));
				student.setReference_designation2(rs.getString("reference_designationtwo"));
				student.setReference_description2(rs.getString("reference_descriptiontwo"));
				student.setCountry(rs.getString("country"));
				student.setState(rs.getString("state"));
				student.setCity(rs.getString("city"));
				student.setAddress(rs.getString("address"));
				student.setAge(rs.getInt("age"));
				student.setZip(rs.getInt("zip"));
			
	    	}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Student getStudent(String email) {
		Student student=null;
		Connection con;
		Statement st;
		String query;
		ResultSet rs;
		try {
			con = DBConnection.getConnection();
	    	st = con.createStatement();
	    	query="select * from student where email='"+email+"'";
	    	rs = st.executeQuery(query);
	    	if(rs.next()) {
	    		student=new Student();
				student.setIdStudent(rs.getInt("idStudent"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setUrl(rs.getString("url"));
				student.setPhone(rs.getString("phone"));
				student.setEmail(rs.getString("email"));
				student.setSector(rs.getString("sector"));
				student.setAbout(rs.getString("about"));
				student.setQualification(rs.getString("qualification"));
				student.setMaster(rs.getString("master"));
				student.setBachelor(rs.getString("bachelor"));
				student.setMaster_about(rs.getString("master_about"));
				student.setBachelor_about(rs.getString("bachelor_about"));
				student.setSenior_secondary(rs.getString("senior_secondary"));
				student.setSenior_secondary_about(rs.getString("senior_secondary_about"));
				student.setSecondary(rs.getString("secondary"));
				student.setSecondary_about(rs.getString("secondary_about"));
				student.setCompany_name(rs.getString("company_name"));
				student.setDesignation(rs.getString("designation"));
				student.setDescription(rs.getString("description"));
				student.setCompany_name2(rs.getString("company_nametwo"));
				student.setDesignation2(rs.getString("designationtwo"));
				student.setDescription2(rs.getString("descriptiontwo"));
				student.setCompany_name3(rs.getString("company_namethree"));
				student.setDesignation3(rs.getString("designationthree"));
				student.setDescription3(rs.getString("descriptionthree"));
				student.setIndustry(rs.getString("industry"));
				student.setLanguage(rs.getString("language"));
				student.setProject(rs.getString("project"));
				student.setTopic(rs.getString("topic"));
				student.setProject_description(rs.getString("project_description"));
				student.setReference_name(rs.getString("reference_name"));
				student.setReference_designation(rs.getString("reference_designation"));
				student.setReference_description(rs.getString("reference_description"));
				student.setReference_name2(rs.getString("reference_nametwo"));
				student.setReference_designation2(rs.getString("reference_designationtwo"));
				student.setReference_description2(rs.getString("reference_descriptiontwo"));
				student.setCountry(rs.getString("country"));
				student.setState(rs.getString("state"));
				student.setCity(rs.getString("city"));
				student.setAddress(rs.getString("address"));
				student.setAge(rs.getInt("age"));
				student.setZip(rs.getInt("zip"));
			
	    	}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String getPassword(String email) {
		Connection con;
		Statement st;
		String query=null;
		ResultSet rs;
		String password=null;
		try {
			con = DBConnection.getConnection();
			st = con.createStatement();
			query="select password from user where email='"+email+"'";
			rs = st.executeQuery(query);
	    	if(rs.next()) {
	    	 password=rs.getString("password");
	    	}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return password;
	}

	@Override
	public int countCompany() {
		Connection con;
		Statement st;
		int res=0;
		ResultSet rs;
		String query;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="select count(role) from user where role='company'";
			rs=st.executeQuery(query);
			while(rs.next()) {
				res=rs.getInt(1);
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int countStudent() {
		Connection con;
		Statement st;
		int res=0;
		ResultSet rs;
		String query;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="select count(role) from user where role='jobseeker'";
			rs=st.executeQuery(query);
			while(rs.next()) {
				res=rs.getInt(1);
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int countApplied(String email) {
		Connection con;
		Statement st;
		int res=0;
		ResultSet rs;
		String query;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="select count(distinct id) from apply where email='"+email+"'";
			rs=st.executeQuery(query);
			while(rs.next()) {
				res=rs.getInt(1);
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int countFavorite(String email) {
		Connection con;
		Statement st;
		int res=0;
		ResultSet rs;
		String query;
		try {
			con=DBConnection.getConnection();
			st=con.createStatement();
			query="select count(distinct company_name) from favorite where email='"+email+"'";
			rs=st.executeQuery(query);
			while(rs.next()) {
				res=rs.getInt(1);
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	

	

}
