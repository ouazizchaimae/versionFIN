import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {ExpeditionProduitDto} from '../../../model/expedition/ExpeditionProduit.model';
import {ExpeditionProduitCriteria} from '../../../criteria/expedition/ExpeditionProduitCriteria.model';

export class ExpeditionProduitAdminService extends AbstractService<ExpeditionProduitDto, ExpeditionProduitCriteria>{

    constructor() {
        super(ADMIN_URL , 'expeditionProduit/');
    }

};
