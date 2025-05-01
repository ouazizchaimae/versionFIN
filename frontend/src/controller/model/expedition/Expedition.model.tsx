import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {TypeExpeditionDto} from '../expedition/TypeExpedition.model';
import {ClientDto} from '../referentiel/Client.model';

export class ExpeditionDto extends BaseDto{

    public code: string;

    public libelle: string;

    public description: string;

    public client: ClientDto ;
    public typeExpedition: TypeExpeditionDto ;
     public expeditionProduits: Array<ExpeditionProduitDto>;


    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a expedition';
        this.description = '';
        this.client = new ClientDto() ;
        this.typeExpedition = new TypeExpeditionDto() ;
        this.expeditionProduits = new Array<ExpeditionProduitDto>();
        }

}
