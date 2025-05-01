import {Keyboard, SafeAreaView, Text, View, TouchableOpacity} from 'react-native';
import React, {useEffect, useState} from 'react';
import {NavigationProp, RouteProp, useNavigation} from '@react-navigation/native';
import {useForm} from 'react-hook-form';
import CustomInput from '../../../../../../zynerator/gui/CustomInput';
import CustomButton from '../../../../../../zynerator/gui/CustomButton';
import {ScrollView} from 'react-native-gesture-handler';
import SaveFeedbackModal from '../../../../../../zynerator/gui/SaveFeedbackModal';
import FilterModal from '../../../../../../zynerator/gui/FilterModal';

import {globalStyle} from "../../../../../../shared/globalStyle";
import Ionicons from "react-native-vector-icons/Ionicons";

import {SuiviProductionAdminService} from '../../../../../../controller/service/admin/supply/SuiviProductionAdminService.service';
import  {SuiviProductionDto}  from '../../../../../../controller/model/supply/SuiviProduction.model';

import {UniteDto} from '../../../../../../controller/model/referentiel/Unite.model';
import {UniteAdminService} from '../../../../../../controller/service/admin/referentiel/UniteAdminService.service';
import {StadeOperatoireDto} from '../../../../../../controller/model/referentiel/StadeOperatoire.model';
import {StadeOperatoireAdminService} from '../../../../../../controller/service/admin/referentiel/StadeOperatoireAdminService.service';
import {ProduitDto} from '../../../../../../controller/model/referentiel/Produit.model';
import {ProduitAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitAdminService.service';

type SuiviProductionUpdateScreenRouteProp = RouteProp<{ SuiviProductionUpdate: { suiviProduction: SuiviProductionDto } }, 'SuiviProductionUpdate'>;

type Props = { route: SuiviProductionUpdateScreenRouteProp; };

const SuiviProductionAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { suiviProduction } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

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


    const { control, handleSubmit } = useForm<SuiviProductionDto>({
        defaultValues: {
            id: suiviProduction.id ,
            code: suiviProduction.code ,
            libelle: suiviProduction.libelle ,
            description: suiviProduction.description ,
            jour: suiviProduction.jour ,
            volume: suiviProduction.volume ,
            tsm: suiviProduction.tsm ,
        },
    });



    const handleCloseProduitModal = () => {
        setProduitModalVisible(false);
    };

    const onProduitSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedProduit(item);
        setProduitModalVisible(false);
    };
    const handleCloseStadeOperatoireModal = () => {
        setStadeOperatoireModalVisible(false);
    };

    const onStadeOperatoireSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedStadeOperatoire(item);
        setStadeOperatoireModalVisible(false);
    };
    const handleCloseUniteModal = () => {
        setUniteModalVisible(false);
    };

    const onUniteSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedUnite(item);
        setUniteModalVisible(false);
    };


    useEffect(() => {
        produitAdminService.getList().then(({data}) => setProduits(data)).catch(error => console.log(error));
        setSelectedProduit(suiviProduction.produit)
        stadeOperatoireAdminService.getList().then(({data}) => setStadeOperatoires(data)).catch(error => console.log(error));
        setSelectedStadeOperatoire(suiviProduction.stadeOperatoire)
        uniteAdminService.getList().then(({data}) => setUnites(data)).catch(error => console.log(error));
        setSelectedUnite(suiviProduction.unite)
    }, []);



    const handleUpdate = async (item: SuiviProductionDto) => {
        item.produit = selectedProduit;
        item.stadeOperatoire = selectedStadeOperatoire;
        item.unite = selectedUnite;
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving suivi production:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Suivi production</Text>

            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />
            <CustomInput control={control} name={'jour'} placeholder={'Jour'} keyboardT="numeric" />

            <TouchableOpacity onPress={() => setProduitModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedProduit?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <TouchableOpacity onPress={() => setStadeOperatoireModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedStadeOperatoire?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <TouchableOpacity onPress={() => setUniteModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedUnite?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Suivi production"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {produits &&
            <FilterModal visibility={produitModalVisible} placeholder={"Select a Produit"} onItemSelect={onProduitSelect} items={produits} onClose={handleCloseProduitModal} variable={'libelle'} />
        }
        {stadeOperatoires &&
            <FilterModal visibility={stadeOperatoireModalVisible} placeholder={"Select a StadeOperatoire"} onItemSelect={onStadeOperatoireSelect} items={stadeOperatoires} onClose={handleCloseStadeOperatoireModal} variable={'libelle'} />
        }
        {unites &&
            <FilterModal visibility={uniteModalVisible} placeholder={"Select a Unite"} onItemSelect={onUniteSelect} items={unites} onClose={handleCloseUniteModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default SuiviProductionAdminEdit;
