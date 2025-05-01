package  ma.zyn.app.ws.converter.expedition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.expedition.AnalyseChimiqueDetailConverter;
import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail;
import ma.zyn.app.ws.converter.referentiel.ElementChimiqueConverter;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.ws.converter.referentiel.ProduitMarchandConverter;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import ma.zyn.app.ws.dto.expedition.AnalyseChimiqueDto;

@Component
public class AnalyseChimiqueConverter {

    @Autowired
    private AnalyseChimiqueDetailConverter analyseChimiqueDetailConverter ;
    @Autowired
    private ElementChimiqueConverter elementChimiqueConverter ;
    @Autowired
    private ProduitMarchandConverter produitMarchandConverter ;
    private boolean produitMarchand;
    private boolean analyseChimiqueDetails;

    public  AnalyseChimiqueConverter() {
        init(true);
    }

    public AnalyseChimique toItem(AnalyseChimiqueDto dto) {
        if (dto == null) {
            return null;
        } else {
        AnalyseChimique item = new AnalyseChimique();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.produitMarchand && dto.getProduitMarchand()!=null)
                item.setProduitMarchand(produitMarchandConverter.toItem(dto.getProduitMarchand())) ;


            if(this.analyseChimiqueDetails && ListUtil.isNotEmpty(dto.getAnalyseChimiqueDetails()))
                item.setAnalyseChimiqueDetails(analyseChimiqueDetailConverter.toItem(dto.getAnalyseChimiqueDetails()));


        return item;
        }
    }


    public AnalyseChimiqueDto toDto(AnalyseChimique item) {
        if (item == null) {
            return null;
        } else {
            AnalyseChimiqueDto dto = new AnalyseChimiqueDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.produitMarchand && item.getProduitMarchand()!=null) {
                dto.setProduitMarchand(produitMarchandConverter.toDto(item.getProduitMarchand())) ;

            }
        if(this.analyseChimiqueDetails && ListUtil.isNotEmpty(item.getAnalyseChimiqueDetails())){
            analyseChimiqueDetailConverter.init(true);
            analyseChimiqueDetailConverter.setAnalyseChimique(false);
            dto.setAnalyseChimiqueDetails(analyseChimiqueDetailConverter.toDto(item.getAnalyseChimiqueDetails()));
            analyseChimiqueDetailConverter.setAnalyseChimique(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.analyseChimiqueDetails = value;
    }
    public void initObject(boolean value) {
        this.produitMarchand = value;
    }
	
    public List<AnalyseChimique> toItem(List<AnalyseChimiqueDto> dtos) {
        List<AnalyseChimique> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AnalyseChimiqueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AnalyseChimiqueDto> toDto(List<AnalyseChimique> items) {
        List<AnalyseChimiqueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (AnalyseChimique item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AnalyseChimiqueDto dto, AnalyseChimique t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProduitMarchand() == null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(new ProduitMarchand());
        }else if (t.getProduitMarchand() != null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(null);
            t.setProduitMarchand(new ProduitMarchand());
        }
        if (dto.getProduitMarchand() != null)
        produitMarchandConverter.copy(dto.getProduitMarchand(), t.getProduitMarchand());
        if (dto.getAnalyseChimiqueDetails() != null)
            t.setAnalyseChimiqueDetails(analyseChimiqueDetailConverter.copy(dto.getAnalyseChimiqueDetails()));
    }

    public List<AnalyseChimique> copy(List<AnalyseChimiqueDto> dtos) {
        List<AnalyseChimique> result = new ArrayList<>();
        if (dtos != null) {
            for (AnalyseChimiqueDto dto : dtos) {
                AnalyseChimique instance = new AnalyseChimique();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public AnalyseChimiqueDetailConverter getAnalyseChimiqueDetailConverter(){
        return this.analyseChimiqueDetailConverter;
    }
    public void setAnalyseChimiqueDetailConverter(AnalyseChimiqueDetailConverter analyseChimiqueDetailConverter ){
        this.analyseChimiqueDetailConverter = analyseChimiqueDetailConverter;
    }
    public ElementChimiqueConverter getElementChimiqueConverter(){
        return this.elementChimiqueConverter;
    }
    public void setElementChimiqueConverter(ElementChimiqueConverter elementChimiqueConverter ){
        this.elementChimiqueConverter = elementChimiqueConverter;
    }
    public ProduitMarchandConverter getProduitMarchandConverter(){
        return this.produitMarchandConverter;
    }
    public void setProduitMarchandConverter(ProduitMarchandConverter produitMarchandConverter ){
        this.produitMarchandConverter = produitMarchandConverter;
    }
    public boolean  isProduitMarchand(){
        return this.produitMarchand;
    }
    public void  setProduitMarchand(boolean produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    public boolean  isAnalyseChimiqueDetails(){
        return this.analyseChimiqueDetails ;
    }
    public void  setAnalyseChimiqueDetails(boolean analyseChimiqueDetails ){
        this.analyseChimiqueDetails  = analyseChimiqueDetails ;
    }
}
