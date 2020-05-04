package employees.operations;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import employees.entity.Employee;

public class QueryEmployee {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
	
		try {
			
			session.beginTransaction();
			List<Employee> theEmployees = session.createQuery("from Employee").getResultList();
			displayEmployees(theEmployees);
			
			// query employees for a given company
			theEmployees = session.createQuery("from Employee s where " 
					+ " s.company LIKE '%Bulls'").getResultList();
			System.out.println("\nEmployees of Chicago Bulls: ");
			displayEmployees(theEmployees);
			
			session.getTransaction().commit();
			System.out.println("Done querying employees.");
			
		}
		finally {
			factory.close();
		}
	}

	private static void displayEmployees(List<Employee> theEmployees) {
		for (Employee tempEmployee : theEmployees) {
			System.out.println(tempEmployee);
		}
		
	}
}
