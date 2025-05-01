import {createNativeStackNavigator} from '@react-navigation/native-stack';

import AnalyseChimiqueAdminView from "../../../component/admin/view/expedition/analyse-chimique/view/analyse-chimique-view-admin.component";
import AnalyseChimiqueAdminList from "../../../component/admin/view/expedition/analyse-chimique/list/analyse-chimique-list-admin.component";
import AnalyseChimiqueAdminEdit from "../../../component/admin/view/expedition/analyse-chimique/edit/analyse-chimique-edit-admin.component";


const Stack = createNativeStackNavigator();

function AnalyseChimiqueAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="AnalyseChimiqueAdminList"
                component={AnalyseChimiqueAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="AnalyseChimiqueAdminUpdate"
                component={AnalyseChimiqueAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="AnalyseChimiqueAdminDetails"
                component={AnalyseChimiqueAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default AnalyseChimiqueAdminStack;
