import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {AnalyseChimiqueDetailDto} from '../../../model/expedition/AnalyseChimiqueDetail.model';
import {AnalyseChimiqueDetailCriteria} from '../../../criteria/expedition/AnalyseChimiqueDetailCriteria.model';

export class AnalyseChimiqueDetailAdminService extends AbstractService<AnalyseChimiqueDetailDto, AnalyseChimiqueDetailCriteria>{

    constructor() {
        super(ADMIN_URL , 'analyseChimiqueDetail/');
    }

};
