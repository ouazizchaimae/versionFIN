import {createNativeStackNavigator} from '@react-navigation/native-stack';

import StadeOperatoireProduitAdminView from "../../../component/admin/view/referentiel/stade-operatoire-produit/view/stade-operatoire-produit-view-admin.component";
import StadeOperatoireProduitAdminList from "../../../component/admin/view/referentiel/stade-operatoire-produit/list/stade-operatoire-produit-list-admin.component";
import StadeOperatoireProduitAdminEdit from "../../../component/admin/view/referentiel/stade-operatoire-produit/edit/stade-operatoire-produit-edit-admin.component";


const Stack = createNativeStackNavigator();

function StadeOperatoireProduitAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="StadeOperatoireProduitAdminList"
                component={StadeOperatoireProduitAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="StadeOperatoireProduitAdminUpdate"
                component={StadeOperatoireProduitAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="StadeOperatoireProduitAdminDetails"
                component={StadeOperatoireProduitAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default StadeOperatoireProduitAdminStack;
