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

import {StadeOperatoireAdminService} from '../../../../../../controller/service/admin/referentiel/StadeOperatoireAdminService.service';
import  {StadeOperatoireDto}  from '../../../../../../controller/model/referentiel/StadeOperatoire.model';

import {StadeOperatoireProduitDto} from '../../../../../../controller/model/referentiel/StadeOperatoireProduit.model';
import {StadeOperatoireProduitAdminService} from '../../../../../../controller/service/admin/referentiel/StadeOperatoireProduitAdminService.service';
import {EntiteDto} from '../../../../../../controller/model/referentiel/Entite.model';
import {EntiteAdminService} from '../../../../../../controller/service/admin/referentiel/EntiteAdminService.service';
import {ProduitDto} from '../../../../../../controller/model/referentiel/Produit.model';
import {ProduitAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitAdminService.service';

const StadeOperatoireAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isStadeOperatoireCollapsed, setIsStadeOperatoireCollapsed] = useState(true);


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

    const { control: stadeOperatoireControl, handleSubmit: stadeOperatoireHandleSubmit, reset: stadeOperatoireReset} = useForm<StadeOperatoireDto>({
        defaultValues: {
        code: '' ,
        libelle: '' ,
        style: '' ,
        description: '' ,
        capaciteMin: null ,
        capaciteMax: null ,
        indice: null ,
        entite: undefined,
        },
    });

    const stadeOperatoireCollapsible = () => {
        setIsStadeOperatoireCollapsed(!isStadeOperatoireCollapsed);
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



    const handleSave = async (item: StadeOperatoireDto) => {
        item.entite = selectedEntite;
        Keyboard.dismiss();
        try {
            await service.save( item );
            stadeOperatoireReset();
            setSelectedEntite(emptyEntite);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving stadeOperatoire:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create StadeOperatoire</Text>

            <TouchableOpacity onPress={stadeOperatoireCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>StadeOperatoire</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isStadeOperatoireCollapsed}>
                            <CustomInput control={stadeOperatoireControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={stadeOperatoireControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={stadeOperatoireControl} name={'style'} placeholder={'Style'} keyboardT="default" />
                            <CustomInput control={stadeOperatoireControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                        <TouchableOpacity onPress={() => setEntiteModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedEntite.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
            </Collapsible>
        <CustomButton onPress={stadeOperatoireHandleSubmit(handleSave)} text={"Save StadeOperatoire"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {entites !== null && entites.length > 0 ? ( <FilterModal visibility={entiteModalVisible} placeholder={"Select a Entite"} onItemSelect={onEntiteSelect} items={entites} onClose={handleCloseEntiteModal} variable={'libelle'} /> ) : null}
        {produits !== null && produits.length > 0 ? ( <FilterModal visibility={produitModalVisible} placeholder={"Select a Produit"} onItemSelect={onProduitSelect} items={produits} onClose={handleCloseProduitModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default StadeOperatoireAdminCreate;
