import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {UniteCriteria} from '../referentiel/UniteCriteria.model';
import {StadeOperatoireCriteria} from '../referentiel/StadeOperatoireCriteria.model';
import {ProduitCriteria} from '../referentiel/ProduitCriteria.model';



export class SuiviProductionCriteria  extends  BaseCriteria {

    public id: number;

    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
    public jour: Date;
    public jourFrom: Date;
    public jourTo: Date;
     public volume: null | number;
     public volumeMin: null | number;
     public volumeMax: null | number;
     public tsm: null | number;
     public tsmMin: null | number;
     public tsmMax: null | number;
    public produit: ProduitCriteria ;
    public produits: Array<ProduitCriteria> ;
    public stadeOperatoire: StadeOperatoireCriteria ;
    public stadeOperatoires: Array<StadeOperatoireCriteria> ;
    public unite: UniteCriteria ;
    public unites: Array<UniteCriteria> ;


    constructor() {
        super();
        this.code = '';
        this.codeLike = '';
        this.libelle = '';
        this.libelleLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.jour = null;
        this.jourFrom  = null;
        this.jourTo = null;
        this.volume = null;
        this.volumeMin = null;
        this.volumeMax = null;
        this.tsm = null;
        this.tsmMin = null;
        this.tsmMax = null;
        this.produit = new ProduitCriteria() ;
        this.produits = new Array<ProduitCriteria>() ;
        this.stadeOperatoire = new StadeOperatoireCriteria() ;
        this.stadeOperatoires = new Array<StadeOperatoireCriteria>() ;
        this.unite = new UniteCriteria() ;
        this.unites = new Array<UniteCriteria>() ;
    }

}
