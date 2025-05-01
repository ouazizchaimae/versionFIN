import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {ProduitMarchandDto} from '../referentiel/ProduitMarchand.model';

export class AnalyseChimiqueDto extends BaseDto{

    public code: string;

    public libelle: string;

    public description: string;

    public produitMarchand: ProduitMarchandDto ;
     public analyseChimiqueDetails: Array<AnalyseChimiqueDetailDto>;


    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a analyseChimique';
        this.description = '';
        this.produitMarchand = new ProduitMarchandDto() ;
        this.analyseChimiqueDetails = new Array<AnalyseChimiqueDetailDto>();
        }

}
