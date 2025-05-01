import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {StadeOperatoireDto} from '../referentiel/StadeOperatoire.model';
import {ProduitDto} from '../referentiel/Produit.model';

export class StadeOperatoireProduitDto extends BaseDto{

    public stadeOperatoire: StadeOperatoireDto ;
    public produit: ProduitDto ;


    constructor() {
        super();
        this.stadeOperatoire = new StadeOperatoireDto() ;
        this.produit = new ProduitDto() ;
        }

}
