package  ma.zyn.app.dao.criteria.core.referentiel;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ClientCriteria extends  BaseCriteria  {

    private String libelle;
    private String libelleLike;
    private String code;
    private String codeLike;
    private String description;
    private String descriptionLike;

    private TypeClientCriteria typeClient ;
    private List<TypeClientCriteria> typeClients ;


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


    public TypeClientCriteria getTypeClient(){
        return this.typeClient;
    }

    public void setTypeClient(TypeClientCriteria typeClient){
        this.typeClient = typeClient;
    }
    public List<TypeClientCriteria> getTypeClients(){
        return this.typeClients;
    }

    public void setTypeClients(List<TypeClientCriteria> typeClients){
        this.typeClients = typeClients;
    }
}
