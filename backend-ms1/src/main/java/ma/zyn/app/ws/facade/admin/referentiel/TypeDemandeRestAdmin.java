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

import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.dao.criteria.core.referentiel.TypeDemandeCriteria;
import ma.zyn.app.service.facade.admin.referentiel.TypeDemandeAdminService;
import ma.zyn.app.ws.converter.referentiel.TypeDemandeConverter;
import ma.zyn.app.ws.dto.referentiel.TypeDemandeDto;
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
@RequestMapping("/api/admin/typeDemande/")
public class TypeDemandeRestAdmin {




    @Operation(summary = "Finds a list of all typeDemandes")
    @GetMapping("")
    public ResponseEntity<List<TypeDemandeDto>> findAll() throws Exception {
        ResponseEntity<List<TypeDemandeDto>> res = null;
        List<TypeDemande> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeDemandeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeDemandes")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeDemandeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeDemandeDto>> res = null;
        List<TypeDemande> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeDemandeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeDemande by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeDemandeDto> findById(@PathVariable Long id) {
        TypeDemande t = service.findById(id);
        if (t != null) {
            TypeDemandeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeDemande by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeDemandeDto> findByLibelle(@PathVariable String libelle) {
	    TypeDemande t = service.findByReferenceEntity(new TypeDemande(libelle));
        if (t != null) {
            TypeDemandeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeDemande")
    @PostMapping("")
    public ResponseEntity<TypeDemandeDto> save(@RequestBody TypeDemandeDto dto) throws Exception {
        if(dto!=null){
            TypeDemande myT = converter.toItem(dto);
            TypeDemande t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeDemandeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeDemande")
    @PutMapping("")
    public ResponseEntity<TypeDemandeDto> update(@RequestBody TypeDemandeDto dto) throws Exception {
        ResponseEntity<TypeDemandeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeDemande t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeDemande updated = service.update(t);
            TypeDemandeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeDemande")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeDemandeDto>> delete(@RequestBody List<TypeDemandeDto> dtos) throws Exception {
        ResponseEntity<List<TypeDemandeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeDemande> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeDemande")
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


    @Operation(summary = "Finds a typeDemande and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeDemandeDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeDemande loaded =  service.findWithAssociatedLists(id);
        TypeDemandeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeDemandes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeDemandeDto>> findByCriteria(@RequestBody TypeDemandeCriteria criteria) throws Exception {
        ResponseEntity<List<TypeDemandeDto>> res = null;
        List<TypeDemande> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeDemandeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeDemandes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeDemandeCriteria criteria) throws Exception {
        List<TypeDemande> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeDemandeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeDemande data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeDemandeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeDemandeDto> findDtos(List<TypeDemande> list){
        List<TypeDemandeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeDemandeDto> getDtoResponseEntity(TypeDemandeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public TypeDemandeRestAdmin(TypeDemandeAdminService service, TypeDemandeConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TypeDemandeAdminService service;
    private final TypeDemandeConverter converter;





}
