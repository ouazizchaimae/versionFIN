import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {ElementChimiqueCriteria} from '../referentiel/ElementChimiqueCriteria.model';
import {AnalyseChimiqueCriteria} from '../expedition/AnalyseChimiqueCriteria.model';



export class AnalyseChimiqueDetailCriteria  extends  BaseCriteria {

    public id: number;

    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
     public valeur: null | number;
     public valeurMin: null | number;
     public valeurMax: null | number;
    public conformite: null | boolean;
    public surqualite: null | boolean;
    public elementChimique: ElementChimiqueCriteria ;
    public elementChimiques: Array<ElementChimiqueCriteria> ;
    public analyseChimique: AnalyseChimiqueCriteria ;
    public analyseChimiques: Array<AnalyseChimiqueCriteria> ;


    constructor() {
        super();
        this.libelle = '';
        this.libelleLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.valeur = null;
        this.valeurMin = null;
        this.valeurMax = null;
        this.conformite = null;
        this.surqualite = null;
        this.elementChimique = new ElementChimiqueCriteria() ;
        this.elementChimiques = new Array<ElementChimiqueCriteria>() ;
        this.analyseChimique = new AnalyseChimiqueCriteria() ;
        this.analyseChimiques = new Array<AnalyseChimiqueCriteria>() ;
    }

}
