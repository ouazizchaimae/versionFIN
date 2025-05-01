import {BaseDto} from "../../../zynerator/dto/BaseDto.model";


export class TypeClientDto extends BaseDto{

    public libelle: string;

    public code: string;

    public style: string;

    public description: string;



    constructor() {
        super();
        this.libelle = 'select a typeClient';
        this.code = '';
        this.style = '';
        this.description = '';
        }

}
