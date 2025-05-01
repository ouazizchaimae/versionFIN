import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {TypeDemandeDto} from '../../../model/referentiel/TypeDemande.model';
import {TypeDemandeCriteria} from '../../../criteria/referentiel/TypeDemandeCriteria.model';

export class TypeDemandeAdminService extends AbstractService<TypeDemandeDto, TypeDemandeCriteria>{

    constructor() {
        super(ADMIN_URL , 'typeDemande/');
    }

};
