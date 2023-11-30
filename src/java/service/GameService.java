package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    
    
    
    @GET
    @Path("{type}/{console}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findGameGenreConsole(@PathParam("genre") Game.Genre genre, @PathParam("console") Game.Console console){
        Query query = em.createNamedQuery("findGameGenreConsole");
        query.setParameter("genre", genre);
        query.setParameter("console", console);
        return Response.ok(query.getResultList()).build();
    }
    
    
}