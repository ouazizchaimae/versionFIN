package ma.zyn.app.service.impl.admin.expedition;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail;
import ma.zyn.app.dao.criteria.core.expedition.CharteChimiqueDetailCriteria;
import ma.zyn.app.dao.facade.core.expedition.CharteChimiqueDetailDao;
import ma.zyn.app.dao.specification.core.expedition.CharteChimiqueDetailSpecification;
import ma.zyn.app.service.facade.admin.expedition.CharteChimiqueDetailAdminService;
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
import ma.zyn.app.service.facade.admin.expedition.CharteChimiqueAdminService ;
import ma.zyn.app.bean.core.expedition.CharteChimique ;

import java.util.List;
@Service
public class CharteChimiqueDetailAdminServiceImpl implements CharteChimiqueDetailAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CharteChimiqueDetail update(CharteChimiqueDetail t) {
        CharteChimiqueDetail loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CharteChimiqueDetail.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CharteChimiqueDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CharteChimiqueDetail findOrSave(CharteChimiqueDetail t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            CharteChimiqueDetail result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CharteChimiqueDetail> findAll() {
        return dao.findAll();
    }

    public List<CharteChimiqueDetail> findByCriteria(CharteChimiqueDetailCriteria criteria) {
        List<CharteChimiqueDetail> content = null;
        if (criteria != null) {
            CharteChimiqueDetailSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CharteChimiqueDetailSpecification constructSpecification(CharteChimiqueDetailCriteria criteria) {
        CharteChimiqueDetailSpecification mySpecification =  (CharteChimiqueDetailSpecification) RefelexivityUtil.constructObjectUsingOneParam(CharteChimiqueDetailSpecification.class, criteria);
        return mySpecification;
    }

    public List<CharteChimiqueDetail> findPaginatedByCriteria(CharteChimiqueDetailCriteria criteria, int page, int pageSize, String order, String sortField) {
        CharteChimiqueDetailSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CharteChimiqueDetailCriteria criteria) {
        CharteChimiqueDetailSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<CharteChimiqueDetail> findByElementChimiqueCode(String code){
        return dao.findByElementChimiqueCode(code);
    }
    public List<CharteChimiqueDetail> findByElementChimiqueId(Long id){
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
    public List<CharteChimiqueDetail> findByCharteChimiqueId(Long id){
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




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CharteChimiqueDetail> delete(List<CharteChimiqueDetail> list) {
		List<CharteChimiqueDetail> result = new ArrayList();
        if (list != null) {
            for (CharteChimiqueDetail t : list) {
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
    public CharteChimiqueDetail create(CharteChimiqueDetail t) {
        CharteChimiqueDetail loaded = findByReferenceEntity(t);
        CharteChimiqueDetail saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CharteChimiqueDetail findWithAssociatedLists(Long id){
        CharteChimiqueDetail result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CharteChimiqueDetail> update(List<CharteChimiqueDetail> ts, boolean createIfNotExist) {
        List<CharteChimiqueDetail> result = new ArrayList<>();
        if (ts != null) {
            for (CharteChimiqueDetail t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CharteChimiqueDetail loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CharteChimiqueDetail t, CharteChimiqueDetail loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CharteChimiqueDetail findByReferenceEntity(CharteChimiqueDetail t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(CharteChimiqueDetail t){
        if( t != null) {
            t.setElementChimique(elementChimiqueService.findOrSave(t.getElementChimique()));
            t.setCharteChimique(charteChimiqueService.findOrSave(t.getCharteChimique()));
        }
    }



    public List<CharteChimiqueDetail> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CharteChimiqueDetail>> getToBeSavedAndToBeDeleted(List<CharteChimiqueDetail> oldList, List<CharteChimiqueDetail> newList) {
        List<List<CharteChimiqueDetail>> result = new ArrayList<>();
        List<CharteChimiqueDetail> resultDelete = new ArrayList<>();
        List<CharteChimiqueDetail> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CharteChimiqueDetail> oldList, List<CharteChimiqueDetail> newList, List<CharteChimiqueDetail> resultUpdateOrSave, List<CharteChimiqueDetail> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CharteChimiqueDetail myOld = oldList.get(i);
                CharteChimiqueDetail t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CharteChimiqueDetail myNew = newList.get(i);
                CharteChimiqueDetail t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CharteChimiqueAdminService charteChimiqueService ;

    public CharteChimiqueDetailAdminServiceImpl(CharteChimiqueDetailDao dao) {
        this.dao = dao;
    }

    private CharteChimiqueDetailDao dao;
}
