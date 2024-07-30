package helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {

	public static SessionFactory factory;
	public static SessionFactory getSessionFactory() {
		if(factory==null) {
			SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			
			return factory;
		}else {
			return factory;
		}
	}
	
	public static void closeFactory() {
		if(factory.isOpen()) {
			factory.close();
		}
	}
	
}
