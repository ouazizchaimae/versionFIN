function fn() {

    
    var config = {};
    
   
    const rootUrlDev = 'http://localhost:8036/';
    const adminCredentialsDev = {"username": "admin","password": "123"};
    const urlDev = 'jdbc:mysql://localhost:3306/test';

    const usernameDev = 'root';
    const passwordDev = '';
    const driverDev = 'com.mysql.cj.jdbc.Driver';


    const rootUrlInit = 'https://stage-host/';
    const adminCredentialsInit = {"username": "admin","password": "123"};
    const urlInit = 'jdbc:mysql://localhost:8036/peps-order';
    const usernameInit = 'root';
    const passwordInit = '';
    const driverInit = 'com.mysql.cj.jdbc.Driver';
    
    
    const rootUrlE2e = 'https://e2e-host/';
    const adminCredentialsE2e = {"username": "admin","password": "123"};
    const urlE2e = 'jdbc:mysql://localhost:8036/peps-order';
    const usernameE2e = 'root';
    const passwordE2e = '';
    const driverE2e = 'com.mysql.cj.jdbc.Driver';
    
    var env = karate.env; // get java system property 'karate.env'
    if (!env || env=='dev') {
        env = 'dev';
        config.rootUrl = rootUrlDev;
        config.adminCredentials = adminCredentialsDev;
        config.datasource = { username: usernameDev, password: passwordDev, url: urlDev, driverClassName: driverDev }
    }
    if (env == 'int') {
        // over-ride only those that need to be
        config.rootUrl = rootUrlInit;
        config.adminCredentials = adminCredentialsInit;
        config.datasource = { username: usernameInit, password: passwordInit, url: urlInit, driverClassName: driverInit }
    } else if (env == 'e2e') {
        config.rootUrl = rootUrlE2e;
        config.adminCredentials = adminCredentialsE2e;
        config.datasource = { username: usernameE2e, password: passwordE2e, url: urlE2e, driverClassName: driverE2e }
    }

    config.actuatorUri = config.rootUrl + 'actuator/';
    config.adminUri = config.rootUrl + 'api/admin/';

    config.produitSourceUrl = config.adminUri + 'produitSource/';
    config.etatDemandeUrl = config.adminUri + 'etatDemande/';
    config.charteChimiqueDetailUrl = config.adminUri + 'charteChimiqueDetail/';
    config.produitMarchandUrl = config.adminUri + 'produitMarchand/';
    config.produitUrl = config.adminUri + 'produit/';
    config.ratioUniteUrl = config.adminUri + 'ratioUnite/';
    config.entiteUrl = config.adminUri + 'entite/';
    config.clientUrl = config.adminUri + 'client/';
    config.uniteUrl = config.adminUri + 'unite/';
    config.expeditionProduitUrl = config.adminUri + 'expeditionProduit/';
    config.typeDemandeUrl = config.adminUri + 'typeDemande/';
    config.stadeOperatoireProduitUrl = config.adminUri + 'stadeOperatoireProduit/';
    config.demandeUrl = config.adminUri + 'demande/';
    config.typeExpeditionUrl = config.adminUri + 'typeExpedition/';
    config.analyseChimiqueUrl = config.adminUri + 'analyseChimique/';
    config.expeditionUrl = config.adminUri + 'expedition/';
    config.stadeOperatoireUrl = config.adminUri + 'stadeOperatoire/';
    config.suiviProductionUrl = config.adminUri + 'suiviProduction/';
    config.elementChimiqueUrl = config.adminUri + 'elementChimique/';
    config.charteChimiqueUrl = config.adminUri + 'charteChimique/';
    config.typeClientUrl = config.adminUri + 'typeClient/';
    config.analyseChimiqueDetailUrl = config.adminUri + 'analyseChimiqueDetail/';

    common = karate.callSingle('classpath:common.feature', config);
    config.uniqueId = common.uniqueId
    config.db = common.db
    config.adminToken = common.adminToken
    config.env = env;

    karate.log('karate.env =', karate.env);
    karate.log('config =', config);
    // don't waste time waiting for a connection or if servers don't respond within 5 seconds
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);
    return config;
}
