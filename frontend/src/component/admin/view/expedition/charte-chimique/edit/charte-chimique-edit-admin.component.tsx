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

type CharteChimiqueUpdateScreenRouteProp = RouteProp<{ CharteChimiqueUpdate: { charteChimique: CharteChimiqueDto } }, 'CharteChimiqueUpdate'>;

type Props = { route: CharteChimiqueUpdateScreenRouteProp; };

const CharteChimiqueAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { charteChimique } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

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


    const { control, handleSubmit } = useForm<CharteChimiqueDto>({
        defaultValues: {
            id: charteChimique.id ,
            code: charteChimique.code ,
            libelle: charteChimique.libelle ,
            description: charteChimique.description ,
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
    const handleCloseClientModal = () => {
        setClientModalVisible(false);
    };

    const onClientSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedClient(item);
        setClientModalVisible(false);
    };
    const handleCloseProduitMarchandModal = () => {
        setProduitMarchandModalVisible(false);
    };

    const onProduitMarchandSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedProduitMarchand(item);
        setProduitMarchandModalVisible(false);
    };


    useEffect(() => {
        clientAdminService.getList().then(({data}) => setClients(data)).catch(error => console.log(error));
        setSelectedClient(charteChimique.client)
        produitMarchandAdminService.getList().then(({data}) => setProduitMarchands(data)).catch(error => console.log(error));
        setSelectedProduitMarchand(charteChimique.produitMarchand)

        elementChimiqueAdminService.getList().then(({data}) => setElementChimiques(data)).catch(error => console.log(error));
    }, []);



    const handleUpdate = async (item: CharteChimiqueDto) => {
        item.client = selectedClient;
        item.produitMarchand = selectedProduitMarchand;
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving charte chimique:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Charte chimique</Text>

            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <TouchableOpacity onPress={() => setClientModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedClient?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <TouchableOpacity onPress={() => setProduitMarchandModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedProduitMarchand?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Charte chimique"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {clients &&
            <FilterModal visibility={clientModalVisible} placeholder={"Select a Client"} onItemSelect={onClientSelect} items={clients} onClose={handleCloseClientModal} variable={'libelle'} />
        }
        {produitMarchands &&
            <FilterModal visibility={produitMarchandModalVisible} placeholder={"Select a ProduitMarchand"} onItemSelect={onProduitMarchandSelect} items={produitMarchands} onClose={handleCloseProduitMarchandModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default CharteChimiqueAdminEdit;
