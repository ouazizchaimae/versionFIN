package ma.zyn.app.service.impl.admin.expedition;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.expedition.ExpeditionProduit;
import ma.zyn.app.dao.criteria.core.expedition.ExpeditionProduitCriteria;
import ma.zyn.app.dao.facade.core.expedition.ExpeditionProduitDao;
import ma.zyn.app.dao.specification.core.expedition.ExpeditionProduitSpecification;
import ma.zyn.app.service.facade.admin.expedition.ExpeditionProduitAdminService;
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

import ma.zyn.app.service.facade.admin.expedition.AnalyseChimiqueAdminService ;
import ma.zyn.app.bean.core.expedition.AnalyseChimique ;
import ma.zyn.app.service.facade.admin.expedition.CharteChimiqueAdminService ;
import ma.zyn.app.bean.core.expedition.CharteChimique ;

import java.util.List;
@Service
public class ExpeditionProduitAdminServiceImpl implements ExpeditionProduitAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ExpeditionProduit update(ExpeditionProduit t) {
        ExpeditionProduit loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ExpeditionProduit.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ExpeditionProduit findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ExpeditionProduit findOrSave(ExpeditionProduit t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ExpeditionProduit result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ExpeditionProduit> findAll() {
        return dao.findAll();
    }

    public List<ExpeditionProduit> findByCriteria(ExpeditionProduitCriteria criteria) {
        List<ExpeditionProduit> content = null;
        if (criteria != null) {
            ExpeditionProduitSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ExpeditionProduitSpecification constructSpecification(ExpeditionProduitCriteria criteria) {
        ExpeditionProduitSpecification mySpecification =  (ExpeditionProduitSpecification) RefelexivityUtil.constructObjectUsingOneParam(ExpeditionProduitSpecification.class, criteria);
        return mySpecification;
    }

    public List<ExpeditionProduit> findPaginatedByCriteria(ExpeditionProduitCriteria criteria, int page, int pageSize, String order, String sortField) {
        ExpeditionProduitSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ExpeditionProduitCriteria criteria) {
        ExpeditionProduitSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ExpeditionProduit> findByAnalyseChimiqueId(Long id){
        return dao.findByAnalyseChimiqueId(id);
    }
    public int deleteByAnalyseChimiqueId(Long id){
        return dao.deleteByAnalyseChimiqueId(id);
    }
    public long countByAnalyseChimiqueCode(String code){
        return dao.countByAnalyseChimiqueCode(code);
    }
    public List<ExpeditionProduit> findByCharteChimiqueId(Long id){
        return dao.findByCharteChimiqueId(id);
    }
    public int deleteByCharteChimiqueId(Long id){
        return dao.deleteByCharteChimiqueId(id);
    }
    public long countByCharteChimiqueCode(String code){
        return dao.countByCharteChimiqueCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }
    @Override
    public void deleteByExpeditionId(Long id) {
        dao.deleteByExpeditionId(id);
    }

    @Override
    public List<ExpeditionProduit> findByExpeditionId(Long id) {
        return dao.findByExpeditionId(id);
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ExpeditionProduit> delete(List<ExpeditionProduit> list) {
		List<ExpeditionProduit> result = new ArrayList();
        if (list != null) {
            for (ExpeditionProduit t : list) {
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
    public ExpeditionProduit create(ExpeditionProduit t) {
        ExpeditionProduit loaded = findByReferenceEntity(t);
        ExpeditionProduit saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ExpeditionProduit findWithAssociatedLists(Long id){
        ExpeditionProduit result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ExpeditionProduit> update(List<ExpeditionProduit> ts, boolean createIfNotExist) {
        List<ExpeditionProduit> result = new ArrayList<>();
        if (ts != null) {
            for (ExpeditionProduit t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ExpeditionProduit loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ExpeditionProduit t, ExpeditionProduit loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ExpeditionProduit findByReferenceEntity(ExpeditionProduit t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(ExpeditionProduit t){
        if( t != null) {
            t.setAnalyseChimique(analyseChimiqueService.findOrSave(t.getAnalyseChimique()));
            t.setCharteChimique(charteChimiqueService.findOrSave(t.getCharteChimique()));
        }
    }



    public List<ExpeditionProduit> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ExpeditionProduit>> getToBeSavedAndToBeDeleted(List<ExpeditionProduit> oldList, List<ExpeditionProduit> newList) {
        List<List<ExpeditionProduit>> result = new ArrayList<>();
        List<ExpeditionProduit> resultDelete = new ArrayList<>();
        List<ExpeditionProduit> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ExpeditionProduit> oldList, List<ExpeditionProduit> newList, List<ExpeditionProduit> resultUpdateOrSave, List<ExpeditionProduit> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ExpeditionProduit myOld = oldList.get(i);
                ExpeditionProduit t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ExpeditionProduit myNew = newList.get(i);
                ExpeditionProduit t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private AnalyseChimiqueAdminService analyseChimiqueService ;
    @Autowired
    private CharteChimiqueAdminService charteChimiqueService ;

    public ExpeditionProduitAdminServiceImpl(ExpeditionProduitDao dao) {
        this.dao = dao;
    }

    private ExpeditionProduitDao dao;
}
