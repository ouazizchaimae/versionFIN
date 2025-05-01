import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {UniteCriteria} from '../referentiel/UniteCriteria.model';



export class ElementChimiqueCriteria  extends  BaseCriteria {

    public id: number;

    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public style: string;
    public styleLike: string;
    public description: string;
    public descriptionLike: string;
    public unite: UniteCriteria ;
    public unites: Array<UniteCriteria> ;


    constructor() {
        super();
        this.code = '';
        this.codeLike = '';
        this.libelle = '';
        this.libelleLike = '';
        this.style = '';
        this.styleLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.unite = new UniteCriteria() ;
        this.unites = new Array<UniteCriteria>() ;
    }

}
