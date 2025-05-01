import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {EntiteDto} from '../referentiel/Entite.model';

export class StadeOperatoireDto extends BaseDto{

    public code: string;

    public libelle: string;

    public style: string;

    public description: string;

    public capaciteMin: null | number;

    public capaciteMax: null | number;

    public indice: null | number;

    public entite: EntiteDto ;
     public stadeOperatoireProduits: Array<StadeOperatoireProduitDto>;


    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a stadeOperatoire';
        this.style = '';
        this.description = '';
        this.capaciteMin = null;
        this.capaciteMax = null;
        this.indice = null;
        this.entite = new EntiteDto() ;
        this.stadeOperatoireProduits = new Array<StadeOperatoireProduitDto>();
        }

}
