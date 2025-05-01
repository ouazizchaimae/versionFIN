import {BaseDto} from "../../../zynerator/dto/BaseDto.model";


export class EtatDemandeDto extends BaseDto{

    public libelle: string;

    public code: string;

    public style: string;

    public description: string;



    constructor() {
        super();
        this.libelle = 'select a etatDemande';
        this.code = '';
        this.style = '';
        this.description = '';
        }

}
