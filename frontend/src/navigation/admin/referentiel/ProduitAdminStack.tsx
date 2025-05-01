import {createNativeStackNavigator} from '@react-navigation/native-stack';

import ProduitAdminView from "../../../component/admin/view/referentiel/produit/view/produit-view-admin.component";
import ProduitAdminList from "../../../component/admin/view/referentiel/produit/list/produit-list-admin.component";
import ProduitAdminEdit from "../../../component/admin/view/referentiel/produit/edit/produit-edit-admin.component";


const Stack = createNativeStackNavigator();

function ProduitAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="ProduitAdminList"
                component={ProduitAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ProduitAdminUpdate"
                component={ProduitAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ProduitAdminDetails"
                component={ProduitAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default ProduitAdminStack;
