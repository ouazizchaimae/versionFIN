package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.dao.criteria.core.referentiel.ElementChimiqueCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ElementChimiqueAdminService {



    List<ElementChimique> findByUniteCode(String code);
    List<ElementChimique> findByUniteId(Long id);
    int deleteByUniteId(Long id);
    int deleteByUniteCode(String code);
    long countByUniteCode(String code);




	ElementChimique create(ElementChimique t);

    ElementChimique update(ElementChimique t);

    List<ElementChimique> update(List<ElementChimique> ts,boolean createIfNotExist);

    ElementChimique findById(Long id);

    ElementChimique findOrSave(ElementChimique t);

    ElementChimique findByReferenceEntity(ElementChimique t);

    ElementChimique findWithAssociatedLists(Long id);

    List<ElementChimique> findAllOptimized();

    List<ElementChimique> findAll();

    List<ElementChimique> findByCriteria(ElementChimiqueCriteria criteria);

    List<ElementChimique> findPaginatedByCriteria(ElementChimiqueCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ElementChimiqueCriteria criteria);

    List<ElementChimique> delete(List<ElementChimique> ts);

    boolean deleteById(Long id);

    List<List<ElementChimique>> getToBeSavedAndToBeDeleted(List<ElementChimique> oldList, List<ElementChimique> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
