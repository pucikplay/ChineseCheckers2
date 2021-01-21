package tp.checkers.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "games", schema = "chinesecheckers", catalog = "")
public class EntityGames implements Serializable {
    private int gameId;
    private Timestamp startDate;
    private Integer numberOfPlayers;
    private Integer sizeOfBase;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "number_of_players")
    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Basic
    @Column(name = "size_of_base")
    public Integer getSizeOfBase() {
        return sizeOfBase;
    }

    public void setSizeOfBase(Integer sizeOfBase) {
        this.sizeOfBase = sizeOfBase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityGames that = (EntityGames) o;

        if (gameId != that.gameId) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (numberOfPlayers != null ? !numberOfPlayers.equals(that.numberOfPlayers) : that.numberOfPlayers != null)
            return false;
        if (sizeOfBase != null ? !sizeOfBase.equals(that.sizeOfBase) : that.sizeOfBase != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gameId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (numberOfPlayers != null ? numberOfPlayers.hashCode() : 0);
        result = 31 * result + (sizeOfBase != null ? sizeOfBase.hashCode() : 0);
        return result;
    }
}
