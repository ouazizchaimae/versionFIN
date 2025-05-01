import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {CharteChimiqueDetailDto} from '../../../model/expedition/CharteChimiqueDetail.model';
import {CharteChimiqueDetailCriteria} from '../../../criteria/expedition/CharteChimiqueDetailCriteria.model';

export class CharteChimiqueDetailAdminService extends AbstractService<CharteChimiqueDetailDto, CharteChimiqueDetailCriteria>{

    constructor() {
        super(ADMIN_URL , 'charteChimiqueDetail/');
    }

};
