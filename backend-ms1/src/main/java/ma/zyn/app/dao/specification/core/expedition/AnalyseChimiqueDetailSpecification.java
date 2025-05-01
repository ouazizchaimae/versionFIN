package  ma.zyn.app.dao.specification.core.expedition;

import ma.zyn.app.dao.criteria.core.expedition.AnalyseChimiqueDetailCriteria;
import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class AnalyseChimiqueDetailSpecification extends  AbstractSpecification<AnalyseChimiqueDetailCriteria, AnalyseChimiqueDetail>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateBigDecimal("valeur", criteria.getValeur(), criteria.getValeurMin(), criteria.getValeurMax());
        addPredicateBool("conformite", criteria.getConformite());
        addPredicateBool("surqualite", criteria.getSurqualite());
        addPredicateFk("elementChimique","id", criteria.getElementChimique()==null?null:criteria.getElementChimique().getId());
        addPredicateFk("elementChimique","id", criteria.getElementChimiques());
        addPredicateFk("elementChimique","code", criteria.getElementChimique()==null?null:criteria.getElementChimique().getCode());
        addPredicateFk("analyseChimique","id", criteria.getAnalyseChimique()==null?null:criteria.getAnalyseChimique().getId());
        addPredicateFk("analyseChimique","id", criteria.getAnalyseChimiques());
        addPredicateFk("analyseChimique","code", criteria.getAnalyseChimique()==null?null:criteria.getAnalyseChimique().getCode());
    }

    public AnalyseChimiqueDetailSpecification(AnalyseChimiqueDetailCriteria criteria) {
        super(criteria);
    }

    public AnalyseChimiqueDetailSpecification(AnalyseChimiqueDetailCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
