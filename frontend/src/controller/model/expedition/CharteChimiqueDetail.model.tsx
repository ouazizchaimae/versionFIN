import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {ElementChimiqueDto} from '../referentiel/ElementChimique.model';
import {CharteChimiqueDto} from '../expedition/CharteChimique.model';

export class CharteChimiqueDetailDto extends BaseDto{

    public libelle: string;

    public description: string;

    public minimum: null | number;

    public maximum: null | number;

    public average: null | number;

    public methodeAnalyse: string;

    public unite: string;

    public elementChimique: ElementChimiqueDto ;
    public charteChimique: CharteChimiqueDto ;


    constructor() {
        super();
        this.libelle = 'select a charteChimiqueDetail';
        this.description = '';
        this.minimum = null;
        this.maximum = null;
        this.average = null;
        this.methodeAnalyse = '';
        this.unite = '';
        this.elementChimique = new ElementChimiqueDto() ;
        this.charteChimique = new CharteChimiqueDto() ;
        }

}
