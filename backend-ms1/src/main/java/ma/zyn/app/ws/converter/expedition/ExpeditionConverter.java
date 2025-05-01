package  ma.zyn.app.ws.converter.expedition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.expedition.ExpeditionProduitConverter;
import ma.zyn.app.bean.core.expedition.ExpeditionProduit;
import ma.zyn.app.ws.converter.expedition.AnalyseChimiqueConverter;
import ma.zyn.app.bean.core.expedition.AnalyseChimique;
import ma.zyn.app.ws.converter.expedition.TypeExpeditionConverter;
import ma.zyn.app.bean.core.expedition.TypeExpedition;
import ma.zyn.app.ws.converter.referentiel.ClientConverter;
import ma.zyn.app.bean.core.referentiel.Client;
import ma.zyn.app.ws.converter.expedition.CharteChimiqueConverter;
import ma.zyn.app.bean.core.expedition.CharteChimique;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.expedition.Expedition;
import ma.zyn.app.ws.dto.expedition.ExpeditionDto;

@Component
public class ExpeditionConverter {

    @Autowired
    private ExpeditionProduitConverter expeditionProduitConverter ;
    @Autowired
    private AnalyseChimiqueConverter analyseChimiqueConverter ;
    @Autowired
    private TypeExpeditionConverter typeExpeditionConverter ;
    @Autowired
    private ClientConverter clientConverter ;
    @Autowired
    private CharteChimiqueConverter charteChimiqueConverter ;
    private boolean client;
    private boolean typeExpedition;
    private boolean expeditionProduits;

    public  ExpeditionConverter() {
        init(true);
    }

    public Expedition toItem(ExpeditionDto dto) {
        if (dto == null) {
            return null;
        } else {
        Expedition item = new Expedition();
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

            if(this.typeExpedition && dto.getTypeExpedition()!=null)
                item.setTypeExpedition(typeExpeditionConverter.toItem(dto.getTypeExpedition())) ;


            if(this.expeditionProduits && ListUtil.isNotEmpty(dto.getExpeditionProduits()))
                item.setExpeditionProduits(expeditionProduitConverter.toItem(dto.getExpeditionProduits()));


        return item;
        }
    }


    public ExpeditionDto toDto(Expedition item) {
        if (item == null) {
            return null;
        } else {
            ExpeditionDto dto = new ExpeditionDto();
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
            if(this.typeExpedition && item.getTypeExpedition()!=null) {
                dto.setTypeExpedition(typeExpeditionConverter.toDto(item.getTypeExpedition())) ;

            }
        if(this.expeditionProduits && ListUtil.isNotEmpty(item.getExpeditionProduits())){
            expeditionProduitConverter.init(true);
            expeditionProduitConverter.setExpedition(false);
            dto.setExpeditionProduits(expeditionProduitConverter.toDto(item.getExpeditionProduits()));
            expeditionProduitConverter.setExpedition(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.expeditionProduits = value;
    }
    public void initObject(boolean value) {
        this.client = value;
        this.typeExpedition = value;
    }
	
    public List<Expedition> toItem(List<ExpeditionDto> dtos) {
        List<Expedition> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ExpeditionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ExpeditionDto> toDto(List<Expedition> items) {
        List<ExpeditionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Expedition item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ExpeditionDto dto, Expedition t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getClient() == null  && dto.getClient() != null){
            t.setClient(new Client());
        }else if (t.getClient() != null  && dto.getClient() != null){
            t.setClient(null);
            t.setClient(new Client());
        }
        if(t.getTypeExpedition() == null  && dto.getTypeExpedition() != null){
            t.setTypeExpedition(new TypeExpedition());
        }else if (t.getTypeExpedition() != null  && dto.getTypeExpedition() != null){
            t.setTypeExpedition(null);
            t.setTypeExpedition(new TypeExpedition());
        }
        if (dto.getClient() != null)
        clientConverter.copy(dto.getClient(), t.getClient());
        if (dto.getTypeExpedition() != null)
        typeExpeditionConverter.copy(dto.getTypeExpedition(), t.getTypeExpedition());
        if (dto.getExpeditionProduits() != null)
            t.setExpeditionProduits(expeditionProduitConverter.copy(dto.getExpeditionProduits()));
    }

    public List<Expedition> copy(List<ExpeditionDto> dtos) {
        List<Expedition> result = new ArrayList<>();
        if (dtos != null) {
            for (ExpeditionDto dto : dtos) {
                Expedition instance = new Expedition();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ExpeditionProduitConverter getExpeditionProduitConverter(){
        return this.expeditionProduitConverter;
    }
    public void setExpeditionProduitConverter(ExpeditionProduitConverter expeditionProduitConverter ){
        this.expeditionProduitConverter = expeditionProduitConverter;
    }
    public AnalyseChimiqueConverter getAnalyseChimiqueConverter(){
        return this.analyseChimiqueConverter;
    }
    public void setAnalyseChimiqueConverter(AnalyseChimiqueConverter analyseChimiqueConverter ){
        this.analyseChimiqueConverter = analyseChimiqueConverter;
    }
    public TypeExpeditionConverter getTypeExpeditionConverter(){
        return this.typeExpeditionConverter;
    }
    public void setTypeExpeditionConverter(TypeExpeditionConverter typeExpeditionConverter ){
        this.typeExpeditionConverter = typeExpeditionConverter;
    }
    public ClientConverter getClientConverter(){
        return this.clientConverter;
    }
    public void setClientConverter(ClientConverter clientConverter ){
        this.clientConverter = clientConverter;
    }
    public CharteChimiqueConverter getCharteChimiqueConverter(){
        return this.charteChimiqueConverter;
    }
    public void setCharteChimiqueConverter(CharteChimiqueConverter charteChimiqueConverter ){
        this.charteChimiqueConverter = charteChimiqueConverter;
    }
    public boolean  isClient(){
        return this.client;
    }
    public void  setClient(boolean client){
        this.client = client;
    }
    public boolean  isTypeExpedition(){
        return this.typeExpedition;
    }
    public void  setTypeExpedition(boolean typeExpedition){
        this.typeExpedition = typeExpedition;
    }
    public boolean  isExpeditionProduits(){
        return this.expeditionProduits ;
    }
    public void  setExpeditionProduits(boolean expeditionProduits ){
        this.expeditionProduits  = expeditionProduits ;
    }
}
