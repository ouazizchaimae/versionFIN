package ma.zyn.app.service.facade.admin.expedition;

import java.util.List;
import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail;
import ma.zyn.app.dao.criteria.core.expedition.CharteChimiqueDetailCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CharteChimiqueDetailAdminService {



    List<CharteChimiqueDetail> findByElementChimiqueCode(String code);
    List<CharteChimiqueDetail> findByElementChimiqueId(Long id);
    int deleteByElementChimiqueId(Long id);
    int deleteByElementChimiqueCode(String code);
    long countByElementChimiqueCode(String code);
    List<CharteChimiqueDetail> findByCharteChimiqueId(Long id);
    int deleteByCharteChimiqueId(Long id);
    long countByCharteChimiqueCode(String code);




	CharteChimiqueDetail create(CharteChimiqueDetail t);

    CharteChimiqueDetail update(CharteChimiqueDetail t);

    List<CharteChimiqueDetail> update(List<CharteChimiqueDetail> ts,boolean createIfNotExist);

    CharteChimiqueDetail findById(Long id);

    CharteChimiqueDetail findOrSave(CharteChimiqueDetail t);

    CharteChimiqueDetail findByReferenceEntity(CharteChimiqueDetail t);

    CharteChimiqueDetail findWithAssociatedLists(Long id);

    List<CharteChimiqueDetail> findAllOptimized();

    List<CharteChimiqueDetail> findAll();

    List<CharteChimiqueDetail> findByCriteria(CharteChimiqueDetailCriteria criteria);

    List<CharteChimiqueDetail> findPaginatedByCriteria(CharteChimiqueDetailCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CharteChimiqueDetailCriteria criteria);

    List<CharteChimiqueDetail> delete(List<CharteChimiqueDetail> ts);

    boolean deleteById(Long id);

    List<List<CharteChimiqueDetail>> getToBeSavedAndToBeDeleted(List<CharteChimiqueDetail> oldList, List<CharteChimiqueDetail> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
