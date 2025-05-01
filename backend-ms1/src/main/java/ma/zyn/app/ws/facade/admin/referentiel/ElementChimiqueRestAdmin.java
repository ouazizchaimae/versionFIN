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

import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.dao.criteria.core.referentiel.ElementChimiqueCriteria;
import ma.zyn.app.service.facade.admin.referentiel.ElementChimiqueAdminService;
import ma.zyn.app.ws.converter.referentiel.ElementChimiqueConverter;
import ma.zyn.app.ws.dto.referentiel.ElementChimiqueDto;
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
@RequestMapping("/api/admin/elementChimique/")
public class ElementChimiqueRestAdmin {




    @Operation(summary = "Finds a list of all elementChimiques")
    @GetMapping("")
    public ResponseEntity<List<ElementChimiqueDto>> findAll() throws Exception {
        ResponseEntity<List<ElementChimiqueDto>> res = null;
        List<ElementChimique> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ElementChimiqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all elementChimiques")
    @GetMapping("optimized")
    public ResponseEntity<List<ElementChimiqueDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ElementChimiqueDto>> res = null;
        List<ElementChimique> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ElementChimiqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a elementChimique by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ElementChimiqueDto> findById(@PathVariable Long id) {
        ElementChimique t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ElementChimiqueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a elementChimique by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ElementChimiqueDto> findByLibelle(@PathVariable String libelle) {
	    ElementChimique t = service.findByReferenceEntity(new ElementChimique(libelle));
        if (t != null) {
            converter.init(true);
            ElementChimiqueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  elementChimique")
    @PostMapping("")
    public ResponseEntity<ElementChimiqueDto> save(@RequestBody ElementChimiqueDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ElementChimique myT = converter.toItem(dto);
            ElementChimique t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ElementChimiqueDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  elementChimique")
    @PutMapping("")
    public ResponseEntity<ElementChimiqueDto> update(@RequestBody ElementChimiqueDto dto) throws Exception {
        ResponseEntity<ElementChimiqueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ElementChimique t = service.findById(dto.getId());
            converter.copy(dto,t);
            ElementChimique updated = service.update(t);
            ElementChimiqueDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of elementChimique")
    @PostMapping("multiple")
    public ResponseEntity<List<ElementChimiqueDto>> delete(@RequestBody List<ElementChimiqueDto> dtos) throws Exception {
        ResponseEntity<List<ElementChimiqueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ElementChimique> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified elementChimique")
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

    @Operation(summary = "find by unite code")
    @GetMapping("unite/code/{code}")
    public List<ElementChimiqueDto> findByUniteCode(@PathVariable String code){
        return findDtos(service.findByUniteCode(code));
    }
    @Operation(summary = "delete by unite code")
    @DeleteMapping("unite/code/{code}")
    public int deleteByUniteCode(@PathVariable String code){
        return service.deleteByUniteCode(code);
    }

    @Operation(summary = "Finds a elementChimique and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ElementChimiqueDto> findWithAssociatedLists(@PathVariable Long id) {
        ElementChimique loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ElementChimiqueDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds elementChimiques by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ElementChimiqueDto>> findByCriteria(@RequestBody ElementChimiqueCriteria criteria) throws Exception {
        ResponseEntity<List<ElementChimiqueDto>> res = null;
        List<ElementChimique> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ElementChimiqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated elementChimiques by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ElementChimiqueCriteria criteria) throws Exception {
        List<ElementChimique> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ElementChimiqueDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets elementChimique data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ElementChimiqueCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ElementChimiqueDto> findDtos(List<ElementChimique> list){
        converter.initObject(true);
        List<ElementChimiqueDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ElementChimiqueDto> getDtoResponseEntity(ElementChimiqueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ElementChimiqueRestAdmin(ElementChimiqueAdminService service, ElementChimiqueConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ElementChimiqueAdminService service;
    private final ElementChimiqueConverter converter;





}
