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

import {ExpeditionProduitAdminService} from '../../../../../../controller/service/admin/expedition/ExpeditionProduitAdminService.service';
import  {ExpeditionProduitDto}  from '../../../../../../controller/model/expedition/ExpeditionProduit.model';

import {AnalyseChimiqueDto} from '../../../../../../controller/model/expedition/AnalyseChimique.model';
import {AnalyseChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueAdminService.service';
import {CharteChimiqueDto} from '../../../../../../controller/model/expedition/CharteChimique.model';
import {CharteChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueAdminService.service';

type ExpeditionProduitUpdateScreenRouteProp = RouteProp<{ ExpeditionProduitUpdate: { expeditionProduit: ExpeditionProduitDto } }, 'ExpeditionProduitUpdate'>;

type Props = { route: ExpeditionProduitUpdateScreenRouteProp; };

const ExpeditionProduitAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { expeditionProduit } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

    const emptyCharteChimique = new CharteChimiqueDto();
    const [charteChimiques, setCharteChimiques] = useState<CharteChimiqueDto[]>([]);
    const [charteChimiqueModalVisible, setCharteChimiqueModalVisible] = useState(false);
    const [selectedCharteChimique, setSelectedCharteChimique] = useState<CharteChimiqueDto>(emptyCharteChimique);

    const emptyAnalyseChimique = new AnalyseChimiqueDto();
    const [analyseChimiques, setAnalyseChimiques] = useState<AnalyseChimiqueDto[]>([]);
    const [analyseChimiqueModalVisible, setAnalyseChimiqueModalVisible] = useState(false);
    const [selectedAnalyseChimique, setSelectedAnalyseChimique] = useState<AnalyseChimiqueDto>(emptyAnalyseChimique);


    const service = new ExpeditionProduitAdminService();
    const analyseChimiqueAdminService = new AnalyseChimiqueAdminService();
    const charteChimiqueAdminService = new CharteChimiqueAdminService();


    const { control, handleSubmit } = useForm<ExpeditionProduitDto>({
        defaultValues: {
            id: expeditionProduit.id ,
            code: expeditionProduit.code ,
            libelle: expeditionProduit.libelle ,
            description: expeditionProduit.description ,
        },
    });



    const handleCloseCharteChimiqueModal = () => {
        setCharteChimiqueModalVisible(false);
    };

    const onCharteChimiqueSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedCharteChimique(item);
        setCharteChimiqueModalVisible(false);
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
        analyseChimiqueAdminService.getList().then(({data}) => setAnalyseChimiques(data)).catch(error => console.log(error));
        setSelectedAnalyseChimique(expeditionProduit.analyseChimique)
        charteChimiqueAdminService.getList().then(({data}) => setCharteChimiques(data)).catch(error => console.log(error));
        setSelectedCharteChimique(expeditionProduit.charteChimique)
    }, []);



    const handleUpdate = async (item: ExpeditionProduitDto) => {
        item.analyseChimique = selectedAnalyseChimique;
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
            console.error('Error saving expedition produit:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Expedition produit</Text>

            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <TouchableOpacity onPress={() => setAnalyseChimiqueModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedAnalyseChimique?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <TouchableOpacity onPress={() => setCharteChimiqueModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedCharteChimique?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Expedition produit"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {analyseChimiques &&
            <FilterModal visibility={analyseChimiqueModalVisible} placeholder={"Select a AnalyseChimique"} onItemSelect={onAnalyseChimiqueSelect} items={analyseChimiques} onClose={handleCloseAnalyseChimiqueModal} variable={'libelle'} />
        }
        {charteChimiques &&
            <FilterModal visibility={charteChimiqueModalVisible} placeholder={"Select a CharteChimique"} onItemSelect={onCharteChimiqueSelect} items={charteChimiques} onClose={handleCloseCharteChimiqueModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default ExpeditionProduitAdminEdit;
