import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {DemandeDto} from '../../../model/demande/Demande.model';
import {DemandeCriteria} from '../../../criteria/demande/DemandeCriteria.model';

export class DemandeAdminService extends AbstractService<DemandeDto, DemandeCriteria>{

    constructor() {
        super(ADMIN_URL , 'demande/');
    }

};
