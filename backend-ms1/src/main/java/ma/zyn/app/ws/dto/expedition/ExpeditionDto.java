package  ma.zyn.app.ws.dto.expedition;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.zyn.app.ws.dto.referentiel.ClientDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpeditionDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;

    private ClientDto client ;
    private TypeExpeditionDto typeExpedition ;

    private List<ExpeditionProduitDto> expeditionProduits ;


    public ExpeditionDto(){
        super();
    }




    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public ClientDto getClient(){
        return this.client;
    }

    public void setClient(ClientDto client){
        this.client = client;
    }
    public TypeExpeditionDto getTypeExpedition(){
        return this.typeExpedition;
    }

    public void setTypeExpedition(TypeExpeditionDto typeExpedition){
        this.typeExpedition = typeExpedition;
    }



    public List<ExpeditionProduitDto> getExpeditionProduits(){
        return this.expeditionProduits;
    }

    public void setExpeditionProduits(List<ExpeditionProduitDto> expeditionProduits){
        this.expeditionProduits = expeditionProduits;
    }



}
