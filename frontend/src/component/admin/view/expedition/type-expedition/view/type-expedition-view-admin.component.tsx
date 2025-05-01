import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {TypeExpeditionDto}  from '../../../../../../controller/model/expedition/TypeExpedition.model';

type TypeExpeditionViewScreenRouteProp = RouteProp<{ TypeExpeditionDetails: { typeExpedition : TypeExpeditionDto } }, 'TypeExpeditionDetails'>;

type Props = { route: TypeExpeditionViewScreenRouteProp; };

const TypeExpeditionAdminView: React.FC<Props> = ({ route }) => {

    const { typeExpedition } = route.params;
    const [isTypeExpeditionCollapsed, setIsTypeExpeditionCollapsed] = useState(false);



    const typeExpeditionCollapsible = () => {
        setIsTypeExpeditionCollapsed(!isTypeExpeditionCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={typeExpeditionCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Type expedition</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isTypeExpeditionCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {typeExpedition.id}</Text>
                        <Text style={globalStyle.infos}>Libelle: {typeExpedition.libelle}</Text>
                        <Text style={globalStyle.infos}>Code: {typeExpedition.code}</Text>
                        <Text style={globalStyle.infos}>Style: {typeExpedition.style}</Text>
                        <Text style={globalStyle.infos}>Description: {typeExpedition.description}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default TypeExpeditionAdminView;
