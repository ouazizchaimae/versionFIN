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

import {CharteChimiqueDetailAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueDetailAdminService.service';
import  {CharteChimiqueDetailDto}  from '../../../../../../controller/model/expedition/CharteChimiqueDetail.model';

import {ElementChimiqueDto} from '../../../../../../controller/model/referentiel/ElementChimique.model';
import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import {CharteChimiqueDto} from '../../../../../../controller/model/expedition/CharteChimique.model';
import {CharteChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueAdminService.service';

const CharteChimiqueDetailAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isCharteChimiqueDetailCollapsed, setIsCharteChimiqueDetailCollapsed] = useState(true);


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


    const { control: charteChimiqueDetailControl, handleSubmit: charteChimiqueDetailHandleSubmit, reset: charteChimiqueDetailReset} = useForm<CharteChimiqueDetailDto>({
        defaultValues: {
        libelle: '' ,
        description: '' ,
        elementChimique: undefined,
        minimum: null ,
        maximum: null ,
        average: null ,
        methodeAnalyse: '' ,
        unite: '' ,
        charteChimique: undefined,
        },
    });

    const charteChimiqueDetailCollapsible = () => {
        setIsCharteChimiqueDetailCollapsed(!isCharteChimiqueDetailCollapsed);
    };

    const handleCloseElementChimiqueModal = () => {
        setElementChimiqueModalVisible(false);
    };

    const onElementChimiqueSelect = (item) => {
        setSelectedElementChimique(item);
        setElementChimiqueModalVisible(false);
    };
    const handleCloseCharteChimiqueModal = () => {
        setCharteChimiqueModalVisible(false);
    };

    const onCharteChimiqueSelect = (item) => {
        setSelectedCharteChimique(item);
        setCharteChimiqueModalVisible(false);
    };


    useEffect(() => {
        elementChimiqueAdminService.getList().then(({data}) => setElementChimiques(data)).catch(error => console.log(error));
        charteChimiqueAdminService.getList().then(({data}) => setCharteChimiques(data)).catch(error => console.log(error));
    }, []);




    const handleSave = async (item: CharteChimiqueDetailDto) => {
        item.elementChimique = selectedElementChimique;
        item.charteChimique = selectedCharteChimique;
        Keyboard.dismiss();
        try {
            await service.save( item );
            charteChimiqueDetailReset();
            setSelectedElementChimique(emptyElementChimique);
            setSelectedCharteChimique(emptyCharteChimique);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving charteChimiqueDetail:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create CharteChimiqueDetail</Text>

            <TouchableOpacity onPress={charteChimiqueDetailCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>CharteChimiqueDetail</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isCharteChimiqueDetailCollapsed}>
                            <CustomInput control={charteChimiqueDetailControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={charteChimiqueDetailControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                        <TouchableOpacity onPress={() => setElementChimiqueModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedElementChimique.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                            <CustomInput control={charteChimiqueDetailControl} name={'methodeAnalyse'} placeholder={'Methode analyse'} keyboardT="default" />
                            <CustomInput control={charteChimiqueDetailControl} name={'unite'} placeholder={'Unite'} keyboardT="default" />
                        <TouchableOpacity onPress={() => setCharteChimiqueModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedCharteChimique.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
            </Collapsible>
        <CustomButton onPress={charteChimiqueDetailHandleSubmit(handleSave)} text={"Save CharteChimiqueDetail"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {elementChimiques !== null && elementChimiques.length > 0 ? ( <FilterModal visibility={elementChimiqueModalVisible} placeholder={"Select a ElementChimique"} onItemSelect={onElementChimiqueSelect} items={elementChimiques} onClose={handleCloseElementChimiqueModal} variable={'libelle'} /> ) : null}
        {charteChimiques !== null && charteChimiques.length > 0 ? ( <FilterModal visibility={charteChimiqueModalVisible} placeholder={"Select a CharteChimique"} onItemSelect={onCharteChimiqueSelect} items={charteChimiques} onClose={handleCloseCharteChimiqueModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default CharteChimiqueDetailAdminCreate;
