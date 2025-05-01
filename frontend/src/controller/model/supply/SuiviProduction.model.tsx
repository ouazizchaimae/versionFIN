import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {UniteDto} from '../referentiel/Unite.model';
import {StadeOperatoireDto} from '../referentiel/StadeOperatoire.model';
import {ProduitDto} from '../referentiel/Produit.model';

export class SuiviProductionDto extends BaseDto{

    public code: string;

    public libelle: string;

    public description: string;

   public jour: Date;

    public volume: null | number;

    public tsm: null | number;

    public produit: ProduitDto ;
    public stadeOperatoire: StadeOperatoireDto ;
    public unite: UniteDto ;


    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a suiviProduction';
        this.description = '';
        this.jour = null;
        this.volume = null;
        this.tsm = null;
        this.produit = new ProduitDto() ;
        this.stadeOperatoire = new StadeOperatoireDto() ;
        this.unite = new UniteDto() ;
        }

}
