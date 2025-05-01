import {createNativeStackNavigator} from '@react-navigation/native-stack';

import TypeClientAdminView from "../../../component/admin/view/referentiel/type-client/view/type-client-view-admin.component";
import TypeClientAdminList from "../../../component/admin/view/referentiel/type-client/list/type-client-list-admin.component";
import TypeClientAdminEdit from "../../../component/admin/view/referentiel/type-client/edit/type-client-edit-admin.component";


const Stack = createNativeStackNavigator();

function TypeClientAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="TypeClientAdminList"
                component={TypeClientAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="TypeClientAdminUpdate"
                component={TypeClientAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="TypeClientAdminDetails"
                component={TypeClientAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default TypeClientAdminStack;
