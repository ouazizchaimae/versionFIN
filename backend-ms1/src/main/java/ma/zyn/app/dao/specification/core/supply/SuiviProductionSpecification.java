package  ma.zyn.app.dao.specification.core.supply;

import ma.zyn.app.dao.criteria.core.supply.SuiviProductionCriteria;
import ma.zyn.app.bean.core.supply.SuiviProduction;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class SuiviProductionSpecification extends  AbstractSpecification<SuiviProductionCriteria, SuiviProduction>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("jour", criteria.getJour(), criteria.getJourFrom(), criteria.getJourTo());
        addPredicateBigDecimal("volume", criteria.getVolume(), criteria.getVolumeMin(), criteria.getVolumeMax());
        addPredicateBigDecimal("tsm", criteria.getTsm(), criteria.getTsmMin(), criteria.getTsmMax());
        addPredicateFk("produit","id", criteria.getProduit()==null?null:criteria.getProduit().getId());
        addPredicateFk("produit","id", criteria.getProduits());
        addPredicateFk("produit","code", criteria.getProduit()==null?null:criteria.getProduit().getCode());
        addPredicateFk("stadeOperatoire","id", criteria.getStadeOperatoire()==null?null:criteria.getStadeOperatoire().getId());
        addPredicateFk("stadeOperatoire","id", criteria.getStadeOperatoires());
        addPredicateFk("stadeOperatoire","code", criteria.getStadeOperatoire()==null?null:criteria.getStadeOperatoire().getCode());
        addPredicateFk("unite","id", criteria.getUnite()==null?null:criteria.getUnite().getId());
        addPredicateFk("unite","id", criteria.getUnites());
        addPredicateFk("unite","code", criteria.getUnite()==null?null:criteria.getUnite().getCode());
    }

    public SuiviProductionSpecification(SuiviProductionCriteria criteria) {
        super(criteria);
    }

    public SuiviProductionSpecification(SuiviProductionCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
