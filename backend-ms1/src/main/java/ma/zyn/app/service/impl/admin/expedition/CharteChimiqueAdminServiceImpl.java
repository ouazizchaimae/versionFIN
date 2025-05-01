package ma.zyn.app.service.impl.admin.expedition;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.expedition.CharteChimique;
import ma.zyn.app.dao.criteria.core.expedition.CharteChimiqueCriteria;
import ma.zyn.app.dao.facade.core.expedition.CharteChimiqueDao;
import ma.zyn.app.dao.specification.core.expedition.CharteChimiqueSpecification;
import ma.zyn.app.service.facade.admin.expedition.CharteChimiqueAdminService;
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

import ma.zyn.app.service.facade.admin.expedition.CharteChimiqueDetailAdminService ;
import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail ;
import ma.zyn.app.service.facade.admin.referentiel.ProduitMarchandAdminService ;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand ;
import ma.zyn.app.service.facade.admin.referentiel.ClientAdminService ;
import ma.zyn.app.bean.core.referentiel.Client ;

import java.util.List;
@Service
public class CharteChimiqueAdminServiceImpl implements CharteChimiqueAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CharteChimique update(CharteChimique t) {
        CharteChimique loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CharteChimique.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public CharteChimique findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CharteChimique findOrSave(CharteChimique t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            CharteChimique result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CharteChimique> findAll() {
        return dao.findAll();
    }

    public List<CharteChimique> findByCriteria(CharteChimiqueCriteria criteria) {
        List<CharteChimique> content = null;
        if (criteria != null) {
            CharteChimiqueSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CharteChimiqueSpecification constructSpecification(CharteChimiqueCriteria criteria) {
        CharteChimiqueSpecification mySpecification =  (CharteChimiqueSpecification) RefelexivityUtil.constructObjectUsingOneParam(CharteChimiqueSpecification.class, criteria);
        return mySpecification;
    }

    public List<CharteChimique> findPaginatedByCriteria(CharteChimiqueCriteria criteria, int page, int pageSize, String order, String sortField) {
        CharteChimiqueSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CharteChimiqueCriteria criteria) {
        CharteChimiqueSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<CharteChimique> findByClientId(Long id){
        return dao.findByClientId(id);
    }
    public int deleteByClientId(Long id){
        return dao.deleteByClientId(id);
    }
    public long countByClientCode(String code){
        return dao.countByClientCode(code);
    }
    public List<CharteChimique> findByProduitMarchandCode(String code){
        return dao.findByProduitMarchandCode(code);
    }
    public List<CharteChimique> findByProduitMarchandId(Long id){
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
        charteChimiqueDetailService.deleteByCharteChimiqueId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CharteChimique> delete(List<CharteChimique> list) {
		List<CharteChimique> result = new ArrayList();
        if (list != null) {
            for (CharteChimique t : list) {
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
    public CharteChimique create(CharteChimique t) {
        CharteChimique loaded = findByReferenceEntity(t);
        CharteChimique saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getCharteChimiqueDetails() != null) {
                t.getCharteChimiqueDetails().forEach(element-> {
                    element.setCharteChimique(saved);
                    charteChimiqueDetailService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public CharteChimique findWithAssociatedLists(Long id){
        CharteChimique result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setCharteChimiqueDetails(charteChimiqueDetailService.findByCharteChimiqueId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CharteChimique> update(List<CharteChimique> ts, boolean createIfNotExist) {
        List<CharteChimique> result = new ArrayList<>();
        if (ts != null) {
            for (CharteChimique t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CharteChimique loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CharteChimique t, CharteChimique loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(CharteChimique charteChimique){
    if(charteChimique !=null && charteChimique.getId() != null){
        List<List<CharteChimiqueDetail>> resultCharteChimiqueDetails= charteChimiqueDetailService.getToBeSavedAndToBeDeleted(charteChimiqueDetailService.findByCharteChimiqueId(charteChimique.getId()),charteChimique.getCharteChimiqueDetails());
            charteChimiqueDetailService.delete(resultCharteChimiqueDetails.get(1));
        emptyIfNull(resultCharteChimiqueDetails.get(0)).forEach(e -> e.setCharteChimique(charteChimique));
        charteChimiqueDetailService.update(resultCharteChimiqueDetails.get(0),true);
        }
    }








    public CharteChimique findByReferenceEntity(CharteChimique t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(CharteChimique t){
        if( t != null) {
            t.setClient(clientService.findOrSave(t.getClient()));
            t.setProduitMarchand(produitMarchandService.findOrSave(t.getProduitMarchand()));
        }
    }



    public List<CharteChimique> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CharteChimique>> getToBeSavedAndToBeDeleted(List<CharteChimique> oldList, List<CharteChimique> newList) {
        List<List<CharteChimique>> result = new ArrayList<>();
        List<CharteChimique> resultDelete = new ArrayList<>();
        List<CharteChimique> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CharteChimique> oldList, List<CharteChimique> newList, List<CharteChimique> resultUpdateOrSave, List<CharteChimique> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CharteChimique myOld = oldList.get(i);
                CharteChimique t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CharteChimique myNew = newList.get(i);
                CharteChimique t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CharteChimiqueDetailAdminService charteChimiqueDetailService ;
    @Autowired
    private ProduitMarchandAdminService produitMarchandService ;
    @Autowired
    private ClientAdminService clientService ;

    public CharteChimiqueAdminServiceImpl(CharteChimiqueDao dao) {
        this.dao = dao;
    }

    private CharteChimiqueDao dao;
}
