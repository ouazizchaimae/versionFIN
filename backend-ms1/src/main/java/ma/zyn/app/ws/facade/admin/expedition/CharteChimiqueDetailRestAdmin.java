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

import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail;
import ma.zyn.app.dao.criteria.core.expedition.CharteChimiqueDetailCriteria;
import ma.zyn.app.service.facade.admin.expedition.CharteChimiqueDetailAdminService;
import ma.zyn.app.ws.converter.expedition.CharteChimiqueDetailConverter;
import ma.zyn.app.ws.dto.expedition.CharteChimiqueDetailDto;
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
@RequestMapping("/api/admin/charteChimiqueDetail/")
public class CharteChimiqueDetailRestAdmin {




    @Operation(summary = "Finds a list of all charteChimiqueDetails")
    @GetMapping("")
    public ResponseEntity<List<CharteChimiqueDetailDto>> findAll() throws Exception {
        ResponseEntity<List<CharteChimiqueDetailDto>> res = null;
        List<CharteChimiqueDetail> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<CharteChimiqueDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all charteChimiqueDetails")
    @GetMapping("optimized")
    public ResponseEntity<List<CharteChimiqueDetailDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CharteChimiqueDetailDto>> res = null;
        List<CharteChimiqueDetail> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CharteChimiqueDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a charteChimiqueDetail by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CharteChimiqueDetailDto> findById(@PathVariable Long id) {
        CharteChimiqueDetail t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CharteChimiqueDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a charteChimiqueDetail by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CharteChimiqueDetailDto> findByLibelle(@PathVariable String libelle) {
	    CharteChimiqueDetail t = service.findByReferenceEntity(new CharteChimiqueDetail(libelle));
        if (t != null) {
            converter.init(true);
            CharteChimiqueDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  charteChimiqueDetail")
    @PostMapping("")
    public ResponseEntity<CharteChimiqueDetailDto> save(@RequestBody CharteChimiqueDetailDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            CharteChimiqueDetail myT = converter.toItem(dto);
            CharteChimiqueDetail t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CharteChimiqueDetailDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  charteChimiqueDetail")
    @PutMapping("")
    public ResponseEntity<CharteChimiqueDetailDto> update(@RequestBody CharteChimiqueDetailDto dto) throws Exception {
        ResponseEntity<CharteChimiqueDetailDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CharteChimiqueDetail t = service.findById(dto.getId());
            converter.copy(dto,t);
            CharteChimiqueDetail updated = service.update(t);
            CharteChimiqueDetailDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of charteChimiqueDetail")
    @PostMapping("multiple")
    public ResponseEntity<List<CharteChimiqueDetailDto>> delete(@RequestBody List<CharteChimiqueDetailDto> dtos) throws Exception {
        ResponseEntity<List<CharteChimiqueDetailDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<CharteChimiqueDetail> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified charteChimiqueDetail")
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


    @Operation(summary = "Finds a charteChimiqueDetail and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CharteChimiqueDetailDto> findWithAssociatedLists(@PathVariable Long id) {
        CharteChimiqueDetail loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CharteChimiqueDetailDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds charteChimiqueDetails by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CharteChimiqueDetailDto>> findByCriteria(@RequestBody CharteChimiqueDetailCriteria criteria) throws Exception {
        ResponseEntity<List<CharteChimiqueDetailDto>> res = null;
        List<CharteChimiqueDetail> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CharteChimiqueDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated charteChimiqueDetails by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CharteChimiqueDetailCriteria criteria) throws Exception {
        List<CharteChimiqueDetail> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<CharteChimiqueDetailDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets charteChimiqueDetail data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CharteChimiqueDetailCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CharteChimiqueDetailDto> findDtos(List<CharteChimiqueDetail> list){
        converter.initObject(true);
        List<CharteChimiqueDetailDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CharteChimiqueDetailDto> getDtoResponseEntity(CharteChimiqueDetailDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CharteChimiqueDetailRestAdmin(CharteChimiqueDetailAdminService service, CharteChimiqueDetailConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CharteChimiqueDetailAdminService service;
    private final CharteChimiqueDetailConverter converter;





}
