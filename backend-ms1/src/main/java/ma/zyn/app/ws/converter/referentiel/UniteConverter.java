package  ma.zyn.app.ws.converter.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.ws.dto.referentiel.UniteDto;

@Component
public class UniteConverter {



    public Unite toItem(UniteDto dto) {
        if (dto == null) {
            return null;
        } else {
        Unite item = new Unite();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());



        return item;
        }
    }


    public UniteDto toDto(Unite item) {
        if (item == null) {
            return null;
        } else {
            UniteDto dto = new UniteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());


        return dto;
        }
    }


	
    public List<Unite> toItem(List<UniteDto> dtos) {
        List<Unite> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (UniteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<UniteDto> toDto(List<Unite> items) {
        List<UniteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Unite item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(UniteDto dto, Unite t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Unite> copy(List<UniteDto> dtos) {
        List<Unite> result = new ArrayList<>();
        if (dtos != null) {
            for (UniteDto dto : dtos) {
                Unite instance = new Unite();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
