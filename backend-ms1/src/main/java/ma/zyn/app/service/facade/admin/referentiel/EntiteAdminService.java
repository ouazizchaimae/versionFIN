package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Entite;
import ma.zyn.app.dao.criteria.core.referentiel.EntiteCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface EntiteAdminService {







	Entite create(Entite t);

    Entite update(Entite t);

    List<Entite> update(List<Entite> ts,boolean createIfNotExist);

    Entite findById(Long id);

    Entite findOrSave(Entite t);

    Entite findByReferenceEntity(Entite t);

    Entite findWithAssociatedLists(Long id);

    List<Entite> findAllOptimized();

    List<Entite> findAll();

    List<Entite> findByCriteria(EntiteCriteria criteria);

    List<Entite> findPaginatedByCriteria(EntiteCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EntiteCriteria criteria);

    List<Entite> delete(List<Entite> ts);

    boolean deleteById(Long id);

    List<List<Entite>> getToBeSavedAndToBeDeleted(List<Entite> oldList, List<Entite> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
