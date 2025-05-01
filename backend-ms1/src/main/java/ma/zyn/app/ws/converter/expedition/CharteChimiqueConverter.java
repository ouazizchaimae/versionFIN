package  ma.zyn.app.ws.converter.expedition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.expedition.CharteChimiqueDetailConverter;
import ma.zyn.app.bean.core.expedition.CharteChimiqueDetail;
import ma.zyn.app.ws.converter.referentiel.ElementChimiqueConverter;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.ws.converter.referentiel.ProduitMarchandConverter;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.ws.converter.referentiel.ClientConverter;
import ma.zyn.app.bean.core.referentiel.Client;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.expedition.CharteChimique;
import ma.zyn.app.ws.dto.expedition.CharteChimiqueDto;

@Component
public class CharteChimiqueConverter {

    @Autowired
    private CharteChimiqueDetailConverter charteChimiqueDetailConverter ;
    @Autowired
    private ElementChimiqueConverter elementChimiqueConverter ;
    @Autowired
    private ProduitMarchandConverter produitMarchandConverter ;
    @Autowired
    private ClientConverter clientConverter ;
    private boolean client;
    private boolean produitMarchand;
    private boolean charteChimiqueDetails;

    public  CharteChimiqueConverter() {
        init(true);
    }

    public CharteChimique toItem(CharteChimiqueDto dto) {
        if (dto == null) {
            return null;
        } else {
        CharteChimique item = new CharteChimique();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.client && dto.getClient()!=null)
                item.setClient(clientConverter.toItem(dto.getClient())) ;

            if(this.produitMarchand && dto.getProduitMarchand()!=null)
                item.setProduitMarchand(produitMarchandConverter.toItem(dto.getProduitMarchand())) ;


            if(this.charteChimiqueDetails && ListUtil.isNotEmpty(dto.getCharteChimiqueDetails()))
                item.setCharteChimiqueDetails(charteChimiqueDetailConverter.toItem(dto.getCharteChimiqueDetails()));


        return item;
        }
    }


    public CharteChimiqueDto toDto(CharteChimique item) {
        if (item == null) {
            return null;
        } else {
            CharteChimiqueDto dto = new CharteChimiqueDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.client && item.getClient()!=null) {
                dto.setClient(clientConverter.toDto(item.getClient())) ;

            }
            if(this.produitMarchand && item.getProduitMarchand()!=null) {
                dto.setProduitMarchand(produitMarchandConverter.toDto(item.getProduitMarchand())) ;

            }
        if(this.charteChimiqueDetails && ListUtil.isNotEmpty(item.getCharteChimiqueDetails())){
            charteChimiqueDetailConverter.init(true);
            charteChimiqueDetailConverter.setCharteChimique(false);
            dto.setCharteChimiqueDetails(charteChimiqueDetailConverter.toDto(item.getCharteChimiqueDetails()));
            charteChimiqueDetailConverter.setCharteChimique(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.charteChimiqueDetails = value;
    }
    public void initObject(boolean value) {
        this.client = value;
        this.produitMarchand = value;
    }
	
    public List<CharteChimique> toItem(List<CharteChimiqueDto> dtos) {
        List<CharteChimique> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CharteChimiqueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CharteChimiqueDto> toDto(List<CharteChimique> items) {
        List<CharteChimiqueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CharteChimique item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CharteChimiqueDto dto, CharteChimique t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getClient() == null  && dto.getClient() != null){
            t.setClient(new Client());
        }else if (t.getClient() != null  && dto.getClient() != null){
            t.setClient(null);
            t.setClient(new Client());
        }
        if(t.getProduitMarchand() == null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(new ProduitMarchand());
        }else if (t.getProduitMarchand() != null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(null);
            t.setProduitMarchand(new ProduitMarchand());
        }
        if (dto.getClient() != null)
        clientConverter.copy(dto.getClient(), t.getClient());
        if (dto.getProduitMarchand() != null)
        produitMarchandConverter.copy(dto.getProduitMarchand(), t.getProduitMarchand());
        if (dto.getCharteChimiqueDetails() != null)
            t.setCharteChimiqueDetails(charteChimiqueDetailConverter.copy(dto.getCharteChimiqueDetails()));
    }

    public List<CharteChimique> copy(List<CharteChimiqueDto> dtos) {
        List<CharteChimique> result = new ArrayList<>();
        if (dtos != null) {
            for (CharteChimiqueDto dto : dtos) {
                CharteChimique instance = new CharteChimique();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CharteChimiqueDetailConverter getCharteChimiqueDetailConverter(){
        return this.charteChimiqueDetailConverter;
    }
    public void setCharteChimiqueDetailConverter(CharteChimiqueDetailConverter charteChimiqueDetailConverter ){
        this.charteChimiqueDetailConverter = charteChimiqueDetailConverter;
    }
    public ElementChimiqueConverter getElementChimiqueConverter(){
        return this.elementChimiqueConverter;
    }
    public void setElementChimiqueConverter(ElementChimiqueConverter elementChimiqueConverter ){
        this.elementChimiqueConverter = elementChimiqueConverter;
    }
    public ProduitMarchandConverter getProduitMarchandConverter(){
        return this.produitMarchandConverter;
    }
    public void setProduitMarchandConverter(ProduitMarchandConverter produitMarchandConverter ){
        this.produitMarchandConverter = produitMarchandConverter;
    }
    public ClientConverter getClientConverter(){
        return this.clientConverter;
    }
    public void setClientConverter(ClientConverter clientConverter ){
        this.clientConverter = clientConverter;
    }
    public boolean  isClient(){
        return this.client;
    }
    public void  setClient(boolean client){
        this.client = client;
    }
    public boolean  isProduitMarchand(){
        return this.produitMarchand;
    }
    public void  setProduitMarchand(boolean produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    public boolean  isCharteChimiqueDetails(){
        return this.charteChimiqueDetails ;
    }
    public void  setCharteChimiqueDetails(boolean charteChimiqueDetails ){
        this.charteChimiqueDetails  = charteChimiqueDetails ;
    }
}
