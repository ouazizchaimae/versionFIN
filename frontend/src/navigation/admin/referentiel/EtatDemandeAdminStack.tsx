import {createNativeStackNavigator} from '@react-navigation/native-stack';

import EtatDemandeAdminView from "../../../component/admin/view/referentiel/etat-demande/view/etat-demande-view-admin.component";
import EtatDemandeAdminList from "../../../component/admin/view/referentiel/etat-demande/list/etat-demande-list-admin.component";
import EtatDemandeAdminEdit from "../../../component/admin/view/referentiel/etat-demande/edit/etat-demande-edit-admin.component";


const Stack = createNativeStackNavigator();

function EtatDemandeAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="EtatDemandeAdminList"
                component={EtatDemandeAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="EtatDemandeAdminUpdate"
                component={EtatDemandeAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="EtatDemandeAdminDetails"
                component={EtatDemandeAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default EtatDemandeAdminStack;
