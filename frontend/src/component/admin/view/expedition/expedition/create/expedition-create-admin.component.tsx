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

import {ExpeditionAdminService} from '../../../../../../controller/service/admin/expedition/ExpeditionAdminService.service';
import  {ExpeditionDto}  from '../../../../../../controller/model/expedition/Expedition.model';

import {ExpeditionProduitDto} from '../../../../../../controller/model/expedition/ExpeditionProduit.model';
import {ExpeditionProduitAdminService} from '../../../../../../controller/service/admin/expedition/ExpeditionProduitAdminService.service';
import {AnalyseChimiqueDto} from '../../../../../../controller/model/expedition/AnalyseChimique.model';
import {AnalyseChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueAdminService.service';
import {TypeExpeditionDto} from '../../../../../../controller/model/expedition/TypeExpedition.model';
import {TypeExpeditionAdminService} from '../../../../../../controller/service/admin/expedition/TypeExpeditionAdminService.service';
import {ClientDto} from '../../../../../../controller/model/referentiel/Client.model';
import {ClientAdminService} from '../../../../../../controller/service/admin/referentiel/ClientAdminService.service';
import {CharteChimiqueDto} from '../../../../../../controller/model/expedition/CharteChimique.model';
import {CharteChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueAdminService.service';

const ExpeditionAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isExpeditionCollapsed, setIsExpeditionCollapsed] = useState(true);


    const emptyClient = new ClientDto();
    const [clients, setClients] = useState<ClientDto[]>([]);
    const [clientModalVisible, setClientModalVisible] = useState(false);
    const [selectedClient, setSelectedClient] = useState<ClientDto>(emptyClient);

    const emptyTypeExpedition = new TypeExpeditionDto();
    const [typeExpeditions, setTypeExpeditions] = useState<TypeExpeditionDto[]>([]);
    const [typeExpeditionModalVisible, setTypeExpeditionModalVisible] = useState(false);
    const [selectedTypeExpedition, setSelectedTypeExpedition] = useState<TypeExpeditionDto>(emptyTypeExpedition);

    const emptyCharteChimique = new CharteChimiqueDto();
    const [charteChimiques, setCharteChimiques] = useState<CharteChimiqueDto[]>([]);
    const [charteChimiqueModalVisible, setCharteChimiqueModalVisible] = useState(false);
    const [selectedCharteChimique, setSelectedCharteChimique] = useState<CharteChimiqueDto>(emptyCharteChimique);

    const emptyAnalyseChimique = new AnalyseChimiqueDto();
    const [analyseChimiques, setAnalyseChimiques] = useState<AnalyseChimiqueDto[]>([]);
    const [analyseChimiqueModalVisible, setAnalyseChimiqueModalVisible] = useState(false);
    const [selectedAnalyseChimique, setSelectedAnalyseChimique] = useState<AnalyseChimiqueDto>(emptyAnalyseChimique);


    const service = new ExpeditionAdminService();
    const expeditionProduitAdminService = new ExpeditionProduitAdminService();
    const analyseChimiqueAdminService = new AnalyseChimiqueAdminService();
    const typeExpeditionAdminService = new TypeExpeditionAdminService();
    const clientAdminService = new ClientAdminService();
    const charteChimiqueAdminService = new CharteChimiqueAdminService();

    const [expeditionProduitsElements, setExpeditionProduitsElements] = useState<ExpeditionProduitDto[]>([]);
    const [expeditionProduits, setExpeditionProduits] = useState<ExpeditionProduitDto>(new ExpeditionProduitDto());
    const [isEditModeExpeditionProduits, setIsEditModeExpeditionProduits] = useState(false);
    const [editIndexExpeditionProduits, setEditIndexExpeditionProduits] = useState(null);

    const [isExpeditionProduitsElementCollapsed, setIsExpeditionProduitsElementCollapsed] = useState(true);
    const [isExpeditionProduitsElementsCollapsed, setIsExpeditionProduitsElementsCollapsed] = useState(true);
    const [isExpeditionProduits, setIsExpeditionProduits] = useState(false);
    const [isEditExpeditionProduitsMode, setIsEditExpeditionProduitsMode] = useState(false);


    const { control: expeditionControl, handleSubmit: expeditionHandleSubmit, reset: expeditionReset} = useForm<ExpeditionDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        description: '' ,
        client: undefined,
        typeExpedition: undefined,
        },
    });

    const expeditionCollapsible = () => {
        setIsExpeditionCollapsed(!isExpeditionCollapsed);
    };

    const handleCloseClientModal = () => {
        setClientModalVisible(false);
    };

    const onClientSelect = (item) => {
        setSelectedClient(item);
        setClientModalVisible(false);
    };
    const handleCloseTypeExpeditionModal = () => {
        setTypeExpeditionModalVisible(false);
    };

    const onTypeExpeditionSelect = (item) => {
        setSelectedTypeExpedition(item);
        setTypeExpeditionModalVisible(false);
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
        clientAdminService.getList().then(({data}) => setClients(data)).catch(error => console.log(error));
        typeExpeditionAdminService.getList().then(({data}) => setTypeExpeditions(data)).catch(error => console.log(error));

        analyseChimiqueAdminService.getList().then(({data}) => setAnalyseChimiques(data)).catch(error => console.log(error));
        charteChimiqueAdminService.getList().then(({data}) => setCharteChimiques(data)).catch(error => console.log(error));
    }, []);


    const { control: expeditionProduitsControl, handleSubmit: expeditionProduitsHandleSubmit, reset: expeditionProduitsReset } = useForm<ExpeditionProduitDto>({
        defaultValues: {
            code: '' ,
            libelle: '' ,
            description: '' ,
            analyseChimique: undefined,
            charteChimique: undefined,
        },
    });

    const expeditionProduitsElementCollapsible = () => {
        setIsExpeditionProduitsElementCollapsed(!isExpeditionProduitsElementCollapsed);
    };

    const expeditionProduitsElementsCollapsible = () => {
        setIsExpeditionProduitsElementsCollapsed(!isExpeditionProduitsElementsCollapsed);
    };

    const handleAddExpeditionProduits = (data: ExpeditionProduitDto) => {
        if (data) {
            const newExpeditionProduit: ExpeditionProduitDto = { id: null  , code: data.code ,libelle: data.libelle ,description: data.description ,analyseChimique: selectedAnalyseChimique, charteChimique: selectedCharteChimique,  };
            setExpeditionProduitsElements((prevItems) => [...prevItems, newExpeditionProduit]);
            expeditionProduitsReset({code: '' ,libelle: '' ,description: '' ,});
                setSelectedAnalyseChimique(emptyAnalyseChimique);
                setSelectedCharteChimique(emptyCharteChimique);
        }
    };

    const handleDeleteExpeditionProduits = (index) => {
        const updatedItems = expeditionProduitsElements.filter((item, i) => i !== index);
        setExpeditionProduitsElements(updatedItems);
    };

    const handleUpdateExpeditionProduits = (data: ExpeditionProduitDto) => {
        if (data) {
            expeditionProduitsElements.map((item, i) => {
                if (i === editIndexExpeditionProduits) {
                    item.code = data.code;
                    item.libelle = data.libelle;
                    item.description = data.description;
                    analyseChimique: undefined ;
                    item.analyseChimique = selectedAnalyseChimique;
                    charteChimique: undefined ;
                    item.charteChimique = selectedCharteChimique;
                }
            });
            expeditionProduitsReset({code: '' ,libelle: '' ,description: '' ,});
            setSelectedAnalyseChimique(emptyAnalyseChimique);
            setSelectedCharteChimique(emptyCharteChimique);
            setIsEditModeExpeditionProduits(false);
        }
        setIsExpeditionProduitsElementCollapsed(!isExpeditionProduitsElementCollapsed);
        setIsExpeditionProduitsElementsCollapsed(!isExpeditionProduitsElementsCollapsed);
    }

    const updateFormDefaultValuesExpeditionProduits = (index: number) => {
        let updatedExpeditionProduit: ExpeditionProduitDto;
        setEditIndexExpeditionProduits(index);
        setIsEditModeExpeditionProduits(true);
        expeditionProduitsElements.map((item, i) => {
            if (i === index) {
                updatedExpeditionProduit = item;
            }
        });
        expeditionProduitsReset({code: updatedExpeditionProduit.code ,libelle: updatedExpeditionProduit.libelle ,description: updatedExpeditionProduit.description ,});
        setSelectedAnalyseChimique(updatedExpeditionProduit.analyseChimique);
        setSelectedCharteChimique(updatedExpeditionProduit.charteChimique);
        setIsExpeditionProduitsElementCollapsed(!isExpeditionProduitsElementCollapsed);
        setIsExpeditionProduitsElementsCollapsed(!isExpeditionProduitsElementsCollapsed);
    };


    const handleSave = async (item: ExpeditionDto) => {
        item.client = selectedClient;
        item.typeExpedition = selectedTypeExpedition;
        item.expeditionProduits = expeditionProduitsElements;
        Keyboard.dismiss();
        try {
            await service.save( item );
            expeditionReset();
            setSelectedClient(emptyClient);
            setSelectedTypeExpedition(emptyTypeExpedition);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
            item.expeditionProduits = expeditionProduitsElements;
            setExpeditionProduitsElements([]);
        } catch (error) {
            console.error('Error saving expedition:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create Expedition</Text>

            <TouchableOpacity onPress={expeditionCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>Expedition</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isExpeditionCollapsed}>
                            <CustomInput control={expeditionControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={expeditionControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={expeditionControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                        <TouchableOpacity onPress={() => setClientModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedClient.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setTypeExpeditionModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedTypeExpedition.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
            </Collapsible>
            <TouchableOpacity onPress={expeditionProduitsElementCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Add Expedition produits</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isExpeditionProduitsElementCollapsed}>
                            <CustomInput control={expeditionProduitsControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={expeditionProduitsControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={expeditionProduitsControl} name={'description'} placeholder={'Description'} keyboardT="default" />
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
                <TouchableOpacity onPress={ isEditExpeditionProduitsMode ? expeditionProduitsHandleSubmit((data) => { handleUpdateExpeditionProduits(data); }) : expeditionProduitsHandleSubmit(handleAddExpeditionProduits) } style={{ backgroundColor: '#32cd32', borderRadius: 10, marginBottom: 5, width: '20%', paddingVertical: 10, marginLeft: '80%', marginTop: 10 }} >
                    <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>
                    {isEditModeExpeditionProduits ? <Ionicons name="pencil-outline" size={25} color={'blue'} /> : '+' }
                    </Text>
                </TouchableOpacity>

            </Collapsible>
            <TouchableOpacity onPress={expeditionProduitsElementsCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>List Expedition produits</Text>
            </TouchableOpacity>
            <Collapsible collapsed={isExpeditionProduitsElementsCollapsed}>
                { expeditionProduits && expeditionProduitsElements.length > 0 ? ( expeditionProduitsElements.map((item, index) => (
                    <View key={index} style={globalStyle.itemCard}>
                        <View>
                            <Text style={globalStyle.infos}>'Code: {item.code}</Text>
                            <Text style={globalStyle.infos}>'Libelle: {item.libelle}</Text>
                            <Text style={globalStyle.infos}>'Description: {item.description}</Text>
                            <Text style={globalStyle.infos}>'Analyse chimique: {item.analyseChimique.libelle}</Text>
                            <Text style={globalStyle.infos}>'Charte chimique: {item.charteChimique.libelle}</Text>
                        </View>
                        <View style={{ alignItems: 'center', flexDirection: 'column', justifyContent: 'space-between' }}>
                            <TouchableOpacity onPress={() => handleDeleteExpeditionProduits(index)}>
                                <Ionicons name="trash-outline" size={22} color={'red'} />
                            </TouchableOpacity>
                            <TouchableOpacity onPress={() => updateFormDefaultValuesExpeditionProduits(index)}>
                                <Ionicons name="pencil-outline" size={22} color={'blue'} />
                            </TouchableOpacity>
                        </View>
                    </View>
                )) ) : (
                    <View style={globalStyle.itemCard}>
                        <Text style={globalStyle.infos}>No expedition produits yet.</Text>
                    </View>
                )}
            </Collapsible>
        <CustomButton onPress={expeditionHandleSubmit(handleSave)} text={"Save Expedition"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {clients !== null && clients.length > 0 ? ( <FilterModal visibility={clientModalVisible} placeholder={"Select a Client"} onItemSelect={onClientSelect} items={clients} onClose={handleCloseClientModal} variable={'libelle'} /> ) : null}
        {typeExpeditions !== null && typeExpeditions.length > 0 ? ( <FilterModal visibility={typeExpeditionModalVisible} placeholder={"Select a TypeExpedition"} onItemSelect={onTypeExpeditionSelect} items={typeExpeditions} onClose={handleCloseTypeExpeditionModal} variable={'libelle'} /> ) : null}
        {charteChimiques !== null && charteChimiques.length > 0 ? ( <FilterModal visibility={charteChimiqueModalVisible} placeholder={"Select a CharteChimique"} onItemSelect={onCharteChimiqueSelect} items={charteChimiques} onClose={handleCloseCharteChimiqueModal} variable={'libelle'} /> ) : null}
        {analyseChimiques !== null && analyseChimiques.length > 0 ? ( <FilterModal visibility={analyseChimiqueModalVisible} placeholder={"Select a AnalyseChimique"} onItemSelect={onAnalyseChimiqueSelect} items={analyseChimiques} onClose={handleCloseAnalyseChimiqueModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default ExpeditionAdminCreate;
