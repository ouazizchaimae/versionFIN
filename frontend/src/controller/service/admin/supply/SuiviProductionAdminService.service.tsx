import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {SuiviProductionDto} from '../../../model/supply/SuiviProduction.model';
import {SuiviProductionCriteria} from '../../../criteria/supply/SuiviProductionCriteria.model';

export class SuiviProductionAdminService extends AbstractService<SuiviProductionDto, SuiviProductionCriteria>{

    constructor() {
        super(ADMIN_URL , 'suiviProduction/');
    }

};
