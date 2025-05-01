import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {TypeClientDto} from '../referentiel/TypeClient.model';

export class ClientDto extends BaseDto{

    public libelle: string;

    public code: string;

    public description: string;

    public typeClient: TypeClientDto ;


    constructor() {
        super();
        this.libelle = 'select a client';
        this.code = '';
        this.description = '';
        this.typeClient = new TypeClientDto() ;
        }

}
