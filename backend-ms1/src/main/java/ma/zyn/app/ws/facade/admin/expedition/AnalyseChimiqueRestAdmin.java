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

import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import ma.zyn.app.dao.criteria.core.expedition.AnalyseChimiqueCriteria;
import ma.zyn.app.service.facade.admin.expedition.AnalyseChimiqueAdminService;
import ma.zyn.app.ws.converter.expedition.AnalyseChimiqueConverter;
import ma.zyn.app.ws.dto.expedition.AnalyseChimiqueDto;
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
@RequestMapping("/api/admin/analyseChimique/")
public class AnalyseChimiqueRestAdmin {




    @Operation(summary = "Finds a list of all analyseChimiques")
    @GetMapping("")
    public ResponseEntity<List<AnalyseChimiqueDto>> findAll() throws Exception {
        ResponseEntity<List<AnalyseChimiqueDto>> res = null;
        List<AnalyseChimique> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<AnalyseChimiqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all analyseChimiques")
    @GetMapping("optimized")
    public ResponseEntity<List<AnalyseChimiqueDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<AnalyseChimiqueDto>> res = null;
        List<AnalyseChimique> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<AnalyseChimiqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a analyseChimique by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AnalyseChimiqueDto> findById(@PathVariable Long id) {
        AnalyseChimique t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AnalyseChimiqueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a analyseChimique by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<AnalyseChimiqueDto> findByLibelle(@PathVariable String libelle) {
	    AnalyseChimique t = service.findByReferenceEntity(new AnalyseChimique(libelle));
        if (t != null) {
            converter.init(true);
            AnalyseChimiqueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  analyseChimique")
    @PostMapping("")
    public ResponseEntity<AnalyseChimiqueDto> save(@RequestBody AnalyseChimiqueDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            AnalyseChimique myT = converter.toItem(dto);
            AnalyseChimique t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AnalyseChimiqueDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  analyseChimique")
    @PutMapping("")
    public ResponseEntity<AnalyseChimiqueDto> update(@RequestBody AnalyseChimiqueDto dto) throws Exception {
        ResponseEntity<AnalyseChimiqueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            AnalyseChimique t = service.findById(dto.getId());
            converter.copy(dto,t);
            AnalyseChimique updated = service.update(t);
            AnalyseChimiqueDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of analyseChimique")
    @PostMapping("multiple")
    public ResponseEntity<List<AnalyseChimiqueDto>> delete(@RequestBody List<AnalyseChimiqueDto> dtos) throws Exception {
        ResponseEntity<List<AnalyseChimiqueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<AnalyseChimique> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified analyseChimique")
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

    @Operation(summary = "find by produitMarchand code")
    @GetMapping("produitMarchand/code/{code}")
    public List<AnalyseChimiqueDto> findByProduitMarchandCode(@PathVariable String code){
        return findDtos(service.findByProduitMarchandCode(code));
    }
    @Operation(summary = "delete by produitMarchand code")
    @DeleteMapping("produitMarchand/code/{code}")
    public int deleteByProduitMarchandCode(@PathVariable String code){
        return service.deleteByProduitMarchandCode(code);
    }

    @Operation(summary = "Finds a analyseChimique and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AnalyseChimiqueDto> findWithAssociatedLists(@PathVariable Long id) {
        AnalyseChimique loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AnalyseChimiqueDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds analyseChimiques by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AnalyseChimiqueDto>> findByCriteria(@RequestBody AnalyseChimiqueCriteria criteria) throws Exception {
        ResponseEntity<List<AnalyseChimiqueDto>> res = null;
        List<AnalyseChimique> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<AnalyseChimiqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated analyseChimiques by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AnalyseChimiqueCriteria criteria) throws Exception {
        List<AnalyseChimique> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<AnalyseChimiqueDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets analyseChimique data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AnalyseChimiqueCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AnalyseChimiqueDto> findDtos(List<AnalyseChimique> list){
        converter.initList(false);
        converter.initObject(true);
        List<AnalyseChimiqueDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AnalyseChimiqueDto> getDtoResponseEntity(AnalyseChimiqueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public AnalyseChimiqueRestAdmin(AnalyseChimiqueAdminService service, AnalyseChimiqueConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final AnalyseChimiqueAdminService service;
    private final AnalyseChimiqueConverter converter;





}
