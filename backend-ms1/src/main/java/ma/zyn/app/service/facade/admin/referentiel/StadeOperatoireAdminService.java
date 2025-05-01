package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.dao.criteria.core.referentiel.StadeOperatoireCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface StadeOperatoireAdminService {



    List<StadeOperatoire> findByEntiteCode(String code);
    List<StadeOperatoire> findByEntiteId(Long id);
    int deleteByEntiteId(Long id);
    int deleteByEntiteCode(String code);
    long countByEntiteCode(String code);




	StadeOperatoire create(StadeOperatoire t);

    StadeOperatoire update(StadeOperatoire t);

    List<StadeOperatoire> update(List<StadeOperatoire> ts,boolean createIfNotExist);

    StadeOperatoire findById(Long id);

    StadeOperatoire findOrSave(StadeOperatoire t);

    StadeOperatoire findByReferenceEntity(StadeOperatoire t);

    StadeOperatoire findWithAssociatedLists(Long id);

    List<StadeOperatoire> findAllOptimized();

    List<StadeOperatoire> findAll();

    List<StadeOperatoire> findByCriteria(StadeOperatoireCriteria criteria);

    List<StadeOperatoire> findPaginatedByCriteria(StadeOperatoireCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StadeOperatoireCriteria criteria);

    List<StadeOperatoire> delete(List<StadeOperatoire> ts);

    boolean deleteById(Long id);

    List<List<StadeOperatoire>> getToBeSavedAndToBeDeleted(List<StadeOperatoire> oldList, List<StadeOperatoire> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
