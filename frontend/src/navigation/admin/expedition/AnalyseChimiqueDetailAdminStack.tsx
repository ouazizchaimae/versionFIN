import {createNativeStackNavigator} from '@react-navigation/native-stack';

import AnalyseChimiqueDetailAdminView from "../../../component/admin/view/expedition/analyse-chimique-detail/view/analyse-chimique-detail-view-admin.component";
import AnalyseChimiqueDetailAdminList from "../../../component/admin/view/expedition/analyse-chimique-detail/list/analyse-chimique-detail-list-admin.component";
import AnalyseChimiqueDetailAdminEdit from "../../../component/admin/view/expedition/analyse-chimique-detail/edit/analyse-chimique-detail-edit-admin.component";


const Stack = createNativeStackNavigator();

function AnalyseChimiqueDetailAdminStack() {
    return (
        <Stack.Navigator>
            <Stack.Screen
                name="AnalyseChimiqueDetailAdminList"
                component={AnalyseChimiqueDetailAdminList}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="AnalyseChimiqueDetailAdminUpdate"
                component={AnalyseChimiqueDetailAdminEdit}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="AnalyseChimiqueDetailAdminDetails"
                component={AnalyseChimiqueDetailAdminView}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default AnalyseChimiqueDetailAdminStack;
