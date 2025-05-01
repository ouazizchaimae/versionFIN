package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto  extends AuditBaseDto {

    private String libelle  ;
    private String code  ;
    private String description  ;

    private TypeClientDto typeClient ;



    public ClientDto(){
        super();
    }




    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public TypeClientDto getTypeClient(){
        return this.typeClient;
    }

    public void setTypeClient(TypeClientDto typeClient){
        this.typeClient = typeClient;
    }






}
