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

import {SuiviProductionAdminService} from '../../../../../../controller/service/admin/supply/SuiviProductionAdminService.service';
import  {SuiviProductionDto}  from '../../../../../../controller/model/supply/SuiviProduction.model';

import {UniteDto} from '../../../../../../controller/model/referentiel/Unite.model';
import {UniteAdminService} from '../../../../../../controller/service/admin/referentiel/UniteAdminService.service';
import {StadeOperatoireDto} from '../../../../../../controller/model/referentiel/StadeOperatoire.model';
import {StadeOperatoireAdminService} from '../../../../../../controller/service/admin/referentiel/StadeOperatoireAdminService.service';
import {ProduitDto} from '../../../../../../controller/model/referentiel/Produit.model';
import {ProduitAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitAdminService.service';

const SuiviProductionAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isSuiviProductionCollapsed, setIsSuiviProductionCollapsed] = useState(true);


    const emptyProduit = new ProduitDto();
    const [produits, setProduits] = useState<ProduitDto[]>([]);
    const [produitModalVisible, setProduitModalVisible] = useState(false);
    const [selectedProduit, setSelectedProduit] = useState<ProduitDto>(emptyProduit);

    const emptyStadeOperatoire = new StadeOperatoireDto();
    const [stadeOperatoires, setStadeOperatoires] = useState<StadeOperatoireDto[]>([]);
    const [stadeOperatoireModalVisible, setStadeOperatoireModalVisible] = useState(false);
    const [selectedStadeOperatoire, setSelectedStadeOperatoire] = useState<StadeOperatoireDto>(emptyStadeOperatoire);

    const emptyUnite = new UniteDto();
    const [unites, setUnites] = useState<UniteDto[]>([]);
    const [uniteModalVisible, setUniteModalVisible] = useState(false);
    const [selectedUnite, setSelectedUnite] = useState<UniteDto>(emptyUnite);


    const service = new SuiviProductionAdminService();
    const uniteAdminService = new UniteAdminService();
    const stadeOperatoireAdminService = new StadeOperatoireAdminService();
    const produitAdminService = new ProduitAdminService();


    const { control: suiviProductionControl, handleSubmit: suiviProductionHandleSubmit, reset: suiviProductionReset} = useForm<SuiviProductionDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        description: '' ,
        volume: null ,
        tsm: null ,
        produit: undefined,
        stadeOperatoire: undefined,
        unite: undefined,
        },
    });

    const suiviProductionCollapsible = () => {
        setIsSuiviProductionCollapsed(!isSuiviProductionCollapsed);
    };

    const handleCloseProduitModal = () => {
        setProduitModalVisible(false);
    };

    const onProduitSelect = (item) => {
        setSelectedProduit(item);
        setProduitModalVisible(false);
    };
    const handleCloseStadeOperatoireModal = () => {
        setStadeOperatoireModalVisible(false);
    };

    const onStadeOperatoireSelect = (item) => {
        setSelectedStadeOperatoire(item);
        setStadeOperatoireModalVisible(false);
    };
    const handleCloseUniteModal = () => {
        setUniteModalVisible(false);
    };

    const onUniteSelect = (item) => {
        setSelectedUnite(item);
        setUniteModalVisible(false);
    };


    useEffect(() => {
        produitAdminService.getList().then(({data}) => setProduits(data)).catch(error => console.log(error));
        stadeOperatoireAdminService.getList().then(({data}) => setStadeOperatoires(data)).catch(error => console.log(error));
        uniteAdminService.getList().then(({data}) => setUnites(data)).catch(error => console.log(error));
    }, []);




    const handleSave = async (item: SuiviProductionDto) => {
        item.produit = selectedProduit;
        item.stadeOperatoire = selectedStadeOperatoire;
        item.unite = selectedUnite;
        Keyboard.dismiss();
        try {
            await service.save( item );
            suiviProductionReset();
            setSelectedProduit(emptyProduit);
            setSelectedStadeOperatoire(emptyStadeOperatoire);
            setSelectedUnite(emptyUnite);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving suiviProduction:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create SuiviProduction</Text>

            <TouchableOpacity onPress={suiviProductionCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>SuiviProduction</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isSuiviProductionCollapsed}>
                            <CustomInput control={suiviProductionControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={suiviProductionControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={suiviProductionControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                            <CustomInput control={suiviProductionControl} name={'jour'} placeholder={'Jour'} keyboardT="numeric" />
                        <TouchableOpacity onPress={() => setProduitModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedProduit.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setStadeOperatoireModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedStadeOperatoire.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={() => setUniteModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedUnite.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
            </Collapsible>
        <CustomButton onPress={suiviProductionHandleSubmit(handleSave)} text={"Save SuiviProduction"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {produits !== null && produits.length > 0 ? ( <FilterModal visibility={produitModalVisible} placeholder={"Select a Produit"} onItemSelect={onProduitSelect} items={produits} onClose={handleCloseProduitModal} variable={'libelle'} /> ) : null}
        {stadeOperatoires !== null && stadeOperatoires.length > 0 ? ( <FilterModal visibility={stadeOperatoireModalVisible} placeholder={"Select a StadeOperatoire"} onItemSelect={onStadeOperatoireSelect} items={stadeOperatoires} onClose={handleCloseStadeOperatoireModal} variable={'libelle'} /> ) : null}
        {unites !== null && unites.length > 0 ? ( <FilterModal visibility={uniteModalVisible} placeholder={"Select a Unite"} onItemSelect={onUniteSelect} items={unites} onClose={handleCloseUniteModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default SuiviProductionAdminCreate;
