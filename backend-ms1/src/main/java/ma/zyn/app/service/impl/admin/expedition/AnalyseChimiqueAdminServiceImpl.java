package ma.zyn.app.service.impl.admin.expedition;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import ma.zyn.app.dao.criteria.core.expedition.AnalyseChimiqueCriteria;
import ma.zyn.app.dao.facade.core.expedition.AnalyseChimiqueDao;
import ma.zyn.app.dao.specification.core.expedition.AnalyseChimiqueSpecification;
import ma.zyn.app.service.facade.admin.expedition.AnalyseChimiqueAdminService;
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

import ma.zyn.app.service.facade.admin.expedition.AnalyseChimiqueDetailAdminService ;
import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitMarchandAdminService ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;

import java.util.List;
@Service
public class AnalyseChimiqueAdminServiceImpl implements AnalyseChimiqueAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AnalyseChimique update(AnalyseChimique t) {
        AnalyseChimique loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{AnalyseChimique.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public AnalyseChimique findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public AnalyseChimique findOrSave(AnalyseChimique t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            AnalyseChimique result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<AnalyseChimique> findAll() {
        return dao.findAll();
    }

    public List<AnalyseChimique> findByCriteria(AnalyseChimiqueCriteria criteria) {
        List<AnalyseChimique> content = null;
        if (criteria != null) {
            AnalyseChimiqueSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private AnalyseChimiqueSpecification constructSpecification(AnalyseChimiqueCriteria criteria) {
        AnalyseChimiqueSpecification mySpecification =  (AnalyseChimiqueSpecification) RefelexivityUtil.constructObjectUsingOneParam(AnalyseChimiqueSpecification.class, criteria);
        return mySpecification;
    }

    public List<AnalyseChimique> findPaginatedByCriteria(AnalyseChimiqueCriteria criteria, int page, int pageSize, String order, String sortField) {
        AnalyseChimiqueSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AnalyseChimiqueCriteria criteria) {
        AnalyseChimiqueSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<AnalyseChimique> findByProduitMarchandCode(String code){
        return dao.findByProduitMarchandCode(code);
    }
    public List<AnalyseChimique> findByProduitMarchandId(Long id){
        return dao.findByProduitMarchandId(id);
    }
    public int deleteByProduitMarchandCode(String code){
        return dao.deleteByProduitMarchandCode(code);
    }
    public int deleteByProduitMarchandId(Long id){
        return dao.deleteByProduitMarchandId(id);
    }
    public long countByProduitMarchandCode(String code){
        return dao.countByProduitMarchandCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        analyseChimiqueDetailService.deleteByAnalyseChimiqueId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AnalyseChimique> delete(List<AnalyseChimique> list) {
		List<AnalyseChimique> result = new ArrayList();
        if (list != null) {
            for (AnalyseChimique t : list) {
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
    public AnalyseChimique create(AnalyseChimique t) {
        AnalyseChimique loaded = findByReferenceEntity(t);
        AnalyseChimique saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getAnalyseChimiqueDetails() != null) {
                t.getAnalyseChimiqueDetails().forEach(element-> {
                    element.setAnalyseChimique(saved);
                    analyseChimiqueDetailService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public AnalyseChimique findWithAssociatedLists(Long id){
        AnalyseChimique result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setAnalyseChimiqueDetails(analyseChimiqueDetailService.findByAnalyseChimiqueId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AnalyseChimique> update(List<AnalyseChimique> ts, boolean createIfNotExist) {
        List<AnalyseChimique> result = new ArrayList<>();
        if (ts != null) {
            for (AnalyseChimique t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    AnalyseChimique loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, AnalyseChimique t, AnalyseChimique loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(AnalyseChimique analyseChimique){
    if(analyseChimique !=null && analyseChimique.getId() != null){
        List<List<AnalyseChimiqueDetail>> resultAnalyseChimiqueDetails= analyseChimiqueDetailService.getToBeSavedAndToBeDeleted(analyseChimiqueDetailService.findByAnalyseChimiqueId(analyseChimique.getId()),analyseChimique.getAnalyseChimiqueDetails());
            analyseChimiqueDetailService.delete(resultAnalyseChimiqueDetails.get(1));
        emptyIfNull(resultAnalyseChimiqueDetails.get(0)).forEach(e -> e.setAnalyseChimique(analyseChimique));
        analyseChimiqueDetailService.update(resultAnalyseChimiqueDetails.get(0),true);
        }
    }








    public AnalyseChimique findByReferenceEntity(AnalyseChimique t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(AnalyseChimique t){
        if( t != null) {
            t.setProduitMarchand(produitMarchandService.findOrSave(t.getProduitMarchand()));
        }
    }



    public List<AnalyseChimique> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<AnalyseChimique>> getToBeSavedAndToBeDeleted(List<AnalyseChimique> oldList, List<AnalyseChimique> newList) {
        List<List<AnalyseChimique>> result = new ArrayList<>();
        List<AnalyseChimique> resultDelete = new ArrayList<>();
        List<AnalyseChimique> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<AnalyseChimique> oldList, List<AnalyseChimique> newList, List<AnalyseChimique> resultUpdateOrSave, List<AnalyseChimique> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                AnalyseChimique myOld = oldList.get(i);
                AnalyseChimique t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                AnalyseChimique myNew = newList.get(i);
                AnalyseChimique t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private AnalyseChimiqueDetailAdminService analyseChimiqueDetailService ;
    @Autowired
    private ProduitMarchandAdminService produitMarchandService ;

    public AnalyseChimiqueAdminServiceImpl(AnalyseChimiqueDao dao) {
        this.dao = dao;
    }

    private AnalyseChimiqueDao dao;
}
