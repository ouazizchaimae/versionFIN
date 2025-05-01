package ma.zyn.app.service.facade.admin.expedition;

import java.util.List;
import ma.zyn.app.bean.core.expedition.Expedition;
import ma.zyn.app.dao.criteria.core.expedition.ExpeditionCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ExpeditionAdminService {



    List<Expedition> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);
    List<Expedition> findByTypeExpeditionCode(String code);
    List<Expedition> findByTypeExpeditionId(Long id);
    int deleteByTypeExpeditionId(Long id);
    int deleteByTypeExpeditionCode(String code);
    long countByTypeExpeditionCode(String code);




	Expedition create(Expedition t);

    Expedition update(Expedition t);

    List<Expedition> update(List<Expedition> ts,boolean createIfNotExist);

    Expedition findById(Long id);

    Expedition findOrSave(Expedition t);

    Expedition findByReferenceEntity(Expedition t);

    Expedition findWithAssociatedLists(Long id);

    List<Expedition> findAllOptimized();

    List<Expedition> findAll();

    List<Expedition> findByCriteria(ExpeditionCriteria criteria);

    List<Expedition> findPaginatedByCriteria(ExpeditionCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ExpeditionCriteria criteria);

    List<Expedition> delete(List<Expedition> ts);

    boolean deleteById(Long id);

    List<List<Expedition>> getToBeSavedAndToBeDeleted(List<Expedition> oldList, List<Expedition> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
