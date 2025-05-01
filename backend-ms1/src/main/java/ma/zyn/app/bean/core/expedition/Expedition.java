package ma.zyn.app.bean.core.expedition;

import java.util.List;





import ma.zyn.app.bean.core.referentiel.Client;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "expedition")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="expedition_seq",sequenceName="expedition_seq",allocationSize=1, initialValue = 1)
public class Expedition  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private Client client ;
    private TypeExpedition typeExpedition ;

    private List<ExpeditionProduit> expeditionProduits ;

    public Expedition(){
        super();
    }

    public Expedition(Long id){
        this.id = id;
    }

    public Expedition(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Expedition(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="expedition_seq")
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
    @JoinColumn(name = "type_expedition")
    public TypeExpedition getTypeExpedition(){
        return this.typeExpedition;
    }
    public void setTypeExpedition(TypeExpedition typeExpedition){
        this.typeExpedition = typeExpedition;
    }
    @OneToMany(mappedBy = "expedition")
    public List<ExpeditionProduit> getExpeditionProduits(){
        return this.expeditionProduits;
    }

    public void setExpeditionProduits(List<ExpeditionProduit> expeditionProduits){
        this.expeditionProduits = expeditionProduits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expedition expedition = (Expedition) o;
        return id != null && id.equals(expedition.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

