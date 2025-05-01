package ma.zyn.app.service.facade.admin.supply;

import java.util.List;
import ma.zyn.app.bean.core.supply.SuiviProduction;
import ma.zyn.app.dao.criteria.core.supply.SuiviProductionCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface SuiviProductionAdminService {



    List<SuiviProduction> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<SuiviProduction> findByStadeOperatoireCode(String code);
    List<SuiviProduction> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<SuiviProduction> findByUniteCode(String code);
    List<SuiviProduction> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);




	SuiviProduction create(SuiviProduction t);

    SuiviProduction update(SuiviProduction t);

    List<SuiviProduction> update(List<SuiviProduction> ts,boolean createIfNotExist);

    SuiviProduction findById(Long id);

    SuiviProduction findOrSave(SuiviProduction t);

    SuiviProduction findByReferenceEntity(SuiviProduction t);

    SuiviProduction findWithAssociatedLists(Long id);

    List<SuiviProduction> findAllOptimized();

    List<SuiviProduction> findAll();

    List<SuiviProduction> findByCriteria(SuiviProductionCriteria criteria);

    List<SuiviProduction> findPaginatedByCriteria(SuiviProductionCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SuiviProductionCriteria criteria);

    List<SuiviProduction> delete(List<SuiviProduction> ts);

    boolean deleteById(Long id);

    List<List<SuiviProduction>> getToBeSavedAndToBeDeleted(List<SuiviProduction> oldList, List<SuiviProduction> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
