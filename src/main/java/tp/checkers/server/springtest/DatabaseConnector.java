package tp.checkers.server.springtest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.connection.DatasourceConnectionProvider;
import org.hibernate.impl.SessionFactoryImpl;
import tp.checkers.entities.EntityGames;
import tp.checkers.entities.EntityMoves;
import tp.checkers.message.MessageUpdate;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

public class DatabaseConnector {

    private SessionFactory sessionFactory;

    private int gameId = 1;

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

    public EntityGames getGame(int gameNo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM tp.checkers.entities.EntityGames WHERE id = ";
        hql += gameNo;
        Query query = session.createQuery(hql);
        List results = query.list();
        EntityGames game = (EntityGames) results.get(0);

        session.getTransaction().commit();
        session.close();

        return game;

    }

    public EntityMoves[] getMoves(int gameNo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM tp.checkers.entities.EntityMoves WHERE game_id = ";
        hql += gameNo;
        Query query = session.createQuery(hql);
        List results = query.list();
        EntityMoves[] moves = new EntityMoves[results.size()];

        int i = 0;
        for (Object e : results) {
            EntityMoves move = (EntityMoves) e;
            moves[i] = move;
            i++;
        }

        session.getTransaction().commit();
        session.close();

        return moves;
    }

    public void storeMove(MessageUpdate messageUpdate) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        EntityMoves move = new EntityMoves();
        move.setiOrigin(messageUpdate.getOrigin().i);
        move.setjOrigin(messageUpdate.getOrigin().j);
        move.setiDestination(messageUpdate.getDestination().i);
        move.setjDestination(messageUpdate.getDestination().j);
        move.setGameId(getGame(gameId));

        session.save(move);

        session.getTransaction().commit();
        session.close();
    }

    public void storeGame(int baseSide, int playerNumber, Timestamp timestamp) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        EntityGames newGame = new EntityGames();
        newGame.setNumberOfPlayers(playerNumber);
        newGame.setSizeOfBase(baseSide);
        newGame.setStartDate(timestamp);

        gameId = (Integer) session.save(newGame);
        System.out.println(gameId);

        session.getTransaction().commit();
        session.close();
    }

}
