import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {ElementChimiqueDto} from '../referentiel/ElementChimique.model';
import {AnalyseChimiqueDto} from '../expedition/AnalyseChimique.model';

export class AnalyseChimiqueDetailDto extends BaseDto{

    public libelle: string;

    public description: string;

    public valeur: null | number;

   public conformite: boolean;

   public surqualite: boolean;

    public elementChimique: ElementChimiqueDto ;
    public analyseChimique: AnalyseChimiqueDto ;


    constructor() {
        super();
        this.libelle = 'select a analyseChimiqueDetail';
        this.description = '';
        this.valeur = null;
        this.conformite = null;
        this.surqualite = null;
        this.elementChimique = new ElementChimiqueDto() ;
        this.analyseChimique = new AnalyseChimiqueDto() ;
        }

}
