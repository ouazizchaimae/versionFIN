import {createNativeStackNavigator} from '@react-navigation/native-stack';

import ExpeditionAdminView from "../../../component/admin/view/expedition/expedition/view/expedition-view-admin.component";
import ExpeditionAdminList from "../../../component/admin/view/expedition/expedition/list/expedition-list-admin.component";
import ExpeditionAdminEdit from "../../../component/admin/view/expedition/expedition/edit/expedition-edit-admin.component";


const Stack = createNativeStackNavigator();

function ExpeditionAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="ExpeditionAdminList"
                component={ExpeditionAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ExpeditionAdminUpdate"
                component={ExpeditionAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ExpeditionAdminDetails"
                component={ExpeditionAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default ExpeditionAdminStack;
