package  ma.zyn.app.dao.criteria.core.referentiel;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class RatioUniteCriteria extends  BaseCriteria  {

    private String ratio;
    private String ratioMin;
    private String ratioMax;

    private EntiteCriteria entite ;
    private List<EntiteCriteria> entites ;
    private ProduitCriteria produit ;
    private List<ProduitCriteria> produits ;


    public String getRatio(){
        return this.ratio;
    }
    public void setRatio(String ratio){
        this.ratio = ratio;
    }   
    public String getRatioMin(){
        return this.ratioMin;
    }
    public void setRatioMin(String ratioMin){
        this.ratioMin = ratioMin;
    }
    public String getRatioMax(){
        return this.ratioMax;
    }
    public void setRatioMax(String ratioMax){
        this.ratioMax = ratioMax;
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
}
