package  ma.zyn.app.ws.converter.expedition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.referentiel.ElementChimiqueConverter;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.ws.converter.expedition.CharteChimiqueConverter;
import ma.zyn.app.bean.core.expedition.CharteChimique;

import ma.zyn.app.bean.core.expedition.CharteChimique;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail;
import ma.zyn.app.ws.dto.expedition.CharteChimiqueDetailDto;

@Component
public class CharteChimiqueDetailConverter {

    @Autowired
    private ElementChimiqueConverter elementChimiqueConverter ;
    @Autowired
    private CharteChimiqueConverter charteChimiqueConverter ;
    private boolean elementChimique;
    private boolean charteChimique;

    public  CharteChimiqueDetailConverter() {
        initObject(true);
    }

    public CharteChimiqueDetail toItem(CharteChimiqueDetailDto dto) {
        if (dto == null) {
            return null;
        } else {
        CharteChimiqueDetail item = new CharteChimiqueDetail();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getMinimum()))
                item.setMinimum(dto.getMinimum());
            if(StringUtil.isNotEmpty(dto.getMaximum()))
                item.setMaximum(dto.getMaximum());
            if(StringUtil.isNotEmpty(dto.getAverage()))
                item.setAverage(dto.getAverage());
            if(StringUtil.isNotEmpty(dto.getMethodeAnalyse()))
                item.setMethodeAnalyse(dto.getMethodeAnalyse());
            if(StringUtil.isNotEmpty(dto.getUnite()))
                item.setUnite(dto.getUnite());
            if(this.elementChimique && dto.getElementChimique()!=null)
                item.setElementChimique(elementChimiqueConverter.toItem(dto.getElementChimique())) ;

            if(dto.getCharteChimique() != null && dto.getCharteChimique().getId() != null){
                item.setCharteChimique(new CharteChimique());
                item.getCharteChimique().setId(dto.getCharteChimique().getId());
                item.getCharteChimique().setLibelle(dto.getCharteChimique().getLibelle());
            }




        return item;
        }
    }


    public CharteChimiqueDetailDto toDto(CharteChimiqueDetail item) {
        if (item == null) {
            return null;
        } else {
            CharteChimiqueDetailDto dto = new CharteChimiqueDetailDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getMinimum()))
                dto.setMinimum(item.getMinimum());
            if(StringUtil.isNotEmpty(item.getMaximum()))
                dto.setMaximum(item.getMaximum());
            if(StringUtil.isNotEmpty(item.getAverage()))
                dto.setAverage(item.getAverage());
            if(StringUtil.isNotEmpty(item.getMethodeAnalyse()))
                dto.setMethodeAnalyse(item.getMethodeAnalyse());
            if(StringUtil.isNotEmpty(item.getUnite()))
                dto.setUnite(item.getUnite());
            if(this.elementChimique && item.getElementChimique()!=null) {
                dto.setElementChimique(elementChimiqueConverter.toDto(item.getElementChimique())) ;

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
        this.elementChimique = value;
        this.charteChimique = value;
    }
	
    public List<CharteChimiqueDetail> toItem(List<CharteChimiqueDetailDto> dtos) {
        List<CharteChimiqueDetail> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CharteChimiqueDetailDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CharteChimiqueDetailDto> toDto(List<CharteChimiqueDetail> items) {
        List<CharteChimiqueDetailDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CharteChimiqueDetail item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CharteChimiqueDetailDto dto, CharteChimiqueDetail t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getElementChimique() == null  && dto.getElementChimique() != null){
            t.setElementChimique(new ElementChimique());
        }else if (t.getElementChimique() != null  && dto.getElementChimique() != null){
            t.setElementChimique(null);
            t.setElementChimique(new ElementChimique());
        }
        if(t.getCharteChimique() == null  && dto.getCharteChimique() != null){
            t.setCharteChimique(new CharteChimique());
        }else if (t.getCharteChimique() != null  && dto.getCharteChimique() != null){
            t.setCharteChimique(null);
            t.setCharteChimique(new CharteChimique());
        }
        if (dto.getElementChimique() != null)
        elementChimiqueConverter.copy(dto.getElementChimique(), t.getElementChimique());
        if (dto.getCharteChimique() != null)
        charteChimiqueConverter.copy(dto.getCharteChimique(), t.getCharteChimique());
    }

    public List<CharteChimiqueDetail> copy(List<CharteChimiqueDetailDto> dtos) {
        List<CharteChimiqueDetail> result = new ArrayList<>();
        if (dtos != null) {
            for (CharteChimiqueDetailDto dto : dtos) {
                CharteChimiqueDetail instance = new CharteChimiqueDetail();
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
    public CharteChimiqueConverter getCharteChimiqueConverter(){
        return this.charteChimiqueConverter;
    }
    public void setCharteChimiqueConverter(CharteChimiqueConverter charteChimiqueConverter ){
        this.charteChimiqueConverter = charteChimiqueConverter;
    }
    public boolean  isElementChimique(){
        return this.elementChimique;
    }
    public void  setElementChimique(boolean elementChimique){
        this.elementChimique = elementChimique;
    }
    public boolean  isCharteChimique(){
        return this.charteChimique;
    }
    public void  setCharteChimique(boolean charteChimique){
        this.charteChimique = charteChimique;
    }
}
