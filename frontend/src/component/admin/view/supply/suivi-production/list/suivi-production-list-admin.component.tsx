import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {SuiviProductionAdminService} from '../../../../../../controller/service/admin/supply/SuiviProductionAdminService.service';
import  {SuiviProductionDto}  from '../../../../../../controller/model/supply/SuiviProduction.model';
import SuiviProductionAdminCard from '../card/suivi-production-card-admin.component';

import { SuiviProductionCriteria } from '../../../../../../controller/criteria/supply/SuiviProductionCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import SuiviProductionAdminSearchModal from '../search/suivi-production-search-admin.component';

const SuiviProductionAdminList: React.FC = () =>  {

    const [suiviProductions, setSuiviProductions] = useState<SuiviProductionDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type SuiviProductionResponse = AxiosResponse<SuiviProductionDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [suiviProductionId, setSuiviProductionId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new SuiviProductionCriteria());

    const service = new SuiviProductionAdminService();

    const handleDeletePress = (id: number) => {
        setSuiviProductionId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(suiviProductionId);
            setSuiviProductions((prevSuiviProductions) => prevSuiviProductions.filter((suiviProduction) => suiviProduction.id !== suiviProductionId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting suivi production:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [suiviProductionResponse] = await Promise.all<SuiviProductionResponse>([
            service.getList(),
            ]);
            setSuiviProductions(suiviProductionResponse.data);
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
            const suiviProductionResponse = await service.find(id);
            const suiviProductionData = suiviProductionResponse.data;
            navigation.navigate('SuiviProductionAdminUpdate', { suiviProduction: suiviProductionData });
        } catch (error) {
            console.error('Error fetching suivi production data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const suiviProductionResponse = await service.find(id);
            const suiviProductionData = suiviProductionResponse.data;
            navigation.navigate('SuiviProductionAdminDetails', { suiviProduction: suiviProductionData });
        } catch (error) {
            console.error('Error fetching suivi production data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new SuiviProductionCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new SuiviProductionCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<SuiviProductionResponse>([ service.findByCriteria(criteria), ]);
            setSuiviProductions(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Suivi production List</Text>

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
            {suiviProductions && suiviProductions.length > 0 ? ( suiviProductions.map((suiviProduction) => (
                <SuiviProductionAdminCard key={suiviProduction.id}
                    code = {suiviProduction.code}
                    libelle = {suiviProduction.libelle}
                    description = {suiviProduction.description}
                    jour = {suiviProduction.jour}
                    volume = {suiviProduction.volume}
                    tsm = {suiviProduction.tsm}
                    produitName = {suiviProduction.produit?.libelle}
                    stadeOperatoireName = {suiviProduction.stadeOperatoire?.libelle}
                    uniteName = {suiviProduction.unite?.libelle}
                    onPressDelete={() => handleDeletePress(suiviProduction.id)}
                    onUpdate={() => handleFetchAndUpdate(suiviProduction.id)}
                    onDetails={() => handleFetchAndDetails(suiviProduction.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No suivi productions found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'SuiviProduction'} />

        <SuiviProductionAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default SuiviProductionAdminList;
