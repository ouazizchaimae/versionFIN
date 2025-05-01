import {createNativeStackNavigator} from '@react-navigation/native-stack';

import CharteChimiqueAdminView from "../../../component/admin/view/expedition/charte-chimique/view/charte-chimique-view-admin.component";
import CharteChimiqueAdminList from "../../../component/admin/view/expedition/charte-chimique/list/charte-chimique-list-admin.component";
import CharteChimiqueAdminEdit from "../../../component/admin/view/expedition/charte-chimique/edit/charte-chimique-edit-admin.component";


const Stack = createNativeStackNavigator();

function CharteChimiqueAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="CharteChimiqueAdminList"
                component={CharteChimiqueAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="CharteChimiqueAdminUpdate"
                component={CharteChimiqueAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="CharteChimiqueAdminDetails"
                component={CharteChimiqueAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default CharteChimiqueAdminStack;
