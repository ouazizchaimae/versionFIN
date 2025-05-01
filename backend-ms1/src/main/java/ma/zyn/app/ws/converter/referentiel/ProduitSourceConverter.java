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
import ma.zyn.app.bean.core.referentiel.ProduitSource;
import ma.zyn.app.ws.dto.referentiel.ProduitSourceDto;

@Component
public class ProduitSourceConverter {



    public ProduitSource toItem(ProduitSourceDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProduitSource item = new ProduitSource();
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


    public ProduitSourceDto toDto(ProduitSource item) {
        if (item == null) {
            return null;
        } else {
            ProduitSourceDto dto = new ProduitSourceDto();
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


	
    public List<ProduitSource> toItem(List<ProduitSourceDto> dtos) {
        List<ProduitSource> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProduitSourceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProduitSourceDto> toDto(List<ProduitSource> items) {
        List<ProduitSourceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProduitSource item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProduitSourceDto dto, ProduitSource t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ProduitSource> copy(List<ProduitSourceDto> dtos) {
        List<ProduitSource> result = new ArrayList<>();
        if (dtos != null) {
            for (ProduitSourceDto dto : dtos) {
                ProduitSource instance = new ProduitSource();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
