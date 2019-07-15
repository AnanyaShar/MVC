package DAO;

import java.util.ArrayList;

import Bean.Company;
import Bean.Student;
import Bean.User;
import Bean.Visitor;
import Bean.favorite;
import Bean.Apply;
public abstract class DAOFactory {

		public static DAOFactory getDao()
		{
			DAOFactory dao = new SqlDAO();
			return dao;
		}
		
		public abstract User checkLogin(User user);
		public abstract boolean registration(User user);
		public abstract boolean postJob(Company company);
		public abstract boolean changePassword(User user);
		public abstract  ArrayList<Company> getAllcompanies();
		public abstract boolean contact_info(Visitor visitor);
		public abstract ArrayList<Company> getCompanyInfo();
		public abstract boolean deletecompany(int id);
		public abstract boolean updateCompany(Company company);
		public abstract Company getCompany(int id);
		public abstract boolean apply(User user,Company company);
		public abstract boolean favorite(User user,Company company);
		public abstract ArrayList<favorite> getFavoriteCompany(String email);
		public abstract boolean deletefavcompany(String job_title);
		public abstract ArrayList<Apply> getApplyDetails(String email);
		public abstract boolean student_profile(Student student);
		public abstract ArrayList<Student> getAllStudent();
		public abstract boolean updateProfile(Student student);
		public abstract boolean deleteStudent(int id);
		public abstract Student getStudent(int id);
		public abstract Student getStudent(String email);
		public abstract String getPassword(String email);
		public abstract int countCompany();
		public abstract int countStudent();
		public abstract int countApplied(String Email);
		public abstract int countFavorite(String Email);
}
