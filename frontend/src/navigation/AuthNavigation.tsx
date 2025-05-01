import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Login from "../component/authentication/Login";
import Register from '../component/authentication/Register';
import ActivateAccount from '../component/authentication/ActivateAccount';

const Stack = createNativeStackNavigator();

function AuthNavigation() {


    return (
        <Stack.Navigator>
            <Stack.Screen
                name="Login"
                component={Login}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="Register"
                component={Register}
                options={{ headerShown: false }}
            />
            <Stack.Screen
                name="ActivateAccount"
                component={ActivateAccount}
                options={{ headerShown: false }}
            />
        </Stack.Navigator>
    );
}

export default AuthNavigation;
