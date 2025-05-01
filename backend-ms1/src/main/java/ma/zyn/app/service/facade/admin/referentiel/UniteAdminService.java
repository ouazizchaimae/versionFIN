package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.dao.criteria.core.referentiel.UniteCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface UniteAdminService {







	Unite create(Unite t);

    Unite update(Unite t);

    List<Unite> update(List<Unite> ts,boolean createIfNotExist);

    Unite findById(Long id);

    Unite findOrSave(Unite t);

    Unite findByReferenceEntity(Unite t);

    Unite findWithAssociatedLists(Long id);

    List<Unite> findAllOptimized();

    List<Unite> findAll();

    List<Unite> findByCriteria(UniteCriteria criteria);

    List<Unite> findPaginatedByCriteria(UniteCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(UniteCriteria criteria);

    List<Unite> delete(List<Unite> ts);

    boolean deleteById(Long id);

    List<List<Unite>> getToBeSavedAndToBeDeleted(List<Unite> oldList, List<Unite> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
