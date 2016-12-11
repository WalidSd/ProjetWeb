package entities;

import entities.Personne;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-11T17:37:57")
@StaticMetamodel(Tache.class)
public class Tache_ { 

    public static volatile SingularAttribute<Tache, Timestamp> dateCreation;
    public static volatile SingularAttribute<Tache, Personne> personne;
    public static volatile SingularAttribute<Tache, Boolean> editable;
    public static volatile SingularAttribute<Tache, String> description;
    public static volatile SingularAttribute<Tache, Integer> id;
    public static volatile SingularAttribute<Tache, String> etat;

}