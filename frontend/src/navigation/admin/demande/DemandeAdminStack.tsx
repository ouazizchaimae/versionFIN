import {createNativeStackNavigator} from '@react-navigation/native-stack';

import DemandeAdminView from "../../../component/admin/view/demande/demande/view/demande-view-admin.component";
import DemandeAdminList from "../../../component/admin/view/demande/demande/list/demande-list-admin.component";
import DemandeAdminEdit from "../../../component/admin/view/demande/demande/edit/demande-edit-admin.component";


const Stack = createNativeStackNavigator();

function DemandeAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="DemandeAdminList"
                component={DemandeAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="DemandeAdminUpdate"
                component={DemandeAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="DemandeAdminDetails"
                component={DemandeAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default DemandeAdminStack;
