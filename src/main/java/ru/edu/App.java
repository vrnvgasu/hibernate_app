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
            .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            Person person = new Person("Test cascading", 77);
//            Item item = new Item("Test cascading item", person);
//            person.setItems(new ArrayList<>(Collections.singletonList(item)));

            // persist вместо save
            // в Person для List<Item> items поставили правило каскадирования
            // cascade = CascadeType.PERSIST
            // session.persist(person) под капотом сделает session.persist(item)
//            session.persist(person);

            // работает с аннотацией каскодирования в Person для List<Item> items
            // @Cascade(CascadeType.SAVE_UPDATE)
//            session.save(person);

            Person person = new Person("Test cascading", 77);
            person.addItem(new Item("Item 1"));
            person.addItem(new Item("Item 2"));
            person.addItem(new Item("Item 3"));
            session.save(person);

            session.getTransaction().commit();
        } finally {
            // обязательно закрываем
            sessionFactory.close();
        }
    }
}
