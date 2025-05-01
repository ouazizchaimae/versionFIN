package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.EntiteCriteria;
import ma.zyn.app.bean.core.referentiel.Entite;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class EntiteSpecification extends  AbstractSpecification<EntiteCriteria, Entite>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public EntiteSpecification(EntiteCriteria criteria) {
        super(criteria);
    }

    public EntiteSpecification(EntiteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
