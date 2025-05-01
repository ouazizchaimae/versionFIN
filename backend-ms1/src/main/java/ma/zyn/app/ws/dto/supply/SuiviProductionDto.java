package  ma.zyn.app.ws.dto.supply;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.UniteDto;
import ma.zyn.app.ws.dto.referentiel.StadeOperatoireDto;
import ma.zyn.app.ws.dto.referentiel.ProduitDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuiviProductionDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private String jour ;
    private BigDecimal volume  ;
    private BigDecimal tsm  ;

    private ProduitDto produit ;
    private StadeOperatoireDto stadeOperatoire ;
    private UniteDto unite ;



    public SuiviProductionDto(){
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


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getJour(){
        return this.jour;
    }
    public void setJour(String jour){
        this.jour = jour;
    }


    public BigDecimal getVolume(){
        return this.volume;
    }
    public void setVolume(BigDecimal volume){
        this.volume = volume;
    }


    public BigDecimal getTsm(){
        return this.tsm;
    }
    public void setTsm(BigDecimal tsm){
        this.tsm = tsm;
    }


    public ProduitDto getProduit(){
        return this.produit;
    }

    public void setProduit(ProduitDto produit){
        this.produit = produit;
    }
    public StadeOperatoireDto getStadeOperatoire(){
        return this.stadeOperatoire;
    }

    public void setStadeOperatoire(StadeOperatoireDto stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public UniteDto getUnite(){
        return this.unite;
    }

    public void setUnite(UniteDto unite){
        this.unite = unite;
    }






}
