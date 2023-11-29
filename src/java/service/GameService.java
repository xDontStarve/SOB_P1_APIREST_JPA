package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.entities.Game;

/**
 *
 * @author Jialiang
 */
public class GameService extends AbstractFacade<Game>{
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
