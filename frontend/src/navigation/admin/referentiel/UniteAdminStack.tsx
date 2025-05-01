import {createNativeStackNavigator} from '@react-navigation/native-stack';

import UniteAdminView from "../../../component/admin/view/referentiel/unite/view/unite-view-admin.component";
import UniteAdminList from "../../../component/admin/view/referentiel/unite/list/unite-list-admin.component";
import UniteAdminEdit from "../../../component/admin/view/referentiel/unite/edit/unite-edit-admin.component";


const Stack = createNativeStackNavigator();

function UniteAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="UniteAdminList"
                component={UniteAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="UniteAdminUpdate"
                component={UniteAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="UniteAdminDetails"
                component={UniteAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default UniteAdminStack;
