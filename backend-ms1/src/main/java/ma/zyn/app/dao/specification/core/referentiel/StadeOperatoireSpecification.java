package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.StadeOperatoireCriteria;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class StadeOperatoireSpecification extends  AbstractSpecification<StadeOperatoireCriteria, StadeOperatoire>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicateBigDecimal("capaciteMin", criteria.getCapaciteMin(), criteria.getCapaciteMinMin(), criteria.getCapaciteMinMax());
        addPredicateBigDecimal("capaciteMax", criteria.getCapaciteMax(), criteria.getCapaciteMaxMin(), criteria.getCapaciteMaxMax());
        addPredicateInt("indice", criteria.getIndice(), criteria.getIndiceMin(), criteria.getIndiceMax());
        addPredicateFk("entite","id", criteria.getEntite()==null?null:criteria.getEntite().getId());
        addPredicateFk("entite","id", criteria.getEntites());
        addPredicateFk("entite","code", criteria.getEntite()==null?null:criteria.getEntite().getCode());
    }

    public StadeOperatoireSpecification(StadeOperatoireCriteria criteria) {
        super(criteria);
    }

    public StadeOperatoireSpecification(StadeOperatoireCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
