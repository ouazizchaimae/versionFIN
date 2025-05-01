import {Keyboard, SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useEffect, useState} from 'react';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';
import Collapsible from 'react-native-collapsible';

import {globalStyle} from '../../../../../../shared/globalStyle';
import Ionicons from 'react-native-vector-icons/Ionicons';

import {TypeClientAdminService} from '../../../../../../controller/service/admin/referentiel/TypeClientAdminService.service';
import  {TypeClientDto}  from '../../../../../../controller/model/referentiel/TypeClient.model';


const TypeClientAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isTypeClientCollapsed, setIsTypeClientCollapsed] = useState(true);



    const service = new TypeClientAdminService();


    const { control: typeClientControl, handleSubmit: typeClientHandleSubmit, reset: typeClientReset} = useForm<TypeClientDto>({
        defaultValues: {
        libelle: '' ,
        code: '' ,
        style: '' ,
        description: '' ,
        },
    });

    const typeClientCollapsible = () => {
        setIsTypeClientCollapsed(!isTypeClientCollapsed);
    };



    useEffect(() => {
    }, []);




    const handleSave = async (item: TypeClientDto) => {
        Keyboard.dismiss();
        try {
            await service.save( item );
            typeClientReset();
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving typeClient:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create TypeClient</Text>

            <TouchableOpacity onPress={typeClientCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>TypeClient</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isTypeClientCollapsed}>
                            <CustomInput control={typeClientControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={typeClientControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={typeClientControl} name={'style'} placeholder={'Style'} keyboardT="default" />
                            <CustomInput control={typeClientControl} name={'description'} placeholder={'Description'} keyboardT="default" />
            </Collapsible>
        <CustomButton onPress={typeClientHandleSubmit(handleSave)} text={"Save TypeClient"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
    </SafeAreaView>
);
};
export default TypeClientAdminCreate;
