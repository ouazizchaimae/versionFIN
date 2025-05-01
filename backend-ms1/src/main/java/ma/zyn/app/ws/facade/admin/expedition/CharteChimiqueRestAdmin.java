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

import ma.zyn.app.bean.core.expedition.CharteChimique;
import ma.zyn.app.dao.criteria.core.expedition.CharteChimiqueCriteria;
import ma.zyn.app.service.facade.admin.expedition.CharteChimiqueAdminService;
import ma.zyn.app.ws.converter.expedition.CharteChimiqueConverter;
import ma.zyn.app.ws.dto.expedition.CharteChimiqueDto;
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
@RequestMapping("/api/admin/charteChimique/")
public class CharteChimiqueRestAdmin {




    @Operation(summary = "Finds a list of all charteChimiques")
    @GetMapping("")
    public ResponseEntity<List<CharteChimiqueDto>> findAll() throws Exception {
        ResponseEntity<List<CharteChimiqueDto>> res = null;
        List<CharteChimique> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<CharteChimiqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all charteChimiques")
    @GetMapping("optimized")
    public ResponseEntity<List<CharteChimiqueDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CharteChimiqueDto>> res = null;
        List<CharteChimique> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<CharteChimiqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a charteChimique by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CharteChimiqueDto> findById(@PathVariable Long id) {
        CharteChimique t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CharteChimiqueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a charteChimique by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CharteChimiqueDto> findByLibelle(@PathVariable String libelle) {
	    CharteChimique t = service.findByReferenceEntity(new CharteChimique(libelle));
        if (t != null) {
            converter.init(true);
            CharteChimiqueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  charteChimique")
    @PostMapping("")
    public ResponseEntity<CharteChimiqueDto> save(@RequestBody CharteChimiqueDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            CharteChimique myT = converter.toItem(dto);
            CharteChimique t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CharteChimiqueDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  charteChimique")
    @PutMapping("")
    public ResponseEntity<CharteChimiqueDto> update(@RequestBody CharteChimiqueDto dto) throws Exception {
        ResponseEntity<CharteChimiqueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CharteChimique t = service.findById(dto.getId());
            converter.copy(dto,t);
            CharteChimique updated = service.update(t);
            CharteChimiqueDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of charteChimique")
    @PostMapping("multiple")
    public ResponseEntity<List<CharteChimiqueDto>> delete(@RequestBody List<CharteChimiqueDto> dtos) throws Exception {
        ResponseEntity<List<CharteChimiqueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<CharteChimique> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified charteChimique")
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

    @Operation(summary = "find by client id")
    @GetMapping("client/id/{id}")
    public List<CharteChimiqueDto> findByClientId(@PathVariable Long id){
        return findDtos(service.findByClientId(id));
    }
    @Operation(summary = "delete by client id")
    @DeleteMapping("client/id/{id}")
    public int deleteByClientId(@PathVariable Long id){
        return service.deleteByClientId(id);
    }
    @Operation(summary = "find by produitMarchand code")
    @GetMapping("produitMarchand/code/{code}")
    public List<CharteChimiqueDto> findByProduitMarchandCode(@PathVariable String code){
        return findDtos(service.findByProduitMarchandCode(code));
    }
    @Operation(summary = "delete by produitMarchand code")
    @DeleteMapping("produitMarchand/code/{code}")
    public int deleteByProduitMarchandCode(@PathVariable String code){
        return service.deleteByProduitMarchandCode(code);
    }

    @Operation(summary = "Finds a charteChimique and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CharteChimiqueDto> findWithAssociatedLists(@PathVariable Long id) {
        CharteChimique loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CharteChimiqueDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds charteChimiques by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CharteChimiqueDto>> findByCriteria(@RequestBody CharteChimiqueCriteria criteria) throws Exception {
        ResponseEntity<List<CharteChimiqueDto>> res = null;
        List<CharteChimique> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<CharteChimiqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated charteChimiques by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CharteChimiqueCriteria criteria) throws Exception {
        List<CharteChimique> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<CharteChimiqueDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets charteChimique data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CharteChimiqueCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CharteChimiqueDto> findDtos(List<CharteChimique> list){
        converter.initList(false);
        converter.initObject(true);
        List<CharteChimiqueDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CharteChimiqueDto> getDtoResponseEntity(CharteChimiqueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CharteChimiqueRestAdmin(CharteChimiqueAdminService service, CharteChimiqueConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CharteChimiqueAdminService service;
    private final CharteChimiqueConverter converter;





}
