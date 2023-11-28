package in.co.vwits.emp.service;

import java.util.List;
import java.util.Optional;

import in.co.vwits.emp.model.Employee;
import in.co.vwits.model.exception.EmployeeNotFoundException;

public interface EmployeeService {

		public List<Employee> findAll();

		public void save(Employee s);

		public Optional<Employee> findByEmpId(int empid) throws EmployeeNotFoundException;

		public void deleteByEmpId(int empid);

		public void updateByEmpId(Employee emp);

		public List<Employee> findAllEmployeesSortedBySalary();
}
