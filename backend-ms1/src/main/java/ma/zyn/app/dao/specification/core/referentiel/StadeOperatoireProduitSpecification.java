package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.StadeOperatoireProduitCriteria;
import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class StadeOperatoireProduitSpecification extends  AbstractSpecification<StadeOperatoireProduitCriteria, StadeOperatoireProduit>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("stadeOperatoire","id", criteria.getStadeOperatoire()==null?null:criteria.getStadeOperatoire().getId());
        addPredicateFk("stadeOperatoire","id", criteria.getStadeOperatoires());
        addPredicateFk("stadeOperatoire","code", criteria.getStadeOperatoire()==null?null:criteria.getStadeOperatoire().getCode());
        addPredicateFk("produit","id", criteria.getProduit()==null?null:criteria.getProduit().getId());
        addPredicateFk("produit","id", criteria.getProduits());
        addPredicateFk("produit","code", criteria.getProduit()==null?null:criteria.getProduit().getCode());
    }

    public StadeOperatoireProduitSpecification(StadeOperatoireProduitCriteria criteria) {
        super(criteria);
    }

    public StadeOperatoireProduitSpecification(StadeOperatoireProduitCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
