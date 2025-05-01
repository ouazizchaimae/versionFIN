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

import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.dao.criteria.core.referentiel.UniteCriteria;
import ma.zyn.app.service.facade.admin.referentiel.UniteAdminService;
import ma.zyn.app.ws.converter.referentiel.UniteConverter;
import ma.zyn.app.ws.dto.referentiel.UniteDto;
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
@RequestMapping("/api/admin/unite/")
public class UniteRestAdmin {




    @Operation(summary = "Finds a list of all unites")
    @GetMapping("")
    public ResponseEntity<List<UniteDto>> findAll() throws Exception {
        ResponseEntity<List<UniteDto>> res = null;
        List<Unite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<UniteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all unites")
    @GetMapping("optimized")
    public ResponseEntity<List<UniteDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<UniteDto>> res = null;
        List<Unite> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<UniteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a unite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<UniteDto> findById(@PathVariable Long id) {
        Unite t = service.findById(id);
        if (t != null) {
            UniteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a unite by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<UniteDto> findByLibelle(@PathVariable String libelle) {
	    Unite t = service.findByReferenceEntity(new Unite(libelle));
        if (t != null) {
            UniteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  unite")
    @PostMapping("")
    public ResponseEntity<UniteDto> save(@RequestBody UniteDto dto) throws Exception {
        if(dto!=null){
            Unite myT = converter.toItem(dto);
            Unite t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                UniteDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  unite")
    @PutMapping("")
    public ResponseEntity<UniteDto> update(@RequestBody UniteDto dto) throws Exception {
        ResponseEntity<UniteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Unite t = service.findById(dto.getId());
            converter.copy(dto,t);
            Unite updated = service.update(t);
            UniteDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of unite")
    @PostMapping("multiple")
    public ResponseEntity<List<UniteDto>> delete(@RequestBody List<UniteDto> dtos) throws Exception {
        ResponseEntity<List<UniteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Unite> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified unite")
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


    @Operation(summary = "Finds a unite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<UniteDto> findWithAssociatedLists(@PathVariable Long id) {
        Unite loaded =  service.findWithAssociatedLists(id);
        UniteDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds unites by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<UniteDto>> findByCriteria(@RequestBody UniteCriteria criteria) throws Exception {
        ResponseEntity<List<UniteDto>> res = null;
        List<Unite> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<UniteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated unites by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody UniteCriteria criteria) throws Exception {
        List<Unite> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<UniteDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets unite data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody UniteCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<UniteDto> findDtos(List<Unite> list){
        List<UniteDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<UniteDto> getDtoResponseEntity(UniteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public UniteRestAdmin(UniteAdminService service, UniteConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final UniteAdminService service;
    private final UniteConverter converter;





}
