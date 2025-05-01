package ma.zyn.app.service.impl.admin.demande;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.dao.criteria.core.demande.DemandeCriteria;
import ma.zyn.app.dao.facade.core.demande.DemandeDao;
import ma.zyn.app.dao.specification.core.demande.DemandeSpecification;
import ma.zyn.app.service.facade.admin.demande.DemandeAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.referentiel.EtatDemandeAdminService ;
import ma.zyn.app.bean.core.referentiel.EtatDemande ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitMarchandAdminService ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
import ma.zyn.app.service.facade.admin.referentiel.TypeDemandeAdminService ;
import ma.zyn.app.bean.core.referentiel.TypeDemande ;
import ma.zyn.app.service.facade.admin.referentiel.ClientAdminService ;
import ma.zyn.app.bean.core.referentiel.Client ;

import java.util.List;
@Service
public class DemandeAdminServiceImpl implements DemandeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Demande update(Demande t) {
        Demande loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Demande.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Demande findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Demande findOrSave(Demande t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Demande result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Demande> findAll() {
        return dao.findAll();
    }

    public List<Demande> findByCriteria(DemandeCriteria criteria) {
        List<Demande> content = null;
        if (criteria != null) {
            DemandeSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private DemandeSpecification constructSpecification(DemandeCriteria criteria) {
        DemandeSpecification mySpecification =  (DemandeSpecification) RefelexivityUtil.constructObjectUsingOneParam(DemandeSpecification.class, criteria);
        return mySpecification;
    }

    public List<Demande> findPaginatedByCriteria(DemandeCriteria criteria, int page, int pageSize, String order, String sortField) {
        DemandeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DemandeCriteria criteria) {
        DemandeSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Demande> findByProduitMarchandCode(String code){
        return dao.findByProduitMarchandCode(code);
    }
    public List<Demande> findByProduitMarchandId(Long id){
        return dao.findByProduitMarchandId(id);
    }
    public int deleteByProduitMarchandCode(String code){
        return dao.deleteByProduitMarchandCode(code);
    }
    public int deleteByProduitMarchandId(Long id){
        return dao.deleteByProduitMarchandId(id);
    }
    public long countByProduitMarchandCode(String code){
        return dao.countByProduitMarchandCode(code);
    }
    public List<Demande> findByClientId(Long id){
        return dao.findByClientId(id);
    }
    public int deleteByClientId(Long id){
        return dao.deleteByClientId(id);
    }
    public long countByClientCode(String code){
        return dao.countByClientCode(code);
    }
    public List<Demande> findByTypeDemandeCode(String code){
        return dao.findByTypeDemandeCode(code);
    }
    public List<Demande> findByTypeDemandeId(Long id){
        return dao.findByTypeDemandeId(id);
    }
    public int deleteByTypeDemandeCode(String code){
        return dao.deleteByTypeDemandeCode(code);
    }
    public int deleteByTypeDemandeId(Long id){
        return dao.deleteByTypeDemandeId(id);
    }
    public long countByTypeDemandeCode(String code){
        return dao.countByTypeDemandeCode(code);
    }
    public List<Demande> findByEtatDemandeCode(String code){
        return dao.findByEtatDemandeCode(code);
    }
    public List<Demande> findByEtatDemandeId(Long id){
        return dao.findByEtatDemandeId(id);
    }
    public int deleteByEtatDemandeCode(String code){
        return dao.deleteByEtatDemandeCode(code);
    }
    public int deleteByEtatDemandeId(Long id){
        return dao.deleteByEtatDemandeId(id);
    }
    public long countByEtatDemandeCode(String code){
        return dao.countByEtatDemandeCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Demande> delete(List<Demande> list) {
		List<Demande> result = new ArrayList();
        if (list != null) {
            for (Demande t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Demande create(Demande t) {
        Demande loaded = findByReferenceEntity(t);
        Demande saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Demande findWithAssociatedLists(Long id){
        Demande result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Demande> update(List<Demande> ts, boolean createIfNotExist) {
        List<Demande> result = new ArrayList<>();
        if (ts != null) {
            for (Demande t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Demande loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Demande t, Demande loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Demande findByReferenceEntity(Demande t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Demande t){
        if( t != null) {
            t.setProduitMarchand(produitMarchandService.findOrSave(t.getProduitMarchand()));
            t.setClient(clientService.findOrSave(t.getClient()));
            t.setTypeDemande(typeDemandeService.findOrSave(t.getTypeDemande()));
            t.setEtatDemande(etatDemandeService.findOrSave(t.getEtatDemande()));
        }
    }



    public List<Demande> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Demande>> getToBeSavedAndToBeDeleted(List<Demande> oldList, List<Demande> newList) {
        List<List<Demande>> result = new ArrayList<>();
        List<Demande> resultDelete = new ArrayList<>();
        List<Demande> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<Demande> oldList, List<Demande> newList, List<Demande> resultUpdateOrSave, List<Demande> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Demande myOld = oldList.get(i);
                Demande t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Demande myNew = newList.get(i);
                Demande t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private EtatDemandeAdminService etatDemandeService ;
    @Autowired
    private ProduitMarchandAdminService produitMarchandService ;
    @Autowired
    private TypeDemandeAdminService typeDemandeService ;
    @Autowired
    private ClientAdminService clientService ;

    public DemandeAdminServiceImpl(DemandeDao dao) {
        this.dao = dao;
    }

    private DemandeDao dao;
}
