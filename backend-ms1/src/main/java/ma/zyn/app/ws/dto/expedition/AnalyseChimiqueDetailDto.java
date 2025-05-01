package  ma.zyn.app.ws.dto.expedition;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.ElementChimiqueDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyseChimiqueDetailDto  extends AuditBaseDto {

    private String libelle  ;
    private String description  ;
    private BigDecimal valeur  ;
    private Boolean conformite  ;
    private Boolean surqualite  ;

    private ElementChimiqueDto elementChimique ;
    private AnalyseChimiqueDto analyseChimique ;



    public AnalyseChimiqueDetailDto(){
        super();
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


    public BigDecimal getValeur(){
        return this.valeur;
    }
    public void setValeur(BigDecimal valeur){
        this.valeur = valeur;
    }


    public Boolean getConformite(){
        return this.conformite;
    }
    public void setConformite(Boolean conformite){
        this.conformite = conformite;
    }


    public Boolean getSurqualite(){
        return this.surqualite;
    }
    public void setSurqualite(Boolean surqualite){
        this.surqualite = surqualite;
    }


    public ElementChimiqueDto getElementChimique(){
        return this.elementChimique;
    }

    public void setElementChimique(ElementChimiqueDto elementChimique){
        this.elementChimique = elementChimique;
    }
    public AnalyseChimiqueDto getAnalyseChimique(){
        return this.analyseChimique;
    }

    public void setAnalyseChimique(AnalyseChimiqueDto analyseChimique){
        this.analyseChimique = analyseChimique;
    }






}
