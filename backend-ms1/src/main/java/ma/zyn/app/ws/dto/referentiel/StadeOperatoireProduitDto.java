package  ma.zyn.app.ws.dto.referentiel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class StadeOperatoireProduitDto  extends AuditBaseDto {


    private StadeOperatoireDto stadeOperatoire ;
    private ProduitDto produit ;



    public StadeOperatoireProduitDto(){
        super();
    }




    public StadeOperatoireDto getStadeOperatoire(){
        return this.stadeOperatoire;
    }

    public void setStadeOperatoire(StadeOperatoireDto stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public ProduitDto getProduit(){
        return this.produit;
    }

    public void setProduit(ProduitDto produit){
        this.produit = produit;
    }






}
