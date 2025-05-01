package ma.zyn.app.service.facade.admin.expedition;

import java.util.List;
import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import ma.zyn.app.dao.criteria.core.expedition.AnalyseChimiqueCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface AnalyseChimiqueAdminService {



    List<AnalyseChimique> findByProduitMarchandCode(String code);
    List<AnalyseChimique> findByProduitMarchandId(Long id);
    int deleteByProduitMarchandId(Long id);
    int deleteByProduitMarchandCode(String code);
    long countByProduitMarchandCode(String code);




	AnalyseChimique create(AnalyseChimique t);

    AnalyseChimique update(AnalyseChimique t);

    List<AnalyseChimique> update(List<AnalyseChimique> ts,boolean createIfNotExist);

    AnalyseChimique findById(Long id);

    AnalyseChimique findOrSave(AnalyseChimique t);

    AnalyseChimique findByReferenceEntity(AnalyseChimique t);

    AnalyseChimique findWithAssociatedLists(Long id);

    List<AnalyseChimique> findAllOptimized();

    List<AnalyseChimique> findAll();

    List<AnalyseChimique> findByCriteria(AnalyseChimiqueCriteria criteria);

    List<AnalyseChimique> findPaginatedByCriteria(AnalyseChimiqueCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AnalyseChimiqueCriteria criteria);

    List<AnalyseChimique> delete(List<AnalyseChimique> ts);

    boolean deleteById(Long id);

    List<List<AnalyseChimique>> getToBeSavedAndToBeDeleted(List<AnalyseChimique> oldList, List<AnalyseChimique> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
