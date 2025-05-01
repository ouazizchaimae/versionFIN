import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {ProduitMarchandDto} from '../referentiel/ProduitMarchand.model';
import {ClientDto} from '../referentiel/Client.model';

export class CharteChimiqueDto extends BaseDto{

    public code: string;

    public libelle: string;

    public description: string;

    public client: ClientDto ;
    public produitMarchand: ProduitMarchandDto ;
     public charteChimiqueDetails: Array<CharteChimiqueDetailDto>;


    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a charteChimique';
        this.description = '';
        this.client = new ClientDto() ;
        this.produitMarchand = new ProduitMarchandDto() ;
        this.charteChimiqueDetails = new Array<CharteChimiqueDetailDto>();
        }

}
