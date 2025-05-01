package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.Client;
import ma.zyn.app.dao.criteria.core.referentiel.ClientCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ClientAdminService {



    List<Client> findByTypeClientCode(String code);
    List<Client> findByTypeClientId(Long id);
    int deleteByTypeClientId(Long id);
    int deleteByTypeClientCode(String code);
    long countByTypeClientCode(String code);




	Client create(Client t);

    Client update(Client t);

    List<Client> update(List<Client> ts,boolean createIfNotExist);

    Client findById(Long id);

    Client findOrSave(Client t);

    Client findByReferenceEntity(Client t);

    Client findWithAssociatedLists(Long id);

    List<Client> findAllOptimized();

    List<Client> findAll();

    List<Client> findByCriteria(ClientCriteria criteria);

    List<Client> findPaginatedByCriteria(ClientCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ClientCriteria criteria);

    List<Client> delete(List<Client> ts);

    boolean deleteById(Long id);

    List<List<Client>> getToBeSavedAndToBeDeleted(List<Client> oldList, List<Client> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
