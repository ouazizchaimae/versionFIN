import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {ClientAdminService} from '../../../../../../controller/service/admin/referentiel/ClientAdminService.service';
import  {ClientDto}  from '../../../../../../controller/model/referentiel/Client.model';
import ClientAdminCard from '../card/client-card-admin.component';

import { ClientCriteria } from '../../../../../../controller/criteria/referentiel/ClientCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import ClientAdminSearchModal from '../search/client-search-admin.component';

const ClientAdminList: React.FC = () =>  {

    const [clients, setClients] = useState<ClientDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type ClientResponse = AxiosResponse<ClientDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [clientId, setClientId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new ClientCriteria());

    const service = new ClientAdminService();

    const handleDeletePress = (id: number) => {
        setClientId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(clientId);
            setClients((prevClients) => prevClients.filter((client) => client.id !== clientId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting client:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [clientResponse] = await Promise.all<ClientResponse>([
            service.getList(),
            ]);
            setClients(clientResponse.data);
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
            const clientResponse = await service.find(id);
            const clientData = clientResponse.data;
            navigation.navigate('ClientAdminUpdate', { client: clientData });
        } catch (error) {
            console.error('Error fetching client data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const clientResponse = await service.find(id);
            const clientData = clientResponse.data;
            navigation.navigate('ClientAdminDetails', { client: clientData });
        } catch (error) {
            console.error('Error fetching client data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new ClientCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new ClientCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<ClientResponse>([ service.findByCriteria(criteria), ]);
            setClients(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Client List</Text>

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
            {clients && clients.length > 0 ? ( clients.map((client) => (
                <ClientAdminCard key={client.id}
                    libelle = {client.libelle}
                    code = {client.code}
                    description = {client.description}
                    typeClientName = {client.typeClient?.libelle}
                    onPressDelete={() => handleDeletePress(client.id)}
                    onUpdate={() => handleFetchAndUpdate(client.id)}
                    onDetails={() => handleFetchAndDetails(client.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No clients found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'Client'} />

        <ClientAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default ClientAdminList;
