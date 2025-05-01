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

import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.dao.criteria.core.referentiel.ProduitMarchandCriteria;
import ma.zyn.app.service.facade.admin.referentiel.ProduitMarchandAdminService;
import ma.zyn.app.ws.converter.referentiel.ProduitMarchandConverter;
import ma.zyn.app.ws.dto.referentiel.ProduitMarchandDto;
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
@RequestMapping("/api/admin/produitMarchand/")
public class ProduitMarchandRestAdmin {




    @Operation(summary = "Finds a list of all produitMarchands")
    @GetMapping("")
    public ResponseEntity<List<ProduitMarchandDto>> findAll() throws Exception {
        ResponseEntity<List<ProduitMarchandDto>> res = null;
        List<ProduitMarchand> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProduitMarchandDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all produitMarchands")
    @GetMapping("optimized")
    public ResponseEntity<List<ProduitMarchandDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProduitMarchandDto>> res = null;
        List<ProduitMarchand> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProduitMarchandDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a produitMarchand by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProduitMarchandDto> findById(@PathVariable Long id) {
        ProduitMarchand t = service.findById(id);
        if (t != null) {
            ProduitMarchandDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a produitMarchand by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ProduitMarchandDto> findByLibelle(@PathVariable String libelle) {
	    ProduitMarchand t = service.findByReferenceEntity(new ProduitMarchand(libelle));
        if (t != null) {
            ProduitMarchandDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  produitMarchand")
    @PostMapping("")
    public ResponseEntity<ProduitMarchandDto> save(@RequestBody ProduitMarchandDto dto) throws Exception {
        if(dto!=null){
            ProduitMarchand myT = converter.toItem(dto);
            ProduitMarchand t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProduitMarchandDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  produitMarchand")
    @PutMapping("")
    public ResponseEntity<ProduitMarchandDto> update(@RequestBody ProduitMarchandDto dto) throws Exception {
        ResponseEntity<ProduitMarchandDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProduitMarchand t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProduitMarchand updated = service.update(t);
            ProduitMarchandDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of produitMarchand")
    @PostMapping("multiple")
    public ResponseEntity<List<ProduitMarchandDto>> delete(@RequestBody List<ProduitMarchandDto> dtos) throws Exception {
        ResponseEntity<List<ProduitMarchandDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ProduitMarchand> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified produitMarchand")
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


    @Operation(summary = "Finds a produitMarchand and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProduitMarchandDto> findWithAssociatedLists(@PathVariable Long id) {
        ProduitMarchand loaded =  service.findWithAssociatedLists(id);
        ProduitMarchandDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds produitMarchands by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProduitMarchandDto>> findByCriteria(@RequestBody ProduitMarchandCriteria criteria) throws Exception {
        ResponseEntity<List<ProduitMarchandDto>> res = null;
        List<ProduitMarchand> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProduitMarchandDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated produitMarchands by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProduitMarchandCriteria criteria) throws Exception {
        List<ProduitMarchand> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ProduitMarchandDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets produitMarchand data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProduitMarchandCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProduitMarchandDto> findDtos(List<ProduitMarchand> list){
        List<ProduitMarchandDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProduitMarchandDto> getDtoResponseEntity(ProduitMarchandDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ProduitMarchandRestAdmin(ProduitMarchandAdminService service, ProduitMarchandConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ProduitMarchandAdminService service;
    private final ProduitMarchandConverter converter;





}
