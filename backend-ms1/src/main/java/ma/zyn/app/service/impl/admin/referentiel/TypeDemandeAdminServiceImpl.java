package ma.zyn.app.service.impl.admin.referentiel;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.dao.criteria.core.referentiel.TypeDemandeCriteria;
import ma.zyn.app.dao.facade.core.referentiel.TypeDemandeDao;
import ma.zyn.app.dao.specification.core.referentiel.TypeDemandeSpecification;
import ma.zyn.app.service.facade.admin.referentiel.TypeDemandeAdminService;
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
public class TypeDemandeAdminServiceImpl implements TypeDemandeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeDemande update(TypeDemande t) {
        TypeDemande loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeDemande.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeDemande findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeDemande findOrSave(TypeDemande t) {
        if (t != null) {
            TypeDemande result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TypeDemande> findAll() {
        return dao.findAll();
    }

    public List<TypeDemande> findByCriteria(TypeDemandeCriteria criteria) {
        List<TypeDemande> content = null;
        if (criteria != null) {
            TypeDemandeSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TypeDemandeSpecification constructSpecification(TypeDemandeCriteria criteria) {
        TypeDemandeSpecification mySpecification =  (TypeDemandeSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeDemandeSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeDemande> findPaginatedByCriteria(TypeDemandeCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeDemandeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeDemandeCriteria criteria) {
        TypeDemandeSpecification mySpecification = constructSpecification(criteria);
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
    public List<TypeDemande> delete(List<TypeDemande> list) {
		List<TypeDemande> result = new ArrayList();
        if (list != null) {
            for (TypeDemande t : list) {
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
    public TypeDemande create(TypeDemande t) {
        TypeDemande loaded = findByReferenceEntity(t);
        TypeDemande saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TypeDemande findWithAssociatedLists(Long id){
        TypeDemande result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeDemande> update(List<TypeDemande> ts, boolean createIfNotExist) {
        List<TypeDemande> result = new ArrayList<>();
        if (ts != null) {
            for (TypeDemande t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeDemande loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TypeDemande t, TypeDemande loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TypeDemande findByReferenceEntity(TypeDemande t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeDemande> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeDemande>> getToBeSavedAndToBeDeleted(List<TypeDemande> oldList, List<TypeDemande> newList) {
        List<List<TypeDemande>> result = new ArrayList<>();
        List<TypeDemande> resultDelete = new ArrayList<>();
        List<TypeDemande> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TypeDemande> oldList, List<TypeDemande> newList, List<TypeDemande> resultUpdateOrSave, List<TypeDemande> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TypeDemande myOld = oldList.get(i);
                TypeDemande t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TypeDemande myNew = newList.get(i);
                TypeDemande t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public TypeDemandeAdminServiceImpl(TypeDemandeDao dao) {
        this.dao = dao;
    }

    private TypeDemandeDao dao;
}
