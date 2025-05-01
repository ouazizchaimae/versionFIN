import {Keyboard, SafeAreaView, ScrollView, Text, TouchableOpacity, View} from 'react-native';
import React, {useEffect, useState} from 'react';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import FilterModal from '../../../../../../zynerator/gui/FilterModal';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';
import Collapsible from 'react-native-collapsible';

import {globalStyle} from '../../../../../../shared/globalStyle';
import Ionicons from 'react-native-vector-icons/Ionicons';

import {RatioUniteAdminService} from '../../../../../../controller/service/admin/referentiel/RatioUniteAdminService.service';
import  {RatioUniteDto}  from '../../../../../../controller/model/referentiel/RatioUnite.model';

import {EntiteDto} from '../../../../../../controller/model/referentiel/Entite.model';
import {EntiteAdminService} from '../../../../../../controller/service/admin/referentiel/EntiteAdminService.service';
import {ProduitDto} from '../../../../../../controller/model/referentiel/Produit.model';
import {ProduitAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitAdminService.service';

const RatioUniteAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isRatioUniteCollapsed, setIsRatioUniteCollapsed] = useState(true);


    const emptyEntite = new EntiteDto();
    const [entites, setEntites] = useState<EntiteDto[]>([]);
    const [entiteModalVisible, setEntiteModalVisible] = useState(false);
    const [selectedEntite, setSelectedEntite] = useState<EntiteDto>(emptyEntite);

    const emptyProduit = new ProduitDto();
    const [produits, setProduits] = useState<ProduitDto[]>([]);
    const [produitModalVisible, setProduitModalVisible] = useState(false);
    const [selectedProduit, setSelectedProduit] = useState<ProduitDto>(emptyProduit);


    const service = new RatioUniteAdminService();
    const entiteAdminService = new EntiteAdminService();
    const produitAdminService = new ProduitAdminService();


    const { control: ratioUniteControl, handleSubmit: ratioUniteHandleSubmit, reset: ratioUniteReset} = useForm<RatioUniteDto>({
        defaultValues: {
        entite: undefined,
        produit: undefined,
        ratio: null ,
        },
    });

    const ratioUniteCollapsible = () => {
        setIsRatioUniteCollapsed(!isRatioUniteCollapsed);
    };

    const handleCloseEntiteModal = () => {
        setEntiteModalVisible(false);
    };

    const onEntiteSelect = (item) => {
        setSelectedEntite(item);
        setEntiteModalVisible(false);
    };
    const handleCloseProduitModal = () => {
        setProduitModalVisible(false);
    };

    const onProduitSelect = (item) => {
        setSelectedProduit(item);
        setProduitModalVisible(false);
    };


    useEffect(() => {
        entiteAdminService.getList().then(({data}) => setEntites(data)).catch(error => console.log(error));
        produitAdminService.getList().then(({data}) => setProduits(data)).catch(error => console.log(error));
    }, []);




    const handleSave = async (item: RatioUniteDto) => {
        item.entite = selectedEntite;
        item.produit = selectedProduit;
        Keyboard.dismiss();
        try {
            await service.save( item );
            ratioUniteReset();
            setSelectedEntite(emptyEntite);
            setSelectedProduit(emptyProduit);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving ratioUnite:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create RatioUnite</Text>

            <TouchableOpacity onPress={ratioUniteCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>RatioUnite</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isRatioUniteCollapsed}>
                        <TouchableOpacity onPress={() => setEntiteModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedEntite.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setProduitModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedProduit.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
            </Collapsible>
        <CustomButton onPress={ratioUniteHandleSubmit(handleSave)} text={"Save RatioUnite"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {entites !== null && entites.length > 0 ? ( <FilterModal visibility={entiteModalVisible} placeholder={"Select a Entite"} onItemSelect={onEntiteSelect} items={entites} onClose={handleCloseEntiteModal} variable={'libelle'} /> ) : null}
        {produits !== null && produits.length > 0 ? ( <FilterModal visibility={produitModalVisible} placeholder={"Select a Produit"} onItemSelect={onProduitSelect} items={produits} onClose={handleCloseProduitModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default RatioUniteAdminCreate;
