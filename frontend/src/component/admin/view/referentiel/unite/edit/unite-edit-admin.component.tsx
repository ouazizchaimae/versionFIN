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

import {UniteAdminService} from '../../../../../../controller/service/admin/referentiel/UniteAdminService.service';
import  {UniteDto}  from '../../../../../../controller/model/referentiel/Unite.model';


type UniteUpdateScreenRouteProp = RouteProp<{ UniteUpdate: { unite: UniteDto } }, 'UniteUpdate'>;

type Props = { route: UniteUpdateScreenRouteProp; };

const UniteAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { unite } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);


    const service = new UniteAdminService();


    const { control, handleSubmit } = useForm<UniteDto>({
        defaultValues: {
            id: unite.id ,
            code: unite.code ,
            libelle: unite.libelle ,
            description: unite.description ,
            style: unite.style ,
        },
    });





    useEffect(() => {
    }, []);



    const handleUpdate = async (item: UniteDto) => {
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving unite:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Unite</Text>

            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />
            <CustomInput control={control} name={'style'} placeholder={'Style'} keyboardT="default" />

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Unite"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />

    </SafeAreaView>
);
};

export default UniteAdminEdit;
