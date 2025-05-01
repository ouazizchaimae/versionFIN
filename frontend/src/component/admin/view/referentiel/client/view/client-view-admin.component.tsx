import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {ClientDto}  from '../../../../../../controller/model/referentiel/Client.model';

type ClientViewScreenRouteProp = RouteProp<{ ClientDetails: { client : ClientDto } }, 'ClientDetails'>;

type Props = { route: ClientViewScreenRouteProp; };

const ClientAdminView: React.FC<Props> = ({ route }) => {

    const { client } = route.params;
    const [isClientCollapsed, setIsClientCollapsed] = useState(false);



    const clientCollapsible = () => {
        setIsClientCollapsed(!isClientCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={clientCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Client</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isClientCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {client.id}</Text>
                        <Text style={globalStyle.infos}>Libelle: {client.libelle}</Text>
                        <Text style={globalStyle.infos}>Code: {client.code}</Text>
                        <Text style={globalStyle.infos}>Description: {client.description}</Text>
                        <Text style={globalStyle.infos}>Type client: {client?.typeClient?.libelle}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default ClientAdminView;
