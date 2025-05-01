import {Keyboard, SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useEffect, useState} from 'react';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';
import Collapsible from 'react-native-collapsible';

import {globalStyle} from '../../../../../../shared/globalStyle';
import Ionicons from 'react-native-vector-icons/Ionicons';

import {UniteAdminService} from '../../../../../../controller/service/admin/referentiel/UniteAdminService.service';
import  {UniteDto}  from '../../../../../../controller/model/referentiel/Unite.model';


const UniteAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isUniteCollapsed, setIsUniteCollapsed] = useState(true);



    const service = new UniteAdminService();


    const { control: uniteControl, handleSubmit: uniteHandleSubmit, reset: uniteReset} = useForm<UniteDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        description: '' ,
        style: '' ,
        },
    });

    const uniteCollapsible = () => {
        setIsUniteCollapsed(!isUniteCollapsed);
    };



    useEffect(() => {
    }, []);




    const handleSave = async (item: UniteDto) => {
        Keyboard.dismiss();
        try {
            await service.save( item );
            uniteReset();
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving unite:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create Unite</Text>

            <TouchableOpacity onPress={uniteCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>Unite</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isUniteCollapsed}>
                            <CustomInput control={uniteControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={uniteControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={uniteControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                            <CustomInput control={uniteControl} name={'style'} placeholder={'Style'} keyboardT="default" />
            </Collapsible>
        <CustomButton onPress={uniteHandleSubmit(handleSave)} text={"Save Unite"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
    </SafeAreaView>
);
};
export default UniteAdminCreate;
