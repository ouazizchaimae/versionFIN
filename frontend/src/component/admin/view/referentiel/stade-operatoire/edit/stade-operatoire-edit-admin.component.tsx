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

import {StadeOperatoireAdminService} from '../../../../../../controller/service/admin/referentiel/StadeOperatoireAdminService.service';
import  {StadeOperatoireDto}  from '../../../../../../controller/model/referentiel/StadeOperatoire.model';

import {StadeOperatoireProduitDto} from '../../../../../../controller/model/referentiel/StadeOperatoireProduit.model';
import {StadeOperatoireProduitAdminService} from '../../../../../../controller/service/admin/referentiel/StadeOperatoireProduitAdminService.service';
import {EntiteDto} from '../../../../../../controller/model/referentiel/Entite.model';
import {EntiteAdminService} from '../../../../../../controller/service/admin/referentiel/EntiteAdminService.service';
import {ProduitDto} from '../../../../../../controller/model/referentiel/Produit.model';
import {ProduitAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitAdminService.service';

type StadeOperatoireUpdateScreenRouteProp = RouteProp<{ StadeOperatoireUpdate: { stadeOperatoire: StadeOperatoireDto } }, 'StadeOperatoireUpdate'>;

type Props = { route: StadeOperatoireUpdateScreenRouteProp; };

const StadeOperatoireAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { stadeOperatoire } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

    const emptyEntite = new EntiteDto();
    const [entites, setEntites] = useState<EntiteDto[]>([]);
    const [entiteModalVisible, setEntiteModalVisible] = useState(false);
    const [selectedEntite, setSelectedEntite] = useState<EntiteDto>(emptyEntite);

    const emptyProduit = new ProduitDto();
    const [produits, setProduits] = useState<ProduitDto[]>([]);
    const [produitModalVisible, setProduitModalVisible] = useState(false);
    const [selectedProduit, setSelectedProduit] = useState<ProduitDto>(emptyProduit);


    const service = new StadeOperatoireAdminService();
    const stadeOperatoireProduitAdminService = new StadeOperatoireProduitAdminService();
    const entiteAdminService = new EntiteAdminService();
    const produitAdminService = new ProduitAdminService();

    const [stadeOperatoireProduits, setStadeOperatoireProduits] = useState<StadeOperatoireProduitDto[]>(new Array<StadeOperatoireProduitDto>());

    const { control, handleSubmit } = useForm<StadeOperatoireDto>({
        defaultValues: {
            id: stadeOperatoire.id ,
            code: stadeOperatoire.code ,
            libelle: stadeOperatoire.libelle ,
            style: stadeOperatoire.style ,
            description: stadeOperatoire.description ,
            capaciteMin: stadeOperatoire.capaciteMin ,
            capaciteMax: stadeOperatoire.capaciteMax ,
            indice: stadeOperatoire.indice ,
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
        setSelectedEntite(stadeOperatoire.entite)
        produitAdminService.getList().then(({data}) => {
            const StadeOperatoireProduits = data?.map(prepareStadeOperatoireProduit)
            setStadeOperatoireProduits(stadeOperatoireProduits)
        })

    }, []);

    const prepareStadeOperatoireProduit = (produit: ProduitDto) => {
        const stadeOperatoireProduit = new StadeOperatoireProduitDto();
        stadeOperatoireProduit.produit = produit;
        return stadeOperatoireProduit;
    }


    const handleUpdate = async (item: StadeOperatoireDto) => {
        item.entite = selectedEntite;
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving stade operatoire:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Stade operatoire</Text>

            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'style'} placeholder={'Style'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <TouchableOpacity onPress={() => setEntiteModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedEntite?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Stade operatoire"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {entites &&
            <FilterModal visibility={entiteModalVisible} placeholder={"Select a Entite"} onItemSelect={onEntiteSelect} items={entites} onClose={handleCloseEntiteModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default StadeOperatoireAdminEdit;
