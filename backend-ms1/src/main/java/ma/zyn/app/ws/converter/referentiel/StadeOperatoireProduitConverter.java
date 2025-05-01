package  ma.zyn.app.ws.converter.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.referentiel.StadeOperatoireConverter;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.ws.converter.referentiel.ProduitConverter;
import ma.zyn.app.bean.core.referentiel.Produit;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.StadeOperatoireProduit;
import ma.zyn.app.ws.dto.referentiel.StadeOperatoireProduitDto;

@Component
public class StadeOperatoireProduitConverter {

    @Autowired
    private StadeOperatoireConverter stadeOperatoireConverter ;
    @Autowired
    private ProduitConverter produitConverter ;
    private boolean stadeOperatoire;
    private boolean produit;

    public  StadeOperatoireProduitConverter() {
        initObject(true);
    }

    public StadeOperatoireProduit toItem(StadeOperatoireProduitDto dto) {
        if (dto == null) {
            return null;
        } else {
        StadeOperatoireProduit item = new StadeOperatoireProduit();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getStadeOperatoire() != null && dto.getStadeOperatoire().getId() != null){
                item.setStadeOperatoire(new StadeOperatoire());
                item.getStadeOperatoire().setId(dto.getStadeOperatoire().getId());
                item.getStadeOperatoire().setLibelle(dto.getStadeOperatoire().getLibelle());
            }

            if(this.produit && dto.getProduit()!=null)
                item.setProduit(produitConverter.toItem(dto.getProduit())) ;




        return item;
        }
    }


    public StadeOperatoireProduitDto toDto(StadeOperatoireProduit item) {
        if (item == null) {
            return null;
        } else {
            StadeOperatoireProduitDto dto = new StadeOperatoireProduitDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.stadeOperatoire && item.getStadeOperatoire()!=null) {
                dto.setStadeOperatoire(stadeOperatoireConverter.toDto(item.getStadeOperatoire())) ;

            }
            if(this.produit && item.getProduit()!=null) {
                dto.setProduit(produitConverter.toDto(item.getProduit())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.stadeOperatoire = value;
        this.produit = value;
    }
	
    public List<StadeOperatoireProduit> toItem(List<StadeOperatoireProduitDto> dtos) {
        List<StadeOperatoireProduit> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StadeOperatoireProduitDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StadeOperatoireProduitDto> toDto(List<StadeOperatoireProduit> items) {
        List<StadeOperatoireProduitDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (StadeOperatoireProduit item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StadeOperatoireProduitDto dto, StadeOperatoireProduit t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getStadeOperatoire() == null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(new StadeOperatoire());
        }else if (t.getStadeOperatoire() != null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(null);
            t.setStadeOperatoire(new StadeOperatoire());
        }
        if(t.getProduit() == null  && dto.getProduit() != null){
            t.setProduit(new Produit());
        }else if (t.getProduit() != null  && dto.getProduit() != null){
            t.setProduit(null);
            t.setProduit(new Produit());
        }
        if (dto.getStadeOperatoire() != null)
        stadeOperatoireConverter.copy(dto.getStadeOperatoire(), t.getStadeOperatoire());
        if (dto.getProduit() != null)
        produitConverter.copy(dto.getProduit(), t.getProduit());
    }

    public List<StadeOperatoireProduit> copy(List<StadeOperatoireProduitDto> dtos) {
        List<StadeOperatoireProduit> result = new ArrayList<>();
        if (dtos != null) {
            for (StadeOperatoireProduitDto dto : dtos) {
                StadeOperatoireProduit instance = new StadeOperatoireProduit();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public StadeOperatoireConverter getStadeOperatoireConverter(){
        return this.stadeOperatoireConverter;
    }
    public void setStadeOperatoireConverter(StadeOperatoireConverter stadeOperatoireConverter ){
        this.stadeOperatoireConverter = stadeOperatoireConverter;
    }
    public ProduitConverter getProduitConverter(){
        return this.produitConverter;
    }
    public void setProduitConverter(ProduitConverter produitConverter ){
        this.produitConverter = produitConverter;
    }
    public boolean  isStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void  setStadeOperatoire(boolean stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public boolean  isProduit(){
        return this.produit;
    }
    public void  setProduit(boolean produit){
        this.produit = produit;
    }
}
