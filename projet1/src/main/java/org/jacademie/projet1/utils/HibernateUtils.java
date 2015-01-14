package org.jacademie.projet1.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


/**
 * Classe qui gère l'interaction avec Hibernete (configuration, session, Factory)
 * @author jacademie-team
 *
 */
public class HibernateUtils {
	
	/**
	 * Factory qui genere les sessions de connexion a la BDD
	 */
	private static SessionFactory sessionFactory = null;
	
	
	/**
	 * Configure la sessionFactory afin de pourvoir se connecter a la BDD.
	 * @throws Exception
	 */
	public static void setUp() throws Exception {

		Configuration configuration = new Configuration().configure();

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		HibernateUtils.sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
	}
	
	/**
	 * Genere une session de connexion à la BDD
	 * @return	Session
	 * @throws 	Exception
	 */
	public static Session getSession() throws Exception {

		return HibernateUtils.sessionFactory.openSession();
	}

	/**
	 * Ferme une session d'accès à la BDD
	 * @param session
	 * @throws Exception
	 */
	public static void closeSession(Session session) throws Exception {

		session.close();
	}

	/**
	 * Ferme la sessionFactory et l'accès à la BDD
	 * @throws Exception
	 */
	public static void tearDown() throws Exception {

		HibernateUtils.sessionFactory.close();
	}
}