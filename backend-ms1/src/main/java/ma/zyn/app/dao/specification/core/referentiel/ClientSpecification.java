package  ma.zyn.app.dao.specification.core.referentiel;

import ma.zyn.app.dao.criteria.core.referentiel.ClientCriteria;
import ma.zyn.app.bean.core.referentiel.Client;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ClientSpecification extends  AbstractSpecification<ClientCriteria, Client>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicateFk("typeClient","id", criteria.getTypeClient()==null?null:criteria.getTypeClient().getId());
        addPredicateFk("typeClient","id", criteria.getTypeClients());
        addPredicateFk("typeClient","code", criteria.getTypeClient()==null?null:criteria.getTypeClient().getCode());
    }

    public ClientSpecification(ClientCriteria criteria) {
        super(criteria);
    }

    public ClientSpecification(ClientCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
