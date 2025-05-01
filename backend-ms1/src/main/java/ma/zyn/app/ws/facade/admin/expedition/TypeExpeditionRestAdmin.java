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

import ma.zyn.app.bean.core.expedition.TypeExpedition;
import ma.zyn.app.dao.criteria.core.expedition.TypeExpeditionCriteria;
import ma.zyn.app.service.facade.admin.expedition.TypeExpeditionAdminService;
import ma.zyn.app.ws.converter.expedition.TypeExpeditionConverter;
import ma.zyn.app.ws.dto.expedition.TypeExpeditionDto;
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
@RequestMapping("/api/admin/typeExpedition/")
public class TypeExpeditionRestAdmin {




    @Operation(summary = "Finds a list of all typeExpeditions")
    @GetMapping("")
    public ResponseEntity<List<TypeExpeditionDto>> findAll() throws Exception {
        ResponseEntity<List<TypeExpeditionDto>> res = null;
        List<TypeExpedition> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeExpeditionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeExpeditions")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeExpeditionDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeExpeditionDto>> res = null;
        List<TypeExpedition> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeExpeditionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeExpedition by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeExpeditionDto> findById(@PathVariable Long id) {
        TypeExpedition t = service.findById(id);
        if (t != null) {
            TypeExpeditionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeExpedition by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeExpeditionDto> findByLibelle(@PathVariable String libelle) {
	    TypeExpedition t = service.findByReferenceEntity(new TypeExpedition(libelle));
        if (t != null) {
            TypeExpeditionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeExpedition")
    @PostMapping("")
    public ResponseEntity<TypeExpeditionDto> save(@RequestBody TypeExpeditionDto dto) throws Exception {
        if(dto!=null){
            TypeExpedition myT = converter.toItem(dto);
            TypeExpedition t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeExpeditionDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeExpedition")
    @PutMapping("")
    public ResponseEntity<TypeExpeditionDto> update(@RequestBody TypeExpeditionDto dto) throws Exception {
        ResponseEntity<TypeExpeditionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeExpedition t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeExpedition updated = service.update(t);
            TypeExpeditionDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeExpedition")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeExpeditionDto>> delete(@RequestBody List<TypeExpeditionDto> dtos) throws Exception {
        ResponseEntity<List<TypeExpeditionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeExpedition> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeExpedition")
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


    @Operation(summary = "Finds a typeExpedition and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeExpeditionDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeExpedition loaded =  service.findWithAssociatedLists(id);
        TypeExpeditionDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeExpeditions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeExpeditionDto>> findByCriteria(@RequestBody TypeExpeditionCriteria criteria) throws Exception {
        ResponseEntity<List<TypeExpeditionDto>> res = null;
        List<TypeExpedition> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeExpeditionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeExpeditions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeExpeditionCriteria criteria) throws Exception {
        List<TypeExpedition> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeExpeditionDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeExpedition data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeExpeditionCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeExpeditionDto> findDtos(List<TypeExpedition> list){
        List<TypeExpeditionDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeExpeditionDto> getDtoResponseEntity(TypeExpeditionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public TypeExpeditionRestAdmin(TypeExpeditionAdminService service, TypeExpeditionConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TypeExpeditionAdminService service;
    private final TypeExpeditionConverter converter;





}
