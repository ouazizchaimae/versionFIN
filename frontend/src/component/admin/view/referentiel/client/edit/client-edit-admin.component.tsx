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

import {ClientAdminService} from '../../../../../../controller/service/admin/referentiel/ClientAdminService.service';
import  {ClientDto}  from '../../../../../../controller/model/referentiel/Client.model';

import {TypeClientDto} from '../../../../../../controller/model/referentiel/TypeClient.model';
import {TypeClientAdminService} from '../../../../../../controller/service/admin/referentiel/TypeClientAdminService.service';

type ClientUpdateScreenRouteProp = RouteProp<{ ClientUpdate: { client: ClientDto } }, 'ClientUpdate'>;

type Props = { route: ClientUpdateScreenRouteProp; };

const ClientAdminEdit: React.FC<Props> = ({ route }) => {

    const navigation = useNavigation<NavigationProp<any>>();
    const [showErrorModal, setShowErrorModal] = useState(false);
    const { client } = route.params;
    const [showSavedModal, setShowSavedModal] = useState(false);

    const emptyTypeClient = new TypeClientDto();
    const [typeClients, setTypeClients] = useState<TypeClientDto[]>([]);
    const [typeClientModalVisible, setTypeClientModalVisible] = useState(false);
    const [selectedTypeClient, setSelectedTypeClient] = useState<TypeClientDto>(emptyTypeClient);


    const service = new ClientAdminService();
    const typeClientAdminService = new TypeClientAdminService();


    const { control, handleSubmit } = useForm<ClientDto>({
        defaultValues: {
            id: client.id ,
            libelle: client.libelle ,
            code: client.code ,
            description: client.description ,
        },
    });



    const handleCloseTypeClientModal = () => {
        setTypeClientModalVisible(false);
    };

    const onTypeClientSelect = (item) => {
        console.log('Selected Item:', item);
        setSelectedTypeClient(item);
        setTypeClientModalVisible(false);
    };


    useEffect(() => {
        typeClientAdminService.getList().then(({data}) => setTypeClients(data)).catch(error => console.log(error));
        setSelectedTypeClient(client.typeClient)
    }, []);



    const handleUpdate = async (item: ClientDto) => {
        item.typeClient = selectedTypeClient;
        Keyboard.dismiss();
        try {
            await service.update(item);
            setShowSavedModal(true);
            setTimeout(() => {
                setShowSavedModal(false);
                navigation.goBack();
                }, 1500);
        } catch (error) {
            console.error('Error saving client:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewEdit}>

        <ScrollView style={{ margin: 20 }} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled">

            <Text style={globalStyle.textHeaderEdit} >Update Client</Text>

            <CustomInput control={control} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
            <CustomInput control={control} name={'code'} placeholder={'Code'} keyboardT="default" />
            <CustomInput control={control} name={'description'} placeholder={'Description'} keyboardT="default" />

            <TouchableOpacity onPress={() => setTypeClientModalVisible(true)} style={globalStyle.placeHolder} >

                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Text>{selectedTypeClient?.libelle}</Text>
                    <Ionicons name="caret-down-outline" size={22} color={'black'} />
                </View>

            </TouchableOpacity>

            <CustomButton onPress={handleSubmit(handleUpdate)} text={"Update Client"} bgColor={'#ffa500'} fgColor={'white'} />

        </ScrollView>

        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on updating'} iconColor={'red'} />
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'updated with success'} iconColor={'#32cd32'} />
        {typeClients &&
            <FilterModal visibility={typeClientModalVisible} placeholder={"Select a TypeClient"} onItemSelect={onTypeClientSelect} items={typeClients} onClose={handleCloseTypeClientModal} variable={'libelle'} />
        }

    </SafeAreaView>
);
};

export default ClientAdminEdit;
