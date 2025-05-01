package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.ProduitMarchandCriteria;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ProduitMarchandSpecification extends  AbstractSpecification<ProduitMarchandCriteria, ProduitMarchand>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public ProduitMarchandSpecification(ProduitMarchandCriteria criteria) {
        super(criteria);
    }

    public ProduitMarchandSpecification(ProduitMarchandCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
