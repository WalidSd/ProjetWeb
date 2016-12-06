package gestionnaires;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entities.Personne;
import entities.Tache;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class GestionnairePersonnes {

    @PersistenceContext(unitName = "MiniProjet-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerPersonne(Personne p) {
        em.persist(p);
    }

    public void creerPersonneDeTest() {
        creerPersonne(new Personne("walid", "rhazadi", "mat1"));
        creerPersonne(new Personne("Manai", "oualid", "mat2"));
        creerPersonne(new Personne("saad", "amine", "mat3"));
    }

    public List<Personne> getAllPersonnes() {
        Query q = em.createQuery("select p from Personne p");
        return q.getResultList();
    }

    public void deletePersonne(int id) {
        Query q1 = em.createQuery("select t from Tache t where t.personne.id=:id");
        q1.setParameter("id", id);
        List<Tache> list = new ArrayList<>();
        list = q1.getResultList();
        if (!list.isEmpty()) {
            System.out.println("Cette personne à des taches, impossible de le supprimer");
            

        } else {
            Query q = em.createQuery("delete from Personne p where p.id=:id");
            q.setParameter("id", id);
            q.executeUpdate();
        }

    }

}
