package ma.zyn.app.service.facade.admin.demande;

import java.util.List;
import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.dao.criteria.core.demande.DemandeCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface DemandeAdminService {



    List<Demande> findByProduitMarchandCode(String code);
    List<Demande> findByProduitMarchandId(Long id);
    int deleteByProduitMarchandId(Long id);
    int deleteByProduitMarchandCode(String code);
    long countByProduitMarchandCode(String code);
    List<Demande> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);
    List<Demande> findByTypeDemandeCode(String code);
    List<Demande> findByTypeDemandeId(Long id);
    int deleteByTypeDemandeId(Long id);
    int deleteByTypeDemandeCode(String code);
    long countByTypeDemandeCode(String code);
    List<Demande> findByEtatDemandeCode(String code);
    List<Demande> findByEtatDemandeId(Long id);
    int deleteByEtatDemandeId(Long id);
    int deleteByEtatDemandeCode(String code);
    long countByEtatDemandeCode(String code);




	Demande create(Demande t);

    Demande update(Demande t);

    List<Demande> update(List<Demande> ts,boolean createIfNotExist);

    Demande findById(Long id);

    Demande findOrSave(Demande t);

    Demande findByReferenceEntity(Demande t);

    Demande findWithAssociatedLists(Long id);

    List<Demande> findAllOptimized();

    List<Demande> findAll();

    List<Demande> findByCriteria(DemandeCriteria criteria);

    List<Demande> findPaginatedByCriteria(DemandeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DemandeCriteria criteria);

    List<Demande> delete(List<Demande> ts);

    boolean deleteById(Long id);

    List<List<Demande>> getToBeSavedAndToBeDeleted(List<Demande> oldList, List<Demande> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
