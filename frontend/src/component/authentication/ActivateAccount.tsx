import React, { useState } from "react";
import { StyleSheet, View, ActivityIndicator, Text, Image, Pressable } from "react-native";
import { useForm } from "react-hook-form";
import { AuthService } from "../../zynerator/security/Auth.service";
import CustomButton from "../../zynerator/gui/CustomButton";
import CustomInput from "../../zynerator/gui/CustomInput";
import { useNavigation } from '@react-navigation/native';
import { ActivationDto } from "../../zynerator/dto/ActivationDto.model";

const ActivateAccount = () => {

    

    const { control, handleSubmit, reset } = useForm({
        defaultValues: {
            username: '',
            activationCode: '',
        }
    });

    const [activationDto, setActivationDto] = useState<ActivationDto>(new ActivationDto());
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const service = new AuthService();
    const navigation = useNavigation<any>();

    const handleActivateAccount = async (data: any) => {
        const { username, activationCode } = data;

        const newUser = {
            ...activationDto,
            username: username,
            activationCode: activationCode,
        };

        setActivationDto(newUser);
        setLoading(true);
        setError(null);

        try {
            await service.activateAccount(activationDto)
            navigation.navigate('Login');
            setError(null);
        } catch (error) {
            console.error("Login error:", error);
            setError("Activation failed: Invalid username or ActivationCode");
        } finally {
            setLoading(false);
            reset();
            setActivationDto(new ActivationDto());
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
                            <Text style={{ fontSize: 30, fontWeight: 'bold' }}>Activate Account</Text>
                        </View>

                        <CustomInput
                            name="username"
                            control={control}
                            placeholder="Username"
                            keyboardT="default"
                        />
                        <CustomInput
                            name="activationCode"
                            control={control}
                            placeholder="Activation Code"
                            keyboardT="default"
                            isTextArea={false}
                            secureTextEntry={true}
                        />
                        <CustomButton
                            onPress={handleSubmit(handleActivateAccount)}
                            text="Login"
                            type="PRIMARY"
                            bgColor="orange"
                            fgColor="black"
                        />

                        {error && (
                            <Text style={styles.errorText}>{error}</Text>
                        )}

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

export default ActivateAccount;
