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
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.ws.dto.referentiel.ProduitMarchandDto;

@Component
public class ProduitMarchandConverter {



    public ProduitMarchand toItem(ProduitMarchandDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProduitMarchand item = new ProduitMarchand();
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


    public ProduitMarchandDto toDto(ProduitMarchand item) {
        if (item == null) {
            return null;
        } else {
            ProduitMarchandDto dto = new ProduitMarchandDto();
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


	
    public List<ProduitMarchand> toItem(List<ProduitMarchandDto> dtos) {
        List<ProduitMarchand> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProduitMarchandDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProduitMarchandDto> toDto(List<ProduitMarchand> items) {
        List<ProduitMarchandDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProduitMarchand item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProduitMarchandDto dto, ProduitMarchand t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ProduitMarchand> copy(List<ProduitMarchandDto> dtos) {
        List<ProduitMarchand> result = new ArrayList<>();
        if (dtos != null) {
            for (ProduitMarchandDto dto : dtos) {
                ProduitMarchand instance = new ProduitMarchand();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
