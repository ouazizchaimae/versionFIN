package ma.zyn.app.bean.core.referentiel;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "ratio_unite")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="ratio_unite_seq",sequenceName="ratio_unite_seq",allocationSize=1, initialValue = 1)
public class RatioUnite  extends BaseEntity     {




    private BigDecimal ratio = BigDecimal.ZERO;

    private Entite entite ;
    private Produit produit ;


    public RatioUnite(){
        super();
    }

    public RatioUnite(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="ratio_unite_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entite")
    public Entite getEntite(){
        return this.entite;
    }
    public void setEntite(Entite entite){
        this.entite = entite;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit")
    public Produit getProduit(){
        return this.produit;
    }
    public void setProduit(Produit produit){
        this.produit = produit;
    }
    public BigDecimal getRatio(){
        return this.ratio;
    }
    public void setRatio(BigDecimal ratio){
        this.ratio = ratio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatioUnite ratioUnite = (RatioUnite) o;
        return id != null && id.equals(ratioUnite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

