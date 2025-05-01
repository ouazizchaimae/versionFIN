import React, { useState } from "react";
import { StyleSheet, View, ActivityIndicator, Text, Image, Pressable } from "react-native";
import { useForm } from "react-hook-form";
import { AuthService } from "../../zynerator/security/Auth.service";
import { useNavigation } from '@react-navigation/native';
import CustomInput from "../../zynerator/gui/CustomInput";
import CustomButton from "../../zynerator/gui/CustomButton";
import {ADMIN_NAVIGATION} from "../../../config/AppConfig";

const Login = () => {
    const { control, handleSubmit, reset } = useForm({
        defaultValues: {
            username: '',
            password: '',
        }
    });

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const service = new AuthService();
    const navigation = useNavigation<any>();
    const [userRoles, setUserRoles] = useState([]);

    const handleLogin = async (data: { username: any; password: any; }) => {
        const { username, password } = data;

        setLoading(true);
        setError(null);

        try {
            const response = await service.signIn(username, password);
            const token = response.data.token || response.data.accessToken;

            if (token) {
                await service.saveToken(token);
                const roles = await service.getRoleConnectedUser();
                handleNavigation(roles);
                setError(null);
            } else {
                setError("Login failed: No token received");
            }
        } catch (error) {
            console.error("Login error:", error);
            setError("Login failed: Invalid username or password");
        } finally {
            setLoading(false);
            reset();
        }
    };

    const handleNavigation = (roles: string[]) => {
        if (roles.includes('ROLE_ADMIN')) {
            navigation.navigate(ADMIN_NAVIGATION);
        }
    };

    return (
        <View style={styles.container}>

            <Image
                source={require('../../../assets/logo.png')}
                style={styles.logo}
                resizeMode="contain"
            />

            {loading ? (
                <ActivityIndicator size="large" color="#ffa500" />
            ) : (
                <>
                    <View style={{ bottom: 50 }}>

                        <View style={{ alignItems: 'center' }}>
                            <Text style={{ fontSize: 30, fontWeight: 'bold' }}>Login</Text>
                        </View>

                        <CustomInput
                            name="username"
                            control={control}
                            placeholder="Username"
                            keyboardT="default"
                        />
                        <CustomInput
                            name="password"
                            control={control}
                            placeholder="Password"
                            keyboardT="default"
                            isTextArea={false}
                            secureTextEntry={true}
                        />
                        <CustomButton
                            onPress={handleSubmit(handleLogin)}
                            text="Login"
                            type="PRIMARY"
                            bgColor="orange"
                            fgColor="black"
                        />

                        {error && (
                            <Text style={styles.errorText}>{error}</Text>
                        )}

                        <Pressable
                            style={styles.pressable}
                            onPress={() => navigation.navigate('Register')}
                        >
                            <Text style={styles.text}>Don't have an account? Register</Text>
                        </Pressable>
                    </View>
                </>
            )
            }
        </View >
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingHorizontal: 20,
        backgroundColor: '#fff',
    },
    logo: {
        width: '100%',
        height: undefined,
        aspectRatio: 1,
        alignSelf: 'center',
        borderRadius: 20,
    },

    errorText: {
        color: 'red',
        marginTop: 10,
        textAlign: 'center',
    },
    pressable: {
        padding: 10,
        borderRadius: 5,
        alignItems: 'center',
    },
    text: {
        color: '#120a8f',
        fontSize: 16,
        fontWeight: 'bold',
    },
});

export default Login;
