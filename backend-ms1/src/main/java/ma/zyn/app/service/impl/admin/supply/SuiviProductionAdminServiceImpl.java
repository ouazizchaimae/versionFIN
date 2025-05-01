package ma.zyn.app.service.impl.admin.supply;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.supply.SuiviProduction;
import ma.zyn.app.dao.criteria.core.supply.SuiviProductionCriteria;
import ma.zyn.app.dao.facade.core.supply.SuiviProductionDao;
import ma.zyn.app.dao.specification.core.supply.SuiviProductionSpecification;
import ma.zyn.app.service.facade.admin.supply.SuiviProductionAdminService;
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
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService ;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitAdminService ;
import ma.zyn.app.bean.core.referentiel.Produit ;

import java.util.List;
@Service
public class SuiviProductionAdminServiceImpl implements SuiviProductionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public SuiviProduction update(SuiviProduction t) {
        SuiviProduction loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{SuiviProduction.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public SuiviProduction findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public SuiviProduction findOrSave(SuiviProduction t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            SuiviProduction result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<SuiviProduction> findAll() {
        return dao.findAll();
    }

    public List<SuiviProduction> findByCriteria(SuiviProductionCriteria criteria) {
        List<SuiviProduction> content = null;
        if (criteria != null) {
            SuiviProductionSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private SuiviProductionSpecification constructSpecification(SuiviProductionCriteria criteria) {
        SuiviProductionSpecification mySpecification =  (SuiviProductionSpecification) RefelexivityUtil.constructObjectUsingOneParam(SuiviProductionSpecification.class, criteria);
        return mySpecification;
    }

    public List<SuiviProduction> findPaginatedByCriteria(SuiviProductionCriteria criteria, int page, int pageSize, String order, String sortField) {
        SuiviProductionSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SuiviProductionCriteria criteria) {
        SuiviProductionSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<SuiviProduction> findByProduitId(Long id){
        return dao.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return dao.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return dao.countByProduitCode(code);
    }
    public List<SuiviProduction> findByStadeOperatoireCode(String code){
        return dao.findByStadeOperatoireCode(code);
    }
    public List<SuiviProduction> findByStadeOperatoireId(Long id){
        return dao.findByStadeOperatoireId(id);
    }
    public int deleteByStadeOperatoireCode(String code){
        return dao.deleteByStadeOperatoireCode(code);
    }
    public int deleteByStadeOperatoireId(Long id){
        return dao.deleteByStadeOperatoireId(id);
    }
    public long countByStadeOperatoireCode(String code){
        return dao.countByStadeOperatoireCode(code);
    }
    public List<SuiviProduction> findByUniteCode(String code){
        return dao.findByUniteCode(code);
    }
    public List<SuiviProduction> findByUniteId(Long id){
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
    public List<SuiviProduction> delete(List<SuiviProduction> list) {
		List<SuiviProduction> result = new ArrayList();
        if (list != null) {
            for (SuiviProduction t : list) {
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
    public SuiviProduction create(SuiviProduction t) {
        SuiviProduction loaded = findByReferenceEntity(t);
        SuiviProduction saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public SuiviProduction findWithAssociatedLists(Long id){
        SuiviProduction result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SuiviProduction> update(List<SuiviProduction> ts, boolean createIfNotExist) {
        List<SuiviProduction> result = new ArrayList<>();
        if (ts != null) {
            for (SuiviProduction t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    SuiviProduction loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, SuiviProduction t, SuiviProduction loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public SuiviProduction findByReferenceEntity(SuiviProduction t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(SuiviProduction t){
        if( t != null) {
            t.setProduit(produitService.findOrSave(t.getProduit()));
            t.setStadeOperatoire(stadeOperatoireService.findOrSave(t.getStadeOperatoire()));
            t.setUnite(uniteService.findOrSave(t.getUnite()));
        }
    }



    public List<SuiviProduction> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<SuiviProduction>> getToBeSavedAndToBeDeleted(List<SuiviProduction> oldList, List<SuiviProduction> newList) {
        List<List<SuiviProduction>> result = new ArrayList<>();
        List<SuiviProduction> resultDelete = new ArrayList<>();
        List<SuiviProduction> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<SuiviProduction> oldList, List<SuiviProduction> newList, List<SuiviProduction> resultUpdateOrSave, List<SuiviProduction> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                SuiviProduction myOld = oldList.get(i);
                SuiviProduction t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                SuiviProduction myNew = newList.get(i);
                SuiviProduction t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    @Autowired
    private StadeOperatoireAdminService stadeOperatoireService ;
    @Autowired
    private ProduitAdminService produitService ;

    public SuiviProductionAdminServiceImpl(SuiviProductionDao dao) {
        this.dao = dao;
    }

    private SuiviProductionDao dao;
}
