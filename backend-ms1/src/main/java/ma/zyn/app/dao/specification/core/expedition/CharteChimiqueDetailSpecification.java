package  ma.zyn.app.dao.specification.core.expedition;

import ma.zyn.app.dao.criteria.core.expedition.CharteChimiqueDetailCriteria;
import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CharteChimiqueDetailSpecification extends  AbstractSpecification<CharteChimiqueDetailCriteria, CharteChimiqueDetail>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateBigDecimal("minimum", criteria.getMinimum(), criteria.getMinimumMin(), criteria.getMinimumMax());
        addPredicateBigDecimal("maximum", criteria.getMaximum(), criteria.getMaximumMin(), criteria.getMaximumMax());
        addPredicateBigDecimal("average", criteria.getAverage(), criteria.getAverageMin(), criteria.getAverageMax());
        addPredicate("methodeAnalyse", criteria.getMethodeAnalyse(),criteria.getMethodeAnalyseLike());
        addPredicate("unite", criteria.getUnite(),criteria.getUniteLike());
        addPredicateFk("elementChimique","id", criteria.getElementChimique()==null?null:criteria.getElementChimique().getId());
        addPredicateFk("elementChimique","id", criteria.getElementChimiques());
        addPredicateFk("elementChimique","code", criteria.getElementChimique()==null?null:criteria.getElementChimique().getCode());
        addPredicateFk("charteChimique","id", criteria.getCharteChimique()==null?null:criteria.getCharteChimique().getId());
        addPredicateFk("charteChimique","id", criteria.getCharteChimiques());
        addPredicateFk("charteChimique","code", criteria.getCharteChimique()==null?null:criteria.getCharteChimique().getCode());
    }

    public CharteChimiqueDetailSpecification(CharteChimiqueDetailCriteria criteria) {
        super(criteria);
    }

    public CharteChimiqueDetailSpecification(CharteChimiqueDetailCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
