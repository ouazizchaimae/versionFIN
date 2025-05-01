import {createNativeStackNavigator} from '@react-navigation/native-stack';

import TypeExpeditionAdminView from "../../../component/admin/view/expedition/type-expedition/view/type-expedition-view-admin.component";
import TypeExpeditionAdminList from "../../../component/admin/view/expedition/type-expedition/list/type-expedition-list-admin.component";
import TypeExpeditionAdminEdit from "../../../component/admin/view/expedition/type-expedition/edit/type-expedition-edit-admin.component";


const Stack = createNativeStackNavigator();

function TypeExpeditionAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="TypeExpeditionAdminList"
                component={TypeExpeditionAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="TypeExpeditionAdminUpdate"
                component={TypeExpeditionAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="TypeExpeditionAdminDetails"
                component={TypeExpeditionAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default TypeExpeditionAdminStack;
