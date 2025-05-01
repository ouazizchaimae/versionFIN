import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {EtatDemandeAdminService} from '../../../../../../controller/service/admin/referentiel/EtatDemandeAdminService.service';
import  {EtatDemandeDto}  from '../../../../../../controller/model/referentiel/EtatDemande.model';
import EtatDemandeAdminCard from '../card/etat-demande-card-admin.component';

import { EtatDemandeCriteria } from '../../../../../../controller/criteria/referentiel/EtatDemandeCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import EtatDemandeAdminSearchModal from '../search/etat-demande-search-admin.component';

const EtatDemandeAdminList: React.FC = () =>  {

    const [etatDemandes, setEtatDemandes] = useState<EtatDemandeDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type EtatDemandeResponse = AxiosResponse<EtatDemandeDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [etatDemandeId, setEtatDemandeId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new EtatDemandeCriteria());

    const service = new EtatDemandeAdminService();

    const handleDeletePress = (id: number) => {
        setEtatDemandeId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(etatDemandeId);
            setEtatDemandes((prevEtatDemandes) => prevEtatDemandes.filter((etatDemande) => etatDemande.id !== etatDemandeId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting etat demande:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [etatDemandeResponse] = await Promise.all<EtatDemandeResponse>([
            service.getList(),
            ]);
            setEtatDemandes(etatDemandeResponse.data);
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
            const etatDemandeResponse = await service.find(id);
            const etatDemandeData = etatDemandeResponse.data;
            navigation.navigate('EtatDemandeAdminUpdate', { etatDemande: etatDemandeData });
        } catch (error) {
            console.error('Error fetching etat demande data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const etatDemandeResponse = await service.find(id);
            const etatDemandeData = etatDemandeResponse.data;
            navigation.navigate('EtatDemandeAdminDetails', { etatDemande: etatDemandeData });
        } catch (error) {
            console.error('Error fetching etat demande data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new EtatDemandeCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new EtatDemandeCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<EtatDemandeResponse>([ service.findByCriteria(criteria), ]);
            setEtatDemandes(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Etat demande List</Text>

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
            {etatDemandes && etatDemandes.length > 0 ? ( etatDemandes.map((etatDemande) => (
                <EtatDemandeAdminCard key={etatDemande.id}
                    libelle = {etatDemande.libelle}
                    code = {etatDemande.code}
                    style = {etatDemande.style}
                    description = {etatDemande.description}
                    onPressDelete={() => handleDeletePress(etatDemande.id)}
                    onUpdate={() => handleFetchAndUpdate(etatDemande.id)}
                    onDetails={() => handleFetchAndDetails(etatDemande.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No etat demandes found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'EtatDemande'} />

        <EtatDemandeAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default EtatDemandeAdminList;
