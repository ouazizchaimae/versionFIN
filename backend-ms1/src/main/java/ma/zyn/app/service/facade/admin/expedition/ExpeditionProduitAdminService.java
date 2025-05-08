package ma.zyn.app.service.facade.admin.expedition;

import java.util.List;
import ma.zyn.app.bean.core.expedition.ExpeditionProduit;
import ma.zyn.app.dao.criteria.core.expedition.ExpeditionProduitCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ExpeditionProduitAdminService {



    List<ExpeditionProduit> findByAnalyseChimiqueId(Long id);
    int deleteByAnalyseChimiqueId(Long id);
    long countByAnalyseChimiqueCode(String code);
    List<ExpeditionProduit> findByCharteChimiqueId(Long id);
    int deleteByCharteChimiqueId(Long id);
    long countByCharteChimiqueCode(String code);




	ExpeditionProduit create(ExpeditionProduit t);

    ExpeditionProduit update(ExpeditionProduit t);

    List<ExpeditionProduit> update(List<ExpeditionProduit> ts,boolean createIfNotExist);

    ExpeditionProduit findById(Long id);

    ExpeditionProduit findOrSave(ExpeditionProduit t);

    ExpeditionProduit findByReferenceEntity(ExpeditionProduit t);

    ExpeditionProduit findWithAssociatedLists(Long id);

    List<ExpeditionProduit> findAllOptimized();

    List<ExpeditionProduit> findAll();

    List<ExpeditionProduit> findByCriteria(ExpeditionProduitCriteria criteria);

    List<ExpeditionProduit> findPaginatedByCriteria(ExpeditionProduitCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ExpeditionProduitCriteria criteria);

    List<ExpeditionProduit> delete(List<ExpeditionProduit> ts);

    boolean deleteById(Long id);

    List<List<ExpeditionProduit>> getToBeSavedAndToBeDeleted(List<ExpeditionProduit> oldList, List<ExpeditionProduit> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    void deleteByExpeditionId(Long id);

    List<ExpeditionProduit> findByExpeditionId(Long id);
}
