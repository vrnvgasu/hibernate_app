package ru.edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.cfg.Configuration;
import ru.edu.model.Actor;
import ru.edu.model.Item;
import ru.edu.model.Movie;
import ru.edu.model.Passport;
import ru.edu.model.Person;

/**
 * Hello world!
 */
public class App {

	public static void main(String[] args) {
		// src/main/resources/hibernate.properties подхватился по умолчанию
		Configuration configuration = new Configuration()
				// указываем конфигурации сущность Person и Item
				.addAnnotatedClass(Person.class)
				.addAnnotatedClass(Item.class)
				.addAnnotatedClass(Passport.class)
				.addAnnotatedClass(Movie.class)
				.addAnnotatedClass(Actor.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		//try with resources - автоматом закрое sessionFactory
		try (sessionFactory) {
			session.beginTransaction();

			Person person = session.get(Person.class, 9);
			System.out.println("Получили person");

			// relations
			// по умолчанию для OneToMany - Lazy (подгружаем данные при явном обращении).
//            System.out.println(person.getItems());

			// Если не взаимодействовать с ленивой загрузкой явно. Т.е. просто вызывать person.getItems(),
			// то компилятор оптимизирует код и не скомпилирует это строку
			// чтобы сделать явную подгрузку надо выполнить
			Hibernate.initialize(person.getItems());
			session.getTransaction().commit();

			/////////////// Открываем сессию еще раз
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Item item = session.get(Item.class, 13);
			// для ManyToOne по умолчанию - Eager
			// при запросе item уже был сделан left join
			System.out.println("Получили item");

			System.out.println(item.getOwner());
			session.getTransaction().commit();

			/////////////// Открываем сессию и транзакцию  еще раз
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			// в предыдущих сессиях person был в persistence context
			// переложили его в эту сессию
			person = (Person) session.merge(person);

			// HQL запрос
			System.out.println("HQL");
			List<Item> items = session.createQuery("select i from Item i where i.owner.id=:personId", Item.class)
					.setParameter("personId", person.getId()).getResultList();
//			Hibernate.initialize(person.getItems());

			session.getTransaction().commit();
		}
	}

}
