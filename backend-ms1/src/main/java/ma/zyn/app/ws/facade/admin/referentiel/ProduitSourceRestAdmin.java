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

import ma.zyn.app.bean.core.referentiel.ProduitSource;
import ma.zyn.app.dao.criteria.core.referentiel.ProduitSourceCriteria;
import ma.zyn.app.service.facade.admin.referentiel.ProduitSourceAdminService;
import ma.zyn.app.ws.converter.referentiel.ProduitSourceConverter;
import ma.zyn.app.ws.dto.referentiel.ProduitSourceDto;
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
@RequestMapping("/api/admin/produitSource/")
public class ProduitSourceRestAdmin {




    @Operation(summary = "Finds a list of all produitSources")
    @GetMapping("")
    public ResponseEntity<List<ProduitSourceDto>> findAll() throws Exception {
        ResponseEntity<List<ProduitSourceDto>> res = null;
        List<ProduitSource> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProduitSourceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all produitSources")
    @GetMapping("optimized")
    public ResponseEntity<List<ProduitSourceDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProduitSourceDto>> res = null;
        List<ProduitSource> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProduitSourceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a produitSource by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProduitSourceDto> findById(@PathVariable Long id) {
        ProduitSource t = service.findById(id);
        if (t != null) {
            ProduitSourceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a produitSource by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ProduitSourceDto> findByLibelle(@PathVariable String libelle) {
	    ProduitSource t = service.findByReferenceEntity(new ProduitSource(libelle));
        if (t != null) {
            ProduitSourceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  produitSource")
    @PostMapping("")
    public ResponseEntity<ProduitSourceDto> save(@RequestBody ProduitSourceDto dto) throws Exception {
        if(dto!=null){
            ProduitSource myT = converter.toItem(dto);
            ProduitSource t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProduitSourceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  produitSource")
    @PutMapping("")
    public ResponseEntity<ProduitSourceDto> update(@RequestBody ProduitSourceDto dto) throws Exception {
        ResponseEntity<ProduitSourceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProduitSource t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProduitSource updated = service.update(t);
            ProduitSourceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of produitSource")
    @PostMapping("multiple")
    public ResponseEntity<List<ProduitSourceDto>> delete(@RequestBody List<ProduitSourceDto> dtos) throws Exception {
        ResponseEntity<List<ProduitSourceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ProduitSource> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified produitSource")
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


    @Operation(summary = "Finds a produitSource and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProduitSourceDto> findWithAssociatedLists(@PathVariable Long id) {
        ProduitSource loaded =  service.findWithAssociatedLists(id);
        ProduitSourceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds produitSources by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProduitSourceDto>> findByCriteria(@RequestBody ProduitSourceCriteria criteria) throws Exception {
        ResponseEntity<List<ProduitSourceDto>> res = null;
        List<ProduitSource> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProduitSourceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated produitSources by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProduitSourceCriteria criteria) throws Exception {
        List<ProduitSource> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ProduitSourceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets produitSource data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProduitSourceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProduitSourceDto> findDtos(List<ProduitSource> list){
        List<ProduitSourceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProduitSourceDto> getDtoResponseEntity(ProduitSourceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ProduitSourceRestAdmin(ProduitSourceAdminService service, ProduitSourceConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ProduitSourceAdminService service;
    private final ProduitSourceConverter converter;





}
