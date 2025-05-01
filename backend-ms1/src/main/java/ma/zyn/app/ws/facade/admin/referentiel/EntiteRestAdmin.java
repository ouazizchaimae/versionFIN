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

import ma.zyn.app.bean.core.referentiel.Entite;
import ma.zyn.app.dao.criteria.core.referentiel.EntiteCriteria;
import ma.zyn.app.service.facade.admin.referentiel.EntiteAdminService;
import ma.zyn.app.ws.converter.referentiel.EntiteConverter;
import ma.zyn.app.ws.dto.referentiel.EntiteDto;
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
@RequestMapping("/api/admin/entite/")
public class EntiteRestAdmin {




    @Operation(summary = "Finds a list of all entites")
    @GetMapping("")
    public ResponseEntity<List<EntiteDto>> findAll() throws Exception {
        ResponseEntity<List<EntiteDto>> res = null;
        List<Entite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EntiteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all entites")
    @GetMapping("optimized")
    public ResponseEntity<List<EntiteDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EntiteDto>> res = null;
        List<Entite> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EntiteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a entite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EntiteDto> findById(@PathVariable Long id) {
        Entite t = service.findById(id);
        if (t != null) {
            EntiteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a entite by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EntiteDto> findByLibelle(@PathVariable String libelle) {
	    Entite t = service.findByReferenceEntity(new Entite(libelle));
        if (t != null) {
            EntiteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  entite")
    @PostMapping("")
    public ResponseEntity<EntiteDto> save(@RequestBody EntiteDto dto) throws Exception {
        if(dto!=null){
            Entite myT = converter.toItem(dto);
            Entite t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EntiteDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  entite")
    @PutMapping("")
    public ResponseEntity<EntiteDto> update(@RequestBody EntiteDto dto) throws Exception {
        ResponseEntity<EntiteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Entite t = service.findById(dto.getId());
            converter.copy(dto,t);
            Entite updated = service.update(t);
            EntiteDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of entite")
    @PostMapping("multiple")
    public ResponseEntity<List<EntiteDto>> delete(@RequestBody List<EntiteDto> dtos) throws Exception {
        ResponseEntity<List<EntiteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Entite> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified entite")
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


    @Operation(summary = "Finds a entite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EntiteDto> findWithAssociatedLists(@PathVariable Long id) {
        Entite loaded =  service.findWithAssociatedLists(id);
        EntiteDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds entites by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EntiteDto>> findByCriteria(@RequestBody EntiteCriteria criteria) throws Exception {
        ResponseEntity<List<EntiteDto>> res = null;
        List<Entite> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EntiteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated entites by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EntiteCriteria criteria) throws Exception {
        List<Entite> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EntiteDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets entite data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EntiteCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EntiteDto> findDtos(List<Entite> list){
        List<EntiteDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EntiteDto> getDtoResponseEntity(EntiteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public EntiteRestAdmin(EntiteAdminService service, EntiteConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final EntiteAdminService service;
    private final EntiteConverter converter;





}
