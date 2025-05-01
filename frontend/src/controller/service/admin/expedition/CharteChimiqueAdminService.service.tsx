import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {CharteChimiqueDto} from '../../../model/expedition/CharteChimique.model';
import {CharteChimiqueCriteria} from '../../../criteria/expedition/CharteChimiqueCriteria.model';

export class CharteChimiqueAdminService extends AbstractService<CharteChimiqueDto, CharteChimiqueCriteria>{

    constructor() {
        super(ADMIN_URL , 'charteChimique/');
    }

};
