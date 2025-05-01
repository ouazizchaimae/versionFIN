import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {TypeExpeditionDto} from '../../../model/expedition/TypeExpedition.model';
import {TypeExpeditionCriteria} from '../../../criteria/expedition/TypeExpeditionCriteria.model';

export class TypeExpeditionAdminService extends AbstractService<TypeExpeditionDto, TypeExpeditionCriteria>{

    constructor() {
        super(ADMIN_URL , 'typeExpedition/');
    }

};
