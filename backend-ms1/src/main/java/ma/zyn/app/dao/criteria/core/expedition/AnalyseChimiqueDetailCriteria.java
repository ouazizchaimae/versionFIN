package  ma.zyn.app.dao.criteria.core.expedition;


import ma.zyn.app.dao.criteria.core.referentiel.ElementChimiqueCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class AnalyseChimiqueDetailCriteria extends  BaseCriteria  {

    private String libelle;
    private String libelleLike;
    private String description;
    private String descriptionLike;
    private String valeur;
    private String valeurMin;
    private String valeurMax;
    private Boolean conformite;
    private Boolean surqualite;

    private ElementChimiqueCriteria elementChimique ;
    private List<ElementChimiqueCriteria> elementChimiques ;
    private AnalyseChimiqueCriteria analyseChimique ;
    private List<AnalyseChimiqueCriteria> analyseChimiques ;


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

    public String getValeur(){
        return this.valeur;
    }
    public void setValeur(String valeur){
        this.valeur = valeur;
    }   
    public String getValeurMin(){
        return this.valeurMin;
    }
    public void setValeurMin(String valeurMin){
        this.valeurMin = valeurMin;
    }
    public String getValeurMax(){
        return this.valeurMax;
    }
    public void setValeurMax(String valeurMax){
        this.valeurMax = valeurMax;
    }
      
    public Boolean getConformite(){
        return this.conformite;
    }
    public void setConformite(Boolean conformite){
        this.conformite = conformite;
    }
    public Boolean getSurqualite(){
        return this.surqualite;
    }
    public void setSurqualite(Boolean surqualite){
        this.surqualite = surqualite;
    }

    public ElementChimiqueCriteria getElementChimique(){
        return this.elementChimique;
    }

    public void setElementChimique(ElementChimiqueCriteria elementChimique){
        this.elementChimique = elementChimique;
    }
    public List<ElementChimiqueCriteria> getElementChimiques(){
        return this.elementChimiques;
    }

    public void setElementChimiques(List<ElementChimiqueCriteria> elementChimiques){
        this.elementChimiques = elementChimiques;
    }
    public AnalyseChimiqueCriteria getAnalyseChimique(){
        return this.analyseChimique;
    }

    public void setAnalyseChimique(AnalyseChimiqueCriteria analyseChimique){
        this.analyseChimique = analyseChimique;
    }
    public List<AnalyseChimiqueCriteria> getAnalyseChimiques(){
        return this.analyseChimiques;
    }

    public void setAnalyseChimiques(List<AnalyseChimiqueCriteria> analyseChimiques){
        this.analyseChimiques = analyseChimiques;
    }
}
