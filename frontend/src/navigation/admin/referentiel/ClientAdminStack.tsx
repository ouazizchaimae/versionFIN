import {createNativeStackNavigator} from '@react-navigation/native-stack';

import ClientAdminView from "../../../component/admin/view/referentiel/client/view/client-view-admin.component";
import ClientAdminList from "../../../component/admin/view/referentiel/client/list/client-list-admin.component";
import ClientAdminEdit from "../../../component/admin/view/referentiel/client/edit/client-edit-admin.component";


const Stack = createNativeStackNavigator();

function ClientAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="ClientAdminList"
                component={ClientAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ClientAdminUpdate"
                component={ClientAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ClientAdminDetails"
                component={ClientAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default ClientAdminStack;
