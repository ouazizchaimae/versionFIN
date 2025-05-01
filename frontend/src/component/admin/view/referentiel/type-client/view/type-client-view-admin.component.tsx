import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {TypeClientDto}  from '../../../../../../controller/model/referentiel/TypeClient.model';

type TypeClientViewScreenRouteProp = RouteProp<{ TypeClientDetails: { typeClient : TypeClientDto } }, 'TypeClientDetails'>;

type Props = { route: TypeClientViewScreenRouteProp; };

const TypeClientAdminView: React.FC<Props> = ({ route }) => {

    const { typeClient } = route.params;
    const [isTypeClientCollapsed, setIsTypeClientCollapsed] = useState(false);



    const typeClientCollapsible = () => {
        setIsTypeClientCollapsed(!isTypeClientCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={typeClientCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Type client</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isTypeClientCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {typeClient.id}</Text>
                        <Text style={globalStyle.infos}>Libelle: {typeClient.libelle}</Text>
                        <Text style={globalStyle.infos}>Code: {typeClient.code}</Text>
                        <Text style={globalStyle.infos}>Style: {typeClient.style}</Text>
                        <Text style={globalStyle.infos}>Description: {typeClient.description}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default TypeClientAdminView;
