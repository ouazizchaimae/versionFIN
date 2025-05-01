import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {TypeExpeditionAdminService} from '../../../../../../controller/service/admin/expedition/TypeExpeditionAdminService.service';
import  {TypeExpeditionDto}  from '../../../../../../controller/model/expedition/TypeExpedition.model';
import TypeExpeditionAdminCard from '../card/type-expedition-card-admin.component';

import { TypeExpeditionCriteria } from '../../../../../../controller/criteria/expedition/TypeExpeditionCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import TypeExpeditionAdminSearchModal from '../search/type-expedition-search-admin.component';

const TypeExpeditionAdminList: React.FC = () =>  {

    const [typeExpeditions, setTypeExpeditions] = useState<TypeExpeditionDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type TypeExpeditionResponse = AxiosResponse<TypeExpeditionDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [typeExpeditionId, setTypeExpeditionId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new TypeExpeditionCriteria());

    const service = new TypeExpeditionAdminService();

    const handleDeletePress = (id: number) => {
        setTypeExpeditionId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(typeExpeditionId);
            setTypeExpeditions((prevTypeExpeditions) => prevTypeExpeditions.filter((typeExpedition) => typeExpedition.id !== typeExpeditionId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting type expedition:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [typeExpeditionResponse] = await Promise.all<TypeExpeditionResponse>([
            service.getList(),
            ]);
            setTypeExpeditions(typeExpeditionResponse.data);
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
            const typeExpeditionResponse = await service.find(id);
            const typeExpeditionData = typeExpeditionResponse.data;
            navigation.navigate('TypeExpeditionAdminUpdate', { typeExpedition: typeExpeditionData });
        } catch (error) {
            console.error('Error fetching type expedition data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const typeExpeditionResponse = await service.find(id);
            const typeExpeditionData = typeExpeditionResponse.data;
            navigation.navigate('TypeExpeditionAdminDetails', { typeExpedition: typeExpeditionData });
        } catch (error) {
            console.error('Error fetching type expedition data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new TypeExpeditionCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new TypeExpeditionCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<TypeExpeditionResponse>([ service.findByCriteria(criteria), ]);
            setTypeExpeditions(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Type expedition List</Text>

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
            {typeExpeditions && typeExpeditions.length > 0 ? ( typeExpeditions.map((typeExpedition) => (
                <TypeExpeditionAdminCard key={typeExpedition.id}
                    libelle = {typeExpedition.libelle}
                    code = {typeExpedition.code}
                    style = {typeExpedition.style}
                    description = {typeExpedition.description}
                    onPressDelete={() => handleDeletePress(typeExpedition.id)}
                    onUpdate={() => handleFetchAndUpdate(typeExpedition.id)}
                    onDetails={() => handleFetchAndDetails(typeExpedition.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No type expeditions found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'TypeExpedition'} />

        <TypeExpeditionAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default TypeExpeditionAdminList;
