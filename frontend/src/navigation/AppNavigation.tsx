import React, {useEffect, useState} from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {ActivityIndicator, View} from 'react-native';
import {AuthService} from '../zynerator/security/Auth.service';
import AuthNavigation from './AuthNavigation';

import AdminNavigation from './admin/AdminNavigation';
import {ADMIN_NAVIGATION} from '../../config/AppConfig';


const Stack = createNativeStackNavigator();

function AppNavigation() {
    const [isLoading, setIsLoading] = useState(true);
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [userRoles, setUserRoles] = useState([]);
    const authService = new AuthService();

    useEffect(() => {
        const checkAuthentication = async () => {
            const token = await authService.getToken();
            const roles = await authService.getRoleConnectedUser();
            if (token) {
                setIsAuthenticated(true);
                setUserRoles(roles);
            }
            setIsLoading(false);
        };

        checkAuthentication();
    }, []);

    if (isLoading) {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                <ActivityIndicator size="large" color="#ffa500" />
            </View>
        );
    }

    const initialRouteName = () => {
        if (userRoles.includes('ROLE_ADMIN')) {
            return ADMIN_NAVIGATION;
        }
    };


    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName={isAuthenticated ? initialRouteName() : 'AuthNavigation'}>
                <Stack.Screen
                    name="AuthNavigation"
                    component={AuthNavigation}
                    options={{ headerShown: false }}
                />
                <Stack.Screen
                        name="AdminNavigation"
                        component={AdminNavigation}
                        options={{ headerShown: false }}
                />
            </Stack.Navigator>
        </NavigationContainer>
    );
}

export default AppNavigation;
