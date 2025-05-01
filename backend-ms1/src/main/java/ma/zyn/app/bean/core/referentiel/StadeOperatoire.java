package ma.zyn.app.bean.core.referentiel;

import java.util.List;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "stade_operatoire")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="stade_operatoire_seq",sequenceName="stade_operatoire_seq",allocationSize=1, initialValue = 1)
public class StadeOperatoire  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;

    private String description;

    private BigDecimal capaciteMin = BigDecimal.ZERO;

    private BigDecimal capaciteMax = BigDecimal.ZERO;

    private Integer indice = 0;

    private Entite entite ;

    private List<StadeOperatoireProduit> stadeOperatoireProduits ;

    public StadeOperatoire(){
        super();
    }

    public StadeOperatoire(Long id){
        this.id = id;
    }

    public StadeOperatoire(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public StadeOperatoire(String libelle){
        this.libelle = libelle ;
    }
    public StadeOperatoire(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="stade_operatoire_seq")
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
    public BigDecimal getCapaciteMin(){
        return this.capaciteMin;
    }
    public void setCapaciteMin(BigDecimal capaciteMin){
        this.capaciteMin = capaciteMin;
    }
    public BigDecimal getCapaciteMax(){
        return this.capaciteMax;
    }
    public void setCapaciteMax(BigDecimal capaciteMax){
        this.capaciteMax = capaciteMax;
    }
    public Integer getIndice(){
        return this.indice;
    }
    public void setIndice(Integer indice){
        this.indice = indice;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entite")
    public Entite getEntite(){
        return this.entite;
    }
    public void setEntite(Entite entite){
        this.entite = entite;
    }
    @OneToMany(mappedBy = "stadeOperatoire")
    public List<StadeOperatoireProduit> getStadeOperatoireProduits(){
        return this.stadeOperatoireProduits;
    }

    public void setStadeOperatoireProduits(List<StadeOperatoireProduit> stadeOperatoireProduits){
        this.stadeOperatoireProduits = stadeOperatoireProduits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StadeOperatoire stadeOperatoire = (StadeOperatoire) o;
        return id != null && id.equals(stadeOperatoire.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

