import {ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useCallback, useState} from 'react';
import {NavigationProp, useFocusEffect, useNavigation} from '@react-navigation/native';
import ConfirmDeleteModal from '../../../../../../zynerator/gui/ConfirmDeleteModal';
import {AxiosResponse} from 'axios';
import Ionicons from 'react-native-vector-icons/Ionicons';
import {RatioUniteAdminService} from '../../../../../../controller/service/admin/referentiel/RatioUniteAdminService.service';
import  {RatioUniteDto}  from '../../../../../../controller/model/referentiel/RatioUnite.model';
import RatioUniteAdminCard from '../card/ratio-unite-card-admin.component';

import { RatioUniteCriteria } from '../../../../../../controller/criteria/referentiel/RatioUniteCriteria.model';
import {globalStyle} from '../../../../../../shared/globalStyle';
import RatioUniteAdminSearchModal from '../search/ratio-unite-search-admin.component';

const RatioUniteAdminList: React.FC = () =>  {

    const [ratioUnites, setRatioUnites] = useState<RatioUniteDto[]>([]);
    const navigation = useNavigation<NavigationProp<any>>();
    type RatioUniteResponse = AxiosResponse<RatioUniteDto[]>;
    const [isDeleteModalVisible, setIsDeleteModalVisible] = useState(false);
    const [ratioUniteId, setRatioUniteId] = useState(0);

    const [isSearchModalVisible, setIsSearchModalVisible] = useState(false);
    const [criteria, setCriteria] = useState(new RatioUniteCriteria());

    const service = new RatioUniteAdminService();

    const handleDeletePress = (id: number) => {
        setRatioUniteId(id);
        setIsDeleteModalVisible(true);
    };

    const handleCancelDelete = () => {
        setIsDeleteModalVisible(false);
    };

    const handleConfirmDelete = async () => {
        try {
            await service.delete(ratioUniteId);
            setRatioUnites((prevRatioUnites) => prevRatioUnites.filter((ratioUnite) => ratioUnite.id !== ratioUniteId));
            setIsDeleteModalVisible(false);
        } catch (error) {
            console.error('Error deleting ratio unite:', error);
            setIsDeleteModalVisible(false);
        }
    };

    const fetchData = async () => {
        try {
            const [ratioUniteResponse] = await Promise.all<RatioUniteResponse>([
            service.getList(),
            ]);
            setRatioUnites(ratioUniteResponse.data);
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
            const ratioUniteResponse = await service.find(id);
            const ratioUniteData = ratioUniteResponse.data;
            navigation.navigate('RatioUniteAdminUpdate', { ratioUnite: ratioUniteData });
        } catch (error) {
            console.error('Error fetching ratio unite data:', error);
        }
    };

    const handleFetchAndDetails = async (id: number) => {
        try {
            const ratioUniteResponse = await service.find(id);
            const ratioUniteData = ratioUniteResponse.data;
            navigation.navigate('RatioUniteAdminDetails', { ratioUnite: ratioUniteData });
        } catch (error) {
            console.error('Error fetching ratio unite data:', error);
        }
    };

    const handleSearch = () => {
        fetchDataByCriteria()
        setIsSearchModalVisible(false);
        setCriteria(new RatioUniteCriteria())
    };

    const handleSearchModalClose = () => {
        setIsSearchModalVisible(false);
        setCriteria(new RatioUniteCriteria())
    };

    const fetchDataByCriteria = async () => {
        try {
            const [response] = await Promise.all<RatioUniteResponse>([ service.findByCriteria(criteria), ]);
            setRatioUnites(response.data);
        } catch (error) {
            console.error(error);
        }
    };

return(
    <ScrollView showsVerticalScrollIndicator={false} style={globalStyle.scrollViewList}>

        <View style={globalStyle.headerContainer}>
            <Text style={globalStyle.textHeaderList} >Ratio unite List</Text>

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
            {ratioUnites && ratioUnites.length > 0 ? ( ratioUnites.map((ratioUnite) => (
                <RatioUniteAdminCard key={ratioUnite.id}
                    entiteName = {ratioUnite.entite?.libelle}
                    produitName = {ratioUnite.produit?.libelle}
                    ratio = {ratioUnite.ratio}
                    onPressDelete={() => handleDeletePress(ratioUnite.id)}
                    onUpdate={() => handleFetchAndUpdate(ratioUnite.id)}
                    onDetails={() => handleFetchAndDetails(ratioUnite.id)}
                />
                )) ) : (
                <Text style={globalStyle.textNotFound}>No ratio unites found.</Text>
            )}
        </View>

        <ConfirmDeleteModal isVisible={isDeleteModalVisible} handleConfirmDelete={handleConfirmDelete} handleCancelDelete={handleCancelDelete} name={'RatioUnite'} />

        <RatioUniteAdminSearchModal
                isVisible={isSearchModalVisible}
                handleSearch={handleSearch}
                handleClose={handleSearchModalClose}
                criteria={criteria}
                setCriteria={setCriteria}
        />
    </ScrollView>

);
};

export default RatioUniteAdminList;
