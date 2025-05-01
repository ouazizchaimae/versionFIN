package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.EtatDemandeCriteria;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class EtatDemandeSpecification extends  AbstractSpecification<EtatDemandeCriteria, EtatDemande>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public EtatDemandeSpecification(EtatDemandeCriteria criteria) {
        super(criteria);
    }

    public EtatDemandeSpecification(EtatDemandeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
