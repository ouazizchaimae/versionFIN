import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';





export class UniteCriteria  extends  BaseCriteria {

    public id: number;

    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
    public style: string;
    public styleLike: string;


    constructor() {
        super();
        this.code = '';
        this.codeLike = '';
        this.libelle = '';
        this.libelleLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.style = '';
        this.styleLike = '';
    }

}
