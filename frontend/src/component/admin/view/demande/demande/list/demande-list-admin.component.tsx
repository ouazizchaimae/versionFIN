import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {DemandeAdminService} from '../../../../../../controller/service/admin/demande/DemandeAdminService.service';
import  {DemandeDto}  from '../../../../../../controller/model/demande/Demande.model';
import DemandeAdminCard from '../card/demande-card-admin.component';

import { DemandeCriteria } from '../../../../../../controller/criteria/demande/DemandeCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import DemandeAdminSearchModal from '../search/demande-search-admin.component';

const DemandeAdminList: React.FC = () =>  {

    const [demandes, setDemandes] = useState<DemandeDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type DemandeResponse = AxiosResponse<DemandeDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [demandeId, setDemandeId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new DemandeCriteria());

    const service = new DemandeAdminService();

    const handleDeletePress = (id: number) => {
        setDemandeId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(demandeId);
            setDemandes((prevDemandes) => prevDemandes.filter((demande) => demande.id !== demandeId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting demande:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [demandeResponse] = await Promise.all<DemandeResponse>([
            service.getList(),
            ]);
            setDemandes(demandeResponse.data);
        } catch (error) {
            console.error(error);
        }
    };

    useFocusEffect(
        useCallback(() => {
            fetchData();
        }, [])
    );

    const handleFetchAndUpdate = async (id: number) => {
        try {
            const demandeResponse = await service.find(id);
            const demandeData = demandeResponse.data;
            navigation.navigate('DemandeAdminUpdate', { demande: demandeData });
        } catch (error) {
            console.error('Error fetching demande data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const demandeResponse = await service.find(id);
            const demandeData = demandeResponse.data;
            navigation.navigate('DemandeAdminDetails', { demande: demandeData });
        } catch (error) {
            console.error('Error fetching demande data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new DemandeCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new DemandeCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<DemandeResponse>([ service.findByCriteria(criteria), ]);
            setDemandes(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Demande List</Text>

            <View style={{flexDirection: 'row'}}>

                <View style={globalStyle.searchContainer}>
                    <TouchableOpacity style={globalStyle.searchButton}
                                      onPress={() => setIsSearchModalVisible(true)}>
                    <Ionicons name="search-sharp" size={22} color={'white'}/>
                        </TouchableOpacity>
                </View>

                <View style={globalStyle.searchContainer}>
                    <TouchableOpacity style={globalStyle.resetSearchButton} onPress={() => fetchData()}>
                    <Ionicons name="refresh-outline" size={22} color={'white'}/>
                        </TouchableOpacity>
                </View>

            </View>

        </View>

        <View style={{ marginBottom: 100 }}>
            {demandes && demandes.length > 0 ? ( demandes.map((demande) => (
                <DemandeAdminCard key={demande.id}
                    code = {demande.code}
                    libelle = {demande.libelle}
                    description = {demande.description}
                    produitMarchandName = {demande.produitMarchand?.libelle}
                    clientName = {demande.client?.libelle}
                    dateDemande = {demande.dateDemande}
                    dateExpedition = {demande.dateExpedition}
                    volume = {demande.volume}
                    typeDemandeName = {demande.typeDemande?.libelle}
                    etatDemandeName = {demande.etatDemande?.libelle}
                    actionEntreprise = {demande.actionEntreprise}
                    trg = {demande.trg}
                    cause = {demande.cause}
                    commentaire = {demande.commentaire}
                    onPressDelete={() => handleDeletePress(demande.id)}
                    onUpdate={() => handleFetchAndUpdate(demande.id)}
                    onDetails={() => handleFetchAndDetails(demande.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No demandes found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'Demande'} />

        <DemandeAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default DemandeAdminList;
