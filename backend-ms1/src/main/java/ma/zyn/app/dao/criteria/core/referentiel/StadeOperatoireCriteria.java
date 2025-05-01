package  ma.zyn.app.dao.criteria.core.referentiel;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class StadeOperatoireCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String style;
    private String styleLike;
    private String description;
    private String descriptionLike;
    private String capaciteMin;
    private String capaciteMinMin;
    private String capaciteMinMax;
    private String capaciteMax;
    private String capaciteMaxMin;
    private String capaciteMaxMax;
    private String indice;
    private String indiceMin;
    private String indiceMax;

    private EntiteCriteria entite ;
    private List<EntiteCriteria> entites ;


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

    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }
    public String getStyleLike(){
        return this.styleLike;
    }
    public void setStyleLike(String styleLike){
        this.styleLike = styleLike;
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

    public String getCapaciteMin(){
        return this.capaciteMin;
    }
    public void setCapaciteMin(String capaciteMin){
        this.capaciteMin = capaciteMin;
    }   
    public String getCapaciteMinMin(){
        return this.capaciteMinMin;
    }
    public void setCapaciteMinMin(String capaciteMinMin){
        this.capaciteMinMin = capaciteMinMin;
    }
    public String getCapaciteMinMax(){
        return this.capaciteMinMax;
    }
    public void setCapaciteMinMax(String capaciteMinMax){
        this.capaciteMinMax = capaciteMinMax;
    }
      
    public String getCapaciteMax(){
        return this.capaciteMax;
    }
    public void setCapaciteMax(String capaciteMax){
        this.capaciteMax = capaciteMax;
    }   
    public String getCapaciteMaxMin(){
        return this.capaciteMaxMin;
    }
    public void setCapaciteMaxMin(String capaciteMaxMin){
        this.capaciteMaxMin = capaciteMaxMin;
    }
    public String getCapaciteMaxMax(){
        return this.capaciteMaxMax;
    }
    public void setCapaciteMaxMax(String capaciteMaxMax){
        this.capaciteMaxMax = capaciteMaxMax;
    }
      
    public String getIndice(){
        return this.indice;
    }
    public void setIndice(String indice){
        this.indice = indice;
    }   
    public String getIndiceMin(){
        return this.indiceMin;
    }
    public void setIndiceMin(String indiceMin){
        this.indiceMin = indiceMin;
    }
    public String getIndiceMax(){
        return this.indiceMax;
    }
    public void setIndiceMax(String indiceMax){
        this.indiceMax = indiceMax;
    }
      

    public EntiteCriteria getEntite(){
        return this.entite;
    }

    public void setEntite(EntiteCriteria entite){
        this.entite = entite;
    }
    public List<EntiteCriteria> getEntites(){
        return this.entites;
    }

    public void setEntites(List<EntiteCriteria> entites){
        this.entites = entites;
    }
}
