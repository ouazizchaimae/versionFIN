import {BaseCriteria} from '../../../zynerator/criteria/BaseCriteria.model';


import {EtatDemandeCriteria} from '../referentiel/EtatDemandeCriteria.model';
import {ProduitMarchandCriteria} from '../referentiel/ProduitMarchandCriteria.model';
import {TypeDemandeCriteria} from '../referentiel/TypeDemandeCriteria.model';
import {ClientCriteria} from '../referentiel/ClientCriteria.model';



export class DemandeCriteria  extends  BaseCriteria {

    public id: number;

    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
    public dateDemande: Date;
    public dateDemandeFrom: Date;
    public dateDemandeTo: Date;
    public dateExpedition: Date;
    public dateExpeditionFrom: Date;
    public dateExpeditionTo: Date;
     public volume: null | number;
     public volumeMin: null | number;
     public volumeMax: null | number;
    public actionEntreprise: string;
    public actionEntrepriseLike: string;
    public trg: string;
    public trgLike: string;
    public cause: string;
    public causeLike: string;
    public commentaire: string;
    public commentaireLike: string;
    public produitMarchand: ProduitMarchandCriteria ;
    public produitMarchands: Array<ProduitMarchandCriteria> ;
    public client: ClientCriteria ;
    public clients: Array<ClientCriteria> ;
    public typeDemande: TypeDemandeCriteria ;
    public typeDemandes: Array<TypeDemandeCriteria> ;
    public etatDemande: EtatDemandeCriteria ;
    public etatDemandes: Array<EtatDemandeCriteria> ;


    constructor() {
        super();
        this.code = '';
        this.codeLike = '';
        this.libelle = '';
        this.libelleLike = '';
        this.description = '';
        this.descriptionLike = '';
        this.dateDemande = null;
        this.dateDemandeFrom  = null;
        this.dateDemandeTo = null;
        this.dateExpedition = null;
        this.dateExpeditionFrom  = null;
        this.dateExpeditionTo = null;
        this.volume = null;
        this.volumeMin = null;
        this.volumeMax = null;
        this.actionEntreprise = '';
        this.actionEntrepriseLike = '';
        this.trg = '';
        this.trgLike = '';
        this.cause = '';
        this.causeLike = '';
        this.commentaire = '';
        this.commentaireLike = '';
        this.produitMarchand = new ProduitMarchandCriteria() ;
        this.produitMarchands = new Array<ProduitMarchandCriteria>() ;
        this.client = new ClientCriteria() ;
        this.clients = new Array<ClientCriteria>() ;
        this.typeDemande = new TypeDemandeCriteria() ;
        this.typeDemandes = new Array<TypeDemandeCriteria>() ;
        this.etatDemande = new EtatDemandeCriteria() ;
        this.etatDemandes = new Array<EtatDemandeCriteria>() ;
    }

}
