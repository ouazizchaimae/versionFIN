import {createNativeStackNavigator} from '@react-navigation/native-stack';

import ProduitSourceAdminView from "../../../component/admin/view/referentiel/produit-source/view/produit-source-view-admin.component";
import ProduitSourceAdminList from "../../../component/admin/view/referentiel/produit-source/list/produit-source-list-admin.component";
import ProduitSourceAdminEdit from "../../../component/admin/view/referentiel/produit-source/edit/produit-source-edit-admin.component";


const Stack = createNativeStackNavigator();

function ProduitSourceAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="ProduitSourceAdminList"
                component={ProduitSourceAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ProduitSourceAdminUpdate"
                component={ProduitSourceAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ProduitSourceAdminDetails"
                component={ProduitSourceAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default ProduitSourceAdminStack;
