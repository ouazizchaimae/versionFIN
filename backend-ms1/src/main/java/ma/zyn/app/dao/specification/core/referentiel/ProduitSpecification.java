package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.ProduitCriteria;
import ma.zyn.app.bean.core.referentiel.Produit;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProduitSpecification extends  AbstractSpecification<ProduitCriteria, Produit>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public ProduitSpecification(ProduitCriteria criteria) {
        super(criteria);
    }

    public ProduitSpecification(ProduitCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
