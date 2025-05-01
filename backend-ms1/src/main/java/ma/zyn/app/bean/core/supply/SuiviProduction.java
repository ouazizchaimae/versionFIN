package ma.zyn.app.bean.core.supply;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.bean.core.referentiel.Produit;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "suivi_production")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="suivi_production_seq",sequenceName="suivi_production_seq",allocationSize=1, initialValue = 1)
public class SuiviProduction  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private LocalDateTime jour ;

    private BigDecimal volume = BigDecimal.ZERO;

    private BigDecimal tsm = BigDecimal.ZERO;

    private Produit produit ;
    private StadeOperatoire stadeOperatoire ;
    private Unite unite ;


    public SuiviProduction(){
        super();
    }

    public SuiviProduction(Long id){
        this.id = id;
    }

    public SuiviProduction(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public SuiviProduction(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="suivi_production_seq")
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
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public LocalDateTime getJour(){
        return this.jour;
    }
    public void setJour(LocalDateTime jour){
        this.jour = jour;
    }
    public BigDecimal getVolume(){
        return this.volume;
    }
    public void setVolume(BigDecimal volume){
        this.volume = volume;
    }
    public BigDecimal getTsm(){
        return this.tsm;
    }
    public void setTsm(BigDecimal tsm){
        this.tsm = tsm;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit")
    public Produit getProduit(){
        return this.produit;
    }
    public void setProduit(Produit produit){
        this.produit = produit;
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
        SuiviProduction suiviProduction = (SuiviProduction) o;
        return id != null && id.equals(suiviProduction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

