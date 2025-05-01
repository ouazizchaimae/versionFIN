import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {ProduitMarchandCriteria} from '../referentiel/ProduitMarchandCriteria.model';
import {ClientCriteria} from '../referentiel/ClientCriteria.model';



export class CharteChimiqueCriteria  extends  BaseCriteria {

    public id: number;

    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
    public client: ClientCriteria ;
    public clients: Array<ClientCriteria> ;
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
        this.client = new ClientCriteria() ;
        this.clients = new Array<ClientCriteria>() ;
        this.produitMarchand = new ProduitMarchandCriteria() ;
        this.produitMarchands = new Array<ProduitMarchandCriteria>() ;
    }

}
