import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import  {ElementChimiqueDto}  from '../../../../../../controller/model/referentiel/ElementChimique.model';
import ElementChimiqueAdminCard from '../card/element-chimique-card-admin.component';

import { ElementChimiqueCriteria } from '../../../../../../controller/criteria/referentiel/ElementChimiqueCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import ElementChimiqueAdminSearchModal from '../search/element-chimique-search-admin.component';

const ElementChimiqueAdminList: React.FC = () =>  {

    const [elementChimiques, setElementChimiques] = useState<ElementChimiqueDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type ElementChimiqueResponse = AxiosResponse<ElementChimiqueDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [elementChimiqueId, setElementChimiqueId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new ElementChimiqueCriteria());

    const service = new ElementChimiqueAdminService();

    const handleDeletePress = (id: number) => {
        setElementChimiqueId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(elementChimiqueId);
            setElementChimiques((prevElementChimiques) => prevElementChimiques.filter((elementChimique) => elementChimique.id !== elementChimiqueId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting element chimique:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [elementChimiqueResponse] = await Promise.all<ElementChimiqueResponse>([
            service.getList(),
            ]);
            setElementChimiques(elementChimiqueResponse.data);
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
            const elementChimiqueResponse = await service.find(id);
            const elementChimiqueData = elementChimiqueResponse.data;
            navigation.navigate('ElementChimiqueAdminUpdate', { elementChimique: elementChimiqueData });
        } catch (error) {
            console.error('Error fetching element chimique data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const elementChimiqueResponse = await service.find(id);
            const elementChimiqueData = elementChimiqueResponse.data;
            navigation.navigate('ElementChimiqueAdminDetails', { elementChimique: elementChimiqueData });
        } catch (error) {
            console.error('Error fetching element chimique data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new ElementChimiqueCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new ElementChimiqueCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<ElementChimiqueResponse>([ service.findByCriteria(criteria), ]);
            setElementChimiques(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Element chimique List</Text>

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
            {elementChimiques && elementChimiques.length > 0 ? ( elementChimiques.map((elementChimique) => (
                <ElementChimiqueAdminCard key={elementChimique.id}
                    code = {elementChimique.code}
                    libelle = {elementChimique.libelle}
                    style = {elementChimique.style}
                    description = {elementChimique.description}
                    uniteName = {elementChimique.unite?.libelle}
                    onPressDelete={() => handleDeletePress(elementChimique.id)}
                    onUpdate={() => handleFetchAndUpdate(elementChimique.id)}
                    onDetails={() => handleFetchAndDetails(elementChimique.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No element chimiques found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'ElementChimique'} />

        <ElementChimiqueAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default ElementChimiqueAdminList;
