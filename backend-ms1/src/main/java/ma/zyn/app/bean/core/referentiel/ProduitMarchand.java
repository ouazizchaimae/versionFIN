package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "produit_marchand")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="produit_marchand_seq",sequenceName="produit_marchand_seq",allocationSize=1, initialValue = 1)
public class ProduitMarchand  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;

    private String description;



    public ProduitMarchand(){
        super();
    }

    public ProduitMarchand(Long id){
        this.id = id;
    }

    public ProduitMarchand(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ProduitMarchand(String libelle){
        this.libelle = libelle ;
    }
    public ProduitMarchand(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="produit_marchand_seq")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitMarchand produitMarchand = (ProduitMarchand) o;
        return id != null && id.equals(produitMarchand.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

