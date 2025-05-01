import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {EtatDemandeDto} from '../../../model/referentiel/EtatDemande.model';
import {EtatDemandeCriteria} from '../../../criteria/referentiel/EtatDemandeCriteria.model';

export class EtatDemandeAdminService extends AbstractService<EtatDemandeDto, EtatDemandeCriteria>{

    constructor() {
        super(ADMIN_URL , 'etatDemande/');
    }

};
