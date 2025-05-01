import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {TypeClientAdminService} from '../../../../../../controller/service/admin/referentiel/TypeClientAdminService.service';
import  {TypeClientDto}  from '../../../../../../controller/model/referentiel/TypeClient.model';
import TypeClientAdminCard from '../card/type-client-card-admin.component';

import { TypeClientCriteria } from '../../../../../../controller/criteria/referentiel/TypeClientCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import TypeClientAdminSearchModal from '../search/type-client-search-admin.component';

const TypeClientAdminList: React.FC = () =>  {

    const [typeClients, setTypeClients] = useState<TypeClientDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type TypeClientResponse = AxiosResponse<TypeClientDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [typeClientId, setTypeClientId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new TypeClientCriteria());

    const service = new TypeClientAdminService();

    const handleDeletePress = (id: number) => {
        setTypeClientId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(typeClientId);
            setTypeClients((prevTypeClients) => prevTypeClients.filter((typeClient) => typeClient.id !== typeClientId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting type client:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [typeClientResponse] = await Promise.all<TypeClientResponse>([
            service.getList(),
            ]);
            setTypeClients(typeClientResponse.data);
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
            const typeClientResponse = await service.find(id);
            const typeClientData = typeClientResponse.data;
            navigation.navigate('TypeClientAdminUpdate', { typeClient: typeClientData });
        } catch (error) {
            console.error('Error fetching type client data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const typeClientResponse = await service.find(id);
            const typeClientData = typeClientResponse.data;
            navigation.navigate('TypeClientAdminDetails', { typeClient: typeClientData });
        } catch (error) {
            console.error('Error fetching type client data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new TypeClientCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new TypeClientCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<TypeClientResponse>([ service.findByCriteria(criteria), ]);
            setTypeClients(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Type client List</Text>

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
            {typeClients && typeClients.length > 0 ? ( typeClients.map((typeClient) => (
                <TypeClientAdminCard key={typeClient.id}
                    libelle = {typeClient.libelle}
                    code = {typeClient.code}
                    style = {typeClient.style}
                    description = {typeClient.description}
                    onPressDelete={() => handleDeletePress(typeClient.id)}
                    onUpdate={() => handleFetchAndUpdate(typeClient.id)}
                    onDetails={() => handleFetchAndDetails(typeClient.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No type clients found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'TypeClient'} />

        <TypeClientAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default TypeClientAdminList;
