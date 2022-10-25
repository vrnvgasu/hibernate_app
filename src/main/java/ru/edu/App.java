package ru.edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

//            // получить данные
//            Person person = session.get(Person.class, 3);
//            // внутри транзакции hibernate из обычного геттера делает sql запрос
//            List<Item> items = person.getItems();
//            System.out.println(items);
//
//            Item item = session.get(Item.class, 5);
//            Person person1 = item.getOwner();
//            System.out.println(person1);

//            // привязываем новый товар к существующему пользователю
//            Person person = session.get(Person.class, 3);
//            Item item = new Item("Product", person);
//            // hibernate кеширует данные. Если не обновить данные с другой стороны
//            // то в приложении может не отображаться связь со стороны person
//            // хотя она будет в бд
////            person.getItems().add(item);
//            session.save(item);

//            // создаем 2 новые сущности
//            Person person = new Person("NEW", 90);
//            Item item = new Item("Apple", person);
//            person.setItems(new ArrayList<>(Collections.singletonList(item)));
//
//            session.save(person);
//            session.save(item);

            // удаляем записи
            Person person = session.get(Person.class, 3);
            List<Item> items = person.getItems();
            for (Item item: items) {
                session.remove(item);
            }

            // если не очистим вручную список в объекте, то
            // все равно получим данные, т.к. hibernate их закешировал
            person.getItems().clear();
            System.out.println(person.getItems());

            session.getTransaction().commit();
        } finally {
            // обязательно закрываем
            sessionFactory.close();
        }
    }
}
