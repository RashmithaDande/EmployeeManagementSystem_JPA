package in.co.vwits.emp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import in.co.vwits.emp.dao.EmployeeDao;
import in.co.vwits.emp.dao.impl.EmployeeJpaDaoImpl;
import in.co.vwits.emp.model.Employee;
import in.co.vwits.emp.service.EmployeeService;
import in.co.vwits.model.exception.EmployeeNotFoundException;


public class EmployeeServiceImpl implements EmployeeService {
	     private EmployeeDao dao;
	     public EmployeeServiceImpl()
	     {
//		    dao= new EmployeeJdbcDaoImpl();
	    	dao= new EmployeeJpaDaoImpl();

	    }
	    //sorting based on salary
	    @Override
	    public List<Employee> findAllEmployeesSortedBySalary(){
	    	return dao.findAll().stream().sorted().collect(Collectors.toList());
	    }
	    
	    @Override
	    //ReadAll
		public List<Employee>findAll(){
			return dao.findAll();
		}
		
	    @Override
		public void save(Employee e) {
			dao.save(e);
		}
	    
		public Optional<Employee> findByEmpId(int empId) throws EmployeeNotFoundException {
			Optional<Employee> p=dao.findByEmpId(empId);
			if(p.isPresent()) {
				return p;
			}
			else {
				
				throw new EmployeeNotFoundException();
			}

		}
		
		@Override
		public void deleteByEmpId(int rollno) {
			dao.deleteByEmpId(rollno);

		}
		
		@Override
		public void updateByEmpId(Employee emp) {
		  dao.updateByEmpId(emp); }
}
