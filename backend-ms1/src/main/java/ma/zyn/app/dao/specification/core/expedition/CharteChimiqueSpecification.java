package  ma.zyn.app.dao.specification.core.expedition;

import ma.zyn.app.dao.criteria.core.expedition.CharteChimiqueCriteria;
import ma.zyn.app.bean.core.expedition.CharteChimique;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CharteChimiqueSpecification extends  AbstractSpecification<CharteChimiqueCriteria, CharteChimique>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("client","id", criteria.getClient()==null?null:criteria.getClient().getId());
        addPredicateFk("client","id", criteria.getClients());
        addPredicateFk("client","code", criteria.getClient()==null?null:criteria.getClient().getCode());
        addPredicateFk("produitMarchand","id", criteria.getProduitMarchand()==null?null:criteria.getProduitMarchand().getId());
        addPredicateFk("produitMarchand","id", criteria.getProduitMarchands());
        addPredicateFk("produitMarchand","code", criteria.getProduitMarchand()==null?null:criteria.getProduitMarchand().getCode());
    }

    public CharteChimiqueSpecification(CharteChimiqueCriteria criteria) {
        super(criteria);
    }

    public CharteChimiqueSpecification(CharteChimiqueCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
