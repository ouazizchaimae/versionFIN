package ma.zyn.app.service.impl.admin.referentiel;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.TypeClient;
import ma.zyn.app.dao.criteria.core.referentiel.TypeClientCriteria;
import ma.zyn.app.dao.facade.core.referentiel.TypeClientDao;
import ma.zyn.app.dao.specification.core.referentiel.TypeClientSpecification;
import ma.zyn.app.service.facade.admin.referentiel.TypeClientAdminService;
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
public class TypeClientAdminServiceImpl implements TypeClientAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeClient update(TypeClient t) {
        TypeClient loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeClient.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeClient findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeClient findOrSave(TypeClient t) {
        if (t != null) {
            TypeClient result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeClient> findAll() {
        return dao.findAll();
    }

    public List<TypeClient> findByCriteria(TypeClientCriteria criteria) {
        List<TypeClient> content = null;
        if (criteria != null) {
            TypeClientSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TypeClientSpecification constructSpecification(TypeClientCriteria criteria) {
        TypeClientSpecification mySpecification =  (TypeClientSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeClientSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeClient> findPaginatedByCriteria(TypeClientCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeClientSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeClientCriteria criteria) {
        TypeClientSpecification mySpecification = constructSpecification(criteria);
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
    public List<TypeClient> delete(List<TypeClient> list) {
		List<TypeClient> result = new ArrayList();
        if (list != null) {
            for (TypeClient t : list) {
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
    public TypeClient create(TypeClient t) {
        TypeClient loaded = findByReferenceEntity(t);
        TypeClient saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeClient findWithAssociatedLists(Long id){
        TypeClient result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeClient> update(List<TypeClient> ts, boolean createIfNotExist) {
        List<TypeClient> result = new ArrayList<>();
        if (ts != null) {
            for (TypeClient t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeClient loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeClient t, TypeClient loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeClient findByReferenceEntity(TypeClient t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeClient> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeClient>> getToBeSavedAndToBeDeleted(List<TypeClient> oldList, List<TypeClient> newList) {
        List<List<TypeClient>> result = new ArrayList<>();
        List<TypeClient> resultDelete = new ArrayList<>();
        List<TypeClient> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeClient> oldList, List<TypeClient> newList, List<TypeClient> resultUpdateOrSave, List<TypeClient> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeClient myOld = oldList.get(i);
                TypeClient t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeClient myNew = newList.get(i);
                TypeClient t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public TypeClientAdminServiceImpl(TypeClientDao dao) {
        this.dao = dao;
    }

    private TypeClientDao dao;
}
