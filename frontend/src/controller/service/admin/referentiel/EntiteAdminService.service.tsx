import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {EntiteDto} from '../../../model/referentiel/Entite.model';
import {EntiteCriteria} from '../../../criteria/referentiel/EntiteCriteria.model';

export class EntiteAdminService extends AbstractService<EntiteDto, EntiteCriteria>{

    constructor() {
        super(ADMIN_URL , 'entite/');
    }

};
