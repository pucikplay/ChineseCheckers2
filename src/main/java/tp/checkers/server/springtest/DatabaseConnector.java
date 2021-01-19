package tp.checkers.server.springtest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import tp.checkers.entities.EntityGames;

import java.util.List;

public class DatabaseConnector {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public EntityGames[] getGames() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM tp.checkers.entities.EntityGames";
        Query query = session.createQuery(hql);
        List results = query.list();
        EntityGames[] games = new EntityGames[results.size()];

        int i = 0;
        for (Object e : results) {
            EntityGames game = (EntityGames) e;
            games[i] = game;
            i++;
        }

        session.getTransaction().commit();
        session.close();

        return games;
    }

}
