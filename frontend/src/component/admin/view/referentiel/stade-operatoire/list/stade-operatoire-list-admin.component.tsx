import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {StadeOperatoireAdminService} from '../../../../../../controller/service/admin/referentiel/StadeOperatoireAdminService.service';
import  {StadeOperatoireDto}  from '../../../../../../controller/model/referentiel/StadeOperatoire.model';
import StadeOperatoireAdminCard from '../card/stade-operatoire-card-admin.component';

import { StadeOperatoireCriteria } from '../../../../../../controller/criteria/referentiel/StadeOperatoireCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import StadeOperatoireAdminSearchModal from '../search/stade-operatoire-search-admin.component';

const StadeOperatoireAdminList: React.FC = () =>  {

    const [stadeOperatoires, setStadeOperatoires] = useState<StadeOperatoireDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type StadeOperatoireResponse = AxiosResponse<StadeOperatoireDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [stadeOperatoireId, setStadeOperatoireId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new StadeOperatoireCriteria());

    const service = new StadeOperatoireAdminService();

    const handleDeletePress = (id: number) => {
        setStadeOperatoireId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(stadeOperatoireId);
            setStadeOperatoires((prevStadeOperatoires) => prevStadeOperatoires.filter((stadeOperatoire) => stadeOperatoire.id !== stadeOperatoireId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting stade operatoire:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [stadeOperatoireResponse] = await Promise.all<StadeOperatoireResponse>([
            service.getList(),
            ]);
            setStadeOperatoires(stadeOperatoireResponse.data);
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
            const stadeOperatoireResponse = await service.find(id);
            const stadeOperatoireData = stadeOperatoireResponse.data;
            navigation.navigate('StadeOperatoireAdminUpdate', { stadeOperatoire: stadeOperatoireData });
        } catch (error) {
            console.error('Error fetching stade operatoire data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const stadeOperatoireResponse = await service.find(id);
            const stadeOperatoireData = stadeOperatoireResponse.data;
            navigation.navigate('StadeOperatoireAdminDetails', { stadeOperatoire: stadeOperatoireData });
        } catch (error) {
            console.error('Error fetching stade operatoire data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new StadeOperatoireCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new StadeOperatoireCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<StadeOperatoireResponse>([ service.findByCriteria(criteria), ]);
            setStadeOperatoires(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Stade operatoire List</Text>

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
            {stadeOperatoires && stadeOperatoires.length > 0 ? ( stadeOperatoires.map((stadeOperatoire) => (
                <StadeOperatoireAdminCard key={stadeOperatoire.id}
                    code = {stadeOperatoire.code}
                    libelle = {stadeOperatoire.libelle}
                    style = {stadeOperatoire.style}
                    description = {stadeOperatoire.description}
                    capaciteMin = {stadeOperatoire.capaciteMin}
                    capaciteMax = {stadeOperatoire.capaciteMax}
                    indice = {stadeOperatoire.indice}
                    entiteName = {stadeOperatoire.entite?.libelle}
                    onPressDelete={() => handleDeletePress(stadeOperatoire.id)}
                    onUpdate={() => handleFetchAndUpdate(stadeOperatoire.id)}
                    onDetails={() => handleFetchAndDetails(stadeOperatoire.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No stade operatoires found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'StadeOperatoire'} />

        <StadeOperatoireAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default StadeOperatoireAdminList;
