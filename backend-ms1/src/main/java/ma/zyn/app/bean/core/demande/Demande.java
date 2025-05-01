package ma.zyn.app.bean.core.demande;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.bean.core.referentiel.Client;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "demande")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="demande_seq",sequenceName="demande_seq",allocationSize=1, initialValue = 1)
public class Demande  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private LocalDateTime dateDemande ;

    private LocalDateTime dateExpedition ;

    private BigDecimal volume = BigDecimal.ZERO;

    @Column(length = 500)
    private String actionEntreprise;

    @Column(length = 500)
    private String trg;

    @Column(length = 500)
    private String cause;

    private String commentaire;

    private ProduitMarchand produitMarchand ;
    private Client client ;
    private TypeDemande typeDemande ;
    private EtatDemande etatDemande ;


    public Demande(){
        super();
    }

    public Demande(Long id){
        this.id = id;
    }

    public Demande(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Demande(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="demande_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client")
    public Client getClient(){
        return this.client;
    }
    public void setClient(Client client){
        this.client = client;
    }
    public LocalDateTime getDateDemande(){
        return this.dateDemande;
    }
    public void setDateDemande(LocalDateTime dateDemande){
        this.dateDemande = dateDemande;
    }
    public LocalDateTime getDateExpedition(){
        return this.dateExpedition;
    }
    public void setDateExpedition(LocalDateTime dateExpedition){
        this.dateExpedition = dateExpedition;
    }
    public BigDecimal getVolume(){
        return this.volume;
    }
    public void setVolume(BigDecimal volume){
        this.volume = volume;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_demande")
    public TypeDemande getTypeDemande(){
        return this.typeDemande;
    }
    public void setTypeDemande(TypeDemande typeDemande){
        this.typeDemande = typeDemande;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etat_demande")
    public EtatDemande getEtatDemande(){
        return this.etatDemande;
    }
    public void setEtatDemande(EtatDemande etatDemande){
        this.etatDemande = etatDemande;
    }
    public String getActionEntreprise(){
        return this.actionEntreprise;
    }
    public void setActionEntreprise(String actionEntreprise){
        this.actionEntreprise = actionEntreprise;
    }
    public String getTrg(){
        return this.trg;
    }
    public void setTrg(String trg){
        this.trg = trg;
    }
    public String getCause(){
        return this.cause;
    }
    public void setCause(String cause){
        this.cause = cause;
    }
      @Column(columnDefinition="TEXT")
    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demande demande = (Demande) o;
        return id != null && id.equals(demande.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

