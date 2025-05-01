import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {AnalyseChimiqueCriteria} from '../expedition/AnalyseChimiqueCriteria.model';
import {CharteChimiqueCriteria} from '../expedition/CharteChimiqueCriteria.model';



export class ExpeditionProduitCriteria  extends  BaseCriteria {

    public id: number;

    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
    public analyseChimique: AnalyseChimiqueCriteria ;
    public analyseChimiques: Array<AnalyseChimiqueCriteria> ;
    public charteChimique: CharteChimiqueCriteria ;
    public charteChimiques: Array<CharteChimiqueCriteria> ;


    constructor() {
        super();
        this.code = '';
        this.codeLike = '';
        this.libelle = '';
        this.libelleLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.analyseChimique = new AnalyseChimiqueCriteria() ;
        this.analyseChimiques = new Array<AnalyseChimiqueCriteria>() ;
        this.charteChimique = new CharteChimiqueCriteria() ;
        this.charteChimiques = new Array<CharteChimiqueCriteria>() ;
    }

}
