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

import {AnalyseChimiqueDetailAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueDetailAdminService.service';
import  {AnalyseChimiqueDetailDto}  from '../../../../../../controller/model/expedition/AnalyseChimiqueDetail.model';

import {ElementChimiqueDto} from '../../../../../../controller/model/referentiel/ElementChimique.model';
import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import {AnalyseChimiqueDto} from '../../../../../../controller/model/expedition/AnalyseChimique.model';
import {AnalyseChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueAdminService.service';

type AnalyseChimiqueDetailUpdateScreenRouteProp = RouteProp<{ AnalyseChimiqueDetailUpdate: { analyseChimiqueDetail: AnalyseChimiqueDetailDto } }, 'AnalyseChimiqueDetailUpdate'>;

type Props = { route: AnalyseChimiqueDetailUpdateScreenRouteProp; };

const AnalyseChimiqueDetailAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { analyseChimiqueDetail } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

    const emptyElementChimique = new ElementChimiqueDto();
    const [elementChimiques, setElementChimiques] = useState<ElementChimiqueDto[]>([]);
    const [elementChimiqueModalVisible, setElementChimiqueModalVisible] = useState(false);
    const [selectedElementChimique, setSelectedElementChimique] = useState<ElementChimiqueDto>(emptyElementChimique);

    const emptyAnalyseChimique = new AnalyseChimiqueDto();
    const [analyseChimiques, setAnalyseChimiques] = useState<AnalyseChimiqueDto[]>([]);
    const [analyseChimiqueModalVisible, setAnalyseChimiqueModalVisible] = useState(false);
    const [selectedAnalyseChimique, setSelectedAnalyseChimique] = useState<AnalyseChimiqueDto>(emptyAnalyseChimique);


    const service = new AnalyseChimiqueDetailAdminService();
    const elementChimiqueAdminService = new ElementChimiqueAdminService();
    const analyseChimiqueAdminService = new AnalyseChimiqueAdminService();


    const { control, handleSubmit } = useForm<AnalyseChimiqueDetailDto>({
        defaultValues: {
            id: analyseChimiqueDetail.id ,
            libelle: analyseChimiqueDetail.libelle ,
            description: analyseChimiqueDetail.description ,
            valeur: analyseChimiqueDetail.valeur ,
            conformite: analyseChimiqueDetail.conformite ,
            surqualite: analyseChimiqueDetail.surqualite ,
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
    const handleCloseAnalyseChimiqueModal = () => {
        setAnalyseChimiqueModalVisible(false);
    };

    const onAnalyseChimiqueSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedAnalyseChimique(item);
        setAnalyseChimiqueModalVisible(false);
    };


    useEffect(() => {
        elementChimiqueAdminService.getList().then(({data}) => setElementChimiques(data)).catch(error => console.log(error));
        setSelectedElementChimique(analyseChimiqueDetail.elementChimique)
        analyseChimiqueAdminService.getList().then(({data}) => setAnalyseChimiques(data)).catch(error => console.log(error));
        setSelectedAnalyseChimique(analyseChimiqueDetail.analyseChimique)
    }, []);



    const handleUpdate = async (item: AnalyseChimiqueDetailDto) => {
        item.elementChimique = selectedElementChimique;
        item.analyseChimique = selectedAnalyseChimique;
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving analyse chimique detail:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Analyse chimique detail</Text>

            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <TouchableOpacity onPress={() => setElementChimiqueModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedElementChimique?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>
            <CustomInput control={control} name={'conformite'} placeholder={'Conformite'} keyboardT="numeric" />
            <CustomInput control={control} name={'surqualite'} placeholder={'Surqualite'} keyboardT="numeric" />

            <TouchableOpacity onPress={() => setAnalyseChimiqueModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedAnalyseChimique?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Analyse chimique detail"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {elementChimiques &&
            <FilterModal visibility={elementChimiqueModalVisible} placeholder={"Select a ElementChimique"} onItemSelect={onElementChimiqueSelect} items={elementChimiques} onClose={handleCloseElementChimiqueModal} variable={'libelle'} />
        }
        {analyseChimiques &&
            <FilterModal visibility={analyseChimiqueModalVisible} placeholder={"Select a AnalyseChimique"} onItemSelect={onAnalyseChimiqueSelect} items={analyseChimiques} onClose={handleCloseAnalyseChimiqueModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default AnalyseChimiqueDetailAdminEdit;
