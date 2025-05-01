import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {ProduitAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitAdminService.service';
import  {ProduitDto}  from '../../../../../../controller/model/referentiel/Produit.model';
import ProduitAdminCard from '../card/produit-card-admin.component';

import { ProduitCriteria } from '../../../../../../controller/criteria/referentiel/ProduitCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import ProduitAdminSearchModal from '../search/produit-search-admin.component';

const ProduitAdminList: React.FC = () =>  {

    const [produits, setProduits] = useState<ProduitDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type ProduitResponse = AxiosResponse<ProduitDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [produitId, setProduitId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new ProduitCriteria());

    const service = new ProduitAdminService();

    const handleDeletePress = (id: number) => {
        setProduitId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(produitId);
            setProduits((prevProduits) => prevProduits.filter((produit) => produit.id !== produitId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting produit:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [produitResponse] = await Promise.all<ProduitResponse>([
            service.getList(),
            ]);
            setProduits(produitResponse.data);
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
            const produitResponse = await service.find(id);
            const produitData = produitResponse.data;
            navigation.navigate('ProduitAdminUpdate', { produit: produitData });
        } catch (error) {
            console.error('Error fetching produit data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const produitResponse = await service.find(id);
            const produitData = produitResponse.data;
            navigation.navigate('ProduitAdminDetails', { produit: produitData });
        } catch (error) {
            console.error('Error fetching produit data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new ProduitCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new ProduitCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<ProduitResponse>([ service.findByCriteria(criteria), ]);
            setProduits(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Produit List</Text>

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
            {produits && produits.length > 0 ? ( produits.map((produit) => (
                <ProduitAdminCard key={produit.id}
                    code = {produit.code}
                    libelle = {produit.libelle}
                    description = {produit.description}
                    onPressDelete={() => handleDeletePress(produit.id)}
                    onUpdate={() => handleFetchAndUpdate(produit.id)}
                    onDetails={() => handleFetchAndDetails(produit.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No produits found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'Produit'} />

        <ProduitAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default ProduitAdminList;
