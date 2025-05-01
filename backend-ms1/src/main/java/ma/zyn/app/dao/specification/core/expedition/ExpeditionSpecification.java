package  ma.zyn.app.dao.specification.core.expedition;

import ma.zyn.app.dao.criteria.core.expedition.ExpeditionCriteria;
import ma.zyn.app.bean.core.expedition.Expedition;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ExpeditionSpecification extends  AbstractSpecification<ExpeditionCriteria, Expedition>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("client","id", criteria.getClient()==null?null:criteria.getClient().getId());
        addPredicateFk("client","id", criteria.getClients());
        addPredicateFk("client","code", criteria.getClient()==null?null:criteria.getClient().getCode());
        addPredicateFk("typeExpedition","id", criteria.getTypeExpedition()==null?null:criteria.getTypeExpedition().getId());
        addPredicateFk("typeExpedition","id", criteria.getTypeExpeditions());
        addPredicateFk("typeExpedition","code", criteria.getTypeExpedition()==null?null:criteria.getTypeExpedition().getCode());
    }

    public ExpeditionSpecification(ExpeditionCriteria criteria) {
        super(criteria);
    }

    public ExpeditionSpecification(ExpeditionCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
