import { BaseDto } from "./BaseDto.model";

export class ActivationDto extends BaseDto {

    public activationCode: string;
    public username: string;

    constructor() {
        super();
        this.activationCode = "";
        this.username = "";
    }

}


