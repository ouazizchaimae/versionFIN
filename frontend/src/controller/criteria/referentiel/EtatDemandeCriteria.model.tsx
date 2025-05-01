import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';





export class EtatDemandeCriteria  extends  BaseCriteria {

    public id: number;

    public libelle: string;
    public libelleLike: string;
    public code: string;
    public codeLike: string;
    public style: string;
    public styleLike: string;
    public description: string;
    public descriptionLike: string;


    constructor() {
        super();
        this.libelle = '';
        this.libelleLike = '';
        this.code = '';
        this.codeLike = '';
        this.style = '';
        this.styleLike = '';
        this.description = '';
        this.descriptionLike = '';
    }

}
