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

import {RatioUniteAdminService} from '../../../../../../controller/service/admin/referentiel/RatioUniteAdminService.service';
import  {RatioUniteDto}  from '../../../../../../controller/model/referentiel/RatioUnite.model';

import {EntiteDto} from '../../../../../../controller/model/referentiel/Entite.model';
import {EntiteAdminService} from '../../../../../../controller/service/admin/referentiel/EntiteAdminService.service';
import {ProduitDto} from '../../../../../../controller/model/referentiel/Produit.model';
import {ProduitAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitAdminService.service';

type RatioUniteUpdateScreenRouteProp = RouteProp<{ RatioUniteUpdate: { ratioUnite: RatioUniteDto } }, 'RatioUniteUpdate'>;

type Props = { route: RatioUniteUpdateScreenRouteProp; };

const RatioUniteAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { ratioUnite } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

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


    const { control, handleSubmit } = useForm<RatioUniteDto>({
        defaultValues: {
            id: ratioUnite.id ,
            ratio: ratioUnite.ratio ,
        },
    });



    const handleCloseEntiteModal = () => {
        setEntiteModalVisible(false);
    };

    const onEntiteSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedEntite(item);
        setEntiteModalVisible(false);
    };
    const handleCloseProduitModal = () => {
        setProduitModalVisible(false);
    };

    const onProduitSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedProduit(item);
        setProduitModalVisible(false);
    };


    useEffect(() => {
        entiteAdminService.getList().then(({data}) => setEntites(data)).catch(error => console.log(error));
        setSelectedEntite(ratioUnite.entite)
        produitAdminService.getList().then(({data}) => setProduits(data)).catch(error => console.log(error));
        setSelectedProduit(ratioUnite.produit)
    }, []);



    const handleUpdate = async (item: RatioUniteDto) => {
        item.entite = selectedEntite;
        item.produit = selectedProduit;
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving ratio unite:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Ratio unite</Text>


            <TouchableOpacity onPress={() => setEntiteModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedEntite?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <TouchableOpacity onPress={() => setProduitModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedProduit?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Ratio unite"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {entites &&
            <FilterModal visibility={entiteModalVisible} placeholder={"Select a Entite"} onItemSelect={onEntiteSelect} items={entites} onClose={handleCloseEntiteModal} variable={'libelle'} />
        }
        {produits &&
            <FilterModal visibility={produitModalVisible} placeholder={"Select a Produit"} onItemSelect={onProduitSelect} items={produits} onClose={handleCloseProduitModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default RatioUniteAdminEdit;
