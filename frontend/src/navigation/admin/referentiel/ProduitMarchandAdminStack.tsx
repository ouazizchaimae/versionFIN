import {createNativeStackNavigator} from '@react-navigation/native-stack';

import ProduitMarchandAdminView from "../../../component/admin/view/referentiel/produit-marchand/view/produit-marchand-view-admin.component";
import ProduitMarchandAdminList from "../../../component/admin/view/referentiel/produit-marchand/list/produit-marchand-list-admin.component";
import ProduitMarchandAdminEdit from "../../../component/admin/view/referentiel/produit-marchand/edit/produit-marchand-edit-admin.component";


const Stack = createNativeStackNavigator();

function ProduitMarchandAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="ProduitMarchandAdminList"
                component={ProduitMarchandAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ProduitMarchandAdminUpdate"
                component={ProduitMarchandAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ProduitMarchandAdminDetails"
                component={ProduitMarchandAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default ProduitMarchandAdminStack;
