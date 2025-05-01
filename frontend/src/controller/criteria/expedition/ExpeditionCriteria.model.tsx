import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {TypeExpeditionCriteria} from '../expedition/TypeExpeditionCriteria.model';
import {ClientCriteria} from '../referentiel/ClientCriteria.model';



export class ExpeditionCriteria  extends  BaseCriteria {

    public id: number;

    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
    public client: ClientCriteria ;
    public clients: Array<ClientCriteria> ;
    public typeExpedition: TypeExpeditionCriteria ;
    public typeExpeditions: Array<TypeExpeditionCriteria> ;


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
        this.typeExpedition = new TypeExpeditionCriteria() ;
        this.typeExpeditions = new Array<TypeExpeditionCriteria>() ;
    }

}
