import {Keyboard, SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useEffect, useState} from 'react';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';
import Collapsible from 'react-native-collapsible';

import {globalStyle} from '../../../../../../shared/globalStyle';
import Ionicons from 'react-native-vector-icons/Ionicons';

import {TypeExpeditionAdminService} from '../../../../../../controller/service/admin/expedition/TypeExpeditionAdminService.service';
import  {TypeExpeditionDto}  from '../../../../../../controller/model/expedition/TypeExpedition.model';


const TypeExpeditionAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isTypeExpeditionCollapsed, setIsTypeExpeditionCollapsed] = useState(true);



    const service = new TypeExpeditionAdminService();


    const { control: typeExpeditionControl, handleSubmit: typeExpeditionHandleSubmit, reset: typeExpeditionReset} = useForm<TypeExpeditionDto>({
        defaultValues: {
        libelle: '' ,
        code: '' ,
        style: '' ,
        description: '' ,
        },
    });

    const typeExpeditionCollapsible = () => {
        setIsTypeExpeditionCollapsed(!isTypeExpeditionCollapsed);
    };



    useEffect(() => {
    }, []);




    const handleSave = async (item: TypeExpeditionDto) => {
        Keyboard.dismiss();
        try {
            await service.save( item );
            typeExpeditionReset();
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving typeExpedition:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create TypeExpedition</Text>

            <TouchableOpacity onPress={typeExpeditionCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>TypeExpedition</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isTypeExpeditionCollapsed}>
                            <CustomInput control={typeExpeditionControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={typeExpeditionControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={typeExpeditionControl} name={'style'} placeholder={'Style'} keyboardT="default" />
                            <CustomInput control={typeExpeditionControl} name={'description'} placeholder={'Description'} keyboardT="default" />
            </Collapsible>
        <CustomButton onPress={typeExpeditionHandleSubmit(handleSave)} text={"Save TypeExpedition"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
    </SafeAreaView>
);
};
export default TypeExpeditionAdminCreate;
