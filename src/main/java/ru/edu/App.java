package ru.edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
            .addAnnotatedClass(Passport.class)
            .addAnnotatedClass(Movie.class)
            .addAnnotatedClass(Actor.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        //try with resources - автоматом закрое sessionFactory
        try(sessionFactory) {
            session.beginTransaction();

//            Movie movie = new Movie("Test movie", 1911);
//            Actor actor1 = new Actor("Actor1", 15);
//            Actor actor2 = new Actor("Actor2", 15);
//
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//
//            // надо сохранять все, если не настраиваем правила каскадирования
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

            Movie movie = session.get(Movie.class, 4);
            System.out.println(movie.getActors());

            session.getTransaction().commit();
        }
    }
}
