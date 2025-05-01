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

import ma.zyn.app.bean.core.referentiel.RatioUnite;
import ma.zyn.app.dao.criteria.core.referentiel.RatioUniteCriteria;
import ma.zyn.app.service.facade.admin.referentiel.RatioUniteAdminService;
import ma.zyn.app.ws.converter.referentiel.RatioUniteConverter;
import ma.zyn.app.ws.dto.referentiel.RatioUniteDto;
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
@RequestMapping("/api/admin/ratioUnite/")
public class RatioUniteRestAdmin {




    @Operation(summary = "Finds a list of all ratioUnites")
    @GetMapping("")
    public ResponseEntity<List<RatioUniteDto>> findAll() throws Exception {
        ResponseEntity<List<RatioUniteDto>> res = null;
        List<RatioUnite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<RatioUniteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a ratioUnite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RatioUniteDto> findById(@PathVariable Long id) {
        RatioUnite t = service.findById(id);
        if (t != null) {
            converter.init(true);
            RatioUniteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  ratioUnite")
    @PostMapping("")
    public ResponseEntity<RatioUniteDto> save(@RequestBody RatioUniteDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            RatioUnite myT = converter.toItem(dto);
            RatioUnite t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RatioUniteDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  ratioUnite")
    @PutMapping("")
    public ResponseEntity<RatioUniteDto> update(@RequestBody RatioUniteDto dto) throws Exception {
        ResponseEntity<RatioUniteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RatioUnite t = service.findById(dto.getId());
            converter.copy(dto,t);
            RatioUnite updated = service.update(t);
            RatioUniteDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of ratioUnite")
    @PostMapping("multiple")
    public ResponseEntity<List<RatioUniteDto>> delete(@RequestBody List<RatioUniteDto> dtos) throws Exception {
        ResponseEntity<List<RatioUniteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<RatioUnite> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified ratioUnite")
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
    public List<RatioUniteDto> findByProduitId(@PathVariable Long id){
        return findDtos(service.findByProduitId(id));
    }
    @Operation(summary = "delete by produit id")
    @DeleteMapping("produit/id/{id}")
    public int deleteByProduitId(@PathVariable Long id){
        return service.deleteByProduitId(id);
    }

    @Operation(summary = "Finds a ratioUnite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RatioUniteDto> findWithAssociatedLists(@PathVariable Long id) {
        RatioUnite loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        RatioUniteDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds ratioUnites by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RatioUniteDto>> findByCriteria(@RequestBody RatioUniteCriteria criteria) throws Exception {
        ResponseEntity<List<RatioUniteDto>> res = null;
        List<RatioUnite> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<RatioUniteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated ratioUnites by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RatioUniteCriteria criteria) throws Exception {
        List<RatioUnite> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<RatioUniteDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets ratioUnite data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RatioUniteCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RatioUniteDto> findDtos(List<RatioUnite> list){
        converter.initObject(true);
        List<RatioUniteDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RatioUniteDto> getDtoResponseEntity(RatioUniteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public RatioUniteRestAdmin(RatioUniteAdminService service, RatioUniteConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final RatioUniteAdminService service;
    private final RatioUniteConverter converter;





}
