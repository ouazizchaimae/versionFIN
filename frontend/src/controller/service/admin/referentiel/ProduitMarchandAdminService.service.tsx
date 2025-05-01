import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {ProduitMarchandDto} from '../../../model/referentiel/ProduitMarchand.model';
import {ProduitMarchandCriteria} from '../../../criteria/referentiel/ProduitMarchandCriteria.model';

export class ProduitMarchandAdminService extends AbstractService<ProduitMarchandDto, ProduitMarchandCriteria>{

    constructor() {
        super(ADMIN_URL , 'produitMarchand/');
    }

};
