package  ma.zyn.app.dao.criteria.core.expedition;


import ma.zyn.app.dao.criteria.core.referentiel.ClientCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ExpeditionCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String description;
    private String descriptionLike;

    private ClientCriteria client ;
    private List<ClientCriteria> clients ;
    private TypeExpeditionCriteria typeExpedition ;
    private List<TypeExpeditionCriteria> typeExpeditions ;


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
    public TypeExpeditionCriteria getTypeExpedition(){
        return this.typeExpedition;
    }

    public void setTypeExpedition(TypeExpeditionCriteria typeExpedition){
        this.typeExpedition = typeExpedition;
    }
    public List<TypeExpeditionCriteria> getTypeExpeditions(){
        return this.typeExpeditions;
    }

    public void setTypeExpeditions(List<TypeExpeditionCriteria> typeExpeditions){
        this.typeExpeditions = typeExpeditions;
    }
}
