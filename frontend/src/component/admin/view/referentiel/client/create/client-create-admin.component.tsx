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

import {ClientAdminService} from '../../../../../../controller/service/admin/referentiel/ClientAdminService.service';
import  {ClientDto}  from '../../../../../../controller/model/referentiel/Client.model';

import {TypeClientDto} from '../../../../../../controller/model/referentiel/TypeClient.model';
import {TypeClientAdminService} from '../../../../../../controller/service/admin/referentiel/TypeClientAdminService.service';

const ClientAdminCreate = () => {

    const [showSavedModal, setShowSavedModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [isClientCollapsed, setIsClientCollapsed] = useState(true);


    const emptyTypeClient = new TypeClientDto();
    const [typeClients, setTypeClients] = useState<TypeClientDto[]>([]);
    const [typeClientModalVisible, setTypeClientModalVisible] = useState(false);
    const [selectedTypeClient, setSelectedTypeClient] = useState<TypeClientDto>(emptyTypeClient);


    const service = new ClientAdminService();
    const typeClientAdminService = new TypeClientAdminService();


    const { control: clientControl, handleSubmit: clientHandleSubmit, reset: clientReset} = useForm<ClientDto>({
        defaultValues: {
        libelle: '' ,
        code: '' ,
        description: '' ,
        typeClient: undefined,
        },
    });

    const clientCollapsible = () => {
        setIsClientCollapsed(!isClientCollapsed);
    };

    const handleCloseTypeClientModal = () => {
        setTypeClientModalVisible(false);
    };

    const onTypeClientSelect = (item) => {
        setSelectedTypeClient(item);
        setTypeClientModalVisible(false);
    };


    useEffect(() => {
        typeClientAdminService.getList().then(({data}) => setTypeClients(data)).catch(error => console.log(error));
    }, []);




    const handleSave = async (item: ClientDto) => {
        item.typeClient = selectedTypeClient;
        Keyboard.dismiss();
        try {
            await service.save( item );
            clientReset();
            setSelectedTypeClient(emptyTypeClient);
            setShowSavedModal(true);
            setTimeout(() => setShowSavedModal(false), 1500);
        } catch (error) {
            console.error('Error saving client:', error);
            setShowErrorModal(true);
            setTimeout(() => setShowErrorModal(false), 1500);
        }
    };

return(
    <SafeAreaView style={globalStyle.safeAreaViewCreate} >
        <ScrollView style={globalStyle.scrolllViewCreate} showsVerticalScrollIndicator={false} keyboardShouldPersistTaps="handled" >
            <Text style={globalStyle.textHeaderCreate} >Create Client</Text>

            <TouchableOpacity onPress={clientCollapsible} style={globalStyle.touchableOpacityCreate}>
                <Text style={globalStyle.touchableOpacityButtonCreate}>Client</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isClientCollapsed}>
                            <CustomInput control={clientControl} name={'libelle'} placeholder={'Libelle'} keyboardT="default" />
                            <CustomInput control={clientControl} name={'code'} placeholder={'Code'} keyboardT="default" />
                            <CustomInput control={clientControl} name={'description'} placeholder={'Description'} keyboardT="default" />
                        <TouchableOpacity onPress={() => setTypeClientModalVisible(true)} style={globalStyle.placeHolder} >
                            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                                <Text>{selectedTypeClient.libelle}</Text>
                                <Ionicons name="caret-down-outline" size={22} color={'black'} />
                            </View>
                        </TouchableOpacity>
            </Collapsible>
        <CustomButton onPress={clientHandleSubmit(handleSave)} text={"Save Client"} bgColor={'#000080'} fgColor={'white'} />
        </ScrollView>
        <SaveFeedbackModal isVisible={showSavedModal} icon={'checkmark-done-sharp'} message={'saved successfully'} iconColor={'#32cd32'} />
        <SaveFeedbackModal isVisible={showErrorModal} icon={'close-sharp'} message={'Error on saving'} iconColor={'red'} />
        {typeClients !== null && typeClients.length > 0 ? ( <FilterModal visibility={typeClientModalVisible} placeholder={"Select a TypeClient"} onItemSelect={onTypeClientSelect} items={typeClients} onClose={handleCloseTypeClientModal} variable={'libelle'} /> ) : null}
    </SafeAreaView>
);
};
export default ClientAdminCreate;
