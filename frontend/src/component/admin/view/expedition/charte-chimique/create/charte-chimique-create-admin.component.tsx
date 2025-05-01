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

import {CharteChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueAdminService.service';
import  {CharteChimiqueDto}  from '../../../../../../controller/model/expedition/CharteChimique.model';

import {CharteChimiqueDetailDto} from '../../../../../../controller/model/expedition/CharteChimiqueDetail.model';
import {CharteChimiqueDetailAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueDetailAdminService.service';
import {ElementChimiqueDto} from '../../../../../../controller/model/referentiel/ElementChimique.model';
import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import {ProduitMarchandDto} from '../../../../../../controller/model/referentiel/ProduitMarchand.model';
import {ProduitMarchandAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitMarchandAdminService.service';
import {ClientDto} from '../../../../../../controller/model/referentiel/Client.model';
import {ClientAdminService} from '../../../../../../controller/service/admin/referentiel/ClientAdminService.service';

const CharteChimiqueAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isCharteChimiqueCollapsed, setIsCharteChimiqueCollapsed] = useState(true);


    const emptyElementChimique = new ElementChimiqueDto();
    const [elementChimiques, setElementChimiques] = useState<ElementChimiqueDto[]>([]);
    const [elementChimiqueModalVisible, setElementChimiqueModalVisible] = useState(false);
    const [selectedElementChimique, setSelectedElementChimique] = useState<ElementChimiqueDto>(emptyElementChimique);

    const emptyClient = new ClientDto();
    const [clients, setClients] = useState<ClientDto[]>([]);
    const [clientModalVisible, setClientModalVisible] = useState(false);
    const [selectedClient, setSelectedClient] = useState<ClientDto>(emptyClient);

    const emptyProduitMarchand = new ProduitMarchandDto();
    const [produitMarchands, setProduitMarchands] = useState<ProduitMarchandDto[]>([]);
    const [produitMarchandModalVisible, setProduitMarchandModalVisible] = useState(false);
    const [selectedProduitMarchand, setSelectedProduitMarchand] = useState<ProduitMarchandDto>(emptyProduitMarchand);


    const service = new CharteChimiqueAdminService();
    const charteChimiqueDetailAdminService = new CharteChimiqueDetailAdminService();
    const elementChimiqueAdminService = new ElementChimiqueAdminService();
    const produitMarchandAdminService = new ProduitMarchandAdminService();
    const clientAdminService = new ClientAdminService();

    const [charteChimiqueDetailsElements, setCharteChimiqueDetailsElements] = useState<CharteChimiqueDetailDto[]>([]);
    const [charteChimiqueDetails, setCharteChimiqueDetails] = useState<CharteChimiqueDetailDto>(new CharteChimiqueDetailDto());
    const [isEditModeCharteChimiqueDetails, setIsEditModeCharteChimiqueDetails] = useState(false);
    const [editIndexCharteChimiqueDetails, setEditIndexCharteChimiqueDetails] = useState(null);

    const [isCharteChimiqueDetailsElementCollapsed, setIsCharteChimiqueDetailsElementCollapsed] = useState(true);
    const [isCharteChimiqueDetailsElementsCollapsed, setIsCharteChimiqueDetailsElementsCollapsed] = useState(true);
    const [isCharteChimiqueDetails, setIsCharteChimiqueDetails] = useState(false);
    const [isEditCharteChimiqueDetailsMode, setIsEditCharteChimiqueDetailsMode] = useState(false);


    const { control: charteChimiqueControl, handleSubmit: charteChimiqueHandleSubmit, reset: charteChimiqueReset} = useForm<CharteChimiqueDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        description: '' ,
        client: undefined,
        produitMarchand: undefined,
        },
    });

    const charteChimiqueCollapsible = () => {
        setIsCharteChimiqueCollapsed(!isCharteChimiqueCollapsed);
    };

    const handleCloseElementChimiqueModal = () => {
        setElementChimiqueModalVisible(false);
    };

    const onElementChimiqueSelect = (item) => {
        setSelectedElementChimique(item);
        setElementChimiqueModalVisible(false);
    };
    const handleCloseClientModal = () => {
        setClientModalVisible(false);
    };

    const onClientSelect = (item) => {
        setSelectedClient(item);
        setClientModalVisible(false);
    };
    const handleCloseProduitMarchandModal = () => {
        setProduitMarchandModalVisible(false);
    };

    const onProduitMarchandSelect = (item) => {
        setSelectedProduitMarchand(item);
        setProduitMarchandModalVisible(false);
    };


    useEffect(() => {
        clientAdminService.getList().then(({data}) => setClients(data)).catch(error => console.log(error));
        produitMarchandAdminService.getList().then(({data}) => setProduitMarchands(data)).catch(error => console.log(error));

        elementChimiqueAdminService.getList().then(({data}) => setElementChimiques(data)).catch(error => console.log(error));
    }, []);


    const { control: charteChimiqueDetailsControl, handleSubmit: charteChimiqueDetailsHandleSubmit, reset: charteChimiqueDetailsReset } = useForm<CharteChimiqueDetailDto>({
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

    const charteChimiqueDetailsElementCollapsible = () => {
        setIsCharteChimiqueDetailsElementCollapsed(!isCharteChimiqueDetailsElementCollapsed);
    };

    const charteChimiqueDetailsElementsCollapsible = () => {
        setIsCharteChimiqueDetailsElementsCollapsed(!isCharteChimiqueDetailsElementsCollapsed);
    };

    const handleAddCharteChimiqueDetails = (data: CharteChimiqueDetailDto) => {
        if (data) {
            const newCharteChimiqueDetail: CharteChimiqueDetailDto = { id: null  , libelle: data.libelle ,description: data.description ,elementChimique: selectedElementChimique, minimum: data.minimum ,maximum: data.maximum ,average: data.average ,methodeAnalyse: data.methodeAnalyse ,unite: data.unite ,charteChimique: undefined , };
            setCharteChimiqueDetailsElements((prevItems) => [...prevItems, newCharteChimiqueDetail]);
            charteChimiqueDetailsReset({libelle: '' ,description: '' ,minimum: null ,maximum: null ,average: null ,methodeAnalyse: '' ,unite: '' ,});
                setSelectedElementChimique(emptyElementChimique);
        }
    };

    const handleDeleteCharteChimiqueDetails = (index) => {
        const updatedItems = charteChimiqueDetailsElements.filter((item, i) => i !== index);
        setCharteChimiqueDetailsElements(updatedItems);
    };

    const handleUpdateCharteChimiqueDetails = (data: CharteChimiqueDetailDto) => {
        if (data) {
            charteChimiqueDetailsElements.map((item, i) => {
                if (i === editIndexCharteChimiqueDetails) {
                    item.libelle = data.libelle;
                    item.description = data.description;
                    elementChimique: undefined ;
                    item.elementChimique = selectedElementChimique;
                    item.minimum = data.minimum;
                    item.maximum = data.maximum;
                    item.average = data.average;
                    item.methodeAnalyse = data.methodeAnalyse;
                    item.unite = data.unite;
                }
            });
            CharteChimiqueDetailsReset({libelle: '' ,description: '' ,minimum: null ,maximum: null ,average: null ,methodeAnalyse: '' ,unite: '' ,});
            setSelectedElementChimique(emptyElementChimique);
            setIsEditModeCharteChimiqueDetails(false);
        }
        setIsCharteChimiqueDetailsElementCollapsed(!isCharteChimiqueDetailsElementCollapsed);
        setIsCharteChimiqueDetailsElementsCollapsed(!isCharteChimiqueDetailsElementsCollapsed);
    }

    const updateFormDefaultValuesCharteChimiqueDetails = (index: number) => {
        let updatedCharteChimiqueDetail: CharteChimiqueDetailDto;
        setEditIndexCharteChimiqueDetails(index);
        setIsEditModeCharteChimiqueDetails(true);
        charteChimiqueDetailsElements.map((item, i) => {
            if (i === index) {
                updatedCharteChimiqueDetail = item;
            }
        });
        CharteChimiqueDetailsReset({libelle: updatedCharteChimiqueDetail.libelle ,description: updatedCharteChimiqueDetail.description ,minimum: updatedCharteChimiqueDetail.minimum ,maximum: updatedCharteChimiqueDetail.maximum ,average: updatedCharteChimiqueDetail.average ,methodeAnalyse: updatedCharteChimiqueDetail.methodeAnalyse ,unite: updatedCharteChimiqueDetail.unite ,});
        setSelectedElementChimique(updatedCharteChimiqueDetail.elementChimique);
        setIsCharteChimiqueDetailsElementCollapsed(!isCharteChimiqueDetailsElementCollapsed);
        setIsCharteChimiqueDetailsElementsCollapsed(!isCharteChimiqueDetailsElementsCollapsed);
    };


    const handleSave = async (item: CharteChimiqueDto) => {
        item.client = selectedClient;
        item.produitMarchand = selectedProduitMarchand;
        item.charteChimiqueDetails = charteChimiqueDetailsElements;
        Keyboard.dismiss();
        try {
            await service.save( item );
            charteChimiqueReset();
            setSelectedClient(emptyClient);
            setSelectedProduitMarchand(emptyProduitMarchand);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
            item.charteChimiqueDetails = charteChimiqueDetailsElements;
            setCharteChimiqueDetailsElements([]);
        } catch (error) {
            console.error('Error saving charteChimique:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create CharteChimique</Text>

            <TouchableOpacity onPress={charteChimiqueCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>CharteChimique</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isCharteChimiqueCollapsed}>
                            <CustomInput control={charteChimiqueControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={charteChimiqueControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={charteChimiqueControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                        <TouchableOpacity onPress={() => setClientModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedClient.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setProduitMarchandModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedProduitMarchand.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
            </Collapsible>
            <TouchableOpacity onPress={charteChimiqueDetailsElementCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Add Charte chimique details</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isCharteChimiqueDetailsElementCollapsed}>
                            <CustomInput control={charteChimiqueDetailsControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={charteChimiqueDetailsControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                <TouchableOpacity onPress={() => setElementChimiqueModalVisible(true)} style={globalStyle.placeHolder} >
                    <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                        <Text>{selectedElementChimique.libelle}</Text>
                        <Ionicons name="caret-down-outline" size={22} color={'black'} />
                    </View>
                </TouchableOpacity>
                            <CustomInput control={charteChimiqueDetailsControl} name={'minimum'} placeholder={'Minimum'} keyboardT="numeric" />
                            <CustomInput control={charteChimiqueDetailsControl} name={'maximum'} placeholder={'Maximum'} keyboardT="numeric" />
                            <CustomInput control={charteChimiqueDetailsControl} name={'average'} placeholder={'Average'} keyboardT="numeric" />
                            <CustomInput control={charteChimiqueDetailsControl} name={'methodeAnalyse'} placeholder={'Methode analyse'} keyboardT="default" />
                            <CustomInput control={charteChimiqueDetailsControl} name={'unite'} placeholder={'Unite'} keyboardT="default" />
                <TouchableOpacity onPress={ isEditCharteChimiqueDetailsMode ? charteChimiqueDetailsHandleSubmit((data) => { handleUpdateCharteChimiqueDetails(data); }) : charteChimiqueDetailsHandleSubmit(handleAddCharteChimiqueDetails) } style={{ backgroundColor: '#32cd32', borderRadius: 10, marginBottom: 5, width: '20%', paddingVertical: 10, marginLeft: '80%', marginTop: 10 }} >
                    <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>
                    {isEditModeCharteChimiqueDetails ? <Ionicons name="pencil-outline" size={25} color={'blue'} /> : '+' }
                    </Text>
                </TouchableOpacity>

            </Collapsible>
            <TouchableOpacity onPress={charteChimiqueDetailsElementsCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>List Charte chimique details</Text>
            </TouchableOpacity>
            <Collapsible collapsed={isCharteChimiqueDetailsElementsCollapsed}>
                { charteChimiqueDetails && charteChimiqueDetailsElements.length > 0 ? ( charteChimiqueDetailsElements.map((item, index) => (
                    <View key={index} style={globalStyle.itemCard}>
                        <View>
                            <Text style={globalStyle.infos}>'Libelle: {item.libelle}</Text>
                            <Text style={globalStyle.infos}>'Description: {item.description}</Text>
                            <Text style={globalStyle.infos}>'Element chimique: {item.elementChimique.libelle}</Text>
                            <Text style={globalStyle.infos}>'Minimum: {item.minimum}</Text>
                            <Text style={globalStyle.infos}>'Maximum: {item.maximum}</Text>
                            <Text style={globalStyle.infos}>'Average: {item.average}</Text>
                            <Text style={globalStyle.infos}>'Methode analyse: {item.methodeAnalyse}</Text>
                            <Text style={globalStyle.infos}>'Unite: {item.unite}</Text>
                        </View>
                        <View style={{ alignItems: 'center', flexDirection: 'column', justifyContent: 'space-between' }}>
                            <TouchableOpacity onPress={() => handleDeleteCharteChimiqueDetails(index)}>
                                <Ionicons name="trash-outline" size={22} color={'red'} />
                            </TouchableOpacity>
                            <TouchableOpacity onPress={() => updateFormDefaultValuesCharteChimiqueDetails(index)}>
                                <Ionicons name="pencil-outline" size={22} color={'blue'} />
                            </TouchableOpacity>
                        </View>
                    </View>
                )) ) : (
                    <View style={globalStyle.itemCard}>
                        <Text style={globalStyle.infos}>No charte chimique details yet.</Text>
                    </View>
                )}
            </Collapsible>
        <CustomButton onPress={charteChimiqueHandleSubmit(handleSave)} text={"Save CharteChimique"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {elementChimiques !== null && elementChimiques.length > 0 ? ( <FilterModal visibility={elementChimiqueModalVisible} placeholder={"Select a ElementChimique"} onItemSelect={onElementChimiqueSelect} items={elementChimiques} onClose={handleCloseElementChimiqueModal} variable={'libelle'} /> ) : null}
        {clients !== null && clients.length > 0 ? ( <FilterModal visibility={clientModalVisible} placeholder={"Select a Client"} onItemSelect={onClientSelect} items={clients} onClose={handleCloseClientModal} variable={'libelle'} /> ) : null}
        {produitMarchands !== null && produitMarchands.length > 0 ? ( <FilterModal visibility={produitMarchandModalVisible} placeholder={"Select a ProduitMarchand"} onItemSelect={onProduitMarchandSelect} items={produitMarchands} onClose={handleCloseProduitMarchandModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default CharteChimiqueAdminCreate;
