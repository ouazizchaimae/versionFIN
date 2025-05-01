import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {UniteDto} from '../../../model/referentiel/Unite.model';
import {UniteCriteria} from '../../../criteria/referentiel/UniteCriteria.model';

export class UniteAdminService extends AbstractService<UniteDto, UniteCriteria>{

    constructor() {
        super(ADMIN_URL , 'unite/');
    }

};
