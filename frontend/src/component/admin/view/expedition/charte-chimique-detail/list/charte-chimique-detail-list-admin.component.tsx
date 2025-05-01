import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {CharteChimiqueDetailAdminService} from '../../../../../../controller/service/admin/expedition/CharteChimiqueDetailAdminService.service';
import  {CharteChimiqueDetailDto}  from '../../../../../../controller/model/expedition/CharteChimiqueDetail.model';
import CharteChimiqueDetailAdminCard from '../card/charte-chimique-detail-card-admin.component';

import { CharteChimiqueDetailCriteria } from '../../../../../../controller/criteria/expedition/CharteChimiqueDetailCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import CharteChimiqueDetailAdminSearchModal from '../search/charte-chimique-detail-search-admin.component';

const CharteChimiqueDetailAdminList: React.FC = () =>  {

    const [charteChimiqueDetails, setCharteChimiqueDetails] = useState<CharteChimiqueDetailDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type CharteChimiqueDetailResponse = AxiosResponse<CharteChimiqueDetailDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [charteChimiqueDetailId, setCharteChimiqueDetailId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new CharteChimiqueDetailCriteria());

    const service = new CharteChimiqueDetailAdminService();

    const handleDeletePress = (id: number) => {
        setCharteChimiqueDetailId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(charteChimiqueDetailId);
            setCharteChimiqueDetails((prevCharteChimiqueDetails) => prevCharteChimiqueDetails.filter((charteChimiqueDetail) => charteChimiqueDetail.id !== charteChimiqueDetailId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting charte chimique detail:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [charteChimiqueDetailResponse] = await Promise.all<CharteChimiqueDetailResponse>([
            service.getList(),
            ]);
            setCharteChimiqueDetails(charteChimiqueDetailResponse.data);
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
            const charteChimiqueDetailResponse = await service.find(id);
            const charteChimiqueDetailData = charteChimiqueDetailResponse.data;
            navigation.navigate('CharteChimiqueDetailAdminUpdate', { charteChimiqueDetail: charteChimiqueDetailData });
        } catch (error) {
            console.error('Error fetching charte chimique detail data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const charteChimiqueDetailResponse = await service.find(id);
            const charteChimiqueDetailData = charteChimiqueDetailResponse.data;
            navigation.navigate('CharteChimiqueDetailAdminDetails', { charteChimiqueDetail: charteChimiqueDetailData });
        } catch (error) {
            console.error('Error fetching charte chimique detail data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new CharteChimiqueDetailCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new CharteChimiqueDetailCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<CharteChimiqueDetailResponse>([ service.findByCriteria(criteria), ]);
            setCharteChimiqueDetails(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Charte chimique detail List</Text>

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
            {charteChimiqueDetails && charteChimiqueDetails.length > 0 ? ( charteChimiqueDetails.map((charteChimiqueDetail) => (
                <CharteChimiqueDetailAdminCard key={charteChimiqueDetail.id}
                    libelle = {charteChimiqueDetail.libelle}
                    description = {charteChimiqueDetail.description}
                    elementChimiqueName = {charteChimiqueDetail.elementChimique?.libelle}
                    minimum = {charteChimiqueDetail.minimum}
                    maximum = {charteChimiqueDetail.maximum}
                    average = {charteChimiqueDetail.average}
                    methodeAnalyse = {charteChimiqueDetail.methodeAnalyse}
                    unite = {charteChimiqueDetail.unite}
                    charteChimiqueName = {charteChimiqueDetail.charteChimique?.libelle}
                    onPressDelete={() => handleDeletePress(charteChimiqueDetail.id)}
                    onUpdate={() => handleFetchAndUpdate(charteChimiqueDetail.id)}
                    onDetails={() => handleFetchAndDetails(charteChimiqueDetail.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No charte chimique details found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'CharteChimiqueDetail'} />

        <CharteChimiqueDetailAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default CharteChimiqueDetailAdminList;
