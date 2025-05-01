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

import {AnalyseChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueAdminService.service';
import  {AnalyseChimiqueDto}  from '../../../../../../controller/model/expedition/AnalyseChimique.model';

import {AnalyseChimiqueDetailDto} from '../../../../../../controller/model/expedition/AnalyseChimiqueDetail.model';
import {AnalyseChimiqueDetailAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueDetailAdminService.service';
import {ElementChimiqueDto} from '../../../../../../controller/model/referentiel/ElementChimique.model';
import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import {ProduitMarchandDto} from '../../../../../../controller/model/referentiel/ProduitMarchand.model';
import {ProduitMarchandAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitMarchandAdminService.service';

const AnalyseChimiqueAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isAnalyseChimiqueCollapsed, setIsAnalyseChimiqueCollapsed] = useState(true);


    const emptyElementChimique = new ElementChimiqueDto();
    const [elementChimiques, setElementChimiques] = useState<ElementChimiqueDto[]>([]);
    const [elementChimiqueModalVisible, setElementChimiqueModalVisible] = useState(false);
    const [selectedElementChimique, setSelectedElementChimique] = useState<ElementChimiqueDto>(emptyElementChimique);

    const emptyProduitMarchand = new ProduitMarchandDto();
    const [produitMarchands, setProduitMarchands] = useState<ProduitMarchandDto[]>([]);
    const [produitMarchandModalVisible, setProduitMarchandModalVisible] = useState(false);
    const [selectedProduitMarchand, setSelectedProduitMarchand] = useState<ProduitMarchandDto>(emptyProduitMarchand);


    const service = new AnalyseChimiqueAdminService();
    const analyseChimiqueDetailAdminService = new AnalyseChimiqueDetailAdminService();
    const elementChimiqueAdminService = new ElementChimiqueAdminService();
    const produitMarchandAdminService = new ProduitMarchandAdminService();

    const [analyseChimiqueDetailsElements, setAnalyseChimiqueDetailsElements] = useState<AnalyseChimiqueDetailDto[]>([]);
    const [analyseChimiqueDetails, setAnalyseChimiqueDetails] = useState<AnalyseChimiqueDetailDto>(new AnalyseChimiqueDetailDto());
    const [isEditModeAnalyseChimiqueDetails, setIsEditModeAnalyseChimiqueDetails] = useState(false);
    const [editIndexAnalyseChimiqueDetails, setEditIndexAnalyseChimiqueDetails] = useState(null);

    const [isAnalyseChimiqueDetailsElementCollapsed, setIsAnalyseChimiqueDetailsElementCollapsed] = useState(true);
    const [isAnalyseChimiqueDetailsElementsCollapsed, setIsAnalyseChimiqueDetailsElementsCollapsed] = useState(true);
    const [isAnalyseChimiqueDetails, setIsAnalyseChimiqueDetails] = useState(false);
    const [isEditAnalyseChimiqueDetailsMode, setIsEditAnalyseChimiqueDetailsMode] = useState(false);


    const { control: analyseChimiqueControl, handleSubmit: analyseChimiqueHandleSubmit, reset: analyseChimiqueReset} = useForm<AnalyseChimiqueDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        description: '' ,
        produitMarchand: undefined,
        },
    });

    const analyseChimiqueCollapsible = () => {
        setIsAnalyseChimiqueCollapsed(!isAnalyseChimiqueCollapsed);
    };

    const handleCloseElementChimiqueModal = () => {
        setElementChimiqueModalVisible(false);
    };

    const onElementChimiqueSelect = (item) => {
        setSelectedElementChimique(item);
        setElementChimiqueModalVisible(false);
    };
    const handleCloseProduitMarchandModal = () => {
        setProduitMarchandModalVisible(false);
    };

    const onProduitMarchandSelect = (item) => {
        setSelectedProduitMarchand(item);
        setProduitMarchandModalVisible(false);
    };


    useEffect(() => {
        produitMarchandAdminService.getList().then(({data}) => setProduitMarchands(data)).catch(error => console.log(error));

        elementChimiqueAdminService.getList().then(({data}) => setElementChimiques(data)).catch(error => console.log(error));
    }, []);


    const { control: analyseChimiqueDetailsControl, handleSubmit: analyseChimiqueDetailsHandleSubmit, reset: analyseChimiqueDetailsReset } = useForm<AnalyseChimiqueDetailDto>({
        defaultValues: {
            libelle: '' ,
            description: '' ,
            elementChimique: undefined,
            valeur: null ,
            analyseChimique: undefined,
        },
    });

    const analyseChimiqueDetailsElementCollapsible = () => {
        setIsAnalyseChimiqueDetailsElementCollapsed(!isAnalyseChimiqueDetailsElementCollapsed);
    };

    const analyseChimiqueDetailsElementsCollapsible = () => {
        setIsAnalyseChimiqueDetailsElementsCollapsed(!isAnalyseChimiqueDetailsElementsCollapsed);
    };

    const handleAddAnalyseChimiqueDetails = (data: AnalyseChimiqueDetailDto) => {
        if (data) {
            const newAnalyseChimiqueDetail: AnalyseChimiqueDetailDto = { id: null  , libelle: data.libelle ,description: data.description ,elementChimique: selectedElementChimique, valeur: data.valeur ,conformite: data.conformite ,surqualite: data.surqualite ,analyseChimique: undefined , };
            setAnalyseChimiqueDetailsElements((prevItems) => [...prevItems, newAnalyseChimiqueDetail]);
            analyseChimiqueDetailsReset({libelle: '' ,description: '' ,valeur: null ,});
                setSelectedElementChimique(emptyElementChimique);
        }
    };

    const handleDeleteAnalyseChimiqueDetails = (index) => {
        const updatedItems = analyseChimiqueDetailsElements.filter((item, i) => i !== index);
        setAnalyseChimiqueDetailsElements(updatedItems);
    };

    const handleUpdateAnalyseChimiqueDetails = (data: AnalyseChimiqueDetailDto) => {
        if (data) {
            analyseChimiqueDetailsElements.map((item, i) => {
                if (i === editIndexAnalyseChimiqueDetails) {
                    item.libelle = data.libelle;
                    item.description = data.description;
                    elementChimique: undefined ;
                    item.elementChimique = selectedElementChimique;
                    item.valeur = data.valeur;
                    item.conformite = data.conformite;
                    item.surqualite = data.surqualite;
                }
            });
            AnalyseChimiqueDetailsReset({libelle: '' ,description: '' ,valeur: null ,});
            setSelectedElementChimique(emptyElementChimique);
            setIsEditModeAnalyseChimiqueDetails(false);
        }
        setIsAnalyseChimiqueDetailsElementCollapsed(!isAnalyseChimiqueDetailsElementCollapsed);
        setIsAnalyseChimiqueDetailsElementsCollapsed(!isAnalyseChimiqueDetailsElementsCollapsed);
    }

    const updateFormDefaultValuesAnalyseChimiqueDetails = (index: number) => {
        let updatedAnalyseChimiqueDetail: AnalyseChimiqueDetailDto;
        setEditIndexAnalyseChimiqueDetails(index);
        setIsEditModeAnalyseChimiqueDetails(true);
        analyseChimiqueDetailsElements.map((item, i) => {
            if (i === index) {
                updatedAnalyseChimiqueDetail = item;
            }
        });
        AnalyseChimiqueDetailsReset({libelle: updatedAnalyseChimiqueDetail.libelle ,description: updatedAnalyseChimiqueDetail.description ,valeur: updatedAnalyseChimiqueDetail.valeur ,conformite: updatedAnalyseChimiqueDetail.conformite ,surqualite: updatedAnalyseChimiqueDetail.surqualite ,});
        setSelectedElementChimique(updatedAnalyseChimiqueDetail.elementChimique);
        setIsAnalyseChimiqueDetailsElementCollapsed(!isAnalyseChimiqueDetailsElementCollapsed);
        setIsAnalyseChimiqueDetailsElementsCollapsed(!isAnalyseChimiqueDetailsElementsCollapsed);
    };


    const handleSave = async (item: AnalyseChimiqueDto) => {
        item.produitMarchand = selectedProduitMarchand;
        item.analyseChimiqueDetails = analyseChimiqueDetailsElements;
        Keyboard.dismiss();
        try {
            await service.save( item );
            analyseChimiqueReset();
            setSelectedProduitMarchand(emptyProduitMarchand);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
            item.analyseChimiqueDetails = analyseChimiqueDetailsElements;
            setAnalyseChimiqueDetailsElements([]);
        } catch (error) {
            console.error('Error saving analyseChimique:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create AnalyseChimique</Text>

            <TouchableOpacity onPress={analyseChimiqueCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>AnalyseChimique</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isAnalyseChimiqueCollapsed}>
                            <CustomInput control={analyseChimiqueControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={analyseChimiqueControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={analyseChimiqueControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                        <TouchableOpacity onPress={() => setProduitMarchandModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedProduitMarchand.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
            </Collapsible>
            <TouchableOpacity onPress={analyseChimiqueDetailsElementCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Add Analyse chimique details</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isAnalyseChimiqueDetailsElementCollapsed}>
                            <CustomInput control={analyseChimiqueDetailsControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={analyseChimiqueDetailsControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                <TouchableOpacity onPress={() => setElementChimiqueModalVisible(true)} style={globalStyle.placeHolder} >
                    <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                        <Text>{selectedElementChimique.libelle}</Text>
                        <Ionicons name="caret-down-outline" size={22} color={'black'} />
                    </View>
                </TouchableOpacity>
                            <CustomInput control={analyseChimiqueDetailsControl} name={'valeur'} placeholder={'Valeur'} keyboardT="numeric" />
                <TouchableOpacity onPress={ isEditAnalyseChimiqueDetailsMode ? analyseChimiqueDetailsHandleSubmit((data) => { handleUpdateAnalyseChimiqueDetails(data); }) : analyseChimiqueDetailsHandleSubmit(handleAddAnalyseChimiqueDetails) } style={{ backgroundColor: '#32cd32', borderRadius: 10, marginBottom: 5, width: '20%', paddingVertical: 10, marginLeft: '80%', marginTop: 10 }} >
                    <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>
                    {isEditModeAnalyseChimiqueDetails ? <Ionicons name="pencil-outline" size={25} color={'blue'} /> : '+' }
                    </Text>
                </TouchableOpacity>

            </Collapsible>
            <TouchableOpacity onPress={analyseChimiqueDetailsElementsCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>List Analyse chimique details</Text>
            </TouchableOpacity>
            <Collapsible collapsed={isAnalyseChimiqueDetailsElementsCollapsed}>
                { analyseChimiqueDetails && analyseChimiqueDetailsElements.length > 0 ? ( analyseChimiqueDetailsElements.map((item, index) => (
                    <View key={index} style={globalStyle.itemCard}>
                        <View>
                            <Text style={globalStyle.infos}>'Libelle: {item.libelle}</Text>
                            <Text style={globalStyle.infos}>'Description: {item.description}</Text>
                            <Text style={globalStyle.infos}>'Element chimique: {item.elementChimique.libelle}</Text>
                            <Text style={globalStyle.infos}>'Valeur: {item.valeur}</Text>
                            <Text style={globalStyle.infos}>'Conformite: {item.conformite}</Text>
                            <Text style={globalStyle.infos}>'Surqualite: {item.surqualite}</Text>
                        </View>
                        <View style={{ alignItems: 'center', flexDirection: 'column', justifyContent: 'space-between' }}>
                            <TouchableOpacity onPress={() => handleDeleteAnalyseChimiqueDetails(index)}>
                                <Ionicons name="trash-outline" size={22} color={'red'} />
                            </TouchableOpacity>
                            <TouchableOpacity onPress={() => updateFormDefaultValuesAnalyseChimiqueDetails(index)}>
                                <Ionicons name="pencil-outline" size={22} color={'blue'} />
                            </TouchableOpacity>
                        </View>
                    </View>
                )) ) : (
                    <View style={globalStyle.itemCard}>
                        <Text style={globalStyle.infos}>No analyse chimique details yet.</Text>
                    </View>
                )}
            </Collapsible>
        <CustomButton onPress={analyseChimiqueHandleSubmit(handleSave)} text={"Save AnalyseChimique"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {elementChimiques !== null && elementChimiques.length > 0 ? ( <FilterModal visibility={elementChimiqueModalVisible} placeholder={"Select a ElementChimique"} onItemSelect={onElementChimiqueSelect} items={elementChimiques} onClose={handleCloseElementChimiqueModal} variable={'libelle'} /> ) : null}
        {produitMarchands !== null && produitMarchands.length > 0 ? ( <FilterModal visibility={produitMarchandModalVisible} placeholder={"Select a ProduitMarchand"} onItemSelect={onProduitMarchandSelect} items={produitMarchands} onClose={handleCloseProduitMarchandModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default AnalyseChimiqueAdminCreate;
