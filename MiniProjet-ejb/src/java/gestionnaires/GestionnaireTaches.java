
package gestionnaires;

import entities.Personne;
import entities.Tache;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
@LocalBean
public class GestionnaireTaches implements Serializable {
    
    @PersistenceContext(unitName = "MiniProjet-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerTache(Tache t) {
        em.persist(t);
    }
    
    public Personne getPersonne(int id) {
        Personne p = em.find(Personne.class, id);
        return p;
    }
    
    public void creerTachesDeTest() {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        //Personne p=new Personne("walid","rhazadi","145ds");
        Personne p1=getPersonne(1);
        Personne p2=getPersonne(2);
        creerTache(new Tache("tache1", currentTimestamp,"En cours",p1));      
        creerTache(new Tache("tache2", currentTimestamp,"Non attribue",null)); 
        creerTache(new Tache("tache3", currentTimestamp,"complete",p2)); 
    }
    
    public List<Tache> getAllTaches() {
        Query q = em.createQuery("select t from Tache t");
        return q.getResultList();
    }
    
    public void deleteTache(int id) {
        Query q = em.createQuery("delete from Tache t where t.id=:id");
        q.setParameter("id", id);
        q.executeUpdate();     
    }
    
     public void updateTache(int id,String desc, Timestamp date, String etat) {         
        Query q = em.createQuery("update Tache t set t.description=:desc, t.dateCreation=:date, t.etat=:etat where t.id=:id");
         q.setParameter("desc", desc);
         q.setParameter("etat", etat);
         q.setParameter("id", id);
         q.executeUpdate(); 
    }
     
    
    
   
}

