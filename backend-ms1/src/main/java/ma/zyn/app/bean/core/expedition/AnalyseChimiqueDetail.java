package ma.zyn.app.bean.core.expedition;






import ma.zyn.app.bean.core.referentiel.ElementChimique;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "analyse_chimique_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="analyse_chimique_detail_seq",sequenceName="analyse_chimique_detail_seq",allocationSize=1, initialValue = 1)
public class AnalyseChimiqueDetail  extends BaseEntity     {




    @Column(length = 500)
    private String libelle;

    private String description;

    private BigDecimal valeur = BigDecimal.ZERO;

    @Column(columnDefinition = "boolean default false")
    private Boolean conformite = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean surqualite = false;

    private ElementChimique elementChimique ;
    private AnalyseChimique analyseChimique ;


    public AnalyseChimiqueDetail(){
        super();
    }

    public AnalyseChimiqueDetail(Long id){
        this.id = id;
    }

    public AnalyseChimiqueDetail(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public AnalyseChimiqueDetail(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="analyse_chimique_detail_seq")
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
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "element_chimique")
    public ElementChimique getElementChimique(){
        return this.elementChimique;
    }
    public void setElementChimique(ElementChimique elementChimique){
        this.elementChimique = elementChimique;
    }
    public BigDecimal getValeur(){
        return this.valeur;
    }
    public void setValeur(BigDecimal valeur){
        this.valeur = valeur;
    }
    public Boolean  getConformite(){
        return this.conformite;
    }
    public void setConformite(Boolean conformite){
        this.conformite = conformite;
    }
    public Boolean  getSurqualite(){
        return this.surqualite;
    }
    public void setSurqualite(Boolean surqualite){
        this.surqualite = surqualite;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyse_chimique")
    public AnalyseChimique getAnalyseChimique(){
        return this.analyseChimique;
    }
    public void setAnalyseChimique(AnalyseChimique analyseChimique){
        this.analyseChimique = analyseChimique;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyseChimiqueDetail analyseChimiqueDetail = (AnalyseChimiqueDetail) o;
        return id != null && id.equals(analyseChimiqueDetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

