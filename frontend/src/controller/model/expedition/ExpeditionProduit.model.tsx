import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {AnalyseChimiqueDto} from '../expedition/AnalyseChimique.model';
import {CharteChimiqueDto} from '../expedition/CharteChimique.model';

export class ExpeditionProduitDto extends BaseDto{

    public code: string;

    public libelle: string;

    public description: string;

    public analyseChimique: AnalyseChimiqueDto ;
    public charteChimique: CharteChimiqueDto ;


    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a expeditionProduit';
        this.description = '';
        this.analyseChimique = new AnalyseChimiqueDto() ;
        this.charteChimique = new CharteChimiqueDto() ;
        }

}
