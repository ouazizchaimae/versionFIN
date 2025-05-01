package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.ProduitSourceCriteria;
import ma.zyn.app.bean.core.referentiel.ProduitSource;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProduitSourceSpecification extends  AbstractSpecification<ProduitSourceCriteria, ProduitSource>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public ProduitSourceSpecification(ProduitSourceCriteria criteria) {
        super(criteria);
    }

    public ProduitSourceSpecification(ProduitSourceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
