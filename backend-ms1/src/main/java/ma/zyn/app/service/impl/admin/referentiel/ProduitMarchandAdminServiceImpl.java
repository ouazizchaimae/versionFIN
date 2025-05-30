package ma.zyn.app.service.impl.admin.referentiel;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.dao.criteria.core.referentiel.ProduitMarchandCriteria;
import ma.zyn.app.dao.facade.core.referentiel.ProduitMarchandDao;
import ma.zyn.app.dao.specification.core.referentiel.ProduitMarchandSpecification;
import ma.zyn.app.service.facade.admin.referentiel.ProduitMarchandAdminService;
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
public class ProduitMarchandAdminServiceImpl implements ProduitMarchandAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProduitMarchand update(ProduitMarchand t) {
        ProduitMarchand loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProduitMarchand.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProduitMarchand findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProduitMarchand findOrSave(ProduitMarchand t) {
        if (t != null) {
            ProduitMarchand result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProduitMarchand> findAll() {
        return dao.findAll();
    }

    public List<ProduitMarchand> findByCriteria(ProduitMarchandCriteria criteria) {
        List<ProduitMarchand> content = null;
        if (criteria != null) {
            ProduitMarchandSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ProduitMarchandSpecification constructSpecification(ProduitMarchandCriteria criteria) {
        ProduitMarchandSpecification mySpecification =  (ProduitMarchandSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProduitMarchandSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProduitMarchand> findPaginatedByCriteria(ProduitMarchandCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProduitMarchandSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProduitMarchandCriteria criteria) {
        ProduitMarchandSpecification mySpecification = constructSpecification(criteria);
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
    public List<ProduitMarchand> delete(List<ProduitMarchand> list) {
		List<ProduitMarchand> result = new ArrayList();
        if (list != null) {
            for (ProduitMarchand t : list) {
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
    public ProduitMarchand create(ProduitMarchand t) {
        ProduitMarchand loaded = findByReferenceEntity(t);
        ProduitMarchand saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProduitMarchand findWithAssociatedLists(Long id){
        ProduitMarchand result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProduitMarchand> update(List<ProduitMarchand> ts, boolean createIfNotExist) {
        List<ProduitMarchand> result = new ArrayList<>();
        if (ts != null) {
            for (ProduitMarchand t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProduitMarchand loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProduitMarchand t, ProduitMarchand loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProduitMarchand findByReferenceEntity(ProduitMarchand t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ProduitMarchand> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ProduitMarchand>> getToBeSavedAndToBeDeleted(List<ProduitMarchand> oldList, List<ProduitMarchand> newList) {
        List<List<ProduitMarchand>> result = new ArrayList<>();
        List<ProduitMarchand> resultDelete = new ArrayList<>();
        List<ProduitMarchand> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProduitMarchand> oldList, List<ProduitMarchand> newList, List<ProduitMarchand> resultUpdateOrSave, List<ProduitMarchand> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProduitMarchand myOld = oldList.get(i);
                ProduitMarchand t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProduitMarchand myNew = newList.get(i);
                ProduitMarchand t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ProduitMarchandAdminServiceImpl(ProduitMarchandDao dao) {
        this.dao = dao;
    }

    private ProduitMarchandDao dao;
}
