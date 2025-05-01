import {Keyboard, SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useEffect, useState} from 'react';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';
import Collapsible from 'react-native-collapsible';

import {globalStyle} from '../../../../../../shared/globalStyle';
import Ionicons from 'react-native-vector-icons/Ionicons';

import {EntiteAdminService} from '../../../../../../controller/service/admin/referentiel/EntiteAdminService.service';
import  {EntiteDto}  from '../../../../../../controller/model/referentiel/Entite.model';


const EntiteAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isEntiteCollapsed, setIsEntiteCollapsed] = useState(true);



    const service = new EntiteAdminService();


    const { control: entiteControl, handleSubmit: entiteHandleSubmit, reset: entiteReset} = useForm<EntiteDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        style: '' ,
        description: '' ,
        },
    });

    const entiteCollapsible = () => {
        setIsEntiteCollapsed(!isEntiteCollapsed);
    };



    useEffect(() => {
    }, []);




    const handleSave = async (item: EntiteDto) => {
        Keyboard.dismiss();
        try {
            await service.save( item );
            entiteReset();
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving entite:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create Entite</Text>

            <TouchableOpacity onPress={entiteCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>Entite</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isEntiteCollapsed}>
                            <CustomInput control={entiteControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={entiteControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={entiteControl} name={'style'} placeholder={'Style'} keyboardT="default" />
                            <CustomInput control={entiteControl} name={'description'} placeholder={'Description'} keyboardT="default" />
            </Collapsible>
        <CustomButton onPress={entiteHandleSubmit(handleSave)} text={"Save Entite"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
    </SafeAreaView>
);
};
export default EntiteAdminCreate;
