package ma.zyn.app.service.facade.admin.expedition;

import java.util.List;
import ma.zyn.app.bean.core.expedition.CharteChimique;
import ma.zyn.app.dao.criteria.core.expedition.CharteChimiqueCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CharteChimiqueAdminService {



    List<CharteChimique> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);
    List<CharteChimique> findByProduitMarchandCode(String code);
    List<CharteChimique> findByProduitMarchandId(Long id);
    int deleteByProduitMarchandId(Long id);
    int deleteByProduitMarchandCode(String code);
    long countByProduitMarchandCode(String code);




	CharteChimique create(CharteChimique t);

    CharteChimique update(CharteChimique t);

    List<CharteChimique> update(List<CharteChimique> ts,boolean createIfNotExist);

    CharteChimique findById(Long id);

    CharteChimique findOrSave(CharteChimique t);

    CharteChimique findByReferenceEntity(CharteChimique t);

    CharteChimique findWithAssociatedLists(Long id);

    List<CharteChimique> findAllOptimized();

    List<CharteChimique> findAll();

    List<CharteChimique> findByCriteria(CharteChimiqueCriteria criteria);

    List<CharteChimique> findPaginatedByCriteria(CharteChimiqueCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CharteChimiqueCriteria criteria);

    List<CharteChimique> delete(List<CharteChimique> ts);

    boolean deleteById(Long id);

    List<List<CharteChimique>> getToBeSavedAndToBeDeleted(List<CharteChimique> oldList, List<CharteChimique> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
