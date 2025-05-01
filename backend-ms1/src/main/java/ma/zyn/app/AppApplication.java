package ma.zyn.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;


import ma.zyn.app.zynerator.security.bean.*;
import ma.zyn.app.zynerator.security.common.AuthoritiesConstants;
import ma.zyn.app.zynerator.security.service.facade.*;

import ma.zyn.app.bean.core.referentiel.ProduitSource;
import ma.zyn.app.service.facade.admin.referentiel.ProduitSourceAdminService;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.service.facade.admin.referentiel.EtatDemandeAdminService;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.service.facade.admin.referentiel.ProduitMarchandAdminService;
import ma.zyn.app.bean.core.referentiel.Entite;
import ma.zyn.app.service.facade.admin.referentiel.EntiteAdminService;
import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.service.facade.admin.referentiel.UniteAdminService;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.service.facade.admin.referentiel.TypeDemandeAdminService;
import ma.zyn.app.bean.core.expedition.TypeExpedition;
import ma.zyn.app.service.facade.admin.expedition.TypeExpeditionAdminService;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.service.facade.admin.referentiel.ElementChimiqueAdminService;
import ma.zyn.app.bean.core.referentiel.TypeClient;
import ma.zyn.app.service.facade.admin.referentiel.TypeClientAdminService;

