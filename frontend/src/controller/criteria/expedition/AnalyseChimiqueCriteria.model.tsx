import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {ProduitMarchandCriteria} from '../referentiel/ProduitMarchandCriteria.model';



export class AnalyseChimiqueCriteria  extends  BaseCriteria {

    public id: number;

    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
    public produitMarchand: ProduitMarchandCriteria ;
    public produitMarchands: Array<ProduitMarchandCriteria> ;


    constructor() {
        super();
        this.code = '';
        this.codeLike = '';
        this.libelle = '';
        this.libelleLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.produitMarchand = new ProduitMarchandCriteria() ;
        this.produitMarchands = new Array<ProduitMarchandCriteria>() ;
    }

}
