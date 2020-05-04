package employees.operations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import employees.entity.Employee;

public class ReadEmployee {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			Employee tempEmployee = new Employee("Scotty", "Pippen", "Chicago Bulls");
			session.beginTransaction();
			System.out.println("Saving the employee...");
			System.out.println(tempEmployee);
			session.save(tempEmployee);
			session.getTransaction().commit();
			System.out.println("Saved employee. Generated id: " + tempEmployee.getId());
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("\nGetting employee with id: " + tempEmployee.getId());
			Employee myEmployee = session.get(Employee.class, tempEmployee.getId());
			session.getTransaction().commit();
			System.out.println("Done reading the employee.");
			
		}
		finally {
			factory.close();
		}
	}

}
