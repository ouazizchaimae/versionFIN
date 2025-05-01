import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {TypeClientDto} from '../../../model/referentiel/TypeClient.model';
import {TypeClientCriteria} from '../../../criteria/referentiel/TypeClientCriteria.model';

export class TypeClientAdminService extends AbstractService<TypeClientDto, TypeClientCriteria>{

    constructor() {
        super(ADMIN_URL , 'typeClient/');
    }

};
