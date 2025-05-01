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

import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail;
import ma.zyn.app.dao.criteria.core.expedition.AnalyseChimiqueDetailCriteria;
import ma.zyn.app.service.facade.admin.expedition.AnalyseChimiqueDetailAdminService;
import ma.zyn.app.ws.converter.expedition.AnalyseChimiqueDetailConverter;
import ma.zyn.app.ws.dto.expedition.AnalyseChimiqueDetailDto;
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
@RequestMapping("/api/admin/analyseChimiqueDetail/")
public class AnalyseChimiqueDetailRestAdmin {




    @Operation(summary = "Finds a list of all analyseChimiqueDetails")
    @GetMapping("")
    public ResponseEntity<List<AnalyseChimiqueDetailDto>> findAll() throws Exception {
        ResponseEntity<List<AnalyseChimiqueDetailDto>> res = null;
        List<AnalyseChimiqueDetail> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<AnalyseChimiqueDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all analyseChimiqueDetails")
    @GetMapping("optimized")
    public ResponseEntity<List<AnalyseChimiqueDetailDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<AnalyseChimiqueDetailDto>> res = null;
        List<AnalyseChimiqueDetail> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AnalyseChimiqueDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a analyseChimiqueDetail by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AnalyseChimiqueDetailDto> findById(@PathVariable Long id) {
        AnalyseChimiqueDetail t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AnalyseChimiqueDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a analyseChimiqueDetail by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<AnalyseChimiqueDetailDto> findByLibelle(@PathVariable String libelle) {
	    AnalyseChimiqueDetail t = service.findByReferenceEntity(new AnalyseChimiqueDetail(libelle));
        if (t != null) {
            converter.init(true);
            AnalyseChimiqueDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  analyseChimiqueDetail")
    @PostMapping("")
    public ResponseEntity<AnalyseChimiqueDetailDto> save(@RequestBody AnalyseChimiqueDetailDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            AnalyseChimiqueDetail myT = converter.toItem(dto);
            AnalyseChimiqueDetail t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AnalyseChimiqueDetailDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  analyseChimiqueDetail")
    @PutMapping("")
    public ResponseEntity<AnalyseChimiqueDetailDto> update(@RequestBody AnalyseChimiqueDetailDto dto) throws Exception {
        ResponseEntity<AnalyseChimiqueDetailDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            AnalyseChimiqueDetail t = service.findById(dto.getId());
            converter.copy(dto,t);
            AnalyseChimiqueDetail updated = service.update(t);
            AnalyseChimiqueDetailDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of analyseChimiqueDetail")
    @PostMapping("multiple")
    public ResponseEntity<List<AnalyseChimiqueDetailDto>> delete(@RequestBody List<AnalyseChimiqueDetailDto> dtos) throws Exception {
        ResponseEntity<List<AnalyseChimiqueDetailDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<AnalyseChimiqueDetail> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified analyseChimiqueDetail")
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

    @Operation(summary = "find by elementChimique code")
    @GetMapping("elementChimique/code/{code}")
    public List<AnalyseChimiqueDetailDto> findByElementChimiqueCode(@PathVariable String code){
        return findDtos(service.findByElementChimiqueCode(code));
    }
    @Operation(summary = "delete by elementChimique code")
    @DeleteMapping("elementChimique/code/{code}")
    public int deleteByElementChimiqueCode(@PathVariable String code){
        return service.deleteByElementChimiqueCode(code);
    }
    @Operation(summary = "find by analyseChimique id")
    @GetMapping("analyseChimique/id/{id}")
    public List<AnalyseChimiqueDetailDto> findByAnalyseChimiqueId(@PathVariable Long id){
        return findDtos(service.findByAnalyseChimiqueId(id));
    }
    @Operation(summary = "delete by analyseChimique id")
    @DeleteMapping("analyseChimique/id/{id}")
    public int deleteByAnalyseChimiqueId(@PathVariable Long id){
        return service.deleteByAnalyseChimiqueId(id);
    }

    @Operation(summary = "Finds a analyseChimiqueDetail and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AnalyseChimiqueDetailDto> findWithAssociatedLists(@PathVariable Long id) {
        AnalyseChimiqueDetail loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AnalyseChimiqueDetailDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds analyseChimiqueDetails by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AnalyseChimiqueDetailDto>> findByCriteria(@RequestBody AnalyseChimiqueDetailCriteria criteria) throws Exception {
        ResponseEntity<List<AnalyseChimiqueDetailDto>> res = null;
        List<AnalyseChimiqueDetail> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AnalyseChimiqueDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated analyseChimiqueDetails by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AnalyseChimiqueDetailCriteria criteria) throws Exception {
        List<AnalyseChimiqueDetail> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<AnalyseChimiqueDetailDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets analyseChimiqueDetail data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AnalyseChimiqueDetailCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AnalyseChimiqueDetailDto> findDtos(List<AnalyseChimiqueDetail> list){
        converter.initObject(true);
        List<AnalyseChimiqueDetailDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AnalyseChimiqueDetailDto> getDtoResponseEntity(AnalyseChimiqueDetailDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public AnalyseChimiqueDetailRestAdmin(AnalyseChimiqueDetailAdminService service, AnalyseChimiqueDetailConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final AnalyseChimiqueDetailAdminService service;
    private final AnalyseChimiqueDetailConverter converter;





}
