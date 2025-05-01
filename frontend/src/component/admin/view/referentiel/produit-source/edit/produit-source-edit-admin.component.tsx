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

import {ProduitSourceAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitSourceAdminService.service';
import  {ProduitSourceDto}  from '../../../../../../controller/model/referentiel/ProduitSource.model';


type ProduitSourceUpdateScreenRouteProp = RouteProp<{ ProduitSourceUpdate: { produitSource: ProduitSourceDto } }, 'ProduitSourceUpdate'>;

type Props = { route: ProduitSourceUpdateScreenRouteProp; };

const ProduitSourceAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { produitSource } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);


    const service = new ProduitSourceAdminService();


    const { control, handleSubmit } = useForm<ProduitSourceDto>({
        defaultValues: {
            id: produitSource.id ,
            code: produitSource.code ,
            libelle: produitSource.libelle ,
            style: produitSource.style ,
            description: produitSource.description ,
        },
    });





    useEffect(() => {
    }, []);



    const handleUpdate = async (item: ProduitSourceDto) => {
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving produit source:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Produit source</Text>

            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'style'} placeholder={'Style'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Produit source"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />

    </SafeAreaView>
);
};

export default ProduitSourceAdminEdit;
