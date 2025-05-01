package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProduitSourceDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String style  ;
    private String description  ;




    public ProduitSourceDto(){
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


    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }








}
