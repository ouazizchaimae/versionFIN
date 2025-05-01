import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {ExpeditionProduitAdminService} from '../../../../../../controller/service/admin/expedition/ExpeditionProduitAdminService.service';
import  {ExpeditionProduitDto}  from '../../../../../../controller/model/expedition/ExpeditionProduit.model';
import ExpeditionProduitAdminCard from '../card/expedition-produit-card-admin.component';

import { ExpeditionProduitCriteria } from '../../../../../../controller/criteria/expedition/ExpeditionProduitCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import ExpeditionProduitAdminSearchModal from '../search/expedition-produit-search-admin.component';

const ExpeditionProduitAdminList: React.FC = () =>  {

    const [expeditionProduits, setExpeditionProduits] = useState<ExpeditionProduitDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type ExpeditionProduitResponse = AxiosResponse<ExpeditionProduitDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [expeditionProduitId, setExpeditionProduitId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new ExpeditionProduitCriteria());

    const service = new ExpeditionProduitAdminService();

    const handleDeletePress = (id: number) => {
        setExpeditionProduitId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(expeditionProduitId);
            setExpeditionProduits((prevExpeditionProduits) => prevExpeditionProduits.filter((expeditionProduit) => expeditionProduit.id !== expeditionProduitId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting expedition produit:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [expeditionProduitResponse] = await Promise.all<ExpeditionProduitResponse>([
            service.getList(),
            ]);
            setExpeditionProduits(expeditionProduitResponse.data);
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
            const expeditionProduitResponse = await service.find(id);
            const expeditionProduitData = expeditionProduitResponse.data;
            navigation.navigate('ExpeditionProduitAdminUpdate', { expeditionProduit: expeditionProduitData });
        } catch (error) {
            console.error('Error fetching expedition produit data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const expeditionProduitResponse = await service.find(id);
            const expeditionProduitData = expeditionProduitResponse.data;
            navigation.navigate('ExpeditionProduitAdminDetails', { expeditionProduit: expeditionProduitData });
        } catch (error) {
            console.error('Error fetching expedition produit data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new ExpeditionProduitCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new ExpeditionProduitCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<ExpeditionProduitResponse>([ service.findByCriteria(criteria), ]);
            setExpeditionProduits(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Expedition produit List</Text>

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
            {expeditionProduits && expeditionProduits.length > 0 ? ( expeditionProduits.map((expeditionProduit) => (
                <ExpeditionProduitAdminCard key={expeditionProduit.id}
                    code = {expeditionProduit.code}
                    libelle = {expeditionProduit.libelle}
                    description = {expeditionProduit.description}
                    analyseChimiqueName = {expeditionProduit.analyseChimique?.libelle}
                    charteChimiqueName = {expeditionProduit.charteChimique?.libelle}
                    onPressDelete={() => handleDeletePress(expeditionProduit.id)}
                    onUpdate={() => handleFetchAndUpdate(expeditionProduit.id)}
                    onDetails={() => handleFetchAndDetails(expeditionProduit.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No expedition produits found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'ExpeditionProduit'} />

        <ExpeditionProduitAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default ExpeditionProduitAdminList;
