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

import {CharteChimiqueDetailAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueDetailAdminService.service';
import  {CharteChimiqueDetailDto}  from '../../../../../../controller/model/expedition/CharteChimiqueDetail.model';

import {ElementChimiqueDto} from '../../../../../../controller/model/referentiel/ElementChimique.model';
import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import {CharteChimiqueDto} from '../../../../../../controller/model/expedition/CharteChimique.model';
import {CharteChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueAdminService.service';

type CharteChimiqueDetailUpdateScreenRouteProp = RouteProp<{ CharteChimiqueDetailUpdate: { charteChimiqueDetail: CharteChimiqueDetailDto } }, 'CharteChimiqueDetailUpdate'>;

type Props = { route: CharteChimiqueDetailUpdateScreenRouteProp; };

const CharteChimiqueDetailAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { charteChimiqueDetail } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

    const emptyElementChimique = new ElementChimiqueDto();
    const [elementChimiques, setElementChimiques] = useState<ElementChimiqueDto[]>([]);
    const [elementChimiqueModalVisible, setElementChimiqueModalVisible] = useState(false);
    const [selectedElementChimique, setSelectedElementChimique] = useState<ElementChimiqueDto>(emptyElementChimique);

    const emptyCharteChimique = new CharteChimiqueDto();
    const [charteChimiques, setCharteChimiques] = useState<CharteChimiqueDto[]>([]);
    const [charteChimiqueModalVisible, setCharteChimiqueModalVisible] = useState(false);
    const [selectedCharteChimique, setSelectedCharteChimique] = useState<CharteChimiqueDto>(emptyCharteChimique);


    const service = new CharteChimiqueDetailAdminService();
    const elementChimiqueAdminService = new ElementChimiqueAdminService();
    const charteChimiqueAdminService = new CharteChimiqueAdminService();


    const { control, handleSubmit } = useForm<CharteChimiqueDetailDto>({
        defaultValues: {
            id: charteChimiqueDetail.id ,
            libelle: charteChimiqueDetail.libelle ,
            description: charteChimiqueDetail.description ,
            minimum: charteChimiqueDetail.minimum ,
            maximum: charteChimiqueDetail.maximum ,
            average: charteChimiqueDetail.average ,
            methodeAnalyse: charteChimiqueDetail.methodeAnalyse ,
            unite: charteChimiqueDetail.unite ,
        },
    });



    const handleCloseElementChimiqueModal = () => {
        setElementChimiqueModalVisible(false);
    };

    const onElementChimiqueSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedElementChimique(item);
        setElementChimiqueModalVisible(false);
    };
    const handleCloseCharteChimiqueModal = () => {
        setCharteChimiqueModalVisible(false);
    };

    const onCharteChimiqueSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedCharteChimique(item);
        setCharteChimiqueModalVisible(false);
    };


    useEffect(() => {
        elementChimiqueAdminService.getList().then(({data}) => setElementChimiques(data)).catch(error => console.log(error));
        setSelectedElementChimique(charteChimiqueDetail.elementChimique)
        charteChimiqueAdminService.getList().then(({data}) => setCharteChimiques(data)).catch(error => console.log(error));
        setSelectedCharteChimique(charteChimiqueDetail.charteChimique)
    }, []);



    const handleUpdate = async (item: CharteChimiqueDetailDto) => {
        item.elementChimique = selectedElementChimique;
        item.charteChimique = selectedCharteChimique;
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving charte chimique detail:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Charte chimique detail</Text>

            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <TouchableOpacity onPress={() => setElementChimiqueModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedElementChimique?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>
            <CustomInput control={control} name={'methodeAnalyse'} placeholder={'Methode analyse'} keyboardT="default" />
            <CustomInput control={control} name={'unite'} placeholder={'Unite'} keyboardT="default" />

            <TouchableOpacity onPress={() => setCharteChimiqueModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedCharteChimique?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Charte chimique detail"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {elementChimiques &&
            <FilterModal visibility={elementChimiqueModalVisible} placeholder={"Select a ElementChimique"} onItemSelect={onElementChimiqueSelect} items={elementChimiques} onClose={handleCloseElementChimiqueModal} variable={'libelle'} />
        }
        {charteChimiques &&
            <FilterModal visibility={charteChimiqueModalVisible} placeholder={"Select a CharteChimique"} onItemSelect={onCharteChimiqueSelect} items={charteChimiques} onClose={handleCloseCharteChimiqueModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default CharteChimiqueDetailAdminEdit;
