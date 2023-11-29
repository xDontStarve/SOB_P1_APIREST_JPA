package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.entities.Game;
import model.entities.Game.Console;
import model.entities.Game.Type;

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
    
    @GET
    @Path("/{type}/{console}")
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Game> findAll(@PathParam("type") Type type, @PathParam("console") Console console){
    List<Game> games = super.findAll();
    
    for(int i = 0; i< games.size(); i++){
        if(games.get(i).getType().equals(type) || games.get(i).getConsole().equals(console)){
            games.remove(games.get(i));   
        }
    }
    return games;
    }
    
    
    
    @POST
    @Path("{game}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Override
    public void create(@PathParam("game") Game entity) {
        super.create(entity);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/game/{console}/{type}")
    public Response findGameSpecs(Game.Type type, Game.Console console){
        TypedQuery<Game> query = getEntityManager().createNamedQuery("findGameSpecs", Game.class);
        query.setParameter("type", type);
        query.setParameter("console", console);
        
        return Response.ok(query.getResultList()).build();
    }
    
    public  void addGame(Game entity){
        
    }
}