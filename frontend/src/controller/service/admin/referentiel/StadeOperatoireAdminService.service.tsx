import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {StadeOperatoireDto} from '../../../model/referentiel/StadeOperatoire.model';
import {StadeOperatoireCriteria} from '../../../criteria/referentiel/StadeOperatoireCriteria.model';

export class StadeOperatoireAdminService extends AbstractService<StadeOperatoireDto, StadeOperatoireCriteria>{

    constructor() {
        super(ADMIN_URL , 'stadeOperatoire/');
    }

};
