package employees.operations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import employees.entity.Employee;

public class DeleteEmployee {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			int employeeId = 1;
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("\nGetting employee with id: " + employeeId);
			Employee myEmployee = session.get(Employee.class, employeeId);
			
			System.out.println("Deleting employee: " + employeeId);
			session.delete(myEmployee);
			session.getTransaction().commit();
			System.out.println("Done deleting employee.");
			
		}
		finally {
			factory.close();
		}
	}
}