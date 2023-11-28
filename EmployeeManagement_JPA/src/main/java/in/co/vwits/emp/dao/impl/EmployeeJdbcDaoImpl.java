package in.co.vwits.emp.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import in.co.vwits.emp.dao.EmployeeDao;
import in.co.vwits.emp.model.Employee;

public class EmployeeJdbcDaoImpl implements EmployeeDao {
	
	public int save(Employee e) { 
		try (Connection con =
			DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db","root","root");
		    PreparedStatement pstmt =con.prepareStatement("INSERT INTO tbl_emp VALUES(?,?,?)");) 
		{
		   pstmt.setInt(1, e.getId()); 
		   pstmt.setString(2,e.getName());
		   pstmt.setDouble(3,e.getSalary());
		
	       int noOfRowSpaceUpdated = pstmt.executeUpdate();
		   return noOfRowSpaceUpdated;
	    }
	    catch (SQLException e1) 
		{ 
	    	e1.printStackTrace(); 
	    }
        return 0;
	}
	
	//Delete employee by id
	public void deleteByEmpId(int empid) {
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db","root","root");
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM tbl_emp WHERE id = ?"); )
		{
			pstmt.setInt(1, empid);
			int noOfRowsUpdated = pstmt.executeUpdate(); // firing query
			System.out.println("No of records affected are: "+ noOfRowsUpdated);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//update employee by id
	public void updateByEmpId(int empid, double modifiedsalary) {
		try 
		(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db","root","root");
		 PreparedStatement pstmt = con.prepareStatement("UPDATE tbl_emp SET salary =? WHERE id =?");)
		 {
	        pstmt.setDouble(1,modifiedsalary);
            pstmt.setInt(2,empid);
             
            int noOfRowSpaceUpdated = pstmt.executeUpdate();// firing query...

            System.out.println("No of records updated are "+ noOfRowSpaceUpdated);
	    } 
		catch (SQLException e) {
		    e.printStackTrace();
	    }
	
     }
	
	
	//Find by empid
	public Optional<Employee> findByEmpId(int rollno)
	{
		try 
		(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db","root","root");
	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM tbl_emp WHERE id= ?");)
		
		{Employee foundEmployee = new Employee();
         pstmt.setInt(1, rollno);
       
        ResultSet rs = pstmt.executeQuery(); 
        if(rs.next()) {                           

        	foundEmployee.setId(rs.getInt(1));
        	foundEmployee.setName(rs.getString(2));
        	foundEmployee.setSalary(rs.getDouble(3));    	
        }
        return Optional.of(foundEmployee);
       
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return Optional.empty();
		
	}
	
	//Find All
	public List<Employee> findAll(){
		List<Employee> employees= new ArrayList<>();
		try 
		(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db","root","root");
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM tbl_emp ");)
		{
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {                          
				
				Employee foundEmployee=new Employee();
				foundEmployee.setId(rs.getInt(1));
	        	foundEmployee.setName(rs.getString(2));
	        	foundEmployee.setSalary(rs.getDouble(3));
				employees.add(foundEmployee);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public void updateByEmpId(Employee emp) {
		// TODO Auto-generated method stub
		
	}
		 
		
}
