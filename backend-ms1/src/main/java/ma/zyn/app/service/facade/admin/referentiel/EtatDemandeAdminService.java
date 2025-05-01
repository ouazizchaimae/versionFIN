package ma.zyn.app.service.facade.admin.referentiel;

import java.util.List;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.dao.criteria.core.referentiel.EtatDemandeCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface EtatDemandeAdminService {







	EtatDemande create(EtatDemande t);

    EtatDemande update(EtatDemande t);

    List<EtatDemande> update(List<EtatDemande> ts,boolean createIfNotExist);

    EtatDemande findById(Long id);

    EtatDemande findOrSave(EtatDemande t);

    EtatDemande findByReferenceEntity(EtatDemande t);

    EtatDemande findWithAssociatedLists(Long id);

    List<EtatDemande> findAllOptimized();

    List<EtatDemande> findAll();

    List<EtatDemande> findByCriteria(EtatDemandeCriteria criteria);

    List<EtatDemande> findPaginatedByCriteria(EtatDemandeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EtatDemandeCriteria criteria);

    List<EtatDemande> delete(List<EtatDemande> ts);

    boolean deleteById(Long id);

    List<List<EtatDemande>> getToBeSavedAndToBeDeleted(List<EtatDemande> oldList, List<EtatDemande> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
