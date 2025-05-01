import { BaseDto } from "./BaseDto.model";

export class ModelPermissionDto extends BaseDto{

    public reference: string;

    public libelle: string;
    public globalValue: null | boolean;

    constructor() {
        super();

        this.reference = '';
        this.libelle = '';
        this.globalValue = true;

        }

}