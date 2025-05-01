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
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.ws.dto.referentiel.EtatDemandeDto;

@Component
public class EtatDemandeConverter {



    public EtatDemande toItem(EtatDemandeDto dto) {
        if (dto == null) {
            return null;
        } else {
        EtatDemande item = new EtatDemande();
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


    public EtatDemandeDto toDto(EtatDemande item) {
        if (item == null) {
            return null;
        } else {
            EtatDemandeDto dto = new EtatDemandeDto();
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


	
    public List<EtatDemande> toItem(List<EtatDemandeDto> dtos) {
        List<EtatDemande> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EtatDemandeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EtatDemandeDto> toDto(List<EtatDemande> items) {
        List<EtatDemandeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EtatDemande item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EtatDemandeDto dto, EtatDemande t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<EtatDemande> copy(List<EtatDemandeDto> dtos) {
        List<EtatDemande> result = new ArrayList<>();
        if (dtos != null) {
            for (EtatDemandeDto dto : dtos) {
                EtatDemande instance = new EtatDemande();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
