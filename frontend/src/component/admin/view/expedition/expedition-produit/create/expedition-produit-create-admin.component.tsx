import {Keyboard, SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useEffect, useState} from 'react';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import FilterModal from '../../../../../../zynerator/gui/FilterModal';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';
import Collapsible from 'react-native-collapsible';

import {globalStyle} from '../../../../../../shared/globalStyle';
import Ionicons from 'react-native-vector-icons/Ionicons';

import {ExpeditionProduitAdminService} from '../../../../../../controller/service/admin/expedition/ExpeditionProduitAdminService.service';
import  {ExpeditionProduitDto}  from '../../../../../../controller/model/expedition/ExpeditionProduit.model';

import {AnalyseChimiqueDto} from '../../../../../../controller/model/expedition/AnalyseChimique.model';
import {AnalyseChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueAdminService.service';
import {CharteChimiqueDto} from '../../../../../../controller/model/expedition/CharteChimique.model';
import {CharteChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueAdminService.service';

const ExpeditionProduitAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isExpeditionProduitCollapsed, setIsExpeditionProduitCollapsed] = useState(true);


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


    const { control: expeditionProduitControl, handleSubmit: expeditionProduitHandleSubmit, reset: expeditionProduitReset} = useForm<ExpeditionProduitDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        description: '' ,
        analyseChimique: undefined,
        charteChimique: undefined,
        },
    });

    const expeditionProduitCollapsible = () => {
        setIsExpeditionProduitCollapsed(!isExpeditionProduitCollapsed);
    };

    const handleCloseCharteChimiqueModal = () => {
        setCharteChimiqueModalVisible(false);
    };

    const onCharteChimiqueSelect = (item) => {
        setSelectedCharteChimique(item);
        setCharteChimiqueModalVisible(false);
    };
    const handleCloseAnalyseChimiqueModal = () => {
        setAnalyseChimiqueModalVisible(false);
    };

    const onAnalyseChimiqueSelect = (item) => {
        setSelectedAnalyseChimique(item);
        setAnalyseChimiqueModalVisible(false);
    };


    useEffect(() => {
        analyseChimiqueAdminService.getList().then(({data}) => setAnalyseChimiques(data)).catch(error => console.log(error));
        charteChimiqueAdminService.getList().then(({data}) => setCharteChimiques(data)).catch(error => console.log(error));
    }, []);




    const handleSave = async (item: ExpeditionProduitDto) => {
        item.analyseChimique = selectedAnalyseChimique;
        item.charteChimique = selectedCharteChimique;
        Keyboard.dismiss();
        try {
            await service.save( item );
            expeditionProduitReset();
            setSelectedAnalyseChimique(emptyAnalyseChimique);
            setSelectedCharteChimique(emptyCharteChimique);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving expeditionProduit:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create ExpeditionProduit</Text>

            <TouchableOpacity onPress={expeditionProduitCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>ExpeditionProduit</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isExpeditionProduitCollapsed}>
                            <CustomInput control={expeditionProduitControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={expeditionProduitControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={expeditionProduitControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                        <TouchableOpacity onPress={() => setAnalyseChimiqueModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedAnalyseChimique.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setCharteChimiqueModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedCharteChimique.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
            </Collapsible>
        <CustomButton onPress={expeditionProduitHandleSubmit(handleSave)} text={"Save ExpeditionProduit"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {charteChimiques !== null && charteChimiques.length > 0 ? ( <FilterModal visibility={charteChimiqueModalVisible} placeholder={"Select a CharteChimique"} onItemSelect={onCharteChimiqueSelect} items={charteChimiques} onClose={handleCloseCharteChimiqueModal} variable={'libelle'} /> ) : null}
        {analyseChimiques !== null && analyseChimiques.length > 0 ? ( <FilterModal visibility={analyseChimiqueModalVisible} placeholder={"Select a AnalyseChimique"} onItemSelect={onAnalyseChimiqueSelect} items={analyseChimiques} onClose={handleCloseAnalyseChimiqueModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default ExpeditionProduitAdminCreate;
