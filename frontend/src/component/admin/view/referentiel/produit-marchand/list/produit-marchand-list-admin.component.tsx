import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {ProduitMarchandAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitMarchandAdminService.service';
import  {ProduitMarchandDto}  from '../../../../../../controller/model/referentiel/ProduitMarchand.model';
import ProduitMarchandAdminCard from '../card/produit-marchand-card-admin.component';

import { ProduitMarchandCriteria } from '../../../../../../controller/criteria/referentiel/ProduitMarchandCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import ProduitMarchandAdminSearchModal from '../search/produit-marchand-search-admin.component';

const ProduitMarchandAdminList: React.FC = () =>  {

    const [produitMarchands, setProduitMarchands] = useState<ProduitMarchandDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type ProduitMarchandResponse = AxiosResponse<ProduitMarchandDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [produitMarchandId, setProduitMarchandId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new ProduitMarchandCriteria());

    const service = new ProduitMarchandAdminService();

    const handleDeletePress = (id: number) => {
        setProduitMarchandId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(produitMarchandId);
            setProduitMarchands((prevProduitMarchands) => prevProduitMarchands.filter((produitMarchand) => produitMarchand.id !== produitMarchandId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting produit marchand:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [produitMarchandResponse] = await Promise.all<ProduitMarchandResponse>([
            service.getList(),
            ]);
            setProduitMarchands(produitMarchandResponse.data);
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
            const produitMarchandResponse = await service.find(id);
            const produitMarchandData = produitMarchandResponse.data;
            navigation.navigate('ProduitMarchandAdminUpdate', { produitMarchand: produitMarchandData });
        } catch (error) {
            console.error('Error fetching produit marchand data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const produitMarchandResponse = await service.find(id);
            const produitMarchandData = produitMarchandResponse.data;
            navigation.navigate('ProduitMarchandAdminDetails', { produitMarchand: produitMarchandData });
        } catch (error) {
            console.error('Error fetching produit marchand data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new ProduitMarchandCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new ProduitMarchandCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<ProduitMarchandResponse>([ service.findByCriteria(criteria), ]);
            setProduitMarchands(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Produit marchand List</Text>

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
            {produitMarchands && produitMarchands.length > 0 ? ( produitMarchands.map((produitMarchand) => (
                <ProduitMarchandAdminCard key={produitMarchand.id}
                    code = {produitMarchand.code}
                    libelle = {produitMarchand.libelle}
                    style = {produitMarchand.style}
                    description = {produitMarchand.description}
                    onPressDelete={() => handleDeletePress(produitMarchand.id)}
                    onUpdate={() => handleFetchAndUpdate(produitMarchand.id)}
                    onDetails={() => handleFetchAndDetails(produitMarchand.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No produit marchands found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'ProduitMarchand'} />

        <ProduitMarchandAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default ProduitMarchandAdminList;
