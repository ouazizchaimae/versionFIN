package ma.zyn.app.bean.core.expedition;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "expedition_produit")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="expedition_produit_seq",sequenceName="expedition_produit_seq",allocationSize=1, initialValue = 1)
public class ExpeditionProduit  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private AnalyseChimique analyseChimique ;
    private CharteChimique charteChimique ;

    private Expedition expedition;
    public ExpeditionProduit(){
        super();
    }

    public ExpeditionProduit(Long id){
        this.id = id;
    }

    public ExpeditionProduit(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ExpeditionProduit(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="expedition_produit_seq")
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
    @JoinColumn(name = "analyse_chimique")
    public AnalyseChimique getAnalyseChimique(){
        return this.analyseChimique;
    }
    public void setAnalyseChimique(AnalyseChimique analyseChimique){
        this.analyseChimique = analyseChimique;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charte_chimique")
    public CharteChimique getCharteChimique(){
        return this.charteChimique;
    }
    public void setCharteChimique(CharteChimique charteChimique){
        this.charteChimique = charteChimique;
    }
    @ManyToOne
    @JoinColumn(name = "expedition_id") // ou le nom de colonne que vous préférez
    public Expedition getExpedition(){
        return this.expedition;
    }
    public void setExpedition(Expedition expedition){
        this.expedition = expedition;
    }

   }

