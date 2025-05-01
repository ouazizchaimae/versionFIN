import {BaseDto} from "../../../zynerator/dto/BaseDto.model";


export class ProduitDto extends BaseDto{

    public code: string;

    public libelle: string;

    public description: string;



    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a produit';
        this.description = '';
        }

}
