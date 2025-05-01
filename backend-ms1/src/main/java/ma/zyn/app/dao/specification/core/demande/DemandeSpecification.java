package  ma.zyn.app.dao.specification.core.demande;

import ma.zyn.app.dao.criteria.core.demande.DemandeCriteria;
import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class DemandeSpecification extends  AbstractSpecification<DemandeCriteria, Demande>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("dateDemande", criteria.getDateDemande(), criteria.getDateDemandeFrom(), criteria.getDateDemandeTo());
        addPredicate("dateExpedition", criteria.getDateExpedition(), criteria.getDateExpeditionFrom(), criteria.getDateExpeditionTo());
        addPredicateBigDecimal("volume", criteria.getVolume(), criteria.getVolumeMin(), criteria.getVolumeMax());
        addPredicate("actionEntreprise", criteria.getActionEntreprise(),criteria.getActionEntrepriseLike());
        addPredicate("trg", criteria.getTrg(),criteria.getTrgLike());
        addPredicate("cause", criteria.getCause(),criteria.getCauseLike());
        addPredicateFk("produitMarchand","id", criteria.getProduitMarchand()==null?null:criteria.getProduitMarchand().getId());
        addPredicateFk("produitMarchand","id", criteria.getProduitMarchands());
        addPredicateFk("produitMarchand","code", criteria.getProduitMarchand()==null?null:criteria.getProduitMarchand().getCode());
        addPredicateFk("client","id", criteria.getClient()==null?null:criteria.getClient().getId());
        addPredicateFk("client","id", criteria.getClients());
        addPredicateFk("client","code", criteria.getClient()==null?null:criteria.getClient().getCode());
        addPredicateFk("typeDemande","id", criteria.getTypeDemande()==null?null:criteria.getTypeDemande().getId());
        addPredicateFk("typeDemande","id", criteria.getTypeDemandes());
        addPredicateFk("typeDemande","code", criteria.getTypeDemande()==null?null:criteria.getTypeDemande().getCode());
        addPredicateFk("etatDemande","id", criteria.getEtatDemande()==null?null:criteria.getEtatDemande().getId());
        addPredicateFk("etatDemande","id", criteria.getEtatDemandes());
        addPredicateFk("etatDemande","code", criteria.getEtatDemande()==null?null:criteria.getEtatDemande().getCode());
    }

    public DemandeSpecification(DemandeCriteria criteria) {
        super(criteria);
    }

    public DemandeSpecification(DemandeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
