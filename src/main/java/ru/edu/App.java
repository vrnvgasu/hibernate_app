package ru.edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.cfg.Configuration;
import ru.edu.model.Item;
import ru.edu.model.Passport;
import ru.edu.model.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // src/main/resources/hibernate.properties подхватился по умолчанию
        Configuration configuration = new Configuration()
            // указываем конфигурации сущность Person и Item
            .addAnnotatedClass(Person.class)
            .addAnnotatedClass(Item.class)
            .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = new Person("Test person", 77);
            Passport passport = new Passport(777);
            person.setPassport(passport);

            // достаточно сохранить одну модель
            // связанная сущность сохраниться благодаря каскадированию
            // @Cascade(CascadeType.SAVE_UPDATE)
            session.save(person);

//            Person person = session.get(Person.class, 10);
//            System.out.println(person.getPassport().getPassportNumber());
//
//            // на сеттере hibernate сделает запрос к бд
//            // т.к. session.get выше помещает объект в persistence context
//            person.getPassport().setPassportNumber(888);
//
//            session.remove(person);

            session.getTransaction().commit();
        } finally {
            // обязательно закрываем
            sessionFactory.close();
        }
    }
}
