import {BaseDto} from "../../../zynerator/dto/BaseDto.model";


export class ProduitMarchandDto extends BaseDto{

    public code: string;

    public libelle: string;

    public style: string;

    public description: string;



    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a produitMarchand';
        this.style = '';
        this.description = '';
        }

}
