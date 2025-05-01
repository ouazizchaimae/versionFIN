package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit;
import ma.zyn.app.dao.criteria.core.referentiel.StadeOperatoireProduitCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface StadeOperatoireProduitAdminService {



    List<StadeOperatoireProduit> findByStadeOperatoireCode(String code);
    List<StadeOperatoireProduit> findByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireId(Long id);
    int deleteByStadeOperatoireCode(String code);
    long countByStadeOperatoireCode(String code);
    List<StadeOperatoireProduit> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);




	StadeOperatoireProduit create(StadeOperatoireProduit t);

    StadeOperatoireProduit update(StadeOperatoireProduit t);

    List<StadeOperatoireProduit> update(List<StadeOperatoireProduit> ts,boolean createIfNotExist);

    StadeOperatoireProduit findById(Long id);

    StadeOperatoireProduit findOrSave(StadeOperatoireProduit t);

    StadeOperatoireProduit findByReferenceEntity(StadeOperatoireProduit t);

    StadeOperatoireProduit findWithAssociatedLists(Long id);

    List<StadeOperatoireProduit> findAllOptimized();

    List<StadeOperatoireProduit> findAll();

    List<StadeOperatoireProduit> findByCriteria(StadeOperatoireProduitCriteria criteria);

    List<StadeOperatoireProduit> findPaginatedByCriteria(StadeOperatoireProduitCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StadeOperatoireProduitCriteria criteria);

    List<StadeOperatoireProduit> delete(List<StadeOperatoireProduit> ts);

    boolean deleteById(Long id);

    List<List<StadeOperatoireProduit>> getToBeSavedAndToBeDeleted(List<StadeOperatoireProduit> oldList, List<StadeOperatoireProduit> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
