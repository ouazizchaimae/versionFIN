import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {ElementChimiqueDto} from '../../../model/referentiel/ElementChimique.model';
import {ElementChimiqueCriteria} from '../../../criteria/referentiel/ElementChimiqueCriteria.model';

export class ElementChimiqueAdminService extends AbstractService<ElementChimiqueDto, ElementChimiqueCriteria>{

    constructor() {
        super(ADMIN_URL , 'elementChimique/');
    }

};
