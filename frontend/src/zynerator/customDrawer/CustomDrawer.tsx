import React from 'react';
import {
    View,
    Text,
    ImageBackground,
    Image,
    BackHandler
} from 'react-native';
import {
    DrawerContentScrollView,
    DrawerItemList,
} from '@react-navigation/drawer';
import { TouchableOpacity } from 'react-native-gesture-handler';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { AuthService } from '../security/Auth.service';
import { useNavigation } from '@react-navigation/native';




const CustomDrawer = props => {

    const authService = new AuthService();
    const navigation = useNavigation<any>();


    const handleLogout = () => {
        authService.signOut();
        navigation.reset({
            index: 0,
            routes: [{ name: 'AuthNavigation' }],
        });
    };


    return (
        <View style={{ flex: 1 }}>
            <DrawerContentScrollView
                {...props}
                contentContainerStyle={{ backgroundColor: '#232b2b' }}>
                <ImageBackground
                    //source={require('../../../assets/logo.png')}
                    style={{ padding: 2, height: 60 }}>

                    
                    <Image
                        source={require('../../../assets/logo.png')}
                        style={{height: "100%", width: "100%", marginBottom: 50}}
                        resizeMode="contain"

                    />
                    


                    <View style={{ marginTop: 130, marginLeft: 20 }}>
                        <View style={{ flexDirection: 'row', alignItems: 'center' }}>

                            <View style={{ marginTop: 7, marginRight: 5 }}>
                                <Ionicons name="cash-sharp" size={22} color={'white'} />
                            </View>
                            <Text
                                style={{
                                    color: 'white',
                                    fontSize: 22,
                                    fontWeight: 'bold',
                                }}>
                                purchase-front1
                            </Text>
                        </View>
                    </View>
                </ImageBackground>
                <View style={{ flex: 1, backgroundColor: '#fff', paddingTop: 10 }}>
                    <DrawerItemList {...props} />
                </View>
            </DrawerContentScrollView>

            <View style={{ padding: 10, borderTopWidth: 1, borderTopColor: '#ccc' }}>

                <TouchableOpacity onPress={handleLogout} style={{ paddingVertical: 15 }}>
                    <View style={{ flexDirection: 'row', alignItems: 'center' }}>
                        <Ionicons name="exit-outline" size={22} />
                        <Text
                            style={{
                                fontSize: 15,
                                fontWeight: 'bold',
                                marginLeft: 5,
                            }}>
                            Logout
                        </Text>
                    </View>
                </TouchableOpacity>

            </View>

        </View>
    );
};

export default CustomDrawer;



