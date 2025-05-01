import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {TypeDemandeAdminService} from '../../../../../../controller/service/admin/referentiel/TypeDemandeAdminService.service';
import  {TypeDemandeDto}  from '../../../../../../controller/model/referentiel/TypeDemande.model';
import TypeDemandeAdminCard from '../card/type-demande-card-admin.component';

import { TypeDemandeCriteria } from '../../../../../../controller/criteria/referentiel/TypeDemandeCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import TypeDemandeAdminSearchModal from '../search/type-demande-search-admin.component';

const TypeDemandeAdminList: React.FC = () =>  {

    const [typeDemandes, setTypeDemandes] = useState<TypeDemandeDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type TypeDemandeResponse = AxiosResponse<TypeDemandeDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [typeDemandeId, setTypeDemandeId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new TypeDemandeCriteria());

    const service = new TypeDemandeAdminService();

    const handleDeletePress = (id: number) => {
        setTypeDemandeId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(typeDemandeId);
            setTypeDemandes((prevTypeDemandes) => prevTypeDemandes.filter((typeDemande) => typeDemande.id !== typeDemandeId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting type demande:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [typeDemandeResponse] = await Promise.all<TypeDemandeResponse>([
            service.getList(),
            ]);
            setTypeDemandes(typeDemandeResponse.data);
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
            const typeDemandeResponse = await service.find(id);
            const typeDemandeData = typeDemandeResponse.data;
            navigation.navigate('TypeDemandeAdminUpdate', { typeDemande: typeDemandeData });
        } catch (error) {
            console.error('Error fetching type demande data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const typeDemandeResponse = await service.find(id);
            const typeDemandeData = typeDemandeResponse.data;
            navigation.navigate('TypeDemandeAdminDetails', { typeDemande: typeDemandeData });
        } catch (error) {
            console.error('Error fetching type demande data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new TypeDemandeCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new TypeDemandeCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<TypeDemandeResponse>([ service.findByCriteria(criteria), ]);
            setTypeDemandes(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Type demande List</Text>

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
            {typeDemandes && typeDemandes.length > 0 ? ( typeDemandes.map((typeDemande) => (
                <TypeDemandeAdminCard key={typeDemande.id}
                    libelle = {typeDemande.libelle}
                    code = {typeDemande.code}
                    style = {typeDemande.style}
                    description = {typeDemande.description}
                    onPressDelete={() => handleDeletePress(typeDemande.id)}
                    onUpdate={() => handleFetchAndUpdate(typeDemande.id)}
                    onDetails={() => handleFetchAndDetails(typeDemande.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No type demandes found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'TypeDemande'} />

        <TypeDemandeAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default TypeDemandeAdminList;
