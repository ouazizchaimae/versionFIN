import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {EntiteCriteria} from '../referentiel/EntiteCriteria.model';



export class StadeOperatoireCriteria  extends  BaseCriteria {

    public id: number;

    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public style: string;
    public styleLike: string;
    public description: string;
    public descriptionLike: string;
     public capaciteMin: null | number;
     public capaciteMinMin: null | number;
     public capaciteMinMax: null | number;
     public capaciteMax: null | number;
     public capaciteMaxMin: null | number;
     public capaciteMaxMax: null | number;
     public indice: null | number;
     public indiceMin: null | number;
     public indiceMax: null | number;
    public entite: EntiteCriteria ;
    public entites: Array<EntiteCriteria> ;


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
        this.capaciteMin = null;
        this.capaciteMinMin = null;
        this.capaciteMinMax = null;
        this.capaciteMax = null;
        this.capaciteMaxMin = null;
        this.capaciteMaxMax = null;
        this.indice = null;
        this.indiceMin = null;
        this.indiceMax = null;
        this.entite = new EntiteCriteria() ;
        this.entites = new Array<EntiteCriteria>() ;
    }

}
