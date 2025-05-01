package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.UniteCriteria;
import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class UniteSpecification extends  AbstractSpecification<UniteCriteria, Unite>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public UniteSpecification(UniteCriteria criteria) {
        super(criteria);
    }

    public UniteSpecification(UniteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
