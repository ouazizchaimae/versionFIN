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

type ExpeditionUpdateScreenRouteProp = RouteProp<{ ExpeditionUpdate: { expedition: ExpeditionDto } }, 'ExpeditionUpdate'>;

type Props = { route: ExpeditionUpdateScreenRouteProp; };

const ExpeditionAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { expedition } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

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


    const { control, handleSubmit } = useForm<ExpeditionDto>({
        defaultValues: {
            id: expedition.id ,
            code: expedition.code ,
            libelle: expedition.libelle ,
            description: expedition.description ,
        },
    });



    const handleCloseClientModal = () => {
        setClientModalVisible(false);
    };

    const onClientSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedClient(item);
        setClientModalVisible(false);
    };
    const handleCloseTypeExpeditionModal = () => {
        setTypeExpeditionModalVisible(false);
    };

    const onTypeExpeditionSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedTypeExpedition(item);
        setTypeExpeditionModalVisible(false);
    };
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
        clientAdminService.getList().then(({data}) => setClients(data)).catch(error => console.log(error));
        setSelectedClient(expedition.client)
        typeExpeditionAdminService.getList().then(({data}) => setTypeExpeditions(data)).catch(error => console.log(error));
        setSelectedTypeExpedition(expedition.typeExpedition)

        analyseChimiqueAdminService.getList().then(({data}) => setAnalyseChimiques(data)).catch(error => console.log(error));
        charteChimiqueAdminService.getList().then(({data}) => setCharteChimiques(data)).catch(error => console.log(error));
    }, []);



    const handleUpdate = async (item: ExpeditionDto) => {
        item.client = selectedClient;
        item.typeExpedition = selectedTypeExpedition;
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving expedition:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Expedition</Text>

            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <TouchableOpacity onPress={() => setClientModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedClient?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <TouchableOpacity onPress={() => setTypeExpeditionModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedTypeExpedition?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Expedition"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {clients &&
            <FilterModal visibility={clientModalVisible} placeholder={"Select a Client"} onItemSelect={onClientSelect} items={clients} onClose={handleCloseClientModal} variable={'libelle'} />
        }
        {typeExpeditions &&
            <FilterModal visibility={typeExpeditionModalVisible} placeholder={"Select a TypeExpedition"} onItemSelect={onTypeExpeditionSelect} items={typeExpeditions} onClose={handleCloseTypeExpeditionModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default ExpeditionAdminEdit;
