package  ma.zyn.app.ws.converter.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.referentiel.StadeOperatoireProduitConverter;
import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit;
import ma.zyn.app.ws.converter.referentiel.EntiteConverter;
import ma.zyn.app.bean.core.referentiel.Entite;
import ma.zyn.app.ws.converter.referentiel.ProduitConverter;
import ma.zyn.app.bean.core.referentiel.Produit;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.ws.dto.referentiel.StadeOperatoireDto;

@Component
public class StadeOperatoireConverter {

    @Autowired
    private StadeOperatoireProduitConverter stadeOperatoireProduitConverter ;
    @Autowired
    private EntiteConverter entiteConverter ;
    @Autowired
    private ProduitConverter produitConverter ;
    private boolean entite;
    private boolean stadeOperatoireProduits;

    public  StadeOperatoireConverter() {
        init(true);
    }

    public StadeOperatoire toItem(StadeOperatoireDto dto) {
        if (dto == null) {
            return null;
        } else {
        StadeOperatoire item = new StadeOperatoire();
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
            if(StringUtil.isNotEmpty(dto.getCapaciteMin()))
                item.setCapaciteMin(dto.getCapaciteMin());
            if(StringUtil.isNotEmpty(dto.getCapaciteMax()))
                item.setCapaciteMax(dto.getCapaciteMax());
            if(StringUtil.isNotEmpty(dto.getIndice()))
                item.setIndice(dto.getIndice());
            if(this.entite && dto.getEntite()!=null)
                item.setEntite(entiteConverter.toItem(dto.getEntite())) ;


            if(this.stadeOperatoireProduits && ListUtil.isNotEmpty(dto.getStadeOperatoireProduits()))
                item.setStadeOperatoireProduits(stadeOperatoireProduitConverter.toItem(dto.getStadeOperatoireProduits()));


        return item;
        }
    }


    public StadeOperatoireDto toDto(StadeOperatoire item) {
        if (item == null) {
            return null;
        } else {
            StadeOperatoireDto dto = new StadeOperatoireDto();
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
            if(StringUtil.isNotEmpty(item.getCapaciteMin()))
                dto.setCapaciteMin(item.getCapaciteMin());
            if(StringUtil.isNotEmpty(item.getCapaciteMax()))
                dto.setCapaciteMax(item.getCapaciteMax());
            if(StringUtil.isNotEmpty(item.getIndice()))
                dto.setIndice(item.getIndice());
            if(this.entite && item.getEntite()!=null) {
                dto.setEntite(entiteConverter.toDto(item.getEntite())) ;

            }
        if(this.stadeOperatoireProduits && ListUtil.isNotEmpty(item.getStadeOperatoireProduits())){
            stadeOperatoireProduitConverter.init(true);
            stadeOperatoireProduitConverter.setStadeOperatoire(false);
            dto.setStadeOperatoireProduits(stadeOperatoireProduitConverter.toDto(item.getStadeOperatoireProduits()));
            stadeOperatoireProduitConverter.setStadeOperatoire(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.stadeOperatoireProduits = value;
    }
    public void initObject(boolean value) {
        this.entite = value;
    }
	
    public List<StadeOperatoire> toItem(List<StadeOperatoireDto> dtos) {
        List<StadeOperatoire> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StadeOperatoireDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StadeOperatoireDto> toDto(List<StadeOperatoire> items) {
        List<StadeOperatoireDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (StadeOperatoire item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StadeOperatoireDto dto, StadeOperatoire t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getEntite() == null  && dto.getEntite() != null){
            t.setEntite(new Entite());
        }else if (t.getEntite() != null  && dto.getEntite() != null){
            t.setEntite(null);
            t.setEntite(new Entite());
        }
        if (dto.getEntite() != null)
        entiteConverter.copy(dto.getEntite(), t.getEntite());
        if (dto.getStadeOperatoireProduits() != null)
            t.setStadeOperatoireProduits(stadeOperatoireProduitConverter.copy(dto.getStadeOperatoireProduits()));
    }

    public List<StadeOperatoire> copy(List<StadeOperatoireDto> dtos) {
        List<StadeOperatoire> result = new ArrayList<>();
        if (dtos != null) {
            for (StadeOperatoireDto dto : dtos) {
                StadeOperatoire instance = new StadeOperatoire();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public StadeOperatoireProduitConverter getStadeOperatoireProduitConverter(){
        return this.stadeOperatoireProduitConverter;
    }
    public void setStadeOperatoireProduitConverter(StadeOperatoireProduitConverter stadeOperatoireProduitConverter ){
        this.stadeOperatoireProduitConverter = stadeOperatoireProduitConverter;
    }
    public EntiteConverter getEntiteConverter(){
        return this.entiteConverter;
    }
    public void setEntiteConverter(EntiteConverter entiteConverter ){
        this.entiteConverter = entiteConverter;
    }
    public ProduitConverter getProduitConverter(){
        return this.produitConverter;
    }
    public void setProduitConverter(ProduitConverter produitConverter ){
        this.produitConverter = produitConverter;
    }
    public boolean  isEntite(){
        return this.entite;
    }
    public void  setEntite(boolean entite){
        this.entite = entite;
    }
    public boolean  isStadeOperatoireProduits(){
        return this.stadeOperatoireProduits ;
    }
    public void  setStadeOperatoireProduits(boolean stadeOperatoireProduits ){
        this.stadeOperatoireProduits  = stadeOperatoireProduits ;
    }
}
