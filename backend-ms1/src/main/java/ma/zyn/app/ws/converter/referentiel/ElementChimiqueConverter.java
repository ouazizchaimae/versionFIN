package  ma.zyn.app.ws.converter.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.referentiel.UniteConverter;
import ma.zyn.app.bean.core.referentiel.Unite;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.ws.dto.referentiel.ElementChimiqueDto;

@Component
public class ElementChimiqueConverter {

    @Autowired
    private UniteConverter uniteConverter ;
    private boolean unite;

    public  ElementChimiqueConverter() {
        initObject(true);
    }

    public ElementChimique toItem(ElementChimiqueDto dto) {
        if (dto == null) {
            return null;
        } else {
        ElementChimique item = new ElementChimique();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.unite && dto.getUnite()!=null)
                item.setUnite(uniteConverter.toItem(dto.getUnite())) ;




        return item;
        }
    }


    public ElementChimiqueDto toDto(ElementChimique item) {
        if (item == null) {
            return null;
        } else {
            ElementChimiqueDto dto = new ElementChimiqueDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.unite && item.getUnite()!=null) {
                dto.setUnite(uniteConverter.toDto(item.getUnite())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.unite = value;
    }
	
    public List<ElementChimique> toItem(List<ElementChimiqueDto> dtos) {
        List<ElementChimique> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ElementChimiqueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ElementChimiqueDto> toDto(List<ElementChimique> items) {
        List<ElementChimiqueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ElementChimique item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ElementChimiqueDto dto, ElementChimique t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getUnite() == null  && dto.getUnite() != null){
            t.setUnite(new Unite());
        }else if (t.getUnite() != null  && dto.getUnite() != null){
            t.setUnite(null);
            t.setUnite(new Unite());
        }
        if (dto.getUnite() != null)
        uniteConverter.copy(dto.getUnite(), t.getUnite());
    }

    public List<ElementChimique> copy(List<ElementChimiqueDto> dtos) {
        List<ElementChimique> result = new ArrayList<>();
        if (dtos != null) {
            for (ElementChimiqueDto dto : dtos) {
                ElementChimique instance = new ElementChimique();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public UniteConverter getUniteConverter(){
        return this.uniteConverter;
    }
    public void setUniteConverter(UniteConverter uniteConverter ){
        this.uniteConverter = uniteConverter;
    }
    public boolean  isUnite(){
        return this.unite;
    }
    public void  setUnite(boolean unite){
        this.unite = unite;
    }
}
