import {Keyboard, SafeAreaView, Text, View, TouchableOpacity} from 'react-native';
import React, {useEffect, useState} from 'react';
import {NavigationProp, RouteProp, useNavigation} from '@react-navigation/native';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import {ScrollView} from 'react-native-gesture-handler';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';
import FilterModal from '../../../../../../zynerator/gui/FilterModal';

import {globalStyle} from "../../../../../../shared/globalStyle";
import Ionicons from "react-native-vector-icons/Ionicons";

import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import  {ElementChimiqueDto}  from '../../../../../../controller/model/referentiel/ElementChimique.model';

import {UniteDto} from '../../../../../../controller/model/referentiel/Unite.model';
import {UniteAdminService} from '../../../../../../controller/service/admin/referentiel/UniteAdminService.service';

type ElementChimiqueUpdateScreenRouteProp = RouteProp<{ ElementChimiqueUpdate: { elementChimique: ElementChimiqueDto } }, 'ElementChimiqueUpdate'>;

type Props = { route: ElementChimiqueUpdateScreenRouteProp; };

const ElementChimiqueAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { elementChimique } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

    const emptyUnite = new UniteDto();
    const [unites, setUnites] = useState<UniteDto[]>([]);
    const [uniteModalVisible, setUniteModalVisible] = useState(false);
    const [selectedUnite, setSelectedUnite] = useState<UniteDto>(emptyUnite);


    const service = new ElementChimiqueAdminService();
    const uniteAdminService = new UniteAdminService();


    const { control, handleSubmit } = useForm<ElementChimiqueDto>({
        defaultValues: {
            id: elementChimique.id ,
            code: elementChimique.code ,
            libelle: elementChimique.libelle ,
            style: elementChimique.style ,
            description: elementChimique.description ,
        },
    });



    const handleCloseUniteModal = () => {
        setUniteModalVisible(false);
    };

    const onUniteSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedUnite(item);
        setUniteModalVisible(false);
    };


    useEffect(() => {
        uniteAdminService.getList().then(({data}) => setUnites(data)).catch(error => console.log(error));
        setSelectedUnite(elementChimique.unite)
    }, []);



    const handleUpdate = async (item: ElementChimiqueDto) => {
        item.unite = selectedUnite;
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving element chimique:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Element chimique</Text>

            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'style'} placeholder={'Style'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <TouchableOpacity onPress={() => setUniteModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedUnite?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Element chimique"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {unites &&
            <FilterModal visibility={uniteModalVisible} placeholder={"Select a Unite"} onItemSelect={onUniteSelect} items={unites} onClose={handleCloseUniteModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default ElementChimiqueAdminEdit;
