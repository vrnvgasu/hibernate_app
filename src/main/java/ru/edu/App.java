package ru.edu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.edu.model.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // указываем конфигурации сущность Person
        // src/main/resources/hibernate.properties подхватился по умолчанию
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            Person person1 = new Person("Test1", 20);
            Person person2 = new Person("Test1", 30);
            Person person3 = new Person("Test1", 40);

            session.save(person1);
            session.save(person2);
            session.save(person3);

            session.getTransaction().commit();
        } finally {
            // обязательно закрываем
            sessionFactory.close();
        }
    }
}
