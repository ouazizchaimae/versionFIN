import {createNativeStackNavigator} from '@react-navigation/native-stack';

import SuiviProductionAdminView from "../../../component/admin/view/supply/suivi-production/view/suivi-production-view-admin.component";
import SuiviProductionAdminList from "../../../component/admin/view/supply/suivi-production/list/suivi-production-list-admin.component";
import SuiviProductionAdminEdit from "../../../component/admin/view/supply/suivi-production/edit/suivi-production-edit-admin.component";


const Stack = createNativeStackNavigator();

function SuiviProductionAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="SuiviProductionAdminList"
                component={SuiviProductionAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="SuiviProductionAdminUpdate"
                component={SuiviProductionAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="SuiviProductionAdminDetails"
                component={SuiviProductionAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default SuiviProductionAdminStack;
