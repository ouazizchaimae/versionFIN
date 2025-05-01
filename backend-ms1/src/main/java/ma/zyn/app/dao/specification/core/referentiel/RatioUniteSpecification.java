package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.RatioUniteCriteria;
import ma.zyn.app.bean.core.referentiel.RatioUnite;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class RatioUniteSpecification extends  AbstractSpecification<RatioUniteCriteria, RatioUnite>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("ratio", criteria.getRatio(), criteria.getRatioMin(), criteria.getRatioMax());
        addPredicateFk("entite","id", criteria.getEntite()==null?null:criteria.getEntite().getId());
        addPredicateFk("entite","id", criteria.getEntites());
        addPredicateFk("entite","code", criteria.getEntite()==null?null:criteria.getEntite().getCode());
        addPredicateFk("produit","id", criteria.getProduit()==null?null:criteria.getProduit().getId());
        addPredicateFk("produit","id", criteria.getProduits());
        addPredicateFk("produit","code", criteria.getProduit()==null?null:criteria.getProduit().getCode());
    }

    public RatioUniteSpecification(RatioUniteCriteria criteria) {
        super(criteria);
    }

    public RatioUniteSpecification(RatioUniteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
