package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.TypeClientCriteria;
import ma.zyn.app.bean.core.referentiel.TypeClient;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TypeClientSpecification extends  AbstractSpecification<TypeClientCriteria, TypeClient>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public TypeClientSpecification(TypeClientCriteria criteria) {
        super(criteria);
    }

    public TypeClientSpecification(TypeClientCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
