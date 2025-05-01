import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {AnalyseChimiqueDetailAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueDetailAdminService.service';
import  {AnalyseChimiqueDetailDto}  from '../../../../../../controller/model/expedition/AnalyseChimiqueDetail.model';
import AnalyseChimiqueDetailAdminCard from '../card/analyse-chimique-detail-card-admin.component';

import { AnalyseChimiqueDetailCriteria } from '../../../../../../controller/criteria/expedition/AnalyseChimiqueDetailCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import AnalyseChimiqueDetailAdminSearchModal from '../search/analyse-chimique-detail-search-admin.component';

const AnalyseChimiqueDetailAdminList: React.FC = () =>  {

    const [analyseChimiqueDetails, setAnalyseChimiqueDetails] = useState<AnalyseChimiqueDetailDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type AnalyseChimiqueDetailResponse = AxiosResponse<AnalyseChimiqueDetailDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [analyseChimiqueDetailId, setAnalyseChimiqueDetailId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new AnalyseChimiqueDetailCriteria());

    const service = new AnalyseChimiqueDetailAdminService();

    const handleDeletePress = (id: number) => {
        setAnalyseChimiqueDetailId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(analyseChimiqueDetailId);
            setAnalyseChimiqueDetails((prevAnalyseChimiqueDetails) => prevAnalyseChimiqueDetails.filter((analyseChimiqueDetail) => analyseChimiqueDetail.id !== analyseChimiqueDetailId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting analyse chimique detail:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [analyseChimiqueDetailResponse] = await Promise.all<AnalyseChimiqueDetailResponse>([
            service.getList(),
            ]);
            setAnalyseChimiqueDetails(analyseChimiqueDetailResponse.data);
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
            const analyseChimiqueDetailResponse = await service.find(id);
            const analyseChimiqueDetailData = analyseChimiqueDetailResponse.data;
            navigation.navigate('AnalyseChimiqueDetailAdminUpdate', { analyseChimiqueDetail: analyseChimiqueDetailData });
        } catch (error) {
            console.error('Error fetching analyse chimique detail data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const analyseChimiqueDetailResponse = await service.find(id);
            const analyseChimiqueDetailData = analyseChimiqueDetailResponse.data;
            navigation.navigate('AnalyseChimiqueDetailAdminDetails', { analyseChimiqueDetail: analyseChimiqueDetailData });
        } catch (error) {
            console.error('Error fetching analyse chimique detail data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new AnalyseChimiqueDetailCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new AnalyseChimiqueDetailCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<AnalyseChimiqueDetailResponse>([ service.findByCriteria(criteria), ]);
            setAnalyseChimiqueDetails(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Analyse chimique detail List</Text>

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
            {analyseChimiqueDetails && analyseChimiqueDetails.length > 0 ? ( analyseChimiqueDetails.map((analyseChimiqueDetail) => (
                <AnalyseChimiqueDetailAdminCard key={analyseChimiqueDetail.id}
                    libelle = {analyseChimiqueDetail.libelle}
                    description = {analyseChimiqueDetail.description}
                    elementChimiqueName = {analyseChimiqueDetail.elementChimique?.libelle}
                    valeur = {analyseChimiqueDetail.valeur}
                    conformite = {analyseChimiqueDetail.conformite}
                    surqualite = {analyseChimiqueDetail.surqualite}
                    analyseChimiqueName = {analyseChimiqueDetail.analyseChimique?.libelle}
                    onPressDelete={() => handleDeletePress(analyseChimiqueDetail.id)}
                    onUpdate={() => handleFetchAndUpdate(analyseChimiqueDetail.id)}
                    onDetails={() => handleFetchAndDetails(analyseChimiqueDetail.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No analyse chimique details found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'AnalyseChimiqueDetail'} />

        <AnalyseChimiqueDetailAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default AnalyseChimiqueDetailAdminList;
