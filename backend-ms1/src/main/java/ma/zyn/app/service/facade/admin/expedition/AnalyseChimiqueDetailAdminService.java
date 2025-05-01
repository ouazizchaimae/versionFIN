package ma.zyn.app.service.facade.admin.expedition;

import java.util.List;
import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail;
import ma.zyn.app.dao.criteria.core.expedition.AnalyseChimiqueDetailCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface AnalyseChimiqueDetailAdminService {



    List<AnalyseChimiqueDetail> findByElementChimiqueCode(String code);
    List<AnalyseChimiqueDetail> findByElementChimiqueId(Long id);
    int deleteByElementChimiqueId(Long id);
    int deleteByElementChimiqueCode(String code);
    long countByElementChimiqueCode(String code);
    List<AnalyseChimiqueDetail> findByAnalyseChimiqueId(Long id);
    int deleteByAnalyseChimiqueId(Long id);
    long countByAnalyseChimiqueCode(String code);




	AnalyseChimiqueDetail create(AnalyseChimiqueDetail t);

    AnalyseChimiqueDetail update(AnalyseChimiqueDetail t);

    List<AnalyseChimiqueDetail> update(List<AnalyseChimiqueDetail> ts,boolean createIfNotExist);

    AnalyseChimiqueDetail findById(Long id);

    AnalyseChimiqueDetail findOrSave(AnalyseChimiqueDetail t);

    AnalyseChimiqueDetail findByReferenceEntity(AnalyseChimiqueDetail t);

    AnalyseChimiqueDetail findWithAssociatedLists(Long id);

    List<AnalyseChimiqueDetail> findAllOptimized();

    List<AnalyseChimiqueDetail> findAll();

    List<AnalyseChimiqueDetail> findByCriteria(AnalyseChimiqueDetailCriteria criteria);

    List<AnalyseChimiqueDetail> findPaginatedByCriteria(AnalyseChimiqueDetailCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AnalyseChimiqueDetailCriteria criteria);

    List<AnalyseChimiqueDetail> delete(List<AnalyseChimiqueDetail> ts);

    boolean deleteById(Long id);

    List<List<AnalyseChimiqueDetail>> getToBeSavedAndToBeDeleted(List<AnalyseChimiqueDetail> oldList, List<AnalyseChimiqueDetail> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
