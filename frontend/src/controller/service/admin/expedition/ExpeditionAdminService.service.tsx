import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {ExpeditionDto} from '../../../model/expedition/Expedition.model';
import {ExpeditionCriteria} from '../../../criteria/expedition/ExpeditionCriteria.model';

export class ExpeditionAdminService extends AbstractService<ExpeditionDto, ExpeditionCriteria>{

    constructor() {
        super(ADMIN_URL , 'expedition/');
    }

};
