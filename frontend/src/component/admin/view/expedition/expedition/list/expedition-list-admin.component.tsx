import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {ExpeditionAdminService} from '../../../../../../controller/service/admin/expedition/ExpeditionAdminService.service';
import  {ExpeditionDto}  from '../../../../../../controller/model/expedition/Expedition.model';
import ExpeditionAdminCard from '../card/expedition-card-admin.component';

import { ExpeditionCriteria } from '../../../../../../controller/criteria/expedition/ExpeditionCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import ExpeditionAdminSearchModal from '../search/expedition-search-admin.component';

const ExpeditionAdminList: React.FC = () =>  {

    const [expeditions, setExpeditions] = useState<ExpeditionDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type ExpeditionResponse = AxiosResponse<ExpeditionDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [expeditionId, setExpeditionId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new ExpeditionCriteria());

    const service = new ExpeditionAdminService();

    const handleDeletePress = (id: number) => {
        setExpeditionId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(expeditionId);
            setExpeditions((prevExpeditions) => prevExpeditions.filter((expedition) => expedition.id !== expeditionId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting expedition:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [expeditionResponse] = await Promise.all<ExpeditionResponse>([
            service.getList(),
            ]);
            setExpeditions(expeditionResponse.data);
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
            const expeditionResponse = await service.find(id);
            const expeditionData = expeditionResponse.data;
            navigation.navigate('ExpeditionAdminUpdate', { expedition: expeditionData });
        } catch (error) {
            console.error('Error fetching expedition data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const expeditionResponse = await service.find(id);
            const expeditionData = expeditionResponse.data;
            navigation.navigate('ExpeditionAdminDetails', { expedition: expeditionData });
        } catch (error) {
            console.error('Error fetching expedition data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new ExpeditionCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new ExpeditionCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<ExpeditionResponse>([ service.findByCriteria(criteria), ]);
            setExpeditions(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Expedition List</Text>

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
            {expeditions && expeditions.length > 0 ? ( expeditions.map((expedition) => (
                <ExpeditionAdminCard key={expedition.id}
                    code = {expedition.code}
                    libelle = {expedition.libelle}
                    description = {expedition.description}
                    clientName = {expedition.client?.libelle}
                    typeExpeditionName = {expedition.typeExpedition?.libelle}
                    onPressDelete={() => handleDeletePress(expedition.id)}
                    onUpdate={() => handleFetchAndUpdate(expedition.id)}
                    onDetails={() => handleFetchAndDetails(expedition.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No expeditions found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'Expedition'} />

        <ExpeditionAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default ExpeditionAdminList;
