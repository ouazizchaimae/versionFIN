package  ma.zyn.app.dao.specification.core.expedition;

import ma.zyn.app.dao.criteria.core.expedition.TypeExpeditionCriteria;
import ma.zyn.app.bean.core.expedition.TypeExpedition;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TypeExpeditionSpecification extends  AbstractSpecification<TypeExpeditionCriteria, TypeExpedition>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public TypeExpeditionSpecification(TypeExpeditionCriteria criteria) {
        super(criteria);
    }

    public TypeExpeditionSpecification(TypeExpeditionCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
