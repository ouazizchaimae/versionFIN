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

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.dao.criteria.core.referentiel.StadeOperatoireCriteria;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService;
import ma.zyn.app.ws.converter.referentiel.StadeOperatoireConverter;
import ma.zyn.app.ws.dto.referentiel.StadeOperatoireDto;
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
@RequestMapping("/api/admin/stadeOperatoire/")
public class StadeOperatoireRestAdmin {




    @Operation(summary = "Finds a list of all stadeOperatoires")
    @GetMapping("")
    public ResponseEntity<List<StadeOperatoireDto>> findAll() throws Exception {
        ResponseEntity<List<StadeOperatoireDto>> res = null;
        List<StadeOperatoire> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<StadeOperatoireDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all stadeOperatoires")
    @GetMapping("optimized")
    public ResponseEntity<List<StadeOperatoireDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<StadeOperatoireDto>> res = null;
        List<StadeOperatoire> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<StadeOperatoireDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a stadeOperatoire by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StadeOperatoireDto> findById(@PathVariable Long id) {
        StadeOperatoire t = service.findById(id);
        if (t != null) {
            converter.init(true);
            StadeOperatoireDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a stadeOperatoire by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<StadeOperatoireDto> findByLibelle(@PathVariable String libelle) {
	    StadeOperatoire t = service.findByReferenceEntity(new StadeOperatoire(libelle));
        if (t != null) {
            converter.init(true);
            StadeOperatoireDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  stadeOperatoire")
    @PostMapping("")
    public ResponseEntity<StadeOperatoireDto> save(@RequestBody StadeOperatoireDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            StadeOperatoire myT = converter.toItem(dto);
            StadeOperatoire t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StadeOperatoireDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  stadeOperatoire")
    @PutMapping("")
    public ResponseEntity<StadeOperatoireDto> update(@RequestBody StadeOperatoireDto dto) throws Exception {
        ResponseEntity<StadeOperatoireDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StadeOperatoire t = service.findById(dto.getId());
            converter.copy(dto,t);
            StadeOperatoire updated = service.update(t);
            StadeOperatoireDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of stadeOperatoire")
    @PostMapping("multiple")
    public ResponseEntity<List<StadeOperatoireDto>> delete(@RequestBody List<StadeOperatoireDto> dtos) throws Exception {
        ResponseEntity<List<StadeOperatoireDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<StadeOperatoire> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified stadeOperatoire")
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

    @Operation(summary = "find by entite code")
    @GetMapping("entite/code/{code}")
    public List<StadeOperatoireDto> findByEntiteCode(@PathVariable String code){
        return findDtos(service.findByEntiteCode(code));
    }
    @Operation(summary = "delete by entite code")
    @DeleteMapping("entite/code/{code}")
    public int deleteByEntiteCode(@PathVariable String code){
        return service.deleteByEntiteCode(code);
    }

    @Operation(summary = "Finds a stadeOperatoire and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StadeOperatoireDto> findWithAssociatedLists(@PathVariable Long id) {
        StadeOperatoire loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        StadeOperatoireDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds stadeOperatoires by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StadeOperatoireDto>> findByCriteria(@RequestBody StadeOperatoireCriteria criteria) throws Exception {
        ResponseEntity<List<StadeOperatoireDto>> res = null;
        List<StadeOperatoire> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<StadeOperatoireDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated stadeOperatoires by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StadeOperatoireCriteria criteria) throws Exception {
        List<StadeOperatoire> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<StadeOperatoireDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets stadeOperatoire data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StadeOperatoireCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StadeOperatoireDto> findDtos(List<StadeOperatoire> list){
        converter.initList(false);
        converter.initObject(true);
        List<StadeOperatoireDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StadeOperatoireDto> getDtoResponseEntity(StadeOperatoireDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public StadeOperatoireRestAdmin(StadeOperatoireAdminService service, StadeOperatoireConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final StadeOperatoireAdminService service;
    private final StadeOperatoireConverter converter;





}
