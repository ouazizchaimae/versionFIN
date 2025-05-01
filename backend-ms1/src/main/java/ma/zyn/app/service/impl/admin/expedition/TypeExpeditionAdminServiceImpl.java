package ma.zyn.app.service.impl.admin.expedition;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.expedition.TypeExpedition;
import ma.zyn.app.dao.criteria.core.expedition.TypeExpeditionCriteria;
import ma.zyn.app.dao.facade.core.expedition.TypeExpeditionDao;
import ma.zyn.app.dao.specification.core.expedition.TypeExpeditionSpecification;
import ma.zyn.app.service.facade.admin.expedition.TypeExpeditionAdminService;
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
public class TypeExpeditionAdminServiceImpl implements TypeExpeditionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeExpedition update(TypeExpedition t) {
        TypeExpedition loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeExpedition.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeExpedition findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeExpedition findOrSave(TypeExpedition t) {
        if (t != null) {
            TypeExpedition result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeExpedition> findAll() {
        return dao.findAll();
    }

    public List<TypeExpedition> findByCriteria(TypeExpeditionCriteria criteria) {
        List<TypeExpedition> content = null;
        if (criteria != null) {
            TypeExpeditionSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TypeExpeditionSpecification constructSpecification(TypeExpeditionCriteria criteria) {
        TypeExpeditionSpecification mySpecification =  (TypeExpeditionSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeExpeditionSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeExpedition> findPaginatedByCriteria(TypeExpeditionCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeExpeditionSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeExpeditionCriteria criteria) {
        TypeExpeditionSpecification mySpecification = constructSpecification(criteria);
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
    public List<TypeExpedition> delete(List<TypeExpedition> list) {
		List<TypeExpedition> result = new ArrayList();
        if (list != null) {
            for (TypeExpedition t : list) {
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
    public TypeExpedition create(TypeExpedition t) {
        TypeExpedition loaded = findByReferenceEntity(t);
        TypeExpedition saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeExpedition findWithAssociatedLists(Long id){
        TypeExpedition result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeExpedition> update(List<TypeExpedition> ts, boolean createIfNotExist) {
        List<TypeExpedition> result = new ArrayList<>();
        if (ts != null) {
            for (TypeExpedition t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeExpedition loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeExpedition t, TypeExpedition loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeExpedition findByReferenceEntity(TypeExpedition t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeExpedition> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeExpedition>> getToBeSavedAndToBeDeleted(List<TypeExpedition> oldList, List<TypeExpedition> newList) {
        List<List<TypeExpedition>> result = new ArrayList<>();
        List<TypeExpedition> resultDelete = new ArrayList<>();
        List<TypeExpedition> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeExpedition> oldList, List<TypeExpedition> newList, List<TypeExpedition> resultUpdateOrSave, List<TypeExpedition> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeExpedition myOld = oldList.get(i);
                TypeExpedition t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeExpedition myNew = newList.get(i);
                TypeExpedition t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public TypeExpeditionAdminServiceImpl(TypeExpeditionDao dao) {
        this.dao = dao;
    }

    private TypeExpeditionDao dao;
}
