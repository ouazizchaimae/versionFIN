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

const DemandeAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isDemandeCollapsed, setIsDemandeCollapsed] = useState(true);


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


    const { control: demandeControl, handleSubmit: demandeHandleSubmit, reset: demandeReset} = useForm<DemandeDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        description: '' ,
        produitMarchand: undefined,
        client: undefined,
        volume: null ,
        typeDemande: undefined,
        etatDemande: undefined,
        actionEntreprise: '' ,
        trg: '' ,
        cause: '' ,
        commentaire: '' ,
        },
    });

    const demandeCollapsible = () => {
        setIsDemandeCollapsed(!isDemandeCollapsed);
    };

    const handleCloseTypeDemandeModal = () => {
        setTypeDemandeModalVisible(false);
    };

    const onTypeDemandeSelect = (item) => {
        setSelectedTypeDemande(item);
        setTypeDemandeModalVisible(false);
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
    const handleCloseEtatDemandeModal = () => {
        setEtatDemandeModalVisible(false);
    };

    const onEtatDemandeSelect = (item) => {
        setSelectedEtatDemande(item);
        setEtatDemandeModalVisible(false);
    };


    useEffect(() => {
        produitMarchandAdminService.getList().then(({data}) => setProduitMarchands(data)).catch(error => console.log(error));
        clientAdminService.getList().then(({data}) => setClients(data)).catch(error => console.log(error));
        typeDemandeAdminService.getList().then(({data}) => setTypeDemandes(data)).catch(error => console.log(error));
        etatDemandeAdminService.getList().then(({data}) => setEtatDemandes(data)).catch(error => console.log(error));
    }, []);




    const handleSave = async (item: DemandeDto) => {
        item.produitMarchand = selectedProduitMarchand;
        item.client = selectedClient;
        item.typeDemande = selectedTypeDemande;
        item.etatDemande = selectedEtatDemande;
        Keyboard.dismiss();
        try {
            await service.save( item );
            demandeReset();
            setSelectedProduitMarchand(emptyProduitMarchand);
            setSelectedClient(emptyClient);
            setSelectedTypeDemande(emptyTypeDemande);
            setSelectedEtatDemande(emptyEtatDemande);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving demande:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create Demande</Text>

            <TouchableOpacity onPress={demandeCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>Demande</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isDemandeCollapsed}>
                            <CustomInput control={demandeControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={demandeControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={demandeControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                        <TouchableOpacity onPress={() => setProduitMarchandModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedProduitMarchand.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setClientModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedClient.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                            <CustomInput control={demandeControl} name={'dateDemande'} placeholder={'Date demande'} keyboardT="numeric" />
                            <CustomInput control={demandeControl} name={'dateExpedition'} placeholder={'Date expedition'} keyboardT="numeric" />
                        <TouchableOpacity onPress={() => setTypeDemandeModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedTypeDemande.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setEtatDemandeModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedEtatDemande.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                            <CustomInput control={demandeControl} name={'actionEntreprise'} placeholder={'Action entreprise'} keyboardT="default" />
                            <CustomInput control={demandeControl} name={'trg'} placeholder={'Trg'} keyboardT="default" />
                            <CustomInput control={demandeControl} name={'cause'} placeholder={'Cause'} keyboardT="default" />
                            <CustomInput control={demandeControl} name={'commentaire'} placeholder={'Commentaire'} keyboardT="default" />
            </Collapsible>
        <CustomButton onPress={demandeHandleSubmit(handleSave)} text={"Save Demande"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {typeDemandes !== null && typeDemandes.length > 0 ? ( <FilterModal visibility={typeDemandeModalVisible} placeholder={"Select a TypeDemande"} onItemSelect={onTypeDemandeSelect} items={typeDemandes} onClose={handleCloseTypeDemandeModal} variable={'libelle'} /> ) : null}
        {clients !== null && clients.length > 0 ? ( <FilterModal visibility={clientModalVisible} placeholder={"Select a Client"} onItemSelect={onClientSelect} items={clients} onClose={handleCloseClientModal} variable={'libelle'} /> ) : null}
        {produitMarchands !== null && produitMarchands.length > 0 ? ( <FilterModal visibility={produitMarchandModalVisible} placeholder={"Select a ProduitMarchand"} onItemSelect={onProduitMarchandSelect} items={produitMarchands} onClose={handleCloseProduitMarchandModal} variable={'libelle'} /> ) : null}
        {etatDemandes !== null && etatDemandes.length > 0 ? ( <FilterModal visibility={etatDemandeModalVisible} placeholder={"Select a EtatDemande"} onItemSelect={onEtatDemandeSelect} items={etatDemandes} onClose={handleCloseEtatDemandeModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default DemandeAdminCreate;
