import {BaseDto} from "../../../zynerator/dto/BaseDto.model";


export class TypeExpeditionDto extends BaseDto{

    public libelle: string;

    public code: string;

    public style: string;

    public description: string;



    constructor() {
        super();
        this.libelle = 'select a typeExpedition';
        this.code = '';
        this.style = '';
        this.description = '';
        }

}
