package  ma.zyn.app.dao.criteria.core.supply;


import ma.zyn.app.dao.criteria.core.referentiel.UniteCriteria;
import ma.zyn.app.dao.criteria.core.referentiel.StadeOperatoireCriteria;
import ma.zyn.app.dao.criteria.core.referentiel.ProduitCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class SuiviProductionCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String description;
    private String descriptionLike;
    private LocalDateTime jour;
    private LocalDateTime jourFrom;
    private LocalDateTime jourTo;
    private String volume;
    private String volumeMin;
    private String volumeMax;
    private String tsm;
    private String tsmMin;
    private String tsmMax;

    private ProduitCriteria produit ;
    private List<ProduitCriteria> produits ;
    private StadeOperatoireCriteria stadeOperatoire ;
    private List<StadeOperatoireCriteria> stadeOperatoires ;
    private UniteCriteria unite ;
    private List<UniteCriteria> unites ;


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

    public LocalDateTime getJour(){
        return this.jour;
    }
    public void setJour(LocalDateTime jour){
        this.jour = jour;
    }
    public LocalDateTime getJourFrom(){
        return this.jourFrom;
    }
    public void setJourFrom(LocalDateTime jourFrom){
        this.jourFrom = jourFrom;
    }
    public LocalDateTime getJourTo(){
        return this.jourTo;
    }
    public void setJourTo(LocalDateTime jourTo){
        this.jourTo = jourTo;
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
      
    public String getTsm(){
        return this.tsm;
    }
    public void setTsm(String tsm){
        this.tsm = tsm;
    }   
    public String getTsmMin(){
        return this.tsmMin;
    }
    public void setTsmMin(String tsmMin){
        this.tsmMin = tsmMin;
    }
    public String getTsmMax(){
        return this.tsmMax;
    }
    public void setTsmMax(String tsmMax){
        this.tsmMax = tsmMax;
    }
      

    public ProduitCriteria getProduit(){
        return this.produit;
    }

    public void setProduit(ProduitCriteria produit){
        this.produit = produit;
    }
    public List<ProduitCriteria> getProduits(){
        return this.produits;
    }

    public void setProduits(List<ProduitCriteria> produits){
        this.produits = produits;
    }
    public StadeOperatoireCriteria getStadeOperatoire(){
        return this.stadeOperatoire;
    }

    public void setStadeOperatoire(StadeOperatoireCriteria stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public List<StadeOperatoireCriteria> getStadeOperatoires(){
        return this.stadeOperatoires;
    }

    public void setStadeOperatoires(List<StadeOperatoireCriteria> stadeOperatoires){
        this.stadeOperatoires = stadeOperatoires;
    }
    public UniteCriteria getUnite(){
        return this.unite;
    }

    public void setUnite(UniteCriteria unite){
        this.unite = unite;
    }
    public List<UniteCriteria> getUnites(){
        return this.unites;
    }

    public void setUnites(List<UniteCriteria> unites){
        this.unites = unites;
    }
}
