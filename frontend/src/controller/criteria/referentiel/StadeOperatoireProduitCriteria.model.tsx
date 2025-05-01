import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {StadeOperatoireCriteria} from '../referentiel/StadeOperatoireCriteria.model';
import {ProduitCriteria} from '../referentiel/ProduitCriteria.model';



export class StadeOperatoireProduitCriteria  extends  BaseCriteria {

    public id: number;

    public stadeOperatoire: StadeOperatoireCriteria ;
    public stadeOperatoires: Array<StadeOperatoireCriteria> ;
    public produit: ProduitCriteria ;
    public produits: Array<ProduitCriteria> ;


    constructor() {
        super();
        this.stadeOperatoire = new StadeOperatoireCriteria() ;
        this.stadeOperatoires = new Array<StadeOperatoireCriteria>() ;
        this.produit = new ProduitCriteria() ;
        this.produits = new Array<ProduitCriteria>() ;
    }

}
