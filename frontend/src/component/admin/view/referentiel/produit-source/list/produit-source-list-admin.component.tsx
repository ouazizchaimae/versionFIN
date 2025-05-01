import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {ProduitSourceAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitSourceAdminService.service';
import  {ProduitSourceDto}  from '../../../../../../controller/model/referentiel/ProduitSource.model';
import ProduitSourceAdminCard from '../card/produit-source-card-admin.component';

import { ProduitSourceCriteria } from '../../../../../../controller/criteria/referentiel/ProduitSourceCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import ProduitSourceAdminSearchModal from '../search/produit-source-search-admin.component';

const ProduitSourceAdminList: React.FC = () =>  {

    const [produitSources, setProduitSources] = useState<ProduitSourceDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type ProduitSourceResponse = AxiosResponse<ProduitSourceDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [produitSourceId, setProduitSourceId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new ProduitSourceCriteria());

    const service = new ProduitSourceAdminService();

    const handleDeletePress = (id: number) => {
        setProduitSourceId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(produitSourceId);
            setProduitSources((prevProduitSources) => prevProduitSources.filter((produitSource) => produitSource.id !== produitSourceId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting produit source:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [produitSourceResponse] = await Promise.all<ProduitSourceResponse>([
            service.getList(),
            ]);
            setProduitSources(produitSourceResponse.data);
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
            const produitSourceResponse = await service.find(id);
            const produitSourceData = produitSourceResponse.data;
            navigation.navigate('ProduitSourceAdminUpdate', { produitSource: produitSourceData });
        } catch (error) {
            console.error('Error fetching produit source data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const produitSourceResponse = await service.find(id);
            const produitSourceData = produitSourceResponse.data;
            navigation.navigate('ProduitSourceAdminDetails', { produitSource: produitSourceData });
        } catch (error) {
            console.error('Error fetching produit source data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new ProduitSourceCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new ProduitSourceCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<ProduitSourceResponse>([ service.findByCriteria(criteria), ]);
            setProduitSources(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Produit source List</Text>

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
            {produitSources && produitSources.length > 0 ? ( produitSources.map((produitSource) => (
                <ProduitSourceAdminCard key={produitSource.id}
                    code = {produitSource.code}
                    libelle = {produitSource.libelle}
                    style = {produitSource.style}
                    description = {produitSource.description}
                    onPressDelete={() => handleDeletePress(produitSource.id)}
                    onUpdate={() => handleFetchAndUpdate(produitSource.id)}
                    onDetails={() => handleFetchAndDetails(produitSource.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No produit sources found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'ProduitSource'} />

        <ProduitSourceAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default ProduitSourceAdminList;
