package  ma.zyn.app.dao.specification.core.expedition;

import ma.zyn.app.dao.criteria.core.expedition.ExpeditionProduitCriteria;
import ma.zyn.app.bean.core.expedition.ExpeditionProduit;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ExpeditionProduitSpecification extends  AbstractSpecification<ExpeditionProduitCriteria, ExpeditionProduit>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("analyseChimique","id", criteria.getAnalyseChimique()==null?null:criteria.getAnalyseChimique().getId());
        addPredicateFk("analyseChimique","id", criteria.getAnalyseChimiques());
        addPredicateFk("analyseChimique","code", criteria.getAnalyseChimique()==null?null:criteria.getAnalyseChimique().getCode());
        addPredicateFk("charteChimique","id", criteria.getCharteChimique()==null?null:criteria.getCharteChimique().getId());
        addPredicateFk("charteChimique","id", criteria.getCharteChimiques());
        addPredicateFk("charteChimique","code", criteria.getCharteChimique()==null?null:criteria.getCharteChimique().getCode());
    }

    public ExpeditionProduitSpecification(ExpeditionProduitCriteria criteria) {
        super(criteria);
    }

    public ExpeditionProduitSpecification(ExpeditionProduitCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
