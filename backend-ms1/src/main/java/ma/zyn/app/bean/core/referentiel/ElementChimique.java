package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "element_chimique")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="element_chimique_seq",sequenceName="element_chimique_seq",allocationSize=1, initialValue = 1)
public class ElementChimique  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;

    private String description;

    private Unite unite ;


    public ElementChimique(){
        super();
    }

    public ElementChimique(Long id){
        this.id = id;
    }

    public ElementChimique(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ElementChimique(String libelle){
        this.libelle = libelle ;
    }
    public ElementChimique(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="element_chimique_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unite")
    public Unite getUnite(){
        return this.unite;
    }
    public void setUnite(Unite unite){
        this.unite = unite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementChimique elementChimique = (ElementChimique) o;
        return id != null && id.equals(elementChimique.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

