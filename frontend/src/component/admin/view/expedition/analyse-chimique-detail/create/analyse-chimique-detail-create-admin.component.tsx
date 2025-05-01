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

import {AnalyseChimiqueDetailAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueDetailAdminService.service';
import  {AnalyseChimiqueDetailDto}  from '../../../../../../controller/model/expedition/AnalyseChimiqueDetail.model';

import {ElementChimiqueDto} from '../../../../../../controller/model/referentiel/ElementChimique.model';
import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import {AnalyseChimiqueDto} from '../../../../../../controller/model/expedition/AnalyseChimique.model';
import {AnalyseChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueAdminService.service';

const AnalyseChimiqueDetailAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isAnalyseChimiqueDetailCollapsed, setIsAnalyseChimiqueDetailCollapsed] = useState(true);


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


    const { control: analyseChimiqueDetailControl, handleSubmit: analyseChimiqueDetailHandleSubmit, reset: analyseChimiqueDetailReset} = useForm<AnalyseChimiqueDetailDto>({
        defaultValues: {
        libelle: '' ,
        description: '' ,
        elementChimique: undefined,
        valeur: null ,
        analyseChimique: undefined,
        },
    });

    const analyseChimiqueDetailCollapsible = () => {
        setIsAnalyseChimiqueDetailCollapsed(!isAnalyseChimiqueDetailCollapsed);
    };

    const handleCloseElementChimiqueModal = () => {
        setElementChimiqueModalVisible(false);
    };

    const onElementChimiqueSelect = (item) => {
        setSelectedElementChimique(item);
        setElementChimiqueModalVisible(false);
    };
    const handleCloseAnalyseChimiqueModal = () => {
        setAnalyseChimiqueModalVisible(false);
    };

    const onAnalyseChimiqueSelect = (item) => {
        setSelectedAnalyseChimique(item);
        setAnalyseChimiqueModalVisible(false);
    };


    useEffect(() => {
        elementChimiqueAdminService.getList().then(({data}) => setElementChimiques(data)).catch(error => console.log(error));
        analyseChimiqueAdminService.getList().then(({data}) => setAnalyseChimiques(data)).catch(error => console.log(error));
    }, []);




    const handleSave = async (item: AnalyseChimiqueDetailDto) => {
        item.elementChimique = selectedElementChimique;
        item.analyseChimique = selectedAnalyseChimique;
        Keyboard.dismiss();
        try {
            await service.save( item );
            analyseChimiqueDetailReset();
            setSelectedElementChimique(emptyElementChimique);
            setSelectedAnalyseChimique(emptyAnalyseChimique);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving analyseChimiqueDetail:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create AnalyseChimiqueDetail</Text>

            <TouchableOpacity onPress={analyseChimiqueDetailCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>AnalyseChimiqueDetail</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isAnalyseChimiqueDetailCollapsed}>
                            <CustomInput control={analyseChimiqueDetailControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={analyseChimiqueDetailControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                        <TouchableOpacity onPress={() => setElementChimiqueModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedElementChimique.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                            <CustomInput control={analyseChimiqueDetailControl} name={'conformite'} placeholder={'Conformite'} keyboardT="numeric" />
                            <CustomInput control={analyseChimiqueDetailControl} name={'surqualite'} placeholder={'Surqualite'} keyboardT="numeric" />
                        <TouchableOpacity onPress={() => setAnalyseChimiqueModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedAnalyseChimique.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
            </Collapsible>
        <CustomButton onPress={analyseChimiqueDetailHandleSubmit(handleSave)} text={"Save AnalyseChimiqueDetail"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {elementChimiques !== null && elementChimiques.length > 0 ? ( <FilterModal visibility={elementChimiqueModalVisible} placeholder={"Select a ElementChimique"} onItemSelect={onElementChimiqueSelect} items={elementChimiques} onClose={handleCloseElementChimiqueModal} variable={'libelle'} /> ) : null}
        {analyseChimiques !== null && analyseChimiques.length > 0 ? ( <FilterModal visibility={analyseChimiqueModalVisible} placeholder={"Select a AnalyseChimique"} onItemSelect={onAnalyseChimiqueSelect} items={analyseChimiques} onClose={handleCloseAnalyseChimiqueModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default AnalyseChimiqueDetailAdminCreate;
