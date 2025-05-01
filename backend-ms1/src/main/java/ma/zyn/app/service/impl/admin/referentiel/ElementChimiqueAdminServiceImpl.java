package ma.zyn.app.service.impl.admin.referentiel;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.dao.criteria.core.referentiel.ElementChimiqueCriteria;
import ma.zyn.app.dao.facade.core.referentiel.ElementChimiqueDao;
import ma.zyn.app.dao.specification.core.referentiel.ElementChimiqueSpecification;
import ma.zyn.app.service.facade.admin.referentiel.ElementChimiqueAdminService;
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

import ma.zyn.app.service.facade.admin.referentiel.UniteAdminService ;
import ma.zyn.app.bean.core.referentiel.Unite ;

import java.util.List;
@Service
public class ElementChimiqueAdminServiceImpl implements ElementChimiqueAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ElementChimique update(ElementChimique t) {
        ElementChimique loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ElementChimique.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ElementChimique findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ElementChimique findOrSave(ElementChimique t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ElementChimique result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ElementChimique> findAll() {
        return dao.findAll();
    }

    public List<ElementChimique> findByCriteria(ElementChimiqueCriteria criteria) {
        List<ElementChimique> content = null;
        if (criteria != null) {
            ElementChimiqueSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ElementChimiqueSpecification constructSpecification(ElementChimiqueCriteria criteria) {
        ElementChimiqueSpecification mySpecification =  (ElementChimiqueSpecification) RefelexivityUtil.constructObjectUsingOneParam(ElementChimiqueSpecification.class, criteria);
        return mySpecification;
    }

    public List<ElementChimique> findPaginatedByCriteria(ElementChimiqueCriteria criteria, int page, int pageSize, String order, String sortField) {
        ElementChimiqueSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ElementChimiqueCriteria criteria) {
        ElementChimiqueSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ElementChimique> findByUniteCode(String code){
        return dao.findByUniteCode(code);
    }
    public List<ElementChimique> findByUniteId(Long id){
        return dao.findByUniteId(id);
    }
    public int deleteByUniteCode(String code){
        return dao.deleteByUniteCode(code);
    }
    public int deleteByUniteId(Long id){
        return dao.deleteByUniteId(id);
    }
    public long countByUniteCode(String code){
        return dao.countByUniteCode(code);
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
    public List<ElementChimique> delete(List<ElementChimique> list) {
		List<ElementChimique> result = new ArrayList();
        if (list != null) {
            for (ElementChimique t : list) {
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
    public ElementChimique create(ElementChimique t) {
        ElementChimique loaded = findByReferenceEntity(t);
        ElementChimique saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ElementChimique findWithAssociatedLists(Long id){
        ElementChimique result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ElementChimique> update(List<ElementChimique> ts, boolean createIfNotExist) {
        List<ElementChimique> result = new ArrayList<>();
        if (ts != null) {
            for (ElementChimique t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ElementChimique loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ElementChimique t, ElementChimique loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ElementChimique findByReferenceEntity(ElementChimique t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(ElementChimique t){
        if( t != null) {
            t.setUnite(uniteService.findOrSave(t.getUnite()));
        }
    }



    public List<ElementChimique> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ElementChimique>> getToBeSavedAndToBeDeleted(List<ElementChimique> oldList, List<ElementChimique> newList) {
        List<List<ElementChimique>> result = new ArrayList<>();
        List<ElementChimique> resultDelete = new ArrayList<>();
        List<ElementChimique> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ElementChimique> oldList, List<ElementChimique> newList, List<ElementChimique> resultUpdateOrSave, List<ElementChimique> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ElementChimique myOld = oldList.get(i);
                ElementChimique t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ElementChimique myNew = newList.get(i);
                ElementChimique t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private UniteAdminService uniteService ;

    public ElementChimiqueAdminServiceImpl(ElementChimiqueDao dao) {
        this.dao = dao;
    }

    private ElementChimiqueDao dao;
}
