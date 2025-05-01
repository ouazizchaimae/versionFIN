package ma.zyn.app.service.facade.admin.expedition;

import java.util.List;
import ma.zyn.app.bean.core.expedition.TypeExpedition;
import ma.zyn.app.dao.criteria.core.expedition.TypeExpeditionCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface TypeExpeditionAdminService {







	TypeExpedition create(TypeExpedition t);

    TypeExpedition update(TypeExpedition t);

    List<TypeExpedition> update(List<TypeExpedition> ts,boolean createIfNotExist);

    TypeExpedition findById(Long id);

    TypeExpedition findOrSave(TypeExpedition t);

    TypeExpedition findByReferenceEntity(TypeExpedition t);

    TypeExpedition findWithAssociatedLists(Long id);

    List<TypeExpedition> findAllOptimized();

    List<TypeExpedition> findAll();

    List<TypeExpedition> findByCriteria(TypeExpeditionCriteria criteria);

    List<TypeExpedition> findPaginatedByCriteria(TypeExpeditionCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeExpeditionCriteria criteria);

    List<TypeExpedition> delete(List<TypeExpedition> ts);

    boolean deleteById(Long id);

    List<List<TypeExpedition>> getToBeSavedAndToBeDeleted(List<TypeExpedition> oldList, List<TypeExpedition> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
