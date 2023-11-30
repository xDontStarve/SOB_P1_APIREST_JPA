package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Path;
import model.entities.Comment;
import model.entities.Game;

/**
 *
 * @author Jialiang
 */
@Stateless
@Path("game")
public class GameService extends AbstractFacade<Game> {
    
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    
    public GameService() {
        super(Game.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}