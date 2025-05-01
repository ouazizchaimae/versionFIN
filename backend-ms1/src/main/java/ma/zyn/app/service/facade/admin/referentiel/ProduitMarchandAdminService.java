package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.dao.criteria.core.referentiel.ProduitMarchandCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ProduitMarchandAdminService {







	ProduitMarchand create(ProduitMarchand t);

    ProduitMarchand update(ProduitMarchand t);

    List<ProduitMarchand> update(List<ProduitMarchand> ts,boolean createIfNotExist);

    ProduitMarchand findById(Long id);

    ProduitMarchand findOrSave(ProduitMarchand t);

    ProduitMarchand findByReferenceEntity(ProduitMarchand t);

    ProduitMarchand findWithAssociatedLists(Long id);

    List<ProduitMarchand> findAllOptimized();

    List<ProduitMarchand> findAll();

    List<ProduitMarchand> findByCriteria(ProduitMarchandCriteria criteria);

    List<ProduitMarchand> findPaginatedByCriteria(ProduitMarchandCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProduitMarchandCriteria criteria);

    List<ProduitMarchand> delete(List<ProduitMarchand> ts);

    boolean deleteById(Long id);

    List<List<ProduitMarchand>> getToBeSavedAndToBeDeleted(List<ProduitMarchand> oldList, List<ProduitMarchand> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
