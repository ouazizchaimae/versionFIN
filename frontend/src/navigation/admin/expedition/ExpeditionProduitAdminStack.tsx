import {createNativeStackNavigator} from '@react-navigation/native-stack';

import ExpeditionProduitAdminView from "../../../component/admin/view/expedition/expedition-produit/view/expedition-produit-view-admin.component";
import ExpeditionProduitAdminList from "../../../component/admin/view/expedition/expedition-produit/list/expedition-produit-list-admin.component";
import ExpeditionProduitAdminEdit from "../../../component/admin/view/expedition/expedition-produit/edit/expedition-produit-edit-admin.component";


const Stack = createNativeStackNavigator();

function ExpeditionProduitAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="ExpeditionProduitAdminList"
                component={ExpeditionProduitAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ExpeditionProduitAdminUpdate"
                component={ExpeditionProduitAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ExpeditionProduitAdminDetails"
                component={ExpeditionProduitAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default ExpeditionProduitAdminStack;
