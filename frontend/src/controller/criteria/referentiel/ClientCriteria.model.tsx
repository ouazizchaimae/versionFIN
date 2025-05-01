import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {TypeClientCriteria} from '../referentiel/TypeClientCriteria.model';



export class ClientCriteria  extends  BaseCriteria {

    public id: number;

    public libelle: string;
    public libelleLike: string;
    public code: string;
    public codeLike: string;
    public description: string;
    public descriptionLike: string;
    public typeClient: TypeClientCriteria ;
    public typeClients: Array<TypeClientCriteria> ;


    constructor() {
        super();
        this.libelle = '';
        this.libelleLike = '';
        this.code = '';
        this.codeLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.typeClient = new TypeClientCriteria() ;
        this.typeClients = new Array<TypeClientCriteria>() ;
    }

}
