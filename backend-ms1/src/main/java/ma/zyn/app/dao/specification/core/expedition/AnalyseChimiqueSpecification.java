package  ma.zyn.app.dao.specification.core.expedition;

import ma.zyn.app.dao.criteria.core.expedition.AnalyseChimiqueCriteria;
import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class AnalyseChimiqueSpecification extends  AbstractSpecification<AnalyseChimiqueCriteria, AnalyseChimique>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("produitMarchand","id", criteria.getProduitMarchand()==null?null:criteria.getProduitMarchand().getId());
        addPredicateFk("produitMarchand","id", criteria.getProduitMarchands());
        addPredicateFk("produitMarchand","code", criteria.getProduitMarchand()==null?null:criteria.getProduitMarchand().getCode());
    }

    public AnalyseChimiqueSpecification(AnalyseChimiqueCriteria criteria) {
        super(criteria);
    }

    public AnalyseChimiqueSpecification(AnalyseChimiqueCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
