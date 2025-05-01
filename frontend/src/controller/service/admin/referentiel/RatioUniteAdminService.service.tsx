import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {RatioUniteDto} from '../../../model/referentiel/RatioUnite.model';
import {RatioUniteCriteria} from '../../../criteria/referentiel/RatioUniteCriteria.model';

export class RatioUniteAdminService extends AbstractService<RatioUniteDto, RatioUniteCriteria>{

    constructor() {
        super(ADMIN_URL , 'ratioUnite/');
    }

};
