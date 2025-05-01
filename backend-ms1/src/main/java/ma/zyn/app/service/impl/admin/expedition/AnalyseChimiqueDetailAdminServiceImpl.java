package ma.zyn.app.service.impl.admin.expedition;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail;
import ma.zyn.app.dao.criteria.core.expedition.AnalyseChimiqueDetailCriteria;
import ma.zyn.app.dao.facade.core.expedition.AnalyseChimiqueDetailDao;
import ma.zyn.app.dao.specification.core.expedition.AnalyseChimiqueDetailSpecification;
import ma.zyn.app.service.facade.admin.expedition.AnalyseChimiqueDetailAdminService;
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

import ma.zyn.app.service.facade.admin.referentiel.ElementChimiqueAdminService ;
import ma.zyn.app.bean.core.referentiel.ElementChimique ;
import ma.zyn.app.service.facade.admin.expedition.AnalyseChimiqueAdminService ;
import ma.zyn.app.bean.core.expedition.AnalyseChimique ;

import java.util.List;
@Service
public class AnalyseChimiqueDetailAdminServiceImpl implements AnalyseChimiqueDetailAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AnalyseChimiqueDetail update(AnalyseChimiqueDetail t) {
        AnalyseChimiqueDetail loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{AnalyseChimiqueDetail.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public AnalyseChimiqueDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public AnalyseChimiqueDetail findOrSave(AnalyseChimiqueDetail t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            AnalyseChimiqueDetail result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<AnalyseChimiqueDetail> findAll() {
        return dao.findAll();
    }

    public List<AnalyseChimiqueDetail> findByCriteria(AnalyseChimiqueDetailCriteria criteria) {
        List<AnalyseChimiqueDetail> content = null;
        if (criteria != null) {
            AnalyseChimiqueDetailSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private AnalyseChimiqueDetailSpecification constructSpecification(AnalyseChimiqueDetailCriteria criteria) {
        AnalyseChimiqueDetailSpecification mySpecification =  (AnalyseChimiqueDetailSpecification) RefelexivityUtil.constructObjectUsingOneParam(AnalyseChimiqueDetailSpecification.class, criteria);
        return mySpecification;
    }

    public List<AnalyseChimiqueDetail> findPaginatedByCriteria(AnalyseChimiqueDetailCriteria criteria, int page, int pageSize, String order, String sortField) {
        AnalyseChimiqueDetailSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AnalyseChimiqueDetailCriteria criteria) {
        AnalyseChimiqueDetailSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<AnalyseChimiqueDetail> findByElementChimiqueCode(String code){
        return dao.findByElementChimiqueCode(code);
    }
    public List<AnalyseChimiqueDetail> findByElementChimiqueId(Long id){
        return dao.findByElementChimiqueId(id);
    }
    public int deleteByElementChimiqueCode(String code){
        return dao.deleteByElementChimiqueCode(code);
    }
    public int deleteByElementChimiqueId(Long id){
        return dao.deleteByElementChimiqueId(id);
    }
    public long countByElementChimiqueCode(String code){
        return dao.countByElementChimiqueCode(code);
    }
    public List<AnalyseChimiqueDetail> findByAnalyseChimiqueId(Long id){
        return dao.findByAnalyseChimiqueId(id);
    }
    public int deleteByAnalyseChimiqueId(Long id){
        return dao.deleteByAnalyseChimiqueId(id);
    }
    public long countByAnalyseChimiqueCode(String code){
        return dao.countByAnalyseChimiqueCode(code);
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
    public List<AnalyseChimiqueDetail> delete(List<AnalyseChimiqueDetail> list) {
		List<AnalyseChimiqueDetail> result = new ArrayList();
        if (list != null) {
            for (AnalyseChimiqueDetail t : list) {
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
    public AnalyseChimiqueDetail create(AnalyseChimiqueDetail t) {
        AnalyseChimiqueDetail loaded = findByReferenceEntity(t);
        AnalyseChimiqueDetail saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public AnalyseChimiqueDetail findWithAssociatedLists(Long id){
        AnalyseChimiqueDetail result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AnalyseChimiqueDetail> update(List<AnalyseChimiqueDetail> ts, boolean createIfNotExist) {
        List<AnalyseChimiqueDetail> result = new ArrayList<>();
        if (ts != null) {
            for (AnalyseChimiqueDetail t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    AnalyseChimiqueDetail loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, AnalyseChimiqueDetail t, AnalyseChimiqueDetail loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public AnalyseChimiqueDetail findByReferenceEntity(AnalyseChimiqueDetail t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(AnalyseChimiqueDetail t){
        if( t != null) {
            t.setElementChimique(elementChimiqueService.findOrSave(t.getElementChimique()));
            t.setAnalyseChimique(analyseChimiqueService.findOrSave(t.getAnalyseChimique()));
        }
    }



    public List<AnalyseChimiqueDetail> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<AnalyseChimiqueDetail>> getToBeSavedAndToBeDeleted(List<AnalyseChimiqueDetail> oldList, List<AnalyseChimiqueDetail> newList) {
        List<List<AnalyseChimiqueDetail>> result = new ArrayList<>();
        List<AnalyseChimiqueDetail> resultDelete = new ArrayList<>();
        List<AnalyseChimiqueDetail> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<AnalyseChimiqueDetail> oldList, List<AnalyseChimiqueDetail> newList, List<AnalyseChimiqueDetail> resultUpdateOrSave, List<AnalyseChimiqueDetail> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                AnalyseChimiqueDetail myOld = oldList.get(i);
                AnalyseChimiqueDetail t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                AnalyseChimiqueDetail myNew = newList.get(i);
                AnalyseChimiqueDetail t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ElementChimiqueAdminService elementChimiqueService ;
    @Autowired
    private AnalyseChimiqueAdminService analyseChimiqueService ;

    public AnalyseChimiqueDetailAdminServiceImpl(AnalyseChimiqueDetailDao dao) {
        this.dao = dao;
    }

    private AnalyseChimiqueDetailDao dao;
}
