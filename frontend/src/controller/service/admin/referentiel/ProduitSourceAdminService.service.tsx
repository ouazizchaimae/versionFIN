import { ADMIN_URL } from '../../../../../config/AppConfig';
import AbstractService from "../../../../zynerator/service/AbstractService";

import {ProduitSourceDto} from '../../../model/referentiel/ProduitSource.model';
import {ProduitSourceCriteria} from '../../../criteria/referentiel/ProduitSourceCriteria.model';

export class ProduitSourceAdminService extends AbstractService<ProduitSourceDto, ProduitSourceCriteria>{

    constructor() {
        super(ADMIN_URL , 'produitSource/');
    }

};
