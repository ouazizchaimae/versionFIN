package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class StadeOperatoireDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String style  ;
    private String description  ;
    private BigDecimal capaciteMin  ;
    private BigDecimal capaciteMax  ;
    private Integer indice  = 0 ;

    private EntiteDto entite ;

    private List<StadeOperatoireProduitDto> stadeOperatoireProduits ;


    public StadeOperatoireDto(){
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


    public BigDecimal getCapaciteMin(){
        return this.capaciteMin;
    }
    public void setCapaciteMin(BigDecimal capaciteMin){
        this.capaciteMin = capaciteMin;
    }


    public BigDecimal getCapaciteMax(){
        return this.capaciteMax;
    }
    public void setCapaciteMax(BigDecimal capaciteMax){
        this.capaciteMax = capaciteMax;
    }


    public Integer getIndice(){
        return this.indice;
    }
    public void setIndice(Integer indice){
        this.indice = indice;
    }


    public EntiteDto getEntite(){
        return this.entite;
    }

    public void setEntite(EntiteDto entite){
        this.entite = entite;
    }



    public List<StadeOperatoireProduitDto> getStadeOperatoireProduits(){
        return this.stadeOperatoireProduits;
    }

    public void setStadeOperatoireProduits(List<StadeOperatoireProduitDto> stadeOperatoireProduits){
        this.stadeOperatoireProduits = stadeOperatoireProduits;
    }



}
