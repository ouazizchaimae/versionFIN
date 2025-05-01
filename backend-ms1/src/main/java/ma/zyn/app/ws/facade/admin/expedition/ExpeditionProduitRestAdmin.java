package  ma.zyn.app.ws.facade.admin.expedition;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.expedition.ExpeditionProduit;
import ma.zyn.app.dao.criteria.core.expedition.ExpeditionProduitCriteria;
import ma.zyn.app.service.facade.admin.expedition.ExpeditionProduitAdminService;
import ma.zyn.app.ws.converter.expedition.ExpeditionProduitConverter;
import ma.zyn.app.ws.dto.expedition.ExpeditionProduitDto;
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
@RequestMapping("/api/admin/expeditionProduit/")
public class ExpeditionProduitRestAdmin {




    @Operation(summary = "Finds a list of all expeditionProduits")
    @GetMapping("")
    public ResponseEntity<List<ExpeditionProduitDto>> findAll() throws Exception {
        ResponseEntity<List<ExpeditionProduitDto>> res = null;
        List<ExpeditionProduit> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ExpeditionProduitDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all expeditionProduits")
    @GetMapping("optimized")
    public ResponseEntity<List<ExpeditionProduitDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ExpeditionProduitDto>> res = null;
        List<ExpeditionProduit> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ExpeditionProduitDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a expeditionProduit by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ExpeditionProduitDto> findById(@PathVariable Long id) {
        ExpeditionProduit t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ExpeditionProduitDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a expeditionProduit by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ExpeditionProduitDto> findByLibelle(@PathVariable String libelle) {
	    ExpeditionProduit t = service.findByReferenceEntity(new ExpeditionProduit(libelle));
        if (t != null) {
            converter.init(true);
            ExpeditionProduitDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  expeditionProduit")
    @PostMapping("")
    public ResponseEntity<ExpeditionProduitDto> save(@RequestBody ExpeditionProduitDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ExpeditionProduit myT = converter.toItem(dto);
            ExpeditionProduit t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ExpeditionProduitDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  expeditionProduit")
    @PutMapping("")
    public ResponseEntity<ExpeditionProduitDto> update(@RequestBody ExpeditionProduitDto dto) throws Exception {
        ResponseEntity<ExpeditionProduitDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ExpeditionProduit t = service.findById(dto.getId());
            converter.copy(dto,t);
            ExpeditionProduit updated = service.update(t);
            ExpeditionProduitDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of expeditionProduit")
    @PostMapping("multiple")
    public ResponseEntity<List<ExpeditionProduitDto>> delete(@RequestBody List<ExpeditionProduitDto> dtos) throws Exception {
        ResponseEntity<List<ExpeditionProduitDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ExpeditionProduit> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified expeditionProduit")
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


    @Operation(summary = "Finds a expeditionProduit and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ExpeditionProduitDto> findWithAssociatedLists(@PathVariable Long id) {
        ExpeditionProduit loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ExpeditionProduitDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds expeditionProduits by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ExpeditionProduitDto>> findByCriteria(@RequestBody ExpeditionProduitCriteria criteria) throws Exception {
        ResponseEntity<List<ExpeditionProduitDto>> res = null;
        List<ExpeditionProduit> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ExpeditionProduitDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated expeditionProduits by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ExpeditionProduitCriteria criteria) throws Exception {
        List<ExpeditionProduit> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ExpeditionProduitDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets expeditionProduit data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ExpeditionProduitCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ExpeditionProduitDto> findDtos(List<ExpeditionProduit> list){
        converter.initObject(true);
        List<ExpeditionProduitDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ExpeditionProduitDto> getDtoResponseEntity(ExpeditionProduitDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ExpeditionProduitRestAdmin(ExpeditionProduitAdminService service, ExpeditionProduitConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ExpeditionProduitAdminService service;
    private final ExpeditionProduitConverter converter;





}
