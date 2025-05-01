import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {ClientDto} from '../../../model/referentiel/Client.model';
import {ClientCriteria} from '../../../criteria/referentiel/ClientCriteria.model';

export class ClientAdminService extends AbstractService<ClientDto, ClientCriteria>{

    constructor() {
        super(ADMIN_URL , 'client/');
    }

};
