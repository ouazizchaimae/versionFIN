import {createNativeStackNavigator} from '@react-navigation/native-stack';

import ElementChimiqueAdminView from "../../../component/admin/view/referentiel/element-chimique/view/element-chimique-view-admin.component";
import ElementChimiqueAdminList from "../../../component/admin/view/referentiel/element-chimique/list/element-chimique-list-admin.component";
import ElementChimiqueAdminEdit from "../../../component/admin/view/referentiel/element-chimique/edit/element-chimique-edit-admin.component";


const Stack = createNativeStackNavigator();

function ElementChimiqueAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="ElementChimiqueAdminList"
                component={ElementChimiqueAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ElementChimiqueAdminUpdate"
                component={ElementChimiqueAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ElementChimiqueAdminDetails"
                component={ElementChimiqueAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default ElementChimiqueAdminStack;
