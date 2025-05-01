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
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.ws.dto.referentiel.TypeDemandeDto;

@Component
public class TypeDemandeConverter {



    public TypeDemande toItem(TypeDemandeDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeDemande item = new TypeDemande();
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


    public TypeDemandeDto toDto(TypeDemande item) {
        if (item == null) {
            return null;
        } else {
            TypeDemandeDto dto = new TypeDemandeDto();
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


	
    public List<TypeDemande> toItem(List<TypeDemandeDto> dtos) {
        List<TypeDemande> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeDemandeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeDemandeDto> toDto(List<TypeDemande> items) {
        List<TypeDemandeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeDemande item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeDemandeDto dto, TypeDemande t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeDemande> copy(List<TypeDemandeDto> dtos) {
        List<TypeDemande> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeDemandeDto dto : dtos) {
                TypeDemande instance = new TypeDemande();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
