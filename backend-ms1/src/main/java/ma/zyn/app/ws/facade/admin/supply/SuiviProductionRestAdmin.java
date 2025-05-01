package  ma.zyn.app.ws.facade.admin.supply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.supply.SuiviProduction;
import ma.zyn.app.dao.criteria.core.supply.SuiviProductionCriteria;
import ma.zyn.app.service.facade.admin.supply.SuiviProductionAdminService;
import ma.zyn.app.ws.converter.supply.SuiviProductionConverter;
import ma.zyn.app.ws.dto.supply.SuiviProductionDto;
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
@RequestMapping("/api/admin/suiviProduction/")
public class SuiviProductionRestAdmin {




    @Operation(summary = "Finds a list of all suiviProductions")
    @GetMapping("")
    public ResponseEntity<List<SuiviProductionDto>> findAll() throws Exception {
        ResponseEntity<List<SuiviProductionDto>> res = null;
        List<SuiviProduction> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<SuiviProductionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all suiviProductions")
    @GetMapping("optimized")
    public ResponseEntity<List<SuiviProductionDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<SuiviProductionDto>> res = null;
        List<SuiviProduction> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<SuiviProductionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a suiviProduction by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SuiviProductionDto> findById(@PathVariable Long id) {
        SuiviProduction t = service.findById(id);
        if (t != null) {
            converter.init(true);
            SuiviProductionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a suiviProduction by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<SuiviProductionDto> findByLibelle(@PathVariable String libelle) {
	    SuiviProduction t = service.findByReferenceEntity(new SuiviProduction(libelle));
        if (t != null) {
            converter.init(true);
            SuiviProductionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  suiviProduction")
    @PostMapping("")
    public ResponseEntity<SuiviProductionDto> save(@RequestBody SuiviProductionDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            SuiviProduction myT = converter.toItem(dto);
            SuiviProduction t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SuiviProductionDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  suiviProduction")
    @PutMapping("")
    public ResponseEntity<SuiviProductionDto> update(@RequestBody SuiviProductionDto dto) throws Exception {
        ResponseEntity<SuiviProductionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            SuiviProduction t = service.findById(dto.getId());
            converter.copy(dto,t);
            SuiviProduction updated = service.update(t);
            SuiviProductionDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of suiviProduction")
    @PostMapping("multiple")
    public ResponseEntity<List<SuiviProductionDto>> delete(@RequestBody List<SuiviProductionDto> dtos) throws Exception {
        ResponseEntity<List<SuiviProductionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<SuiviProduction> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified suiviProduction")
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
    public List<SuiviProductionDto> findByProduitId(@PathVariable Long id){
        return findDtos(service.findByProduitId(id));
    }
    @Operation(summary = "delete by produit id")
    @DeleteMapping("produit/id/{id}")
    public int deleteByProduitId(@PathVariable Long id){
        return service.deleteByProduitId(id);
    }
    @Operation(summary = "find by stadeOperatoire code")
    @GetMapping("stadeOperatoire/code/{code}")
    public List<SuiviProductionDto> findByStadeOperatoireCode(@PathVariable String code){
        return findDtos(service.findByStadeOperatoireCode(code));
    }
    @Operation(summary = "delete by stadeOperatoire code")
    @DeleteMapping("stadeOperatoire/code/{code}")
    public int deleteByStadeOperatoireCode(@PathVariable String code){
        return service.deleteByStadeOperatoireCode(code);
    }
    @Operation(summary = "find by unite code")
    @GetMapping("unite/code/{code}")
    public List<SuiviProductionDto> findByUniteCode(@PathVariable String code){
        return findDtos(service.findByUniteCode(code));
    }
    @Operation(summary = "delete by unite code")
    @DeleteMapping("unite/code/{code}")
    public int deleteByUniteCode(@PathVariable String code){
        return service.deleteByUniteCode(code);
    }

    @Operation(summary = "Finds a suiviProduction and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SuiviProductionDto> findWithAssociatedLists(@PathVariable Long id) {
        SuiviProduction loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        SuiviProductionDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds suiviProductions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SuiviProductionDto>> findByCriteria(@RequestBody SuiviProductionCriteria criteria) throws Exception {
        ResponseEntity<List<SuiviProductionDto>> res = null;
        List<SuiviProduction> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<SuiviProductionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated suiviProductions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SuiviProductionCriteria criteria) throws Exception {
        List<SuiviProduction> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<SuiviProductionDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets suiviProduction data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SuiviProductionCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SuiviProductionDto> findDtos(List<SuiviProduction> list){
        converter.initObject(true);
        List<SuiviProductionDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SuiviProductionDto> getDtoResponseEntity(SuiviProductionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public SuiviProductionRestAdmin(SuiviProductionAdminService service, SuiviProductionConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final SuiviProductionAdminService service;
    private final SuiviProductionConverter converter;





}
