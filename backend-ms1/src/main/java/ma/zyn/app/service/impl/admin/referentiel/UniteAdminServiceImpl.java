package ma.zyn.app.service.impl.admin.referentiel;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.dao.criteria.core.referentiel.UniteCriteria;
import ma.zyn.app.dao.facade.core.referentiel.UniteDao;
import ma.zyn.app.dao.specification.core.referentiel.UniteSpecification;
import ma.zyn.app.service.facade.admin.referentiel.UniteAdminService;
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


import java.util.List;
@Service
public class UniteAdminServiceImpl implements UniteAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Unite update(Unite t) {
        Unite loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Unite.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Unite findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Unite findOrSave(Unite t) {
        if (t != null) {
            Unite result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Unite> findAll() {
        return dao.findAll();
    }

    public List<Unite> findByCriteria(UniteCriteria criteria) {
        List<Unite> content = null;
        if (criteria != null) {
            UniteSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private UniteSpecification constructSpecification(UniteCriteria criteria) {
        UniteSpecification mySpecification =  (UniteSpecification) RefelexivityUtil.constructObjectUsingOneParam(UniteSpecification.class, criteria);
        return mySpecification;
    }

    public List<Unite> findPaginatedByCriteria(UniteCriteria criteria, int page, int pageSize, String order, String sortField) {
        UniteSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(UniteCriteria criteria) {
        UniteSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public List<Unite> delete(List<Unite> list) {
		List<Unite> result = new ArrayList();
        if (list != null) {
            for (Unite t : list) {
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
    public Unite create(Unite t) {
        Unite loaded = findByReferenceEntity(t);
        Unite saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Unite findWithAssociatedLists(Long id){
        Unite result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Unite> update(List<Unite> ts, boolean createIfNotExist) {
        List<Unite> result = new ArrayList<>();
        if (ts != null) {
            for (Unite t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Unite loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Unite t, Unite loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Unite findByReferenceEntity(Unite t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Unite> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Unite>> getToBeSavedAndToBeDeleted(List<Unite> oldList, List<Unite> newList) {
        List<List<Unite>> result = new ArrayList<>();
        List<Unite> resultDelete = new ArrayList<>();
        List<Unite> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Unite> oldList, List<Unite> newList, List<Unite> resultUpdateOrSave, List<Unite> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Unite myOld = oldList.get(i);
                Unite t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Unite myNew = newList.get(i);
                Unite t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public UniteAdminServiceImpl(UniteDao dao) {
        this.dao = dao;
    }

    private UniteDao dao;
}
