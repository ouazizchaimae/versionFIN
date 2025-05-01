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
import ma.zyn.app.bean.core.referentiel.Entite;
import ma.zyn.app.ws.dto.referentiel.EntiteDto;

@Component
public class EntiteConverter {



    public Entite toItem(EntiteDto dto) {
        if (dto == null) {
            return null;
        } else {
        Entite item = new Entite();
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



        return item;
        }
    }


    public EntiteDto toDto(Entite item) {
        if (item == null) {
            return null;
        } else {
            EntiteDto dto = new EntiteDto();
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


        return dto;
        }
    }


	
    public List<Entite> toItem(List<EntiteDto> dtos) {
        List<Entite> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EntiteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EntiteDto> toDto(List<Entite> items) {
        List<EntiteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Entite item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EntiteDto dto, Entite t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Entite> copy(List<EntiteDto> dtos) {
        List<Entite> result = new ArrayList<>();
        if (dtos != null) {
            for (EntiteDto dto : dtos) {
                Entite instance = new Entite();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
