package  ma.zyn.app.ws.dto.expedition;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.zyn.app.ws.dto.referentiel.ElementChimiqueDto;
import ma.zyn.app.ws.dto.referentiel.ProduitMarchandDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyseChimiqueDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;

    private ProduitMarchandDto produitMarchand ;

    private List<AnalyseChimiqueDetailDto> analyseChimiqueDetails ;


    public AnalyseChimiqueDto(){
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


    public ProduitMarchandDto getProduitMarchand(){
        return this.produitMarchand;
    }

    public void setProduitMarchand(ProduitMarchandDto produitMarchand){
        this.produitMarchand = produitMarchand;
    }



    public List<AnalyseChimiqueDetailDto> getAnalyseChimiqueDetails(){
        return this.analyseChimiqueDetails;
    }

    public void setAnalyseChimiqueDetails(List<AnalyseChimiqueDetailDto> analyseChimiqueDetails){
        this.analyseChimiqueDetails = analyseChimiqueDetails;
    }



}
