package  ma.zyn.app.dao.criteria.core.referentiel;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class StadeOperatoireProduitCriteria extends  BaseCriteria  {


    private StadeOperatoireCriteria stadeOperatoire ;
    private List<StadeOperatoireCriteria> stadeOperatoires ;
    private ProduitCriteria produit ;
    private List<ProduitCriteria> produits ;



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
