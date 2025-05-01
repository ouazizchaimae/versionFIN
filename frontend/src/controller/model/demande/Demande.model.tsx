import {BaseDto} from "../../../zynerator/dto/BaseDto.model";

import {EtatDemandeDto} from '../referentiel/EtatDemande.model';
import {ProduitMarchandDto} from '../referentiel/ProduitMarchand.model';
import {TypeDemandeDto} from '../referentiel/TypeDemande.model';
import {ClientDto} from '../referentiel/Client.model';

export class DemandeDto extends BaseDto{

    public code: string;

    public libelle: string;

    public description: string;

   public dateDemande: Date;

   public dateExpedition: Date;

    public volume: null | number;

    public actionEntreprise: string;

    public trg: string;

    public cause: string;

    public commentaire: string;

    public produitMarchand: ProduitMarchandDto ;
    public client: ClientDto ;
    public typeDemande: TypeDemandeDto ;
    public etatDemande: EtatDemandeDto ;


    constructor() {
        super();
        this.code = '';
        this.libelle = 'select a demande';
        this.description = '';
        this.dateDemande = null;
        this.dateExpedition = null;
        this.volume = null;
        this.actionEntreprise = '';
        this.trg = '';
        this.cause = '';
        this.commentaire = '';
        this.produitMarchand = new ProduitMarchandDto() ;
        this.client = new ClientDto() ;
        this.typeDemande = new TypeDemandeDto() ;
        this.etatDemande = new EtatDemandeDto() ;
        }

}
