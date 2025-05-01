import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {UniteDto} from '../referentiel/Unite.model';

export class ElementChimiqueDto extends BaseDto{

    public code: string;

    public libelle: string;

    public style: string;

    public description: string;

    public unite: UniteDto ;


    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a elementChimique';
        this.style = '';
        this.description = '';
        this.unite = new UniteDto() ;
        }

}
