import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import {StyleSheet, Text, View,} from 'react-native';
import Ionicons from 'react-native-vector-icons/Ionicons';
import UniteAdminCreate from '../../../component/admin/view/referentiel/unite/create/unite-create-admin.component';
import UniteAdminStack from './UniteAdminStack';


const Tab = createBottomTabNavigator();



function UniteAdminTabNavigation() {
    return (


        <Tab.Navigator
            screenOptions={{
                tabBarShowLabel: false,
                headerShown: false,
                tabBarStyle: {
                    position: 'absolute',
                    bottom: 20,
                    left: 20,
                    right: 20,
                    elevation: 0,
                    backgroundColor: 'orange',
                    borderRadius: 15,
                    height: 60,

                    ...styles.shadow
                }
            }}
        >
            <Tab.Screen name="Create" component={UniteAdminCreate} options={{
                tabBarIcon: ({ focused }) => (
                    <View style={{ alignItems: 'center', justifyContent: 'center' }}>
                        <Ionicons name="create-outline"
                                  size={27}
                                  color={focused ? 'purple' : 'white'}
                        />
                        <Text
                            style={{ color: focused ? 'purple' : 'white', fontSize: 15 }}
                        >Create</Text>
                    </View>
                ),
            }} />



            <Tab.Screen name="List" component={UniteAdminStack} options={{
                tabBarIcon: ({ focused }) => (
                    <View style={{ alignItems: 'center', justifyContent: 'center' }}>
                        <Ionicons name="list-outline"
                                  size={27}
                                  color={focused ? 'purple' : 'white'}
                        />
                        <Text
                            style={{ color: focused ? 'purple' : 'white', fontSize: 15 }}
                        >List</Text>
                    </View>
                ),
            }}
            />
        </Tab.Navigator>


    );
}

const styles = StyleSheet.create({
    shadow: {
        shadowColor: '#7F5DF0',
        shadowOffset: {
            width: 0,
            height: 10,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.5,
        elevation: 5
    }
});

export default UniteAdminTabNavigation;
