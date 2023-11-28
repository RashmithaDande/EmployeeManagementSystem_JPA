package in.co.vwits.emp.dao.impl;

import java.util.List;
import java.util.Optional;

import in.co.vwits.emp.dao.EmployeeDao;
import in.co.vwits.emp.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class EmployeeJpaDaoImpl implements EmployeeDao
{

	private EntityManagerFactory factory;
	
	public  EmployeeJpaDaoImpl() {
		factory = Persistence.createEntityManagerFactory("empapp");
		
	}
	@Override
	public int save(Employee s) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(s);//It insterts record in db
		tx.commit();
		em.close();
		return 1;
	}

	@Override
	public Optional<Employee> findByEmpId(int rollno) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Employee e = em.find(Employee.class, rollno);
		tx.commit();
		em.close();
		return Optional.of(e);
	}

	@Override
	public List<Employee> findAll() {
	    EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    String jpql = "FROM Employee";
	    TypedQuery<Employee> query = em.createQuery(jpql,Employee.class);
	    List<Employee> employeeList = query.getResultList();
	    tx.commit();
	    em.close();
		return employeeList;
	}

	@Override
	public void deleteByEmpId(int rollno) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(em.find(Employee.class,rollno));
		tx.commit();
		em.close();

		
	}

	@Override
	public void updateByEmpId(Employee emp) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(emp);
		tx.commit();
		em.close();
		
	}

}
