package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.ElementChimiqueCriteria;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ElementChimiqueSpecification extends  AbstractSpecification<ElementChimiqueCriteria, ElementChimique>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicateFk("unite","id", criteria.getUnite()==null?null:criteria.getUnite().getId());
        addPredicateFk("unite","id", criteria.getUnites());
        addPredicateFk("unite","code", criteria.getUnite()==null?null:criteria.getUnite().getCode());
    }

    public ElementChimiqueSpecification(ElementChimiqueCriteria criteria) {
        super(criteria);
    }

    public ElementChimiqueSpecification(ElementChimiqueCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
