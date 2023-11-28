package in.co.vwits.emp.dao.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import in.co.vwits.emp.model.Employee;

public class EmployeeDaoImpl {
	//list creation to add students data
		private List<Employee> employees;
		//constructor
		public EmployeeDaoImpl() {	
			
			employees = new ArrayList<>();
			Employee e1= new Employee();
			e1.setId(1);
			e1.setName("Anjani");
			e1.setSalary(40000);
	        
			Employee e2= new Employee();
			e2.setId(2);
			e2.setName("Prasoona");
			e2.setSalary(50000);
		
			Employee e3= new Employee();
			e3.setId(2);
			e3.setName("Prasoona");
			e3.setSalary(50000);
			
			employees.add(e1);
			employees.add(e2);
			employees.add(e3);
			
		}
		//ReadAll
		public List<Employee>findAll(){
			return employees;
		}
		
		//create 
		public void save(Employee e) {
			employees.add(e);
		}
		
		//sort by salary
		public  List<Employee> sortBySalary() {
			employees.sort(Comparator.comparingDouble(Employee::getSalary));
			return employees;
		}
		
		public Optional<Employee> findByEmpId(int empId) {
			for(Employee e:employees) {
				if(e.getId()==empId) {
					return Optional.of(e);				
				}
			}
			return Optional.empty();
			
			
		}
		
		public void deleteByEmpId(int empId) {
			
			Iterator<Employee>i= employees.iterator();
			while(i.hasNext()) {
				Employee e= i.next();
				if(e.getId()==empId) {
					i.remove();
				}
			}
			
		}
		
	    public void updateByEmpId(int empId, double salary) {
			for(Employee e:employees) {
				if(e.getId()==empId) {
					e.setSalary(salary);
				}
			}
			
		}
	    
		
}
