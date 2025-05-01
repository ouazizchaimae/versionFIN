package  ma.zyn.app.ws.dto.expedition;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.zyn.app.ws.dto.referentiel.ElementChimiqueDto;
import ma.zyn.app.ws.dto.referentiel.ProduitMarchandDto;
import ma.zyn.app.ws.dto.referentiel.ClientDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharteChimiqueDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;

    private ClientDto client ;
    private ProduitMarchandDto produitMarchand ;

    private List<CharteChimiqueDetailDto> charteChimiqueDetails ;


    public CharteChimiqueDto(){
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
    public ProduitMarchandDto getProduitMarchand(){
        return this.produitMarchand;
    }

    public void setProduitMarchand(ProduitMarchandDto produitMarchand){
        this.produitMarchand = produitMarchand;
    }



    public List<CharteChimiqueDetailDto> getCharteChimiqueDetails(){
        return this.charteChimiqueDetails;
    }

    public void setCharteChimiqueDetails(List<CharteChimiqueDetailDto> charteChimiqueDetails){
        this.charteChimiqueDetails = charteChimiqueDetails;
    }



}
