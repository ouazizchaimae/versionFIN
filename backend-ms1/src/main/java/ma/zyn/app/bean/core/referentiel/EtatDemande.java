package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "etat_demande")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="etat_demande_seq",sequenceName="etat_demande_seq",allocationSize=1, initialValue = 1)
public class EtatDemande  extends BaseEntity     {




    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String style;

    private String description;



    public EtatDemande(){
        super();
    }

    public EtatDemande(Long id){
        this.id = id;
    }

    public EtatDemande(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public EtatDemande(String libelle){
        this.libelle = libelle ;
    }
    public EtatDemande(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="etat_demande_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EtatDemande etatDemande = (EtatDemande) o;
        return id != null && id.equals(etatDemande.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

