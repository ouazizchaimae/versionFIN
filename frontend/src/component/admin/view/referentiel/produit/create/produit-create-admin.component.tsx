import {Keyboard, SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useEffect, useState} from 'react';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';
import Collapsible from 'react-native-collapsible';

import {globalStyle} from '../../../../../../shared/globalStyle';
import Ionicons from 'react-native-vector-icons/Ionicons';

import {ProduitAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitAdminService.service';
import  {ProduitDto}  from '../../../../../../controller/model/referentiel/Produit.model';


const ProduitAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isProduitCollapsed, setIsProduitCollapsed] = useState(true);



    const service = new ProduitAdminService();


    const { control: produitControl, handleSubmit: produitHandleSubmit, reset: produitReset} = useForm<ProduitDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        description: '' ,
        },
    });

    const produitCollapsible = () => {
        setIsProduitCollapsed(!isProduitCollapsed);
    };



    useEffect(() => {
    }, []);




    const handleSave = async (item: ProduitDto) => {
        Keyboard.dismiss();
        try {
            await service.save( item );
            produitReset();
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving produit:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create Produit</Text>

            <TouchableOpacity onPress={produitCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>Produit</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isProduitCollapsed}>
                            <CustomInput control={produitControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={produitControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={produitControl} name={'description'} placeholder={'Description'} keyboardT="default" />
            </Collapsible>
        <CustomButton onPress={produitHandleSubmit(handleSave)} text={"Save Produit"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
    </SafeAreaView>
);
};
export default ProduitAdminCreate;
