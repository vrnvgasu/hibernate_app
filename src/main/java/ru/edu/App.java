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

            Person person = session.get(Person.class, 2);
            // Hibernate связывает объект person с БД.
            // После setName сразу произойдет обновление в базе
            person.setName("NEW");

            // удаление
            Person person2 = session.get(Person.class, 3);
            session.delete(person2);

            session.getTransaction().commit();
        } finally {
            // обязательно закрываем
            sessionFactory.close();
        }
    }
}
