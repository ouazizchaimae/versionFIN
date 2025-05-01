package  ma.zyn.app.dao.criteria.core.expedition;


import ma.zyn.app.dao.criteria.core.referentiel.ProduitMarchandCriteria;
import ma.zyn.app.dao.criteria.core.referentiel.ClientCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class CharteChimiqueCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String description;
    private String descriptionLike;

    private ClientCriteria client ;
    private List<ClientCriteria> clients ;
    private ProduitMarchandCriteria produitMarchand ;
    private List<ProduitMarchandCriteria> produitMarchands ;


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
}
