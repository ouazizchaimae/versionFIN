import {BaseDto} from "../../../zynerator/dto/BaseDto.model";


export class UniteDto extends BaseDto{

    public code: string;

    public libelle: string;

    public description: string;

    public style: string;



    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a unite';
        this.description = '';
        this.style = '';
        }

}
