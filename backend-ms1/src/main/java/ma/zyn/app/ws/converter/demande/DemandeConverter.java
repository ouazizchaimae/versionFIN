package  ma.zyn.app.ws.converter.demande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.referentiel.EtatDemandeConverter;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.ws.converter.referentiel.ProduitMarchandConverter;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.ws.converter.referentiel.TypeDemandeConverter;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.ws.converter.referentiel.ClientConverter;
import ma.zyn.app.bean.core.referentiel.Client;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.ws.dto.demande.DemandeDto;

@Component
public class DemandeConverter {

    @Autowired
    private EtatDemandeConverter etatDemandeConverter ;
    @Autowired
    private ProduitMarchandConverter produitMarchandConverter ;
    @Autowired
    private TypeDemandeConverter typeDemandeConverter ;
    @Autowired
    private ClientConverter clientConverter ;
    private boolean produitMarchand;
    private boolean client;
    private boolean typeDemande;
    private boolean etatDemande;

    public  DemandeConverter() {
        initObject(true);
    }

    public Demande toItem(DemandeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Demande item = new Demande();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getDateDemande()))
                item.setDateDemande(DateUtil.stringEnToDate(dto.getDateDemande()));
            if(StringUtil.isNotEmpty(dto.getDateExpedition()))
                item.setDateExpedition(DateUtil.stringEnToDate(dto.getDateExpedition()));
            if(StringUtil.isNotEmpty(dto.getVolume()))
                item.setVolume(dto.getVolume());
            if(StringUtil.isNotEmpty(dto.getActionEntreprise()))
                item.setActionEntreprise(dto.getActionEntreprise());
            if(StringUtil.isNotEmpty(dto.getTrg()))
                item.setTrg(dto.getTrg());
            if(StringUtil.isNotEmpty(dto.getCause()))
                item.setCause(dto.getCause());
            if(StringUtil.isNotEmpty(dto.getCommentaire()))
                item.setCommentaire(dto.getCommentaire());
            if(this.produitMarchand && dto.getProduitMarchand()!=null)
                item.setProduitMarchand(produitMarchandConverter.toItem(dto.getProduitMarchand())) ;

            if(this.client && dto.getClient()!=null)
                item.setClient(clientConverter.toItem(dto.getClient())) ;

            if(this.typeDemande && dto.getTypeDemande()!=null)
                item.setTypeDemande(typeDemandeConverter.toItem(dto.getTypeDemande())) ;

            if(this.etatDemande && dto.getEtatDemande()!=null)
                item.setEtatDemande(etatDemandeConverter.toItem(dto.getEtatDemande())) ;




        return item;
        }
    }


    public DemandeDto toDto(Demande item) {
        if (item == null) {
            return null;
        } else {
            DemandeDto dto = new DemandeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getDateDemande()!=null)
                dto.setDateDemande(DateUtil.dateTimeToString(item.getDateDemande()));
            if(item.getDateExpedition()!=null)
                dto.setDateExpedition(DateUtil.dateTimeToString(item.getDateExpedition()));
            if(StringUtil.isNotEmpty(item.getVolume()))
                dto.setVolume(item.getVolume());
            if(StringUtil.isNotEmpty(item.getActionEntreprise()))
                dto.setActionEntreprise(item.getActionEntreprise());
            if(StringUtil.isNotEmpty(item.getTrg()))
                dto.setTrg(item.getTrg());
            if(StringUtil.isNotEmpty(item.getCause()))
                dto.setCause(item.getCause());
            if(StringUtil.isNotEmpty(item.getCommentaire()))
                dto.setCommentaire(item.getCommentaire());
            if(this.produitMarchand && item.getProduitMarchand()!=null) {
                dto.setProduitMarchand(produitMarchandConverter.toDto(item.getProduitMarchand())) ;

            }
            if(this.client && item.getClient()!=null) {
                dto.setClient(clientConverter.toDto(item.getClient())) ;

            }
            if(this.typeDemande && item.getTypeDemande()!=null) {
                dto.setTypeDemande(typeDemandeConverter.toDto(item.getTypeDemande())) ;

            }
            if(this.etatDemande && item.getEtatDemande()!=null) {
                dto.setEtatDemande(etatDemandeConverter.toDto(item.getEtatDemande())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.produitMarchand = value;
        this.client = value;
        this.typeDemande = value;
        this.etatDemande = value;
    }
	
    public List<Demande> toItem(List<DemandeDto> dtos) {
        List<Demande> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DemandeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DemandeDto> toDto(List<Demande> items) {
        List<DemandeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Demande item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DemandeDto dto, Demande t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProduitMarchand() == null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(new ProduitMarchand());
        }else if (t.getProduitMarchand() != null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(null);
            t.setProduitMarchand(new ProduitMarchand());
        }
        if(t.getClient() == null  && dto.getClient() != null){
            t.setClient(new Client());
        }else if (t.getClient() != null  && dto.getClient() != null){
            t.setClient(null);
            t.setClient(new Client());
        }
        if(t.getTypeDemande() == null  && dto.getTypeDemande() != null){
            t.setTypeDemande(new TypeDemande());
        }else if (t.getTypeDemande() != null  && dto.getTypeDemande() != null){
            t.setTypeDemande(null);
            t.setTypeDemande(new TypeDemande());
        }
        if(t.getEtatDemande() == null  && dto.getEtatDemande() != null){
            t.setEtatDemande(new EtatDemande());
        }else if (t.getEtatDemande() != null  && dto.getEtatDemande() != null){
            t.setEtatDemande(null);
            t.setEtatDemande(new EtatDemande());
        }
        if (dto.getProduitMarchand() != null)
        produitMarchandConverter.copy(dto.getProduitMarchand(), t.getProduitMarchand());
        if (dto.getClient() != null)
        clientConverter.copy(dto.getClient(), t.getClient());
        if (dto.getTypeDemande() != null)
        typeDemandeConverter.copy(dto.getTypeDemande(), t.getTypeDemande());
        if (dto.getEtatDemande() != null)
        etatDemandeConverter.copy(dto.getEtatDemande(), t.getEtatDemande());
    }

    public List<Demande> copy(List<DemandeDto> dtos) {
        List<Demande> result = new ArrayList<>();
        if (dtos != null) {
            for (DemandeDto dto : dtos) {
                Demande instance = new Demande();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EtatDemandeConverter getEtatDemandeConverter(){
        return this.etatDemandeConverter;
    }
    public void setEtatDemandeConverter(EtatDemandeConverter etatDemandeConverter ){
        this.etatDemandeConverter = etatDemandeConverter;
    }
    public ProduitMarchandConverter getProduitMarchandConverter(){
        return this.produitMarchandConverter;
    }
    public void setProduitMarchandConverter(ProduitMarchandConverter produitMarchandConverter ){
        this.produitMarchandConverter = produitMarchandConverter;
    }
    public TypeDemandeConverter getTypeDemandeConverter(){
        return this.typeDemandeConverter;
    }
    public void setTypeDemandeConverter(TypeDemandeConverter typeDemandeConverter ){
        this.typeDemandeConverter = typeDemandeConverter;
    }
    public ClientConverter getClientConverter(){
        return this.clientConverter;
    }
    public void setClientConverter(ClientConverter clientConverter ){
        this.clientConverter = clientConverter;
    }
    public boolean  isProduitMarchand(){
        return this.produitMarchand;
    }
    public void  setProduitMarchand(boolean produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    public boolean  isClient(){
        return this.client;
    }
    public void  setClient(boolean client){
        this.client = client;
    }
    public boolean  isTypeDemande(){
        return this.typeDemande;
    }
    public void  setTypeDemande(boolean typeDemande){
        this.typeDemande = typeDemande;
    }
    public boolean  isEtatDemande(){
        return this.etatDemande;
    }
    public void  setEtatDemande(boolean etatDemande){
        this.etatDemande = etatDemande;
    }
}
