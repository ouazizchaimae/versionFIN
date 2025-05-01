import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {AnalyseChimiqueDto} from '../../../model/expedition/AnalyseChimique.model';
import {AnalyseChimiqueCriteria} from '../../../criteria/expedition/AnalyseChimiqueCriteria.model';

export class AnalyseChimiqueAdminService extends AbstractService<AnalyseChimiqueDto, AnalyseChimiqueCriteria>{

    constructor() {
        super(ADMIN_URL , 'analyseChimique/');
    }

};
