package  ma.zyn.app.ws.facade.admin.referentiel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit;
import ma.zyn.app.dao.criteria.core.referentiel.StadeOperatoireProduitCriteria;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireProduitAdminService;
import ma.zyn.app.ws.converter.referentiel.StadeOperatoireProduitConverter;
import ma.zyn.app.ws.dto.referentiel.StadeOperatoireProduitDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/stadeOperatoireProduit/")
public class StadeOperatoireProduitRestAdmin {




    @Operation(summary = "Finds a list of all stadeOperatoireProduits")
    @GetMapping("")
    public ResponseEntity<List<StadeOperatoireProduitDto>> findAll() throws Exception {
        ResponseEntity<List<StadeOperatoireProduitDto>> res = null;
        List<StadeOperatoireProduit> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<StadeOperatoireProduitDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a stadeOperatoireProduit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StadeOperatoireProduitDto> findById(@PathVariable Long id) {
        StadeOperatoireProduit t = service.findById(id);
        if (t != null) {
            converter.init(true);
            StadeOperatoireProduitDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  stadeOperatoireProduit")
    @PostMapping("")
    public ResponseEntity<StadeOperatoireProduitDto> save(@RequestBody StadeOperatoireProduitDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            StadeOperatoireProduit myT = converter.toItem(dto);
            StadeOperatoireProduit t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StadeOperatoireProduitDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  stadeOperatoireProduit")
    @PutMapping("")
    public ResponseEntity<StadeOperatoireProduitDto> update(@RequestBody StadeOperatoireProduitDto dto) throws Exception {
        ResponseEntity<StadeOperatoireProduitDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StadeOperatoireProduit t = service.findById(dto.getId());
            converter.copy(dto,t);
            StadeOperatoireProduit updated = service.update(t);
            StadeOperatoireProduitDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of stadeOperatoireProduit")
    @PostMapping("multiple")
    public ResponseEntity<List<StadeOperatoireProduitDto>> delete(@RequestBody List<StadeOperatoireProduitDto> dtos) throws Exception {
        ResponseEntity<List<StadeOperatoireProduitDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<StadeOperatoireProduit> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified stadeOperatoireProduit")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by produit id")
    @GetMapping("produit/id/{id}")
    public List<StadeOperatoireProduitDto> findByProduitId(@PathVariable Long id){
        return findDtos(service.findByProduitId(id));
    }
    @Operation(summary = "delete by produit id")
    @DeleteMapping("produit/id/{id}")
    public int deleteByProduitId(@PathVariable Long id){
        return service.deleteByProduitId(id);
    }

    @Operation(summary = "Finds a stadeOperatoireProduit and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StadeOperatoireProduitDto> findWithAssociatedLists(@PathVariable Long id) {
        StadeOperatoireProduit loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        StadeOperatoireProduitDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds stadeOperatoireProduits by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StadeOperatoireProduitDto>> findByCriteria(@RequestBody StadeOperatoireProduitCriteria criteria) throws Exception {
        ResponseEntity<List<StadeOperatoireProduitDto>> res = null;
        List<StadeOperatoireProduit> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<StadeOperatoireProduitDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated stadeOperatoireProduits by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StadeOperatoireProduitCriteria criteria) throws Exception {
        List<StadeOperatoireProduit> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<StadeOperatoireProduitDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets stadeOperatoireProduit data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StadeOperatoireProduitCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StadeOperatoireProduitDto> findDtos(List<StadeOperatoireProduit> list){
        converter.initObject(true);
        List<StadeOperatoireProduitDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StadeOperatoireProduitDto> getDtoResponseEntity(StadeOperatoireProduitDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public StadeOperatoireProduitRestAdmin(StadeOperatoireProduitAdminService service, StadeOperatoireProduitConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final StadeOperatoireProduitAdminService service;
    private final StadeOperatoireProduitConverter converter;





}
