package employees.operations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import employees.entity.Employee;

public class CreateEmployee {

	public static void main(String[] args) {
	
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			Employee tempEmployee1 = new Employee("Michael", "Jordan", "Chicago Bulls");
			Employee tempEmployee2 = new Employee("Kobe", "Bryant", "Los Angeles Lakers");
			Employee tempEmployee3 = new Employee("Larry", "Bird", "Boston Celtics");
			session.beginTransaction();
			System.out.println("Saving the employees: " + tempEmployee1.getLastName() 
								+ ", " + tempEmployee2.getLastName() 
								+ ", " + tempEmployee3.getLastName());
			session.save(tempEmployee1);
			session.save(tempEmployee2);
			session.save(tempEmployee3);
			session.getTransaction().commit();
			System.out.println("Done creating new employee objects.");
		}
		finally {
			factory.close();
		}
	}
}
