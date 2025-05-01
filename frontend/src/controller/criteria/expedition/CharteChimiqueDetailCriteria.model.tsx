import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {ElementChimiqueCriteria} from '../referentiel/ElementChimiqueCriteria.model';
import {CharteChimiqueCriteria} from '../expedition/CharteChimiqueCriteria.model';



export class CharteChimiqueDetailCriteria  extends  BaseCriteria {

    public id: number;

    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
     public minimum: null | number;
     public minimumMin: null | number;
     public minimumMax: null | number;
     public maximum: null | number;
     public maximumMin: null | number;
     public maximumMax: null | number;
     public average: null | number;
     public averageMin: null | number;
     public averageMax: null | number;
    public methodeAnalyse: string;
    public methodeAnalyseLike: string;
    public unite: string;
    public uniteLike: string;
    public elementChimique: ElementChimiqueCriteria ;
    public elementChimiques: Array<ElementChimiqueCriteria> ;
    public charteChimique: CharteChimiqueCriteria ;
    public charteChimiques: Array<CharteChimiqueCriteria> ;


    constructor() {
        super();
        this.libelle = '';
        this.libelleLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.minimum = null;
        this.minimumMin = null;
        this.minimumMax = null;
        this.maximum = null;
        this.maximumMin = null;
        this.maximumMax = null;
        this.average = null;
        this.averageMin = null;
        this.averageMax = null;
        this.methodeAnalyse = '';
        this.methodeAnalyseLike = '';
        this.unite = '';
        this.uniteLike = '';
        this.elementChimique = new ElementChimiqueCriteria() ;
        this.elementChimiques = new Array<ElementChimiqueCriteria>() ;
        this.charteChimique = new CharteChimiqueCriteria() ;
        this.charteChimiques = new Array<CharteChimiqueCriteria>() ;
    }

}
