package ma.zyn.app.bean.core.expedition;






import ma.zyn.app.bean.core.referentiel.ElementChimique;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "charte_chimique_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="charte_chimique_detail_seq",sequenceName="charte_chimique_detail_seq",allocationSize=1, initialValue = 1)
public class CharteChimiqueDetail  extends BaseEntity     {




    @Column(length = 500)
    private String libelle;

    private String description;

    private BigDecimal minimum = BigDecimal.ZERO;

    private BigDecimal maximum = BigDecimal.ZERO;

    private BigDecimal average = BigDecimal.ZERO;

    @Column(length = 500)
    private String methodeAnalyse;

    @Column(length = 500)
    private String unite;

    private ElementChimique elementChimique ;
    private CharteChimique charteChimique ;


    public CharteChimiqueDetail(){
        super();
    }

    public CharteChimiqueDetail(Long id){
        this.id = id;
    }

    public CharteChimiqueDetail(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public CharteChimiqueDetail(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="charte_chimique_detail_seq")
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
    public BigDecimal getMinimum(){
        return this.minimum;
    }
    public void setMinimum(BigDecimal minimum){
        this.minimum = minimum;
    }
    public BigDecimal getMaximum(){
        return this.maximum;
    }
    public void setMaximum(BigDecimal maximum){
        this.maximum = maximum;
    }
    public BigDecimal getAverage(){
        return this.average;
    }
    public void setAverage(BigDecimal average){
        this.average = average;
    }
    public String getMethodeAnalyse(){
        return this.methodeAnalyse;
    }
    public void setMethodeAnalyse(String methodeAnalyse){
        this.methodeAnalyse = methodeAnalyse;
    }
    public String getUnite(){
        return this.unite;
    }
    public void setUnite(String unite){
        this.unite = unite;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charte_chimique")
    public CharteChimique getCharteChimique(){
        return this.charteChimique;
    }
    public void setCharteChimique(CharteChimique charteChimique){
        this.charteChimique = charteChimique;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharteChimiqueDetail charteChimiqueDetail = (CharteChimiqueDetail) o;
        return id != null && id.equals(charteChimiqueDetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

