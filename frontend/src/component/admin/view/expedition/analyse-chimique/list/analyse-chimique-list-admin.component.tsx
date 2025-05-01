import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {AnalyseChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueAdminService.service';
import  {AnalyseChimiqueDto}  from '../../../../../../controller/model/expedition/AnalyseChimique.model';
import AnalyseChimiqueAdminCard from '../card/analyse-chimique-card-admin.component';

import { AnalyseChimiqueCriteria } from '../../../../../../controller/criteria/expedition/AnalyseChimiqueCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import AnalyseChimiqueAdminSearchModal from '../search/analyse-chimique-search-admin.component';

const AnalyseChimiqueAdminList: React.FC = () =>  {

    const [analyseChimiques, setAnalyseChimiques] = useState<AnalyseChimiqueDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type AnalyseChimiqueResponse = AxiosResponse<AnalyseChimiqueDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [analyseChimiqueId, setAnalyseChimiqueId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new AnalyseChimiqueCriteria());

    const service = new AnalyseChimiqueAdminService();

    const handleDeletePress = (id: number) => {
        setAnalyseChimiqueId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(analyseChimiqueId);
            setAnalyseChimiques((prevAnalyseChimiques) => prevAnalyseChimiques.filter((analyseChimique) => analyseChimique.id !== analyseChimiqueId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting analyse chimique:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [analyseChimiqueResponse] = await Promise.all<AnalyseChimiqueResponse>([
            service.getList(),
            ]);
            setAnalyseChimiques(analyseChimiqueResponse.data);
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
            const analyseChimiqueResponse = await service.find(id);
            const analyseChimiqueData = analyseChimiqueResponse.data;
            navigation.navigate('AnalyseChimiqueAdminUpdate', { analyseChimique: analyseChimiqueData });
        } catch (error) {
            console.error('Error fetching analyse chimique data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const analyseChimiqueResponse = await service.find(id);
            const analyseChimiqueData = analyseChimiqueResponse.data;
            navigation.navigate('AnalyseChimiqueAdminDetails', { analyseChimique: analyseChimiqueData });
        } catch (error) {
            console.error('Error fetching analyse chimique data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new AnalyseChimiqueCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new AnalyseChimiqueCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<AnalyseChimiqueResponse>([ service.findByCriteria(criteria), ]);
            setAnalyseChimiques(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Analyse chimique List</Text>

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
            {analyseChimiques && analyseChimiques.length > 0 ? ( analyseChimiques.map((analyseChimique) => (
                <AnalyseChimiqueAdminCard key={analyseChimique.id}
                    code = {analyseChimique.code}
                    libelle = {analyseChimique.libelle}
                    description = {analyseChimique.description}
                    produitMarchandName = {analyseChimique.produitMarchand?.libelle}
                    onPressDelete={() => handleDeletePress(analyseChimique.id)}
                    onUpdate={() => handleFetchAndUpdate(analyseChimique.id)}
                    onDetails={() => handleFetchAndDetails(analyseChimique.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No analyse chimiques found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'AnalyseChimique'} />

        <AnalyseChimiqueAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default AnalyseChimiqueAdminList;
