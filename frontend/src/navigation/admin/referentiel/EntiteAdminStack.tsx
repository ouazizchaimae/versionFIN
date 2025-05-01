import {createNativeStackNavigator} from '@react-navigation/native-stack';

import EntiteAdminView from "../../../component/admin/view/referentiel/entite/view/entite-view-admin.component";
import EntiteAdminList from "../../../component/admin/view/referentiel/entite/list/entite-list-admin.component";
import EntiteAdminEdit from "../../../component/admin/view/referentiel/entite/edit/entite-edit-admin.component";


const Stack = createNativeStackNavigator();

function EntiteAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="EntiteAdminList"
                component={EntiteAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="EntiteAdminUpdate"
                component={EntiteAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="EntiteAdminDetails"
                component={EntiteAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default EntiteAdminStack;
