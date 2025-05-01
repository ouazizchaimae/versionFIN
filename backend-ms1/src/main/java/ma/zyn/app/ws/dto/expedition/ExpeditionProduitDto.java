package  ma.zyn.app.ws.dto.expedition;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpeditionProduitDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;

    private AnalyseChimiqueDto analyseChimique ;
    private CharteChimiqueDto charteChimique ;



    public ExpeditionProduitDto(){
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


    public AnalyseChimiqueDto getAnalyseChimique(){
        return this.analyseChimique;
    }

    public void setAnalyseChimique(AnalyseChimiqueDto analyseChimique){
        this.analyseChimique = analyseChimique;
    }
    public CharteChimiqueDto getCharteChimique(){
        return this.charteChimique;
    }

    public void setCharteChimique(CharteChimiqueDto charteChimique){
        this.charteChimique = charteChimique;
    }






}
