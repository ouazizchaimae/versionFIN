import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {UniteAdminService} from '../../../../../../controller/service/admin/referentiel/UniteAdminService.service';
import  {UniteDto}  from '../../../../../../controller/model/referentiel/Unite.model';
import UniteAdminCard from '../card/unite-card-admin.component';

import { UniteCriteria } from '../../../../../../controller/criteria/referentiel/UniteCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import UniteAdminSearchModal from '../search/unite-search-admin.component';

const UniteAdminList: React.FC = () =>  {

    const [unites, setUnites] = useState<UniteDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type UniteResponse = AxiosResponse<UniteDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [uniteId, setUniteId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new UniteCriteria());

    const service = new UniteAdminService();

    const handleDeletePress = (id: number) => {
        setUniteId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(uniteId);
            setUnites((prevUnites) => prevUnites.filter((unite) => unite.id !== uniteId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting unite:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [uniteResponse] = await Promise.all<UniteResponse>([
            service.getList(),
            ]);
            setUnites(uniteResponse.data);
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
            const uniteResponse = await service.find(id);
            const uniteData = uniteResponse.data;
            navigation.navigate('UniteAdminUpdate', { unite: uniteData });
        } catch (error) {
            console.error('Error fetching unite data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const uniteResponse = await service.find(id);
            const uniteData = uniteResponse.data;
            navigation.navigate('UniteAdminDetails', { unite: uniteData });
        } catch (error) {
            console.error('Error fetching unite data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new UniteCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new UniteCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<UniteResponse>([ service.findByCriteria(criteria), ]);
            setUnites(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Unite List</Text>

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
            {unites && unites.length > 0 ? ( unites.map((unite) => (
                <UniteAdminCard key={unite.id}
                    code = {unite.code}
                    libelle = {unite.libelle}
                    description = {unite.description}
                    style = {unite.style}
                    onPressDelete={() => handleDeletePress(unite.id)}
                    onUpdate={() => handleFetchAndUpdate(unite.id)}
                    onDetails={() => handleFetchAndDetails(unite.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No unites found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'Unite'} />

        <UniteAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default UniteAdminList;
