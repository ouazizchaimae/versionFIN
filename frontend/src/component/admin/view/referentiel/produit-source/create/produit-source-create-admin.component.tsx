import {Keyboard, SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useEffect, useState} from 'react';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';
import Collapsible from 'react-native-collapsible';

import {globalStyle} from '../../../../../../shared/globalStyle';
import Ionicons from 'react-native-vector-icons/Ionicons';

import {ProduitSourceAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitSourceAdminService.service';
import  {ProduitSourceDto}  from '../../../../../../controller/model/referentiel/ProduitSource.model';


const ProduitSourceAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isProduitSourceCollapsed, setIsProduitSourceCollapsed] = useState(true);



    const service = new ProduitSourceAdminService();


    const { control: produitSourceControl, handleSubmit: produitSourceHandleSubmit, reset: produitSourceReset} = useForm<ProduitSourceDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        style: '' ,
        description: '' ,
        },
    });

    const produitSourceCollapsible = () => {
        setIsProduitSourceCollapsed(!isProduitSourceCollapsed);
    };



    useEffect(() => {
    }, []);




    const handleSave = async (item: ProduitSourceDto) => {
        Keyboard.dismiss();
        try {
            await service.save( item );
            produitSourceReset();
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving produitSource:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create ProduitSource</Text>

            <TouchableOpacity onPress={produitSourceCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>ProduitSource</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isProduitSourceCollapsed}>
                            <CustomInput control={produitSourceControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={produitSourceControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={produitSourceControl} name={'style'} placeholder={'Style'} keyboardT="default" />
                            <CustomInput control={produitSourceControl} name={'description'} placeholder={'Description'} keyboardT="default" />
            </Collapsible>
        <CustomButton onPress={produitSourceHandleSubmit(handleSave)} text={"Save ProduitSource"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
    </SafeAreaView>
);
};
export default ProduitSourceAdminCreate;
