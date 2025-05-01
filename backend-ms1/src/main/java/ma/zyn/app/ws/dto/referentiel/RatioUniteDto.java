package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatioUniteDto  extends AuditBaseDto {

    private BigDecimal ratio  ;

    private EntiteDto entite ;
    private ProduitDto produit ;



    public RatioUniteDto(){
        super();
    }




    public BigDecimal getRatio(){
        return this.ratio;
    }
    public void setRatio(BigDecimal ratio){
        this.ratio = ratio;
    }


    public EntiteDto getEntite(){
        return this.entite;
    }

    public void setEntite(EntiteDto entite){
        this.entite = entite;
    }
    public ProduitDto getProduit(){
        return this.produit;
    }

    public void setProduit(ProduitDto produit){
        this.produit = produit;
    }






}
