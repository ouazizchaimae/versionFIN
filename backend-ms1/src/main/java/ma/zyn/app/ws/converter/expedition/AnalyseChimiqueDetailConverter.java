package  ma.zyn.app.ws.converter.expedition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.referentiel.ElementChimiqueConverter;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.ws.converter.expedition.AnalyseChimiqueConverter;
import ma.zyn.app.bean.core.expedition.AnalyseChimique;

import ma.zyn.app.bean.core.expedition.AnalyseChimique;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.expedition.AnalyseChimiqueDetail;
import ma.zyn.app.ws.dto.expedition.AnalyseChimiqueDetailDto;

@Component
public class AnalyseChimiqueDetailConverter {

    @Autowired
    private ElementChimiqueConverter elementChimiqueConverter ;
    @Autowired
    private AnalyseChimiqueConverter analyseChimiqueConverter ;
    private boolean elementChimique;
    private boolean analyseChimique;

    public  AnalyseChimiqueDetailConverter() {
        initObject(true);
    }

    public AnalyseChimiqueDetail toItem(AnalyseChimiqueDetailDto dto) {
        if (dto == null) {
            return null;
        } else {
        AnalyseChimiqueDetail item = new AnalyseChimiqueDetail();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getValeur()))
                item.setValeur(dto.getValeur());
            if(dto.getConformite() != null)
                item.setConformite(dto.getConformite());
            if(dto.getSurqualite() != null)
                item.setSurqualite(dto.getSurqualite());
            if(this.elementChimique && dto.getElementChimique()!=null)
                item.setElementChimique(elementChimiqueConverter.toItem(dto.getElementChimique())) ;

            if(dto.getAnalyseChimique() != null && dto.getAnalyseChimique().getId() != null){
                item.setAnalyseChimique(new AnalyseChimique());
                item.getAnalyseChimique().setId(dto.getAnalyseChimique().getId());
                item.getAnalyseChimique().setLibelle(dto.getAnalyseChimique().getLibelle());
            }




        return item;
        }
    }


    public AnalyseChimiqueDetailDto toDto(AnalyseChimiqueDetail item) {
        if (item == null) {
            return null;
        } else {
            AnalyseChimiqueDetailDto dto = new AnalyseChimiqueDetailDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getValeur()))
                dto.setValeur(item.getValeur());
                dto.setConformite(item.getConformite());
                dto.setSurqualite(item.getSurqualite());
            if(this.elementChimique && item.getElementChimique()!=null) {
                dto.setElementChimique(elementChimiqueConverter.toDto(item.getElementChimique())) ;

            }
            if(this.analyseChimique && item.getAnalyseChimique()!=null) {
                dto.setAnalyseChimique(analyseChimiqueConverter.toDto(item.getAnalyseChimique())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.elementChimique = value;
        this.analyseChimique = value;
    }
	
    public List<AnalyseChimiqueDetail> toItem(List<AnalyseChimiqueDetailDto> dtos) {
        List<AnalyseChimiqueDetail> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AnalyseChimiqueDetailDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AnalyseChimiqueDetailDto> toDto(List<AnalyseChimiqueDetail> items) {
        List<AnalyseChimiqueDetailDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (AnalyseChimiqueDetail item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AnalyseChimiqueDetailDto dto, AnalyseChimiqueDetail t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getElementChimique() == null  && dto.getElementChimique() != null){
            t.setElementChimique(new ElementChimique());
        }else if (t.getElementChimique() != null  && dto.getElementChimique() != null){
            t.setElementChimique(null);
            t.setElementChimique(new ElementChimique());
        }
        if(t.getAnalyseChimique() == null  && dto.getAnalyseChimique() != null){
            t.setAnalyseChimique(new AnalyseChimique());
        }else if (t.getAnalyseChimique() != null  && dto.getAnalyseChimique() != null){
            t.setAnalyseChimique(null);
            t.setAnalyseChimique(new AnalyseChimique());
        }
        if (dto.getElementChimique() != null)
        elementChimiqueConverter.copy(dto.getElementChimique(), t.getElementChimique());
        if (dto.getAnalyseChimique() != null)
        analyseChimiqueConverter.copy(dto.getAnalyseChimique(), t.getAnalyseChimique());
    }

    public List<AnalyseChimiqueDetail> copy(List<AnalyseChimiqueDetailDto> dtos) {
        List<AnalyseChimiqueDetail> result = new ArrayList<>();
        if (dtos != null) {
            for (AnalyseChimiqueDetailDto dto : dtos) {
                AnalyseChimiqueDetail instance = new AnalyseChimiqueDetail();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ElementChimiqueConverter getElementChimiqueConverter(){
        return this.elementChimiqueConverter;
    }
    public void setElementChimiqueConverter(ElementChimiqueConverter elementChimiqueConverter ){
        this.elementChimiqueConverter = elementChimiqueConverter;
    }
    public AnalyseChimiqueConverter getAnalyseChimiqueConverter(){
        return this.analyseChimiqueConverter;
    }
    public void setAnalyseChimiqueConverter(AnalyseChimiqueConverter analyseChimiqueConverter ){
        this.analyseChimiqueConverter = analyseChimiqueConverter;
    }
    public boolean  isElementChimique(){
        return this.elementChimique;
    }
    public void  setElementChimique(boolean elementChimique){
        this.elementChimique = elementChimique;
    }
    public boolean  isAnalyseChimique(){
        return this.analyseChimique;
    }
    public void  setAnalyseChimique(boolean analyseChimique){
        this.analyseChimique = analyseChimique;
    }
}
