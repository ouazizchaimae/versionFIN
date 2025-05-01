package  ma.zyn.app.ws.dto.demande;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.EtatDemandeDto;
import ma.zyn.app.ws.dto.referentiel.ProduitMarchandDto;
import ma.zyn.app.ws.dto.referentiel.TypeDemandeDto;
import ma.zyn.app.ws.dto.referentiel.ClientDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemandeDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private String dateDemande ;
    private String dateExpedition ;
    private BigDecimal volume  ;
    private String actionEntreprise  ;
    private String trg  ;
    private String cause  ;
    private String commentaire  ;

    private ProduitMarchandDto produitMarchand ;
    private ClientDto client ;
    private TypeDemandeDto typeDemande ;
    private EtatDemandeDto etatDemande ;



    public DemandeDto(){
        super();
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


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateDemande(){
        return this.dateDemande;
    }
    public void setDateDemande(String dateDemande){
        this.dateDemande = dateDemande;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateExpedition(){
        return this.dateExpedition;
    }
    public void setDateExpedition(String dateExpedition){
        this.dateExpedition = dateExpedition;
    }


    public BigDecimal getVolume(){
        return this.volume;
    }
    public void setVolume(BigDecimal volume){
        this.volume = volume;
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


    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }


    public ProduitMarchandDto getProduitMarchand(){
        return this.produitMarchand;
    }

    public void setProduitMarchand(ProduitMarchandDto produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    public ClientDto getClient(){
        return this.client;
    }

    public void setClient(ClientDto client){
        this.client = client;
    }
    public TypeDemandeDto getTypeDemande(){
        return this.typeDemande;
    }

    public void setTypeDemande(TypeDemandeDto typeDemande){
        this.typeDemande = typeDemande;
    }
    public EtatDemandeDto getEtatDemande(){
        return this.etatDemande;
    }

    public void setEtatDemande(EtatDemandeDto etatDemande){
        this.etatDemande = etatDemande;
    }






}
