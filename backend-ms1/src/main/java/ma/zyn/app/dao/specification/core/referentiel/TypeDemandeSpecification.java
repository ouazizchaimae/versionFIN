package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.TypeDemandeCriteria;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class TypeDemandeSpecification extends  AbstractSpecification<TypeDemandeCriteria, TypeDemande>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public TypeDemandeSpecification(TypeDemandeCriteria criteria) {
        super(criteria);
    }

    public TypeDemandeSpecification(TypeDemandeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
