package  ma.zyn.app.ws.dto.expedition;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.referentiel.ElementChimiqueDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharteChimiqueDetailDto  extends AuditBaseDto {

    private String libelle  ;
    private String description  ;
    private BigDecimal minimum  ;
    private BigDecimal maximum  ;
    private BigDecimal average  ;
    private String methodeAnalyse  ;
    private String unite  ;

    private ElementChimiqueDto elementChimique ;
    private CharteChimiqueDto charteChimique ;



    public CharteChimiqueDetailDto(){
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


    public BigDecimal getMinimum(){
        return this.minimum;
    }
    public void setMinimum(BigDecimal minimum){
        this.minimum = minimum;
    }


    public BigDecimal getMaximum(){
        return this.maximum;
    }
    public void setMaximum(BigDecimal maximum){
        this.maximum = maximum;
    }


    public BigDecimal getAverage(){
        return this.average;
    }
    public void setAverage(BigDecimal average){
        this.average = average;
    }


    public String getMethodeAnalyse(){
        return this.methodeAnalyse;
    }
    public void setMethodeAnalyse(String methodeAnalyse){
        this.methodeAnalyse = methodeAnalyse;
    }


    public String getUnite(){
        return this.unite;
    }
    public void setUnite(String unite){
        this.unite = unite;
    }


    public ElementChimiqueDto getElementChimique(){
        return this.elementChimique;
    }

    public void setElementChimique(ElementChimiqueDto elementChimique){
        this.elementChimique = elementChimique;
    }
    public CharteChimiqueDto getCharteChimique(){
        return this.charteChimique;
    }

    public void setCharteChimique(CharteChimiqueDto charteChimique){
        this.charteChimique = charteChimique;
    }






}
