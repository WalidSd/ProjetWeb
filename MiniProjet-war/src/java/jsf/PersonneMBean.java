package jsf;

import entities.Personne;
import gestionnaires.GestionnairePersonnes;
import gestionnaires.GestionnaireTaches;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;


@Named(value = "personneMBean")
@ViewScoped
public class PersonneMBean implements Serializable{
    @EJB
    private GestionnaireTaches gt;
    @EJB
    private GestionnairePersonnes gp;
    
    public PersonneMBean() {
    }
    
    public void creerDonnesDeTest() {
        System.out.print("JSF BEAN CREATION DE TEST");
        gp.creerPersonneDeTest();     
    }
    
    public Personne getPersonne(int id) {
        
        return gt.getPersonne(id);
    }
    
    public String save() {
        addMessage("Success", "Data saved");
        return "listePersonnes?faces-redirect=true";
    }
     
    public void update() {
        addMessage("Success", "Data updated");
    }
     
    public void delete() {
        addMessage("Success", "Data deleted");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public List<Personne> getPersonnes() {
        System.out.print("JSF GET pers");
        return gp.getAllPersonnes();        
    }
    
    public String delete(int id) {
        gp.deletePersonne(id);
        addMessage("Success", "Data deleted");      
        return "listePersonnes?faces-redirect=true";
    }
    
    
}
