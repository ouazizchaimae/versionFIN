package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.TypeClient;
import ma.zyn.app.dao.criteria.core.referentiel.TypeClientCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface TypeClientAdminService {







	TypeClient create(TypeClient t);

    TypeClient update(TypeClient t);

    List<TypeClient> update(List<TypeClient> ts,boolean createIfNotExist);

    TypeClient findById(Long id);

    TypeClient findOrSave(TypeClient t);

    TypeClient findByReferenceEntity(TypeClient t);

    TypeClient findWithAssociatedLists(Long id);

    List<TypeClient> findAllOptimized();

    List<TypeClient> findAll();

    List<TypeClient> findByCriteria(TypeClientCriteria criteria);

    List<TypeClient> findPaginatedByCriteria(TypeClientCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeClientCriteria criteria);

    List<TypeClient> delete(List<TypeClient> ts);

    boolean deleteById(Long id);

    List<List<TypeClient>> getToBeSavedAndToBeDeleted(List<TypeClient> oldList, List<TypeClient> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
