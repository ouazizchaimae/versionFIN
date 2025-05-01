package ma.zyn.app.bean.core.expedition;

import java.util.List;





import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "analyse_chimique")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="analyse_chimique_seq",sequenceName="analyse_chimique_seq",allocationSize=1, initialValue = 1)
public class AnalyseChimique  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private ProduitMarchand produitMarchand ;

    private List<AnalyseChimiqueDetail> analyseChimiqueDetails ;

    public AnalyseChimique(){
        super();
    }

    public AnalyseChimique(Long id){
        this.id = id;
    }

    public AnalyseChimique(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public AnalyseChimique(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="analyse_chimique_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_marchand")
    public ProduitMarchand getProduitMarchand(){
        return this.produitMarchand;
    }
    public void setProduitMarchand(ProduitMarchand produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    @OneToMany(mappedBy = "analyseChimique")
    public List<AnalyseChimiqueDetail> getAnalyseChimiqueDetails(){
        return this.analyseChimiqueDetails;
    }

    public void setAnalyseChimiqueDetails(List<AnalyseChimiqueDetail> analyseChimiqueDetails){
        this.analyseChimiqueDetails = analyseChimiqueDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyseChimique analyseChimique = (AnalyseChimique) o;
        return id != null && id.equals(analyseChimique.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

