package ma.zyn.app.service.impl.admin.referentiel;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.dao.criteria.core.referentiel.EtatDemandeCriteria;
import ma.zyn.app.dao.facade.core.referentiel.EtatDemandeDao;
import ma.zyn.app.dao.specification.core.referentiel.EtatDemandeSpecification;
import ma.zyn.app.service.facade.admin.referentiel.EtatDemandeAdminService;
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
public class EtatDemandeAdminServiceImpl implements EtatDemandeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatDemande update(EtatDemande t) {
        EtatDemande loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EtatDemande.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EtatDemande findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EtatDemande findOrSave(EtatDemande t) {
        if (t != null) {
            EtatDemande result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<EtatDemande> findAll() {
        return dao.findAll();
    }

    public List<EtatDemande> findByCriteria(EtatDemandeCriteria criteria) {
        List<EtatDemande> content = null;
        if (criteria != null) {
            EtatDemandeSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private EtatDemandeSpecification constructSpecification(EtatDemandeCriteria criteria) {
        EtatDemandeSpecification mySpecification =  (EtatDemandeSpecification) RefelexivityUtil.constructObjectUsingOneParam(EtatDemandeSpecification.class, criteria);
        return mySpecification;
    }

    public List<EtatDemande> findPaginatedByCriteria(EtatDemandeCriteria criteria, int page, int pageSize, String order, String sortField) {
        EtatDemandeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EtatDemandeCriteria criteria) {
        EtatDemandeSpecification mySpecification = constructSpecification(criteria);
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
    public List<EtatDemande> delete(List<EtatDemande> list) {
		List<EtatDemande> result = new ArrayList();
        if (list != null) {
            for (EtatDemande t : list) {
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
    public EtatDemande create(EtatDemande t) {
        EtatDemande loaded = findByReferenceEntity(t);
        EtatDemande saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public EtatDemande findWithAssociatedLists(Long id){
        EtatDemande result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatDemande> update(List<EtatDemande> ts, boolean createIfNotExist) {
        List<EtatDemande> result = new ArrayList<>();
        if (ts != null) {
            for (EtatDemande t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EtatDemande loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, EtatDemande t, EtatDemande loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public EtatDemande findByReferenceEntity(EtatDemande t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<EtatDemande> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EtatDemande>> getToBeSavedAndToBeDeleted(List<EtatDemande> oldList, List<EtatDemande> newList) {
        List<List<EtatDemande>> result = new ArrayList<>();
        List<EtatDemande> resultDelete = new ArrayList<>();
        List<EtatDemande> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<EtatDemande> oldList, List<EtatDemande> newList, List<EtatDemande> resultUpdateOrSave, List<EtatDemande> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                EtatDemande myOld = oldList.get(i);
                EtatDemande t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                EtatDemande myNew = newList.get(i);
                EtatDemande t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public EtatDemandeAdminServiceImpl(EtatDemandeDao dao) {
        this.dao = dao;
    }

    private EtatDemandeDao dao;
}
