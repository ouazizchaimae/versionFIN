package  ma.zyn.app.ws.converter.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.referentiel.TypeClientConverter;
import ma.zyn.app.bean.core.referentiel.TypeClient;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.Client;
import ma.zyn.app.ws.dto.referentiel.ClientDto;

@Component
public class ClientConverter {

    @Autowired
    private TypeClientConverter typeClientConverter ;
    private boolean typeClient;

    public  ClientConverter() {
        initObject(true);
    }

    public Client toItem(ClientDto dto) {
        if (dto == null) {
            return null;
        } else {
        Client item = new Client();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.typeClient && dto.getTypeClient()!=null)
                item.setTypeClient(typeClientConverter.toItem(dto.getTypeClient())) ;




        return item;
        }
    }


    public ClientDto toDto(Client item) {
        if (item == null) {
            return null;
        } else {
            ClientDto dto = new ClientDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.typeClient && item.getTypeClient()!=null) {
                dto.setTypeClient(typeClientConverter.toDto(item.getTypeClient())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.typeClient = value;
    }
	
    public List<Client> toItem(List<ClientDto> dtos) {
        List<Client> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ClientDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ClientDto> toDto(List<Client> items) {
        List<ClientDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Client item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ClientDto dto, Client t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getTypeClient() == null  && dto.getTypeClient() != null){
            t.setTypeClient(new TypeClient());
        }else if (t.getTypeClient() != null  && dto.getTypeClient() != null){
            t.setTypeClient(null);
            t.setTypeClient(new TypeClient());
        }
        if (dto.getTypeClient() != null)
        typeClientConverter.copy(dto.getTypeClient(), t.getTypeClient());
    }

    public List<Client> copy(List<ClientDto> dtos) {
        List<Client> result = new ArrayList<>();
        if (dtos != null) {
            for (ClientDto dto : dtos) {
                Client instance = new Client();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TypeClientConverter getTypeClientConverter(){
        return this.typeClientConverter;
    }
    public void setTypeClientConverter(TypeClientConverter typeClientConverter ){
        this.typeClientConverter = typeClientConverter;
    }
    public boolean  isTypeClient(){
        return this.typeClient;
    }
    public void  setTypeClient(boolean typeClient){
        this.typeClient = typeClient;
    }
}
