import {Keyboard, SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useEffect, useState} from 'react';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';
import Collapsible from 'react-native-collapsible';

import {globalStyle} from '../../../../../../shared/globalStyle';
import Ionicons from 'react-native-vector-icons/Ionicons';

import {ProduitMarchandAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitMarchandAdminService.service';
import  {ProduitMarchandDto}  from '../../../../../../controller/model/referentiel/ProduitMarchand.model';


const ProduitMarchandAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isProduitMarchandCollapsed, setIsProduitMarchandCollapsed] = useState(true);



    const service = new ProduitMarchandAdminService();


    const { control: produitMarchandControl, handleSubmit: produitMarchandHandleSubmit, reset: produitMarchandReset} = useForm<ProduitMarchandDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        style: '' ,
        description: '' ,
        },
    });

    const produitMarchandCollapsible = () => {
        setIsProduitMarchandCollapsed(!isProduitMarchandCollapsed);
    };



    useEffect(() => {
    }, []);




    const handleSave = async (item: ProduitMarchandDto) => {
        Keyboard.dismiss();
        try {
            await service.save( item );
            produitMarchandReset();
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving produitMarchand:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create ProduitMarchand</Text>

            <TouchableOpacity onPress={produitMarchandCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>ProduitMarchand</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isProduitMarchandCollapsed}>
                            <CustomInput control={produitMarchandControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={produitMarchandControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={produitMarchandControl} name={'style'} placeholder={'Style'} keyboardT="default" />
                            <CustomInput control={produitMarchandControl} name={'description'} placeholder={'Description'} keyboardT="default" />
            </Collapsible>
        <CustomButton onPress={produitMarchandHandleSubmit(handleSave)} text={"Save ProduitMarchand"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
    </SafeAreaView>
);
};
export default ProduitMarchandAdminCreate;
