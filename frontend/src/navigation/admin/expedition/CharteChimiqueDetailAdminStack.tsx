import {createNativeStackNavigator} from '@react-navigation/native-stack';

import CharteChimiqueDetailAdminView from "../../../component/admin/view/expedition/charte-chimique-detail/view/charte-chimique-detail-view-admin.component";
import CharteChimiqueDetailAdminList from "../../../component/admin/view/expedition/charte-chimique-detail/list/charte-chimique-detail-list-admin.component";
import CharteChimiqueDetailAdminEdit from "../../../component/admin/view/expedition/charte-chimique-detail/edit/charte-chimique-detail-edit-admin.component";


const Stack = createNativeStackNavigator();

function CharteChimiqueDetailAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="CharteChimiqueDetailAdminList"
                component={CharteChimiqueDetailAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="CharteChimiqueDetailAdminUpdate"
                component={CharteChimiqueDetailAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="CharteChimiqueDetailAdminDetails"
                component={CharteChimiqueDetailAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default CharteChimiqueDetailAdminStack;
