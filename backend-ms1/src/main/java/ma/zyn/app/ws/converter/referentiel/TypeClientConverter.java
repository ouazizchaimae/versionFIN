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
import ma.zyn.app.bean.core.referentiel.TypeClient;
import ma.zyn.app.ws.dto.referentiel.TypeClientDto;

@Component
public class TypeClientConverter {



    public TypeClient toItem(TypeClientDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeClient item = new TypeClient();
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


    public TypeClientDto toDto(TypeClient item) {
        if (item == null) {
            return null;
        } else {
            TypeClientDto dto = new TypeClientDto();
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


	
    public List<TypeClient> toItem(List<TypeClientDto> dtos) {
        List<TypeClient> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeClientDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeClientDto> toDto(List<TypeClient> items) {
        List<TypeClientDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeClient item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeClientDto dto, TypeClient t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeClient> copy(List<TypeClientDto> dtos) {
        List<TypeClient> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeClientDto dto : dtos) {
                TypeClient instance = new TypeClient();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
