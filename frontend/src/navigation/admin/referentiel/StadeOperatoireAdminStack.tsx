import {createNativeStackNavigator} from '@react-navigation/native-stack';

import StadeOperatoireAdminView from "../../../component/admin/view/referentiel/stade-operatoire/view/stade-operatoire-view-admin.component";
import StadeOperatoireAdminList from "../../../component/admin/view/referentiel/stade-operatoire/list/stade-operatoire-list-admin.component";
import StadeOperatoireAdminEdit from "../../../component/admin/view/referentiel/stade-operatoire/edit/stade-operatoire-edit-admin.component";


const Stack = createNativeStackNavigator();

function StadeOperatoireAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="StadeOperatoireAdminList"
                component={StadeOperatoireAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="StadeOperatoireAdminUpdate"
                component={StadeOperatoireAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="StadeOperatoireAdminDetails"
                component={StadeOperatoireAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default StadeOperatoireAdminStack;
