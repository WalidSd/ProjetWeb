package jsf;

import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import entities.Personne;
import entities.Tache;
import gestionnaires.GestionnaireTaches;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Clock;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import javax.faces.bean.ManagedBean;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.RowEditEvent;

@Named(value = "tacheMBean")
@ViewScoped
public class TacheMBean implements Serializable {

    @EJB
    private GestionnaireTaches gt;

    public TacheMBean() {
    }

    public void creerDonnesDeTest() {
        System.out.print("JSF BEAN CREATION DE TEST taches");
        gt.creerTachesDeTest();
    }

    public List<Tache> getTaches() {
        System.out.print("JSF GET taches encore");
        return gt.getAllTaches();
    }

    public String save() {
        addMessage("Success", "Data saved");
        return "listeTaches?faces-redirect=true";
    }

    public String modifTaches() {
        return "modifTaches?faces-redirect=true";
    }

    public String delete(int id) {
        gt.deleteTache(id);
        addMessage("Success", "Data deleted");
        return "listeTaches?faces-redirect=true";
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String editAction(Tache tache) {
        System.out.print("--------1---------");
        tache.setEditable(true);
        System.out.print(tache.isEditable());
        return null;
    }

    public String saveAction() {

        //get all existing value but set "editable" to false
        List<Tache> myList = gt.getAllTaches();
        for (Tache tache : myList) {
            tache.setEditable(false);
        }
        //return to current page
        return null;

    }

    public void onCellEdit(CellEditEvent event) {
        System.out.print("--------3-------------");
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         System.out.println(">nouvelle value = " + newValue);
        if(newValue != null && !newValue.equals(oldValue)) {
            System.out.print("--------4-------------");
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        System.out.print("--------5-------------");
        /*
        Tache t = (Tache) event.getObject();
        int id=t.getId();
        String desc=t.getDescription();
        String etat=t.getEtat();
        Timestamp date=t.getDateCreation();
        //Personne p=t.getPersonne();
        gt.updateTache(id,desc,date,etat);      
                */
        
    }

}
