import {Keyboard, SafeAreaView, Text, View, TouchableOpacity} from 'react-native';
import React, {useEffect, useState} from 'react';
import {NavigationProp, RouteProp, useNavigation} from '@react-navigation/native';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import {ScrollView} from 'react-native-gesture-handler';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';

import {globalStyle} from "../../../../../../shared/globalStyle";
import Ionicons from "react-native-vector-icons/Ionicons";

import {TypeExpeditionAdminService} from '../../../../../../controller/service/admin/expedition/TypeExpeditionAdminService.service';
import  {TypeExpeditionDto}  from '../../../../../../controller/model/expedition/TypeExpedition.model';


type TypeExpeditionUpdateScreenRouteProp = RouteProp<{ TypeExpeditionUpdate: { typeExpedition: TypeExpeditionDto } }, 'TypeExpeditionUpdate'>;

type Props = { route: TypeExpeditionUpdateScreenRouteProp; };

const TypeExpeditionAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { typeExpedition } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);


    const service = new TypeExpeditionAdminService();


    const { control, handleSubmit } = useForm<TypeExpeditionDto>({
        defaultValues: {
            id: typeExpedition.id ,
            libelle: typeExpedition.libelle ,
            code: typeExpedition.code ,
            style: typeExpedition.style ,
            description: typeExpedition.description ,
        },
    });





    useEffect(() => {
    }, []);



    const handleUpdate = async (item: TypeExpeditionDto) => {
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving type expedition:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Type expedition</Text>

            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'style'} placeholder={'Style'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Type expedition"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />

    </SafeAreaView>
);
};

export default TypeExpeditionAdminEdit;
