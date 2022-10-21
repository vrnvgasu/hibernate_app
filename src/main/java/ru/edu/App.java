package ru.edu;

import java.util.List;
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

            // Запрос с помощью HQL
            List<Person> people = session
                .createQuery("FROM Person where age > 30 and name like 'S% '").getResultList();
            System.out.println(people);

            // Обновление с помощью HQL
            session
                .createQuery("update Person set name='T' where age<30 ").executeUpdate();
            session
                .createQuery("delete from Person where age<30 ").executeUpdate();

            session.getTransaction().commit();
        } finally {
            // обязательно закрываем
            sessionFactory.close();
        }
    }
}
