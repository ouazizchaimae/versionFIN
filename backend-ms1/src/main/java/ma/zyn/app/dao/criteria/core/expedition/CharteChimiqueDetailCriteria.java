package  ma.zyn.app.dao.criteria.core.expedition;


import ma.zyn.app.dao.criteria.core.referentiel.ElementChimiqueCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class CharteChimiqueDetailCriteria extends  BaseCriteria  {

    private String libelle;
    private String libelleLike;
    private String description;
    private String descriptionLike;
    private String minimum;
    private String minimumMin;
    private String minimumMax;
    private String maximum;
    private String maximumMin;
    private String maximumMax;
    private String average;
    private String averageMin;
    private String averageMax;
    private String methodeAnalyse;
    private String methodeAnalyseLike;
    private String unite;
    private String uniteLike;

    private ElementChimiqueCriteria elementChimique ;
    private List<ElementChimiqueCriteria> elementChimiques ;
    private CharteChimiqueCriteria charteChimique ;
    private List<CharteChimiqueCriteria> charteChimiques ;


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

    public String getMinimum(){
        return this.minimum;
    }
    public void setMinimum(String minimum){
        this.minimum = minimum;
    }   
    public String getMinimumMin(){
        return this.minimumMin;
    }
    public void setMinimumMin(String minimumMin){
        this.minimumMin = minimumMin;
    }
    public String getMinimumMax(){
        return this.minimumMax;
    }
    public void setMinimumMax(String minimumMax){
        this.minimumMax = minimumMax;
    }
      
    public String getMaximum(){
        return this.maximum;
    }
    public void setMaximum(String maximum){
        this.maximum = maximum;
    }   
    public String getMaximumMin(){
        return this.maximumMin;
    }
    public void setMaximumMin(String maximumMin){
        this.maximumMin = maximumMin;
    }
    public String getMaximumMax(){
        return this.maximumMax;
    }
    public void setMaximumMax(String maximumMax){
        this.maximumMax = maximumMax;
    }
      
    public String getAverage(){
        return this.average;
    }
    public void setAverage(String average){
        this.average = average;
    }   
    public String getAverageMin(){
        return this.averageMin;
    }
    public void setAverageMin(String averageMin){
        this.averageMin = averageMin;
    }
    public String getAverageMax(){
        return this.averageMax;
    }
    public void setAverageMax(String averageMax){
        this.averageMax = averageMax;
    }
      
    public String getMethodeAnalyse(){
        return this.methodeAnalyse;
    }
    public void setMethodeAnalyse(String methodeAnalyse){
        this.methodeAnalyse = methodeAnalyse;
    }
    public String getMethodeAnalyseLike(){
        return this.methodeAnalyseLike;
    }
    public void setMethodeAnalyseLike(String methodeAnalyseLike){
        this.methodeAnalyseLike = methodeAnalyseLike;
    }

    public String getUnite(){
        return this.unite;
    }
    public void setUnite(String unite){
        this.unite = unite;
    }
    public String getUniteLike(){
        return this.uniteLike;
    }
    public void setUniteLike(String uniteLike){
        this.uniteLike = uniteLike;
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
    public CharteChimiqueCriteria getCharteChimique(){
        return this.charteChimique;
    }

    public void setCharteChimique(CharteChimiqueCriteria charteChimique){
        this.charteChimique = charteChimique;
    }
    public List<CharteChimiqueCriteria> getCharteChimiques(){
        return this.charteChimiques;
    }

    public void setCharteChimiques(List<CharteChimiqueCriteria> charteChimiques){
        this.charteChimiques = charteChimiques;
    }
}
