package ma.zyn.app.service.impl.admin.referentiel;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.ProduitSource;
import ma.zyn.app.dao.criteria.core.referentiel.ProduitSourceCriteria;
import ma.zyn.app.dao.facade.core.referentiel.ProduitSourceDao;
import ma.zyn.app.dao.specification.core.referentiel.ProduitSourceSpecification;
import ma.zyn.app.service.facade.admin.referentiel.ProduitSourceAdminService;
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
public class ProduitSourceAdminServiceImpl implements ProduitSourceAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProduitSource update(ProduitSource t) {
        ProduitSource loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProduitSource.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProduitSource findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProduitSource findOrSave(ProduitSource t) {
        if (t != null) {
            ProduitSource result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ProduitSource> findAll() {
        return dao.findAll();
    }

    public List<ProduitSource> findByCriteria(ProduitSourceCriteria criteria) {
        List<ProduitSource> content = null;
        if (criteria != null) {
            ProduitSourceSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ProduitSourceSpecification constructSpecification(ProduitSourceCriteria criteria) {
        ProduitSourceSpecification mySpecification =  (ProduitSourceSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProduitSourceSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProduitSource> findPaginatedByCriteria(ProduitSourceCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProduitSourceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProduitSourceCriteria criteria) {
        ProduitSourceSpecification mySpecification = constructSpecification(criteria);
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
    public List<ProduitSource> delete(List<ProduitSource> list) {
		List<ProduitSource> result = new ArrayList();
        if (list != null) {
            for (ProduitSource t : list) {
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
    public ProduitSource create(ProduitSource t) {
        ProduitSource loaded = findByReferenceEntity(t);
        ProduitSource saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ProduitSource findWithAssociatedLists(Long id){
        ProduitSource result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProduitSource> update(List<ProduitSource> ts, boolean createIfNotExist) {
        List<ProduitSource> result = new ArrayList<>();
        if (ts != null) {
            for (ProduitSource t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProduitSource loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ProduitSource t, ProduitSource loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ProduitSource findByReferenceEntity(ProduitSource t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ProduitSource> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ProduitSource>> getToBeSavedAndToBeDeleted(List<ProduitSource> oldList, List<ProduitSource> newList) {
        List<List<ProduitSource>> result = new ArrayList<>();
        List<ProduitSource> resultDelete = new ArrayList<>();
        List<ProduitSource> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ProduitSource> oldList, List<ProduitSource> newList, List<ProduitSource> resultUpdateOrSave, List<ProduitSource> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ProduitSource myOld = oldList.get(i);
                ProduitSource t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ProduitSource myNew = newList.get(i);
                ProduitSource t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ProduitSourceAdminServiceImpl(ProduitSourceDao dao) {
        this.dao = dao;
    }

    private ProduitSourceDao dao;
}
