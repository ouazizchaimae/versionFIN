import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {CharteChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueAdminService.service';
import  {CharteChimiqueDto}  from '../../../../../../controller/model/expedition/CharteChimique.model';
import CharteChimiqueAdminCard from '../card/charte-chimique-card-admin.component';

import { CharteChimiqueCriteria } from '../../../../../../controller/criteria/expedition/CharteChimiqueCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import CharteChimiqueAdminSearchModal from '../search/charte-chimique-search-admin.component';

const CharteChimiqueAdminList: React.FC = () =>  {

    const [charteChimiques, setCharteChimiques] = useState<CharteChimiqueDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type CharteChimiqueResponse = AxiosResponse<CharteChimiqueDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [charteChimiqueId, setCharteChimiqueId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new CharteChimiqueCriteria());

    const service = new CharteChimiqueAdminService();

    const handleDeletePress = (id: number) => {
        setCharteChimiqueId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(charteChimiqueId);
            setCharteChimiques((prevCharteChimiques) => prevCharteChimiques.filter((charteChimique) => charteChimique.id !== charteChimiqueId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting charte chimique:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [charteChimiqueResponse] = await Promise.all<CharteChimiqueResponse>([
            service.getList(),
            ]);
            setCharteChimiques(charteChimiqueResponse.data);
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
            const charteChimiqueResponse = await service.find(id);
            const charteChimiqueData = charteChimiqueResponse.data;
            navigation.navigate('CharteChimiqueAdminUpdate', { charteChimique: charteChimiqueData });
        } catch (error) {
            console.error('Error fetching charte chimique data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const charteChimiqueResponse = await service.find(id);
            const charteChimiqueData = charteChimiqueResponse.data;
            navigation.navigate('CharteChimiqueAdminDetails', { charteChimique: charteChimiqueData });
        } catch (error) {
            console.error('Error fetching charte chimique data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new CharteChimiqueCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new CharteChimiqueCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<CharteChimiqueResponse>([ service.findByCriteria(criteria), ]);
            setCharteChimiques(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Charte chimique List</Text>

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
            {charteChimiques && charteChimiques.length > 0 ? ( charteChimiques.map((charteChimique) => (
                <CharteChimiqueAdminCard key={charteChimique.id}
                    code = {charteChimique.code}
                    libelle = {charteChimique.libelle}
                    description = {charteChimique.description}
                    clientName = {charteChimique.client?.libelle}
                    produitMarchandName = {charteChimique.produitMarchand?.libelle}
                    onPressDelete={() => handleDeletePress(charteChimique.id)}
                    onUpdate={() => handleFetchAndUpdate(charteChimique.id)}
                    onDetails={() => handleFetchAndDetails(charteChimique.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No charte chimiques found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'CharteChimique'} />

        <CharteChimiqueAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default CharteChimiqueAdminList;
