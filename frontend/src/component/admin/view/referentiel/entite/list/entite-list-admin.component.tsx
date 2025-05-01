import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {EntiteAdminService} from '../../../../../../controller/service/admin/referentiel/EntiteAdminService.service';
import  {EntiteDto}  from '../../../../../../controller/model/referentiel/Entite.model';
import EntiteAdminCard from '../card/entite-card-admin.component';

import { EntiteCriteria } from '../../../../../../controller/criteria/referentiel/EntiteCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import EntiteAdminSearchModal from '../search/entite-search-admin.component';

const EntiteAdminList: React.FC = () =>  {

    const [entites, setEntites] = useState<EntiteDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type EntiteResponse = AxiosResponse<EntiteDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [entiteId, setEntiteId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new EntiteCriteria());

    const service = new EntiteAdminService();

    const handleDeletePress = (id: number) => {
        setEntiteId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(entiteId);
            setEntites((prevEntites) => prevEntites.filter((entite) => entite.id !== entiteId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting entite:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [entiteResponse] = await Promise.all<EntiteResponse>([
            service.getList(),
            ]);
            setEntites(entiteResponse.data);
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
            const entiteResponse = await service.find(id);
            const entiteData = entiteResponse.data;
            navigation.navigate('EntiteAdminUpdate', { entite: entiteData });
        } catch (error) {
            console.error('Error fetching entite data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const entiteResponse = await service.find(id);
            const entiteData = entiteResponse.data;
            navigation.navigate('EntiteAdminDetails', { entite: entiteData });
        } catch (error) {
            console.error('Error fetching entite data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new EntiteCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new EntiteCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<EntiteResponse>([ service.findByCriteria(criteria), ]);
            setEntites(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Entite List</Text>

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
            {entites && entites.length > 0 ? ( entites.map((entite) => (
                <EntiteAdminCard key={entite.id}
                    code = {entite.code}
                    libelle = {entite.libelle}
                    style = {entite.style}
                    description = {entite.description}
                    onPressDelete={() => handleDeletePress(entite.id)}
                    onUpdate={() => handleFetchAndUpdate(entite.id)}
                    onDetails={() => handleFetchAndDetails(entite.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No entites found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'Entite'} />

        <EntiteAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default EntiteAdminList;
