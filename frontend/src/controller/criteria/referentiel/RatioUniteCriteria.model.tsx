import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {EntiteCriteria} from '../referentiel/EntiteCriteria.model';
import {ProduitCriteria} from '../referentiel/ProduitCriteria.model';



export class RatioUniteCriteria  extends  BaseCriteria {

    public id: number;

     public ratio: null | number;
     public ratioMin: null | number;
     public ratioMax: null | number;
    public entite: EntiteCriteria ;
    public entites: Array<EntiteCriteria> ;
    public produit: ProduitCriteria ;
    public produits: Array<ProduitCriteria> ;


    constructor() {
        super();
        this.ratio = null;
        this.ratioMin = null;
        this.ratioMax = null;
        this.entite = new EntiteCriteria() ;
        this.entites = new Array<EntiteCriteria>() ;
        this.produit = new ProduitCriteria() ;
        this.produits = new Array<ProduitCriteria>() ;
    }

}
