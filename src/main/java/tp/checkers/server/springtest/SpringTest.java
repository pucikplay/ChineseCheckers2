package tp.checkers.server.springtest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import tp.checkers.entities.EntityGames;

import java.util.List;

public class SpringTest {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void testAdding() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        EntityGames entity = new EntityGames();
        entity.setNumberOfPlayers(3);
        entity.setSizeOfBase(3);
        long date = new java.util.Date().getTime();
        entity.setStartDate(new java.sql.Timestamp(date));
        session.save(entity);

        session.getTransaction().commit();
        session.close();
    }

    public void testPrinting() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM tp.checkers.entities.EntityGames";
        Query query = session.createQuery(hql);
        List results = query.list();

        for (Object e : results) {
            EntityGames entity = (EntityGames) e;
            System.out.println(entity.getGameId() + " " + entity.getStartDate() + " " + entity.getNumberOfPlayers());
        }

        session.getTransaction().commit();
        session.close();
    }
}
