package  ma.zyn.app.dao.criteria.core.demande;


import ma.zyn.app.dao.criteria.core.referentiel.EtatDemandeCriteria;
import ma.zyn.app.dao.criteria.core.referentiel.ProduitMarchandCriteria;
import ma.zyn.app.dao.criteria.core.referentiel.TypeDemandeCriteria;
import ma.zyn.app.dao.criteria.core.referentiel.ClientCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class DemandeCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String description;
    private String descriptionLike;
    private LocalDateTime dateDemande;
    private LocalDateTime dateDemandeFrom;
    private LocalDateTime dateDemandeTo;
    private LocalDateTime dateExpedition;
    private LocalDateTime dateExpeditionFrom;
    private LocalDateTime dateExpeditionTo;
    private String volume;
    private String volumeMin;
    private String volumeMax;
    private String actionEntreprise;
    private String actionEntrepriseLike;
    private String trg;
    private String trgLike;
    private String cause;
    private String causeLike;
    private String commentaire;
    private String commentaireLike;

    private ProduitMarchandCriteria produitMarchand ;
    private List<ProduitMarchandCriteria> produitMarchands ;
    private ClientCriteria client ;
    private List<ClientCriteria> clients ;
    private TypeDemandeCriteria typeDemande ;
    private List<TypeDemandeCriteria> typeDemandes ;
    private EtatDemandeCriteria etatDemande ;
    private List<EtatDemandeCriteria> etatDemandes ;


    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public LocalDateTime getDateDemande(){
        return this.dateDemande;
    }
    public void setDateDemande(LocalDateTime dateDemande){
        this.dateDemande = dateDemande;
    }
    public LocalDateTime getDateDemandeFrom(){
        return this.dateDemandeFrom;
    }
    public void setDateDemandeFrom(LocalDateTime dateDemandeFrom){
        this.dateDemandeFrom = dateDemandeFrom;
    }
    public LocalDateTime getDateDemandeTo(){
        return this.dateDemandeTo;
    }
    public void setDateDemandeTo(LocalDateTime dateDemandeTo){
        this.dateDemandeTo = dateDemandeTo;
    }
    public LocalDateTime getDateExpedition(){
        return this.dateExpedition;
    }
    public void setDateExpedition(LocalDateTime dateExpedition){
        this.dateExpedition = dateExpedition;
    }
    public LocalDateTime getDateExpeditionFrom(){
        return this.dateExpeditionFrom;
    }
    public void setDateExpeditionFrom(LocalDateTime dateExpeditionFrom){
        this.dateExpeditionFrom = dateExpeditionFrom;
    }
    public LocalDateTime getDateExpeditionTo(){
        return this.dateExpeditionTo;
    }
    public void setDateExpeditionTo(LocalDateTime dateExpeditionTo){
        this.dateExpeditionTo = dateExpeditionTo;
    }
    public String getVolume(){
        return this.volume;
    }
    public void setVolume(String volume){
        this.volume = volume;
    }   
    public String getVolumeMin(){
        return this.volumeMin;
    }
    public void setVolumeMin(String volumeMin){
        this.volumeMin = volumeMin;
    }
    public String getVolumeMax(){
        return this.volumeMax;
    }
    public void setVolumeMax(String volumeMax){
        this.volumeMax = volumeMax;
    }
      
    public String getActionEntreprise(){
        return this.actionEntreprise;
    }
    public void setActionEntreprise(String actionEntreprise){
        this.actionEntreprise = actionEntreprise;
    }
    public String getActionEntrepriseLike(){
        return this.actionEntrepriseLike;
    }
    public void setActionEntrepriseLike(String actionEntrepriseLike){
        this.actionEntrepriseLike = actionEntrepriseLike;
    }

    public String getTrg(){
        return this.trg;
    }
    public void setTrg(String trg){
        this.trg = trg;
    }
    public String getTrgLike(){
        return this.trgLike;
    }
    public void setTrgLike(String trgLike){
        this.trgLike = trgLike;
    }

    public String getCause(){
        return this.cause;
    }
    public void setCause(String cause){
        this.cause = cause;
    }
    public String getCauseLike(){
        return this.causeLike;
    }
    public void setCauseLike(String causeLike){
        this.causeLike = causeLike;
    }

    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }
    public String getCommentaireLike(){
        return this.commentaireLike;
    }
    public void setCommentaireLike(String commentaireLike){
        this.commentaireLike = commentaireLike;
    }


    public ProduitMarchandCriteria getProduitMarchand(){
        return this.produitMarchand;
    }

    public void setProduitMarchand(ProduitMarchandCriteria produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    public List<ProduitMarchandCriteria> getProduitMarchands(){
        return this.produitMarchands;
    }

    public void setProduitMarchands(List<ProduitMarchandCriteria> produitMarchands){
        this.produitMarchands = produitMarchands;
    }
    public ClientCriteria getClient(){
        return this.client;
    }

    public void setClient(ClientCriteria client){
        this.client = client;
    }
    public List<ClientCriteria> getClients(){
        return this.clients;
    }

    public void setClients(List<ClientCriteria> clients){
        this.clients = clients;
    }
    public TypeDemandeCriteria getTypeDemande(){
        return this.typeDemande;
    }

    public void setTypeDemande(TypeDemandeCriteria typeDemande){
        this.typeDemande = typeDemande;
    }
    public List<TypeDemandeCriteria> getTypeDemandes(){
        return this.typeDemandes;
    }

    public void setTypeDemandes(List<TypeDemandeCriteria> typeDemandes){
        this.typeDemandes = typeDemandes;
    }
    public EtatDemandeCriteria getEtatDemande(){
        return this.etatDemande;
    }

    public void setEtatDemande(EtatDemandeCriteria etatDemande){
        this.etatDemande = etatDemande;
    }
    public List<EtatDemandeCriteria> getEtatDemandes(){
        return this.etatDemandes;
    }

    public void setEtatDemandes(List<EtatDemandeCriteria> etatDemandes){
        this.etatDemandes = etatDemandes;
    }
}
