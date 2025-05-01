package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stade_operatoire_produit")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="stade_operatoire_produit_seq",sequenceName="stade_operatoire_produit_seq",allocationSize=1, initialValue = 1)
public class StadeOperatoireProduit  extends BaseEntity     {




    private StadeOperatoire stadeOperatoire ;
    private Produit produit ;


    public StadeOperatoireProduit(){
        super();
    }

    public StadeOperatoireProduit(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="stade_operatoire_produit_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stade_operatoire")
    public StadeOperatoire getStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void setStadeOperatoire(StadeOperatoire stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit")
    public Produit getProduit(){
        return this.produit;
    }
    public void setProduit(Produit produit){
        this.produit = produit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StadeOperatoireProduit stadeOperatoireProduit = (StadeOperatoireProduit) o;
        return id != null && id.equals(stadeOperatoireProduit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

