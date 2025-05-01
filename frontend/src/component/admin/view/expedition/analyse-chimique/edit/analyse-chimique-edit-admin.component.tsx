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

import {AnalyseChimiqueAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueAdminService.service';
import  {AnalyseChimiqueDto}  from '../../../../../../controller/model/expedition/AnalyseChimique.model';

import {AnalyseChimiqueDetailDto} from '../../../../../../controller/model/expedition/AnalyseChimiqueDetail.model';
import {AnalyseChimiqueDetailAdminService} from '../../../../../../controller/service/admin/expedition/AnalyseChimiqueDetailAdminService.service';
import {ElementChimiqueDto} from '../../../../../../controller/model/referentiel/ElementChimique.model';
import {ElementChimiqueAdminService} from '../../../../../../controller/service/admin/referentiel/ElementChimiqueAdminService.service';
import {ProduitMarchandDto} from '../../../../../../controller/model/referentiel/ProduitMarchand.model';
import {ProduitMarchandAdminService} from '../../../../../../controller/service/admin/referentiel/ProduitMarchandAdminService.service';

type AnalyseChimiqueUpdateScreenRouteProp = RouteProp<{ AnalyseChimiqueUpdate: { analyseChimique: AnalyseChimiqueDto } }, 'AnalyseChimiqueUpdate'>;

type Props = { route: AnalyseChimiqueUpdateScreenRouteProp; };

const AnalyseChimiqueAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { analyseChimique } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

    const emptyElementChimique = new ElementChimiqueDto();
    const [elementChimiques, setElementChimiques] = useState<ElementChimiqueDto[]>([]);
    const [elementChimiqueModalVisible, setElementChimiqueModalVisible] = useState(false);
    const [selectedElementChimique, setSelectedElementChimique] = useState<ElementChimiqueDto>(emptyElementChimique);

    const emptyProduitMarchand = new ProduitMarchandDto();
    const [produitMarchands, setProduitMarchands] = useState<ProduitMarchandDto[]>([]);
    const [produitMarchandModalVisible, setProduitMarchandModalVisible] = useState(false);
    const [selectedProduitMarchand, setSelectedProduitMarchand] = useState<ProduitMarchandDto>(emptyProduitMarchand);


    const service = new AnalyseChimiqueAdminService();
    const analyseChimiqueDetailAdminService = new AnalyseChimiqueDetailAdminService();
    const elementChimiqueAdminService = new ElementChimiqueAdminService();
    const produitMarchandAdminService = new ProduitMarchandAdminService();

    const [analyseChimiqueDetailsElements, setAnalyseChimiqueDetailsElements] = useState<AnalyseChimiqueDetailDto[]>([]);
    const [analyseChimiqueDetails, setAnalyseChimiqueDetails] = useState<AnalyseChimiqueDetailDto>(new AnalyseChimiqueDetailDto());
    const [isEditModeAnalyseChimiqueDetails, setIsEditModeAnalyseChimiqueDetails] = useState(false);
    const [editIndexAnalyseChimiqueDetails, setEditIndexAnalyseChimiqueDetails] = useState(null);

    const [isAnalyseChimiqueDetailsElementCollapsed, setIsAnalyseChimiqueDetailsElementCollapsed] = useState(true);
    const [isAnalyseChimiqueDetailsElementsCollapsed, setIsAnalyseChimiqueDetailsElementsCollapsed] = useState(true);
    const [isAnalyseChimiqueDetails, setIsAnalyseChimiqueDetails] = useState(false);
    const [isEditAnalyseChimiqueDetailsMode, setIsEditAnalyseChimiqueDetailsMode] = useState(false);


    const { control, handleSubmit } = useForm<AnalyseChimiqueDto>({
        defaultValues: {
            id: analyseChimique.id ,
            code: analyseChimique.code ,
            libelle: analyseChimique.libelle ,
            description: analyseChimique.description ,
        },
    });



    const handleCloseElementChimiqueModal = () => {
        setElementChimiqueModalVisible(false);
    };

    const onElementChimiqueSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedElementChimique(item);
        setElementChimiqueModalVisible(false);
    };
    const handleCloseProduitMarchandModal = () => {
        setProduitMarchandModalVisible(false);
    };

    const onProduitMarchandSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedProduitMarchand(item);
        setProduitMarchandModalVisible(false);
    };


    useEffect(() => {
        produitMarchandAdminService.getList().then(({data}) => setProduitMarchands(data)).catch(error => console.log(error));
        setSelectedProduitMarchand(analyseChimique.produitMarchand)

        elementChimiqueAdminService.getList().then(({data}) => setElementChimiques(data)).catch(error => console.log(error));
    }, []);



    const handleUpdate = async (item: AnalyseChimiqueDto) => {
        item.produitMarchand = selectedProduitMarchand;
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving analyse chimique:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Analyse chimique</Text>

            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <TouchableOpacity onPress={() => setProduitMarchandModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedProduitMarchand?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Analyse chimique"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {produitMarchands &&
            <FilterModal visibility={produitMarchandModalVisible} placeholder={"Select a ProduitMarchand"} onItemSelect={onProduitMarchandSelect} items={produitMarchands} onClose={handleCloseProduitMarchandModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default AnalyseChimiqueAdminEdit;
