package tp.checkers.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "moves", schema = "chinesecheckers", catalog = "")
public class EntityMoves implements Serializable {
    private int moveId;
    private EntityGames gameId;
    private Integer iOrigin;
    private Integer jOrigin;
    private Integer iDestination;
    private Integer jDestination;

    @Id
    @Column(name = "move_id")
    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    @ManyToOne
    @JoinColumn(name = "game_id")
    public EntityGames getGameId() {
        return gameId;
    }

    public void setGameId(EntityGames gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "I_origin")
    public Integer getiOrigin() {
        return iOrigin;
    }

    public void setiOrigin(Integer iOrigin) {
        this.iOrigin = iOrigin;
    }

    @Basic
    @Column(name = "J_origin")
    public Integer getjOrigin() {
        return jOrigin;
    }

    public void setjOrigin(Integer jOrigin) {
        this.jOrigin = jOrigin;
    }

    @Basic
    @Column(name = "I_destination")
    public Integer getiDestination() {
        return iDestination;
    }

    public void setiDestination(Integer iDestination) {
        this.iDestination = iDestination;
    }

    @Basic
    @Column(name = "J_destination")
    public Integer getjDestination() {
        return jDestination;
    }

    public void setjDestination(Integer jDestination) {
        this.jDestination = jDestination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityMoves that = (EntityMoves) o;

        if (moveId != that.moveId) return false;
        if (iOrigin != null ? !iOrigin.equals(that.iOrigin) : that.iOrigin != null) return false;
        if (jOrigin != null ? !jOrigin.equals(that.jOrigin) : that.jOrigin != null) return false;
        if (iDestination != null ? !iDestination.equals(that.iDestination) : that.iDestination != null) return false;
        if (jDestination != null ? !jDestination.equals(that.jDestination) : that.jDestination != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = moveId;
        result = 31 * result + (iOrigin != null ? iOrigin.hashCode() : 0);
        result = 31 * result + (jOrigin != null ? jOrigin.hashCode() : 0);
        result = 31 * result + (iDestination != null ? iDestination.hashCode() : 0);
        result = 31 * result + (jDestination != null ? jDestination.hashCode() : 0);
        return result;
    }
}
