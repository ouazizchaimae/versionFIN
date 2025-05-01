import React, { useState } from "react";
import { StyleSheet, View, ActivityIndicator, Text, Image, Pressable, ScrollView } from "react-native";
import { useForm } from "react-hook-form";
import { AuthService } from "../../zynerator/security/Auth.service";
import CustomButton from "../../zynerator/gui/CustomButton";
import CustomInput from "../../zynerator/gui/CustomInput";
import { useNavigation } from '@react-navigation/native';
import { UserDto } from "../../zynerator/dto/UserDto.model";
import { RoleDto } from "../../zynerator/dto/RoleDto.model";
import { RoleUserDto } from "../../zynerator/dto/RoleUserDto.model";

const Register = () => {

    const [user, setUser] = useState<UserDto>(new UserDto());

    const { control, handleSubmit, reset } = useForm({
        defaultValues: {
            firstname: '',
            lastname: '',
            email: '',
            phone: '',
            username: '',
            password: '',
        }
    });

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const service = new AuthService();
    const navigation = useNavigation<any>();

    const handleRegister = async (data: any) => {
        const { username, password, firstname, lastname, email, phone } = data;

        const role = new RoleDto();
        role.authority = 'ROLE_ADMIN';

        const roleUser = new RoleUserDto();
        roleUser.role = role;

        const newUser = {
            ...user,
            username: username,
            password: password,
            firstName: firstname,
            lastName: lastname,
            email: email,
            phone: phone,
            accountNonExpired: true,
            accountNonLocked: true,
            credentialsNonExpired: true,
            enabled: true,
            passwordChanged: true,
            roleUsers: [roleUser],
        };

        setUser(newUser);

        setLoading(true);
        setError(null);

        try {
            await service.register(newUser);
            navigation.navigate('ActivateAccount');
            setError(null);
        } catch (error) {
            setError("Registration failed. Please try again.");
        } finally {
            setLoading(false);
            reset();
        }
    };


    return (
        <ScrollView style={styles.container}>
            <Image
                source={require('../../../assets/logo.png')}
                style={styles.logo}
                resizeMode="contain"
            />

            {loading ? (
                <ActivityIndicator size="large" color="#ffa500" />
            ) : (
                <>
                    <View style={styles.formContainer}>
                        <Text style={styles.title}>Register</Text>

                        <CustomInput
                            name="firstname"
                            control={control}
                            placeholder="First Name"
                            keyboardT="default"
                        />
                        <CustomInput
                            name="lastname"
                            control={control}
                            placeholder="Last Name"
                            keyboardT="default"
                        />
                        <CustomInput
                            name="email"
                            control={control}
                            placeholder="Email"
                            keyboardT="email-address"
                        />
                        <CustomInput
                            name="phone"
                            control={control}
                            placeholder="Phone"
                            keyboardT="phone-pad"
                        />
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
                            onPress={handleSubmit(handleRegister)}
                            text="Register"
                            type="PRIMARY"
                            bgColor="orange"
                            fgColor="black"
                        />

                        {error && (
                            <Text style={styles.errorText}>{error}</Text>
                        )}

                        <Pressable
                            style={styles.pressable}
                            onPress={() => navigation.navigate('Login')}
                        >
                            <Text style={styles.text}>Back to Login</Text>
                        </Pressable>


                    </View>
                </>
            )}
        </ScrollView>
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
        marginBottom: 20,
    },
    formContainer: {
        alignItems: 'center',
        bottom: 150
    },
    title: {
        fontSize: 30,
        fontWeight: 'bold',
        marginBottom: 10,
    },
    input: {
        marginBottom: 15,
        width: '100%',
    },
    button: {
        marginTop: 20,
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

export default Register;
