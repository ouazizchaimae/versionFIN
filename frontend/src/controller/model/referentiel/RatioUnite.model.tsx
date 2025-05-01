import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {EntiteDto} from '../referentiel/Entite.model';
import {ProduitDto} from '../referentiel/Produit.model';

export class RatioUniteDto extends BaseDto{

    public ratio: null | number;

    public entite: EntiteDto ;
    public produit: ProduitDto ;


    constructor() {
        super();
        this.ratio = null;
        this.entite = new EntiteDto() ;
        this.produit = new ProduitDto() ;
        }

}
