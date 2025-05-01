import {createNativeStackNavigator} from '@react-navigation/native-stack';

import RatioUniteAdminView from "../../../component/admin/view/referentiel/ratio-unite/view/ratio-unite-view-admin.component";
import RatioUniteAdminList from "../../../component/admin/view/referentiel/ratio-unite/list/ratio-unite-list-admin.component";
import RatioUniteAdminEdit from "../../../component/admin/view/referentiel/ratio-unite/edit/ratio-unite-edit-admin.component";


const Stack = createNativeStackNavigator();

function RatioUniteAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="RatioUniteAdminList"
                component={RatioUniteAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="RatioUniteAdminUpdate"
                component={RatioUniteAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="RatioUniteAdminDetails"
                component={RatioUniteAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default RatioUniteAdminStack;
