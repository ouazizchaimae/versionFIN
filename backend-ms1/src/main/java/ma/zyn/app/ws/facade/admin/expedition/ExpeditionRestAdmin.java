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

import ma.zyn.app.bean.core.expedition.Expedition;
import ma.zyn.app.dao.criteria.core.expedition.ExpeditionCriteria;
import ma.zyn.app.service.facade.admin.expedition.ExpeditionAdminService;
import ma.zyn.app.ws.converter.expedition.ExpeditionConverter;
import ma.zyn.app.ws.dto.expedition.ExpeditionDto;
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
@RequestMapping("/api/admin/expedition/")
public class ExpeditionRestAdmin {




    @Operation(summary = "Finds a list of all expeditions")
    @GetMapping("")
    public ResponseEntity<List<ExpeditionDto>> findAll() throws Exception {
        ResponseEntity<List<ExpeditionDto>> res = null;
        List<Expedition> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<ExpeditionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all expeditions")
    @GetMapping("optimized")
    public ResponseEntity<List<ExpeditionDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ExpeditionDto>> res = null;
        List<Expedition> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ExpeditionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a expedition by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ExpeditionDto> findById(@PathVariable Long id) {
        Expedition t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ExpeditionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a expedition by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ExpeditionDto> findByLibelle(@PathVariable String libelle) {
	    Expedition t = service.findByReferenceEntity(new Expedition(libelle));
        if (t != null) {
            converter.init(true);
            ExpeditionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  expedition")
    @PostMapping("")
    public ResponseEntity<ExpeditionDto> save(@RequestBody ExpeditionDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Expedition myT = converter.toItem(dto);
            Expedition t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ExpeditionDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  expedition")
    @PutMapping("")
    public ResponseEntity<ExpeditionDto> update(@RequestBody ExpeditionDto dto) throws Exception {
        ResponseEntity<ExpeditionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Expedition t = service.findById(dto.getId());
            converter.copy(dto,t);
            Expedition updated = service.update(t);
            ExpeditionDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of expedition")
    @PostMapping("multiple")
    public ResponseEntity<List<ExpeditionDto>> delete(@RequestBody List<ExpeditionDto> dtos) throws Exception {
        ResponseEntity<List<ExpeditionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Expedition> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified expedition")
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

    @Operation(summary = "find by client id")
    @GetMapping("client/id/{id}")
    public List<ExpeditionDto> findByClientId(@PathVariable Long id){
        return findDtos(service.findByClientId(id));
    }
    @Operation(summary = "delete by client id")
    @DeleteMapping("client/id/{id}")
    public int deleteByClientId(@PathVariable Long id){
        return service.deleteByClientId(id);
    }
    @Operation(summary = "find by typeExpedition code")
    @GetMapping("typeExpedition/code/{code}")
    public List<ExpeditionDto> findByTypeExpeditionCode(@PathVariable String code){
        return findDtos(service.findByTypeExpeditionCode(code));
    }
    @Operation(summary = "delete by typeExpedition code")
    @DeleteMapping("typeExpedition/code/{code}")
    public int deleteByTypeExpeditionCode(@PathVariable String code){
        return service.deleteByTypeExpeditionCode(code);
    }

    @Operation(summary = "Finds a expedition and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ExpeditionDto> findWithAssociatedLists(@PathVariable Long id) {
        Expedition loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ExpeditionDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds expeditions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ExpeditionDto>> findByCriteria(@RequestBody ExpeditionCriteria criteria) throws Exception {
        ResponseEntity<List<ExpeditionDto>> res = null;
        List<Expedition> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ExpeditionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated expeditions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ExpeditionCriteria criteria) throws Exception {
        List<Expedition> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<ExpeditionDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets expedition data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ExpeditionCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ExpeditionDto> findDtos(List<Expedition> list){
        converter.initList(false);
        converter.initObject(true);
        List<ExpeditionDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ExpeditionDto> getDtoResponseEntity(ExpeditionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ExpeditionRestAdmin(ExpeditionAdminService service, ExpeditionConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ExpeditionAdminService service;
    private final ExpeditionConverter converter;





}
