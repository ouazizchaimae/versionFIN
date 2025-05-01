import CustomDrawer from "../../zynerator/customDrawer/CustomDrawer";
import HomeScreen from "../../component/HomeScreen";
import Ionicons from "react-native-vector-icons/Ionicons";
import ProduitSourceAdmin from "../../component/admin/view/referentiel/produit-source/container/produit-source-container-admin.component";
import EtatDemandeAdmin from "../../component/admin/view/referentiel/etat-demande/container/etat-demande-container-admin.component";
import CharteChimiqueDetailAdmin from "../../component/admin/view/expedition/charte-chimique-detail/container/charte-chimique-detail-container-admin.component";
import ProduitMarchandAdmin from "../../component/admin/view/referentiel/produit-marchand/container/produit-marchand-container-admin.component";
import ProduitAdmin from "../../component/admin/view/referentiel/produit/container/produit-container-admin.component";
import RatioUniteAdmin from "../../component/admin/view/referentiel/ratio-unite/container/ratio-unite-container-admin.component";
import EntiteAdmin from "../../component/admin/view/referentiel/entite/container/entite-container-admin.component";
import ClientAdmin from "../../component/admin/view/referentiel/client/container/client-container-admin.component";
import UniteAdmin from "../../component/admin/view/referentiel/unite/container/unite-container-admin.component";
import ExpeditionProduitAdmin from "../../component/admin/view/expedition/expedition-produit/container/expedition-produit-container-admin.component";
import TypeDemandeAdmin from "../../component/admin/view/referentiel/type-demande/container/type-demande-container-admin.component";
import DemandeAdmin from "../../component/admin/view/demande/demande/container/demande-container-admin.component";
import TypeExpeditionAdmin from "../../component/admin/view/expedition/type-expedition/container/type-expedition-container-admin.component";
import AnalyseChimiqueAdmin from "../../component/admin/view/expedition/analyse-chimique/container/analyse-chimique-container-admin.component";
import ExpeditionAdmin from "../../component/admin/view/expedition/expedition/container/expedition-container-admin.component";
import StadeOperatoireAdmin from "../../component/admin/view/referentiel/stade-operatoire/container/stade-operatoire-container-admin.component";
import SuiviProductionAdmin from "../../component/admin/view/supply/suivi-production/container/suivi-production-container-admin.component";
import ElementChimiqueAdmin from "../../component/admin/view/referentiel/element-chimique/container/element-chimique-container-admin.component";
import CharteChimiqueAdmin from "../../component/admin/view/expedition/charte-chimique/container/charte-chimique-container-admin.component";
import TypeClientAdmin from "../../component/admin/view/referentiel/type-client/container/type-client-container-admin.component";
import AnalyseChimiqueDetailAdmin from "../../component/admin/view/expedition/analyse-chimique-detail/container/analyse-chimique-detail-container-admin.component";
import AboutScreen from "../../component/AboutScreen";
import {createDrawerNavigator} from "@react-navigation/drawer";

const Drawer = createDrawerNavigator();

function DrawerNavigation() {
    return (

        <Drawer.Navigator
            drawerContent={props => <CustomDrawer {...props} />}
            screenOptions={{
                drawerActiveBackgroundColor: '#ffa500',
                drawerActiveTintColor: '#fff',
                drawerInactiveTintColor: '#333',
                drawerLabelStyle: {
                    marginLeft: -25,
                    fontWeight: 'bold',
                    fontSize: 15,
                },
            }}>
            <Drawer.Screen
                name="Home"
                component={HomeScreen}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="home-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="ProduitSource"
                component={ProduitSourceAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="EtatDemande"
                component={EtatDemandeAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="CharteChimiqueDetail"
                component={CharteChimiqueDetailAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="ProduitMarchand"
                component={ProduitMarchandAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="Produit"
                component={ProduitAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="RatioUnite"
                component={RatioUniteAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="Entite"
                component={EntiteAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="Client"
                component={ClientAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="Unite"
                component={UniteAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="ExpeditionProduit"
                component={ExpeditionProduitAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="TypeDemande"
                component={TypeDemandeAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="Demande"
                component={DemandeAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="TypeExpedition"
                component={TypeExpeditionAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="AnalyseChimique"
                component={AnalyseChimiqueAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="Expedition"
                component={ExpeditionAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="StadeOperatoire"
                component={StadeOperatoireAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="SuiviProduction"
                component={SuiviProductionAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="ElementChimique"
                component={ElementChimiqueAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="CharteChimique"
                component={CharteChimiqueAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="TypeClient"
                component={TypeClientAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
            <Drawer.Screen
                name="AnalyseChimiqueDetail"
                component={AnalyseChimiqueDetailAdmin}
                options={{
                    drawerIcon: ({ color }) => (
                        <Ionicons name="cart-outline" size={22} color={color} />
                    ),
                }}
            />
        </Drawer.Navigator>

    );
}

export default DrawerNavigation;
