package ma.zyn.app.service.impl.admin.referentiel;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.dao.criteria.core.referentiel.StadeOperatoireCriteria;
import ma.zyn.app.dao.facade.core.referentiel.StadeOperatoireDao;
import ma.zyn.app.dao.specification.core.referentiel.StadeOperatoireSpecification;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService;
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

import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit ;
import ma.zyn.app.service.facade.admin.referentiel.EntiteAdminService ;
import ma.zyn.app.bean.core.referentiel.Entite ;

import java.util.List;
@Service
public class StadeOperatoireAdminServiceImpl implements StadeOperatoireAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StadeOperatoire update(StadeOperatoire t) {
        StadeOperatoire loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{StadeOperatoire.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public StadeOperatoire findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public StadeOperatoire findOrSave(StadeOperatoire t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            StadeOperatoire result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<StadeOperatoire> findAll() {
        return dao.findAll();
    }

    public List<StadeOperatoire> findByCriteria(StadeOperatoireCriteria criteria) {
        List<StadeOperatoire> content = null;
        if (criteria != null) {
            StadeOperatoireSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private StadeOperatoireSpecification constructSpecification(StadeOperatoireCriteria criteria) {
        StadeOperatoireSpecification mySpecification =  (StadeOperatoireSpecification) RefelexivityUtil.constructObjectUsingOneParam(StadeOperatoireSpecification.class, criteria);
        return mySpecification;
    }

    public List<StadeOperatoire> findPaginatedByCriteria(StadeOperatoireCriteria criteria, int page, int pageSize, String order, String sortField) {
        StadeOperatoireSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(StadeOperatoireCriteria criteria) {
        StadeOperatoireSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<StadeOperatoire> findByEntiteCode(String code){
        return dao.findByEntiteCode(code);
    }
    public List<StadeOperatoire> findByEntiteId(Long id){
        return dao.findByEntiteId(id);
    }
    public int deleteByEntiteCode(String code){
        return dao.deleteByEntiteCode(code);
    }
    public int deleteByEntiteId(Long id){
        return dao.deleteByEntiteId(id);
    }
    public long countByEntiteCode(String code){
        return dao.countByEntiteCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        stadeOperatoireProduitService.deleteByStadeOperatoireId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StadeOperatoire> delete(List<StadeOperatoire> list) {
		List<StadeOperatoire> result = new ArrayList();
        if (list != null) {
            for (StadeOperatoire t : list) {
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
    public StadeOperatoire create(StadeOperatoire t) {
        StadeOperatoire loaded = findByReferenceEntity(t);
        StadeOperatoire saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getStadeOperatoireProduits() != null) {
                t.getStadeOperatoireProduits().forEach(element-> {
                    element.setStadeOperatoire(saved);
                    stadeOperatoireProduitService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public StadeOperatoire findWithAssociatedLists(Long id){
        StadeOperatoire result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setStadeOperatoireProduits(stadeOperatoireProduitService.findByStadeOperatoireId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StadeOperatoire> update(List<StadeOperatoire> ts, boolean createIfNotExist) {
        List<StadeOperatoire> result = new ArrayList<>();
        if (ts != null) {
            for (StadeOperatoire t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    StadeOperatoire loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, StadeOperatoire t, StadeOperatoire loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(StadeOperatoire stadeOperatoire){
    if(stadeOperatoire !=null && stadeOperatoire.getId() != null){
        List<List<StadeOperatoireProduit>> resultStadeOperatoireProduits= stadeOperatoireProduitService.getToBeSavedAndToBeDeleted(stadeOperatoireProduitService.findByStadeOperatoireId(stadeOperatoire.getId()),stadeOperatoire.getStadeOperatoireProduits());
            stadeOperatoireProduitService.delete(resultStadeOperatoireProduits.get(1));
        emptyIfNull(resultStadeOperatoireProduits.get(0)).forEach(e -> e.setStadeOperatoire(stadeOperatoire));
        stadeOperatoireProduitService.update(resultStadeOperatoireProduits.get(0),true);
        }
    }








    public StadeOperatoire findByReferenceEntity(StadeOperatoire t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(StadeOperatoire t){
        if( t != null) {
            t.setEntite(entiteService.findOrSave(t.getEntite()));
        }
    }



    public List<StadeOperatoire> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<StadeOperatoire>> getToBeSavedAndToBeDeleted(List<StadeOperatoire> oldList, List<StadeOperatoire> newList) {
        List<List<StadeOperatoire>> result = new ArrayList<>();
        List<StadeOperatoire> resultDelete = new ArrayList<>();
        List<StadeOperatoire> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<StadeOperatoire> oldList, List<StadeOperatoire> newList, List<StadeOperatoire> resultUpdateOrSave, List<StadeOperatoire> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                StadeOperatoire myOld = oldList.get(i);
                StadeOperatoire t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                StadeOperatoire myNew = newList.get(i);
                StadeOperatoire t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private StadeOperatoireProduitAdminService stadeOperatoireProduitService ;
    @Autowired
    private EntiteAdminService entiteService ;

    public StadeOperatoireAdminServiceImpl(StadeOperatoireDao dao) {
        this.dao = dao;
    }

    private StadeOperatoireDao dao;
}
