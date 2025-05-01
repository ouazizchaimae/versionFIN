import {createNativeStackNavigator} from '@react-navigation/native-stack';

import TypeDemandeAdminView from "../../../component/admin/view/referentiel/type-demande/view/type-demande-view-admin.component";
import TypeDemandeAdminList from "../../../component/admin/view/referentiel/type-demande/list/type-demande-list-admin.component";
import TypeDemandeAdminEdit from "../../../component/admin/view/referentiel/type-demande/edit/type-demande-edit-admin.component";


const Stack = createNativeStackNavigator();

function TypeDemandeAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="TypeDemandeAdminList"
                component={TypeDemandeAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="TypeDemandeAdminUpdate"
                component={TypeDemandeAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="TypeDemandeAdminDetails"
                component={TypeDemandeAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default TypeDemandeAdminStack;
