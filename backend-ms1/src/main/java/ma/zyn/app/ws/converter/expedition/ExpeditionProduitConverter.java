package  ma.zyn.app.ws.converter.expedition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.expedition.AnalyseChimiqueConverter;
import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import ma.zyn.app.ws.converter.expedition.CharteChimiqueConverter;
import ma.zyn.app.bean.core.expedition.CharteChimique;

import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import ma.zyn.app.bean.core.expedition.CharteChimique;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.expedition.ExpeditionProduit;
import ma.zyn.app.ws.dto.expedition.ExpeditionProduitDto;

@Component
public class ExpeditionProduitConverter {

    @Autowired
    private AnalyseChimiqueConverter analyseChimiqueConverter ;
    @Autowired
    private CharteChimiqueConverter charteChimiqueConverter ;
    private boolean analyseChimique;
    private boolean charteChimique;

    public  ExpeditionProduitConverter() {
        initObject(true);
    }

    public ExpeditionProduit toItem(ExpeditionProduitDto dto) {
        if (dto == null) {
            return null;
        } else {
        ExpeditionProduit item = new ExpeditionProduit();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(dto.getAnalyseChimique() != null && dto.getAnalyseChimique().getId() != null){
                item.setAnalyseChimique(new AnalyseChimique());
                item.getAnalyseChimique().setId(dto.getAnalyseChimique().getId());
                item.getAnalyseChimique().setLibelle(dto.getAnalyseChimique().getLibelle());
            }

            if(dto.getCharteChimique() != null && dto.getCharteChimique().getId() != null){
                item.setCharteChimique(new CharteChimique());
                item.getCharteChimique().setId(dto.getCharteChimique().getId());
                item.getCharteChimique().setLibelle(dto.getCharteChimique().getLibelle());
            }




        return item;
        }
    }


    public ExpeditionProduitDto toDto(ExpeditionProduit item) {
        if (item == null) {
            return null;
        } else {
            ExpeditionProduitDto dto = new ExpeditionProduitDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.analyseChimique && item.getAnalyseChimique()!=null) {
                dto.setAnalyseChimique(analyseChimiqueConverter.toDto(item.getAnalyseChimique())) ;

            }
            if(this.charteChimique && item.getCharteChimique()!=null) {
                dto.setCharteChimique(charteChimiqueConverter.toDto(item.getCharteChimique())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.analyseChimique = value;
        this.charteChimique = value;
    }
	
    public List<ExpeditionProduit> toItem(List<ExpeditionProduitDto> dtos) {
        List<ExpeditionProduit> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ExpeditionProduitDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ExpeditionProduitDto> toDto(List<ExpeditionProduit> items) {
        List<ExpeditionProduitDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ExpeditionProduit item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ExpeditionProduitDto dto, ExpeditionProduit t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getAnalyseChimique() == null  && dto.getAnalyseChimique() != null){
            t.setAnalyseChimique(new AnalyseChimique());
        }else if (t.getAnalyseChimique() != null  && dto.getAnalyseChimique() != null){
            t.setAnalyseChimique(null);
            t.setAnalyseChimique(new AnalyseChimique());
        }
        if(t.getCharteChimique() == null  && dto.getCharteChimique() != null){
            t.setCharteChimique(new CharteChimique());
        }else if (t.getCharteChimique() != null  && dto.getCharteChimique() != null){
            t.setCharteChimique(null);
            t.setCharteChimique(new CharteChimique());
        }
        if (dto.getAnalyseChimique() != null)
        analyseChimiqueConverter.copy(dto.getAnalyseChimique(), t.getAnalyseChimique());
        if (dto.getCharteChimique() != null)
        charteChimiqueConverter.copy(dto.getCharteChimique(), t.getCharteChimique());
    }

    public List<ExpeditionProduit> copy(List<ExpeditionProduitDto> dtos) {
        List<ExpeditionProduit> result = new ArrayList<>();
        if (dtos != null) {
            for (ExpeditionProduitDto dto : dtos) {
                ExpeditionProduit instance = new ExpeditionProduit();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public AnalyseChimiqueConverter getAnalyseChimiqueConverter(){
        return this.analyseChimiqueConverter;
    }
    public void setAnalyseChimiqueConverter(AnalyseChimiqueConverter analyseChimiqueConverter ){
        this.analyseChimiqueConverter = analyseChimiqueConverter;
    }
    public CharteChimiqueConverter getCharteChimiqueConverter(){
        return this.charteChimiqueConverter;
    }
    public void setCharteChimiqueConverter(CharteChimiqueConverter charteChimiqueConverter ){
        this.charteChimiqueConverter = charteChimiqueConverter;
    }
    public boolean  isAnalyseChimique(){
        return this.analyseChimique;
    }
    public void  setAnalyseChimique(boolean analyseChimique){
        this.analyseChimique = analyseChimique;
    }
    public boolean  isCharteChimique(){
        return this.charteChimique;
    }
    public void  setCharteChimique(boolean charteChimique){
        this.charteChimique = charteChimique;
    }
}
