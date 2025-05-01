import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {StadeOperatoireProduitDto} from '../../../model/referentiel/StadeOperatoireProduit.model';
import {StadeOperatoireProduitCriteria} from '../../../criteria/referentiel/StadeOperatoireProduitCriteria.model';

export class StadeOperatoireProduitAdminService extends AbstractService<StadeOperatoireProduitDto, StadeOperatoireProduitCriteria>{

    constructor() {
        super(ADMIN_URL , 'stadeOperatoireProduit/');
    }

};
