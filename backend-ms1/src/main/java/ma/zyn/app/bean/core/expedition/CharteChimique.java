package ma.zyn.app.bean.core.expedition;

import java.util.List;





import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.bean.core.referentiel.Client;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "charte_chimique")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="charte_chimique_seq",sequenceName="charte_chimique_seq",allocationSize=1, initialValue = 1)
public class CharteChimique  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private Client client ;
    private ProduitMarchand produitMarchand ;

    private List<CharteChimiqueDetail> charteChimiqueDetails ;

    public CharteChimique(){
        super();
    }

    public CharteChimique(Long id){
        this.id = id;
    }

    public CharteChimique(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public CharteChimique(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="charte_chimique_seq")
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
    @JoinColumn(name = "client")
    public Client getClient(){
        return this.client;
    }
    public void setClient(Client client){
        this.client = client;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_marchand")
    public ProduitMarchand getProduitMarchand(){
        return this.produitMarchand;
    }
    public void setProduitMarchand(ProduitMarchand produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    @OneToMany(mappedBy = "charteChimique")
    public List<CharteChimiqueDetail> getCharteChimiqueDetails(){
        return this.charteChimiqueDetails;
    }

    public void setCharteChimiqueDetails(List<CharteChimiqueDetail> charteChimiqueDetails){
        this.charteChimiqueDetails = charteChimiqueDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharteChimique charteChimique = (CharteChimique) o;
        return id != null && id.equals(charteChimique.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

