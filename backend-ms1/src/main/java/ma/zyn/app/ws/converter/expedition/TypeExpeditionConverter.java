package  ma.zyn.app.ws.converter.expedition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.expedition.TypeExpedition;
import ma.zyn.app.ws.dto.expedition.TypeExpeditionDto;

@Component
public class TypeExpeditionConverter {



    public TypeExpedition toItem(TypeExpeditionDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeExpedition item = new TypeExpedition();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());



        return item;
        }
    }


    public TypeExpeditionDto toDto(TypeExpedition item) {
        if (item == null) {
            return null;
        } else {
            TypeExpeditionDto dto = new TypeExpeditionDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());


        return dto;
        }
    }


	
    public List<TypeExpedition> toItem(List<TypeExpeditionDto> dtos) {
        List<TypeExpedition> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeExpeditionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeExpeditionDto> toDto(List<TypeExpedition> items) {
        List<TypeExpeditionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeExpedition item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeExpeditionDto dto, TypeExpedition t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeExpedition> copy(List<TypeExpeditionDto> dtos) {
        List<TypeExpedition> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeExpeditionDto dto : dtos) {
                TypeExpedition instance = new TypeExpedition();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
