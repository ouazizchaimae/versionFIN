package ma.zyn.app.service.impl.admin.expedition;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.expedition.Expedition;
import ma.zyn.app.dao.criteria.core.expedition.ExpeditionCriteria;
import ma.zyn.app.dao.facade.core.expedition.ExpeditionDao;
import ma.zyn.app.dao.specification.core.expedition.ExpeditionSpecification;
import ma.zyn.app.service.facade.admin.expedition.ExpeditionAdminService;
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

import ma.zyn.app.service.facade.admin.expedition.ExpeditionProduitAdminService ;
import ma.zyn.app.bean.core.expedition.ExpeditionProduit ;
import ma.zyn.app.service.facade.admin.expedition.TypeExpeditionAdminService ;
import ma.zyn.app.bean.core.expedition.TypeExpedition ;
import ma.zyn.app.service.facade.admin.referentiel.ClientAdminService ;
import ma.zyn.app.bean.core.referentiel.Client ;

import java.util.List;
@Service
public class ExpeditionAdminServiceImpl implements ExpeditionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Expedition update(Expedition t) {
        Expedition loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Expedition.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Expedition findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Expedition findOrSave(Expedition t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Expedition result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Expedition> findAll() {
        return dao.findAll();
    }

    public List<Expedition> findByCriteria(ExpeditionCriteria criteria) {
        List<Expedition> content = null;
        if (criteria != null) {
            ExpeditionSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ExpeditionSpecification constructSpecification(ExpeditionCriteria criteria) {
        ExpeditionSpecification mySpecification =  (ExpeditionSpecification) RefelexivityUtil.constructObjectUsingOneParam(ExpeditionSpecification.class, criteria);
        return mySpecification;
    }

    public List<Expedition> findPaginatedByCriteria(ExpeditionCriteria criteria, int page, int pageSize, String order, String sortField) {
        ExpeditionSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ExpeditionCriteria criteria) {
        ExpeditionSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Expedition> findByClientId(Long id){
        return dao.findByClientId(id);
    }
    public int deleteByClientId(Long id){
        return dao.deleteByClientId(id);
    }
    public long countByClientCode(String code){
        return dao.countByClientCode(code);
    }
    public List<Expedition> findByTypeExpeditionCode(String code){
        return dao.findByTypeExpeditionCode(code);
    }
    public List<Expedition> findByTypeExpeditionId(Long id){
        return dao.findByTypeExpeditionId(id);
    }
    public int deleteByTypeExpeditionCode(String code){
        return dao.deleteByTypeExpeditionCode(code);
    }
    public int deleteByTypeExpeditionId(Long id){
        return dao.deleteByTypeExpeditionId(id);
    }
    public long countByTypeExpeditionCode(String code){
        return dao.countByTypeExpeditionCode(code);
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
        expeditionProduitService.deleteByExpeditionId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Expedition> delete(List<Expedition> list) {
		List<Expedition> result = new ArrayList();
        if (list != null) {
            for (Expedition t : list) {
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
    public Expedition create(Expedition t) {
        Expedition loaded = findByReferenceEntity(t);
        Expedition saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getExpeditionProduits() != null) {
                t.getExpeditionProduits().forEach(element-> {
                    element.setExpedition(saved);
                    expeditionProduitService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Expedition findWithAssociatedLists(Long id){
        Expedition result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setExpeditionProduits(expeditionProduitService.findByExpeditionId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Expedition> update(List<Expedition> ts, boolean createIfNotExist) {
        List<Expedition> result = new ArrayList<>();
        if (ts != null) {
            for (Expedition t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Expedition loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Expedition t, Expedition loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Expedition expedition){
    if(expedition !=null && expedition.getId() != null){
        List<List<ExpeditionProduit>> resultExpeditionProduits= expeditionProduitService.getToBeSavedAndToBeDeleted(expeditionProduitService.findByExpeditionId(expedition.getId()),expedition.getExpeditionProduits());
            expeditionProduitService.delete(resultExpeditionProduits.get(1));
        emptyIfNull(resultExpeditionProduits.get(0)).forEach(e -> e.setExpedition(expedition));
        expeditionProduitService.update(resultExpeditionProduits.get(0),true);
        }
    }








    public Expedition findByReferenceEntity(Expedition t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Expedition t){
        if( t != null) {
            t.setClient(clientService.findOrSave(t.getClient()));
            t.setTypeExpedition(typeExpeditionService.findOrSave(t.getTypeExpedition()));
        }
    }



    public List<Expedition> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Expedition>> getToBeSavedAndToBeDeleted(List<Expedition> oldList, List<Expedition> newList) {
        List<List<Expedition>> result = new ArrayList<>();
        List<Expedition> resultDelete = new ArrayList<>();
        List<Expedition> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Expedition> oldList, List<Expedition> newList, List<Expedition> resultUpdateOrSave, List<Expedition> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Expedition myOld = oldList.get(i);
                Expedition t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Expedition myNew = newList.get(i);
                Expedition t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ExpeditionProduitAdminService expeditionProduitService ;
    @Autowired
    private TypeExpeditionAdminService typeExpeditionService ;
    @Autowired
    private ClientAdminService clientService ;

    public ExpeditionAdminServiceImpl(ExpeditionDao dao) {
        this.dao = dao;
    }

    private ExpeditionDao dao;
}
