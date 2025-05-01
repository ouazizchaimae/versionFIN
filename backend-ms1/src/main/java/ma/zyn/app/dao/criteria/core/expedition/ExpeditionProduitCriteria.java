package  ma.zyn.app.dao.criteria.core.expedition;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ExpeditionProduitCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String description;
    private String descriptionLike;

    private AnalyseChimiqueCriteria analyseChimique ;
    private List<AnalyseChimiqueCriteria> analyseChimiques ;
    private CharteChimiqueCriteria charteChimique ;
    private List<CharteChimiqueCriteria> charteChimiques ;


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
