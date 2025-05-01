package ma.zyn.app.service.impl.admin.referentiel;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.Entite;
import ma.zyn.app.dao.criteria.core.referentiel.EntiteCriteria;
import ma.zyn.app.dao.facade.core.referentiel.EntiteDao;
import ma.zyn.app.dao.specification.core.referentiel.EntiteSpecification;
import ma.zyn.app.service.facade.admin.referentiel.EntiteAdminService;
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
public class EntiteAdminServiceImpl implements EntiteAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Entite update(Entite t) {
        Entite loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Entite.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Entite findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Entite findOrSave(Entite t) {
        if (t != null) {
            Entite result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Entite> findAll() {
        return dao.findAll();
    }

    public List<Entite> findByCriteria(EntiteCriteria criteria) {
        List<Entite> content = null;
        if (criteria != null) {
            EntiteSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private EntiteSpecification constructSpecification(EntiteCriteria criteria) {
        EntiteSpecification mySpecification =  (EntiteSpecification) RefelexivityUtil.constructObjectUsingOneParam(EntiteSpecification.class, criteria);
        return mySpecification;
    }

    public List<Entite> findPaginatedByCriteria(EntiteCriteria criteria, int page, int pageSize, String order, String sortField) {
        EntiteSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EntiteCriteria criteria) {
        EntiteSpecification mySpecification = constructSpecification(criteria);
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
    public List<Entite> delete(List<Entite> list) {
		List<Entite> result = new ArrayList();
        if (list != null) {
            for (Entite t : list) {
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
    public Entite create(Entite t) {
        Entite loaded = findByReferenceEntity(t);
        Entite saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Entite findWithAssociatedLists(Long id){
        Entite result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Entite> update(List<Entite> ts, boolean createIfNotExist) {
        List<Entite> result = new ArrayList<>();
        if (ts != null) {
            for (Entite t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Entite loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Entite t, Entite loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Entite findByReferenceEntity(Entite t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Entite> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Entite>> getToBeSavedAndToBeDeleted(List<Entite> oldList, List<Entite> newList) {
        List<List<Entite>> result = new ArrayList<>();
        List<Entite> resultDelete = new ArrayList<>();
        List<Entite> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Entite> oldList, List<Entite> newList, List<Entite> resultUpdateOrSave, List<Entite> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Entite myOld = oldList.get(i);
                Entite t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Entite myNew = newList.get(i);
                Entite t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public EntiteAdminServiceImpl(EntiteDao dao) {
        this.dao = dao;
    }

    private EntiteDao dao;
}
