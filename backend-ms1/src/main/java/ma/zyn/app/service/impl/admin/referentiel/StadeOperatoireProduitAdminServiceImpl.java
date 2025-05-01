package ma.zyn.app.service.impl.admin.referentiel;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit;
import ma.zyn.app.dao.criteria.core.referentiel.StadeOperatoireProduitCriteria;
import ma.zyn.app.dao.facade.core.referentiel.StadeOperatoireProduitDao;
import ma.zyn.app.dao.specification.core.referentiel.StadeOperatoireProduitSpecification;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireProduitAdminService;
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

import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import java.util.List;
@Service
public class StadeOperatoireProduitAdminServiceImpl implements StadeOperatoireProduitAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StadeOperatoireProduit update(StadeOperatoireProduit t) {
        StadeOperatoireProduit loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{StadeOperatoireProduit.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public StadeOperatoireProduit findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public StadeOperatoireProduit findOrSave(StadeOperatoireProduit t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            StadeOperatoireProduit result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<StadeOperatoireProduit> findAll() {
        return dao.findAll();
    }

    public List<StadeOperatoireProduit> findByCriteria(StadeOperatoireProduitCriteria criteria) {
        List<StadeOperatoireProduit> content = null;
        if (criteria != null) {
            StadeOperatoireProduitSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private StadeOperatoireProduitSpecification constructSpecification(StadeOperatoireProduitCriteria criteria) {
        StadeOperatoireProduitSpecification mySpecification =  (StadeOperatoireProduitSpecification) RefelexivityUtil.constructObjectUsingOneParam(StadeOperatoireProduitSpecification.class, criteria);
        return mySpecification;
    }

    public List<StadeOperatoireProduit> findPaginatedByCriteria(StadeOperatoireProduitCriteria criteria, int page, int pageSize, String order, String sortField) {
        StadeOperatoireProduitSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(StadeOperatoireProduitCriteria criteria) {
        StadeOperatoireProduitSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<StadeOperatoireProduit> findByStadeOperatoireCode(String code){
        return dao.findByStadeOperatoireCode(code);
    }
    public List<StadeOperatoireProduit> findByStadeOperatoireId(Long id){
        return dao.findByStadeOperatoireId(id);
    }
    public int deleteByStadeOperatoireCode(String code){
        return dao.deleteByStadeOperatoireCode(code);
    }
    public int deleteByStadeOperatoireId(Long id){
        return dao.deleteByStadeOperatoireId(id);
    }
    public long countByStadeOperatoireCode(String code){
        return dao.countByStadeOperatoireCode(code);
    }
    public List<StadeOperatoireProduit> findByProduitId(Long id){
        return dao.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return dao.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return dao.countByProduitCode(code);
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
    public List<StadeOperatoireProduit> delete(List<StadeOperatoireProduit> list) {
		List<StadeOperatoireProduit> result = new ArrayList();
        if (list != null) {
            for (StadeOperatoireProduit t : list) {
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
    public StadeOperatoireProduit create(StadeOperatoireProduit t) {
        StadeOperatoireProduit loaded = findByReferenceEntity(t);
        StadeOperatoireProduit saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public StadeOperatoireProduit findWithAssociatedLists(Long id){
        StadeOperatoireProduit result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StadeOperatoireProduit> update(List<StadeOperatoireProduit> ts, boolean createIfNotExist) {
        List<StadeOperatoireProduit> result = new ArrayList<>();
        if (ts != null) {
            for (StadeOperatoireProduit t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    StadeOperatoireProduit loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, StadeOperatoireProduit t, StadeOperatoireProduit loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public StadeOperatoireProduit findByReferenceEntity(StadeOperatoireProduit t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(StadeOperatoireProduit t){
        if( t != null) {
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
            t.setProduit(produitService.findOrSave(t.getProduit()));
        }
    }



    public List<StadeOperatoireProduit> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<StadeOperatoireProduit>> getToBeSavedAndToBeDeleted(List<StadeOperatoireProduit> oldList, List<StadeOperatoireProduit> newList) {
        List<List<StadeOperatoireProduit>> result = new ArrayList<>();
        List<StadeOperatoireProduit> resultDelete = new ArrayList<>();
        List<StadeOperatoireProduit> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<StadeOperatoireProduit> oldList, List<StadeOperatoireProduit> newList, List<StadeOperatoireProduit> resultUpdateOrSave, List<StadeOperatoireProduit> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                StadeOperatoireProduit myOld = oldList.get(i);
                StadeOperatoireProduit t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                StadeOperatoireProduit myNew = newList.get(i);
                StadeOperatoireProduit t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private StadeOperatoireAdminService stadeOperatoireService ;
    @Autowired
    private ProduitAdminService produitService ;

    public StadeOperatoireProduitAdminServiceImpl(StadeOperatoireProduitDao dao) {
        this.dao = dao;
    }

    private StadeOperatoireProduitDao dao;
}
