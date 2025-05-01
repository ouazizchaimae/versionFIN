import {BaseDto} from "../../../zynerator/dto/BaseDto.model";


export class ProduitSourceDto extends BaseDto{

    public code: string;

    public libelle: string;

    public style: string;

    public description: string;



    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a produitSource';
        this.style = '';
        this.description = '';
        }

}
