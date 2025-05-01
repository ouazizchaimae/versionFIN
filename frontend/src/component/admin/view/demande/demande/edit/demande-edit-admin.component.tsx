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

import {DemandeAdminService} from '../../../../../../controller/service/admin/demande/DemandeAdminService.service';
import  {DemandeDto}  from '../../../../../../controller/model/demande/Demande.model';

import {EtatDemandeDto} from '../../../../../../controller/model/referentiel/EtatDemande.model';
import {EtatDemandeAdminService} from '../../../../../../controller/service/admin/referentiel/EtatDemandeAdminService.service';
import {ProduitMarchandDto} from '../../../../../../controller/model/referentiel/ProduitMarchand.model';
import {ProduitMarchandAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitMarchandAdminService.service';
import {TypeDemandeDto} from '../../../../../../controller/model/referentiel/TypeDemande.model';
import {TypeDemandeAdminService} from '../../../../../../controller/service/admin/referentiel/TypeDemandeAdminService.service';
import {ClientDto} from '../../../../../../controller/model/referentiel/Client.model';
import {ClientAdminService} from '../../../../../../controller/service/admin/referentiel/ClientAdminService.service';

type DemandeUpdateScreenRouteProp = RouteProp<{ DemandeUpdate: { demande: DemandeDto } }, 'DemandeUpdate'>;

type Props = { route: DemandeUpdateScreenRouteProp; };

const DemandeAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { demande } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

    const emptyTypeDemande = new TypeDemandeDto();
    const [typeDemandes, setTypeDemandes] = useState<TypeDemandeDto[]>([]);
    const [typeDemandeModalVisible, setTypeDemandeModalVisible] = useState(false);
    const [selectedTypeDemande, setSelectedTypeDemande] = useState<TypeDemandeDto>(emptyTypeDemande);

    const emptyClient = new ClientDto();
    const [clients, setClients] = useState<ClientDto[]>([]);
    const [clientModalVisible, setClientModalVisible] = useState(false);
    const [selectedClient, setSelectedClient] = useState<ClientDto>(emptyClient);

    const emptyProduitMarchand = new ProduitMarchandDto();
    const [produitMarchands, setProduitMarchands] = useState<ProduitMarchandDto[]>([]);
    const [produitMarchandModalVisible, setProduitMarchandModalVisible] = useState(false);
    const [selectedProduitMarchand, setSelectedProduitMarchand] = useState<ProduitMarchandDto>(emptyProduitMarchand);

    const emptyEtatDemande = new EtatDemandeDto();
    const [etatDemandes, setEtatDemandes] = useState<EtatDemandeDto[]>([]);
    const [etatDemandeModalVisible, setEtatDemandeModalVisible] = useState(false);
    const [selectedEtatDemande, setSelectedEtatDemande] = useState<EtatDemandeDto>(emptyEtatDemande);


    const service = new DemandeAdminService();
    const etatDemandeAdminService = new EtatDemandeAdminService();
    const produitMarchandAdminService = new ProduitMarchandAdminService();
    const typeDemandeAdminService = new TypeDemandeAdminService();
    const clientAdminService = new ClientAdminService();


    const { control, handleSubmit } = useForm<DemandeDto>({
        defaultValues: {
            id: demande.id ,
            code: demande.code ,
            libelle: demande.libelle ,
            description: demande.description ,
            dateDemande: demande.dateDemande ,
            dateExpedition: demande.dateExpedition ,
            volume: demande.volume ,
            actionEntreprise: demande.actionEntreprise ,
            trg: demande.trg ,
            cause: demande.cause ,
            commentaire: demande.commentaire ,
        },
    });



    const handleCloseTypeDemandeModal = () => {
        setTypeDemandeModalVisible(false);
    };

    const onTypeDemandeSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedTypeDemande(item);
        setTypeDemandeModalVisible(false);
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
    const handleCloseEtatDemandeModal = () => {
        setEtatDemandeModalVisible(false);
    };

    const onEtatDemandeSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedEtatDemande(item);
        setEtatDemandeModalVisible(false);
    };


    useEffect(() => {
        produitMarchandAdminService.getList().then(({data}) => setProduitMarchands(data)).catch(error => console.log(error));
        setSelectedProduitMarchand(demande.produitMarchand)
        clientAdminService.getList().then(({data}) => setClients(data)).catch(error => console.log(error));
        setSelectedClient(demande.client)
        typeDemandeAdminService.getList().then(({data}) => setTypeDemandes(data)).catch(error => console.log(error));
        setSelectedTypeDemande(demande.typeDemande)
        etatDemandeAdminService.getList().then(({data}) => setEtatDemandes(data)).catch(error => console.log(error));
        setSelectedEtatDemande(demande.etatDemande)
    }, []);



    const handleUpdate = async (item: DemandeDto) => {
        item.produitMarchand = selectedProduitMarchand;
        item.client = selectedClient;
        item.typeDemande = selectedTypeDemande;
        item.etatDemande = selectedEtatDemande;
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving demande:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Demande</Text>

            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <TouchableOpacity onPress={() => setProduitMarchandModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedProduitMarchand?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <TouchableOpacity onPress={() => setClientModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedClient?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>
            <CustomInput control={control} name={'dateDemande'} placeholder={'Date demande'} keyboardT="numeric" />
            <CustomInput control={control} name={'dateExpedition'} placeholder={'Date expedition'} keyboardT="numeric" />

            <TouchableOpacity onPress={() => setTypeDemandeModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedTypeDemande?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <TouchableOpacity onPress={() => setEtatDemandeModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedEtatDemande?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>
            <CustomInput control={control} name={'actionEntreprise'} placeholder={'Action entreprise'} keyboardT="default" />
            <CustomInput control={control} name={'trg'} placeholder={'Trg'} keyboardT="default" />
            <CustomInput control={control} name={'cause'} placeholder={'Cause'} keyboardT="default" />
            <CustomInput control={control} name={'commentaire'} placeholder={'Commentaire'} keyboardT="default" />

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Demande"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {produitMarchands &&
            <FilterModal visibility={produitMarchandModalVisible} placeholder={"Select a ProduitMarchand"} onItemSelect={onProduitMarchandSelect} items={produitMarchands} onClose={handleCloseProduitMarchandModal} variable={'libelle'} />
        }
        {clients &&
            <FilterModal visibility={clientModalVisible} placeholder={"Select a Client"} onItemSelect={onClientSelect} items={clients} onClose={handleCloseClientModal} variable={'libelle'} />
        }
        {typeDemandes &&
            <FilterModal visibility={typeDemandeModalVisible} placeholder={"Select a TypeDemande"} onItemSelect={onTypeDemandeSelect} items={typeDemandes} onClose={handleCloseTypeDemandeModal} variable={'libelle'} />
        }
        {etatDemandes &&
            <FilterModal visibility={etatDemandeModalVisible} placeholder={"Select a EtatDemande"} onItemSelect={onEtatDemandeSelect} items={etatDemandes} onClose={handleCloseEtatDemandeModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default DemandeAdminEdit;
