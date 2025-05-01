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

import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import  {ElementChimiqueDto}  from '../../../../../../controller/model/referentiel/ElementChimique.model';

import {UniteDto} from '../../../../../../controller/model/referentiel/Unite.model';
import {UniteAdminService} from '../../../../../../controller/service/admin/referentiel/UniteAdminService.service';

const ElementChimiqueAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isElementChimiqueCollapsed, setIsElementChimiqueCollapsed] = useState(true);


    const emptyUnite = new UniteDto();
    const [unites, setUnites] = useState<UniteDto[]>([]);
    const [uniteModalVisible, setUniteModalVisible] = useState(false);
    const [selectedUnite, setSelectedUnite] = useState<UniteDto>(emptyUnite);


    const service = new ElementChimiqueAdminService();
    const uniteAdminService = new UniteAdminService();


    const { control: elementChimiqueControl, handleSubmit: elementChimiqueHandleSubmit, reset: elementChimiqueReset} = useForm<ElementChimiqueDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        style: '' ,
        description: '' ,
        unite: undefined,
        },
    });

    const elementChimiqueCollapsible = () => {
        setIsElementChimiqueCollapsed(!isElementChimiqueCollapsed);
    };

    const handleCloseUniteModal = () => {
        setUniteModalVisible(false);
    };

    const onUniteSelect = (item) => {
        setSelectedUnite(item);
        setUniteModalVisible(false);
    };


    useEffect(() => {
        uniteAdminService.getList().then(({data}) => setUnites(data)).catch(error => console.log(error));
    }, []);




    const handleSave = async (item: ElementChimiqueDto) => {
        item.unite = selectedUnite;
        Keyboard.dismiss();
        try {
            await service.save( item );
            elementChimiqueReset();
            setSelectedUnite(emptyUnite);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving elementChimique:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create ElementChimique</Text>

            <TouchableOpacity onPress={elementChimiqueCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>ElementChimique</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isElementChimiqueCollapsed}>
                            <CustomInput control={elementChimiqueControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={elementChimiqueControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={elementChimiqueControl} name={'style'} placeholder={'Style'} keyboardT="default" />
                            <CustomInput control={elementChimiqueControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                        <TouchableOpacity onPress={() => setUniteModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedUnite.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
            </Collapsible>
        <CustomButton onPress={elementChimiqueHandleSubmit(handleSave)} text={"Save ElementChimique"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {unites !== null && unites.length > 0 ? ( <FilterModal visibility={uniteModalVisible} placeholder={"Select a Unite"} onItemSelect={onUniteSelect} items={unites} onClose={handleCloseUniteModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default ElementChimiqueAdminCreate;