import ma.zyn.app.zynerator.security.bean.User;
import ma.zyn.app.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class AppApplication {
    public static ConfigurableApplicationContext ctx;


    //state: primary success info secondary warning danger contrast
    //_STATE(Pending=warning,Rejeted=danger,Validated=success)
    public static void main(String[] args) {
        ctx=SpringApplication.run(AppApplication.class, args);
    }


    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService ) {
    return (args) -> {
        if(true){

            createProduitSource();
            createEtatDemande();
            createProduitMarchand();
            createEntite();
            createUnite();
            createTypeDemande();
            createTypeExpedition();
            createStadeOperatoire();
            createElementChimique();
            createTypeClient();

        /*
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));
        */

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

            }
        };
    }



    private void createProduitSource(){
            ProduitSource itemInfo = new ProduitSource();
            itemInfo.setStyle("info");
            itemInfo.setLibelle("BT");
            itemInfo.setCode("BT");
            produitSourceService.create(itemInfo);
            ProduitSource itemPrimary = new ProduitSource();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("TBT");
            itemPrimary.setCode("TBT");
            produitSourceService.create(itemPrimary);
            ProduitSource itemDanger = new ProduitSource();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("MT");
            itemDanger.setCode("MT");
            produitSourceService.create(itemDanger);

    }
    private void createEtatDemande(){
            EtatDemande itemPrimary = new EtatDemande();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("En cours");
            itemPrimary.setCode("En cours");
            etatDemandeService.create(itemPrimary);
            EtatDemande itemSecondary = new EtatDemande();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Cloture");
            itemSecondary.setCode("Cloture");
            etatDemandeService.create(itemSecondary);
            EtatDemande itemDanger = new EtatDemande();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Accepte");
            itemDanger.setCode("Accepte");
            etatDemandeService.create(itemDanger);

    }
    private void createProduitMarchand(){
            ProduitMarchand itemPrimary = new ProduitMarchand();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("G10");
            itemPrimary.setCode("G10");
            produitMarchandService.create(itemPrimary);
            ProduitMarchand itemDanger = new ProduitMarchand();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("BG22");
            itemDanger.setCode("BG22");
            produitMarchandService.create(itemDanger);
            ProduitMarchand itemInfo = new ProduitMarchand();
            itemInfo.setStyle("info");
            itemInfo.setLibelle("BG10");
            itemInfo.setCode("BG10");
            produitMarchandService.create(itemInfo);
            ProduitMarchand itemSuccess = new ProduitMarchand();
            itemSuccess.setStyle("success");
            itemSuccess.setLibelle("Ycc01");
            itemSuccess.setCode("Ycc01");
            produitMarchandService.create(itemSuccess);

    }
    private void createEntite(){
            Entite itemPrimary = new Entite();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Mine Mzinda");
            itemPrimary.setCode("Mine Mzinda");
            entiteService.create(itemPrimary);
            Entite itemSecondary = new Entite();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Mine Bouchane");
            itemSecondary.setCode("Mine Bouchane");
            entiteService.create(itemSecondary);
            Entite itemDanger = new Entite();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Laverie Benguerir");
            itemDanger.setCode("Laverie Benguerir");
            entiteService.create(itemDanger);
            Entite itemDark = new Entite();
            itemDark.setStyle("dark");
            itemDark.setLibelle("Calcination");
            itemDark.setCode("Calcination");
            entiteService.create(itemDark);
            Entite itemInfo = new Entite();
            itemInfo.setStyle("info");
            itemInfo.setLibelle("Sechage");
            itemInfo.setCode("Sechage");
            entiteService.create(itemInfo);
            Entite itemSuccess = new Entite();
            itemSuccess.setStyle("success");
            itemSuccess.setLibelle("Mine Youssoufia");
            itemSuccess.setCode("Mine Youssoufia");
            entiteService.create(itemSuccess);
            Entite itemWarning = new Entite();
            itemWarning.setStyle("warning");
            itemWarning.setLibelle("Laverie Youssoufia");
            itemWarning.setCode("Laverie Youssoufia");
            entiteService.create(itemWarning);

    }
    private void createUnite(){
            Unite itemPrimary = new Unite();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Metre cube");
            itemPrimary.setCode("Metre cube");
            uniteService.create(itemPrimary);
            Unite itemSecondary = new Unite();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("KW");
            itemSecondary.setCode("KW");
            uniteService.create(itemSecondary);

    }
    private void createTypeDemande(){
            TypeDemande itemDanger = new TypeDemande();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Imprevue");
            itemDanger.setCode("Imprevue");
            typeDemandeService.create(itemDanger);
            TypeDemande itemPrimary = new TypeDemande();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Normale");
            itemPrimary.setCode("Normale");
            typeDemandeService.create(itemPrimary);

    }
    private void createTypeExpedition(){
            TypeExpedition itemPrimary = new TypeExpedition();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Locale");
            itemPrimary.setCode("Locale");
            typeExpeditionService.create(itemPrimary);
            TypeExpedition itemDanger = new TypeExpedition();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Port");
            itemDanger.setCode("Port");
            typeExpeditionService.create(itemDanger);

    }
    private void createStadeOperatoire(){
            StadeOperatoire itemSecondary = new StadeOperatoire();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Lavage");
            itemSecondary.setCode("Lavage");
            stadeOperatoireService.create(itemSecondary);
            StadeOperatoire itemDark = new StadeOperatoire();
            itemDark.setStyle("dark");
            itemDark.setLibelle("Criblage");
            itemDark.setCode("Criblage");
            stadeOperatoireService.create(itemDark);
            StadeOperatoire itemPrimary = new StadeOperatoire();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Defruitage");
            itemPrimary.setCode("Defruitage");
            stadeOperatoireService.create(itemPrimary);

    }
    private void createElementChimique(){
            ElementChimique itemPrimary = new ElementChimique();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Phosphate");
            itemPrimary.setCode("Phosphate");
            elementChimiqueService.create(itemPrimary);
            ElementChimique itemSecondary = new ElementChimique();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Nitrogene");
            itemSecondary.setCode("Nitrogene");
            elementChimiqueService.create(itemSecondary);

    }
    private void createTypeClient(){
            TypeClient itemDanger = new TypeClient();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Externe");
            itemDanger.setCode("Externe");
            typeClientService.create(itemDanger);
            TypeClient itemPrimary = new TypeClient();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Interne");
            itemPrimary.setCode("Interne");
            typeClientService.create(itemPrimary);

    }

    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("ProduitSource"));
        modelPermissions.add(new ModelPermission("EtatDemande"));
        modelPermissions.add(new ModelPermission("CharteChimiqueDetail"));
        modelPermissions.add(new ModelPermission("ProduitMarchand"));
        modelPermissions.add(new ModelPermission("Produit"));
        modelPermissions.add(new ModelPermission("RatioUnite"));
        modelPermissions.add(new ModelPermission("Entite"));
        modelPermissions.add(new ModelPermission("Client"));
        modelPermissions.add(new ModelPermission("Unite"));
        modelPermissions.add(new ModelPermission("ExpeditionProduit"));
        modelPermissions.add(new ModelPermission("TypeDemande"));
        modelPermissions.add(new ModelPermission("StadeOperatoireProduit"));
        modelPermissions.add(new ModelPermission("Demande"));
        modelPermissions.add(new ModelPermission("TypeExpedition"));
        modelPermissions.add(new ModelPermission("AnalyseChimique"));
        modelPermissions.add(new ModelPermission("Expedition"));
        modelPermissions.add(new ModelPermission("StadeOperatoire"));
        modelPermissions.add(new ModelPermission("SuiviProduction"));
        modelPermissions.add(new ModelPermission("ElementChimique"));
        modelPermissions.add(new ModelPermission("CharteChimique"));
        modelPermissions.add(new ModelPermission("TypeClient"));
        modelPermissions.add(new ModelPermission("AnalyseChimiqueDetail"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


    @Autowired
    ProduitSourceAdminService produitSourceService;
    @Autowired
    EtatDemandeAdminService etatDemandeService;
    @Autowired
    ProduitMarchandAdminService produitMarchandService;
    @Autowired
    EntiteAdminService entiteService;
    @Autowired
    UniteAdminService uniteService;
    @Autowired
    TypeDemandeAdminService typeDemandeService;
    @Autowired
    TypeExpeditionAdminService typeExpeditionService;
    @Autowired
    StadeOperatoireAdminService stadeOperatoireService;
    @Autowired
    ElementChimiqueAdminService elementChimiqueService;
    @Autowired
    TypeClientAdminService typeClientService;
}


