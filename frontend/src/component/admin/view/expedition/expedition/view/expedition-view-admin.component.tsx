import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {ExpeditionDto}  from '../../../../../../controller/model/expedition/Expedition.model';

type ExpeditionViewScreenRouteProp = RouteProp<{ ExpeditionDetails: { expedition : ExpeditionDto } }, 'ExpeditionDetails'>;

type Props = { route: ExpeditionViewScreenRouteProp; };

const ExpeditionAdminView: React.FC<Props> = ({ route }) => {

    const { expedition } = route.params;
    const [isExpeditionCollapsed, setIsExpeditionCollapsed] = useState(false);

    const [isExpeditionProduitsCollapsed, setIsExpeditionProduitsCollapsed] = useState(true);

    const expeditionProduitsCollapsible = () => {
        setIsExpeditionProduitsCollapsed(!isExpeditionProduitsCollapsed);
    };

    const expeditionCollapsible = () => {
        setIsExpeditionCollapsed(!isExpeditionCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={expeditionCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Expedition</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isExpeditionCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {expedition.id}</Text>
                        <Text style={globalStyle.infos}>Code: {expedition.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {expedition.libelle}</Text>
                        <Text style={globalStyle.infos}>Description: {expedition.description}</Text>
                        <Text style={globalStyle.infos}>Client: {expedition?.client?.libelle}</Text>
                        <Text style={globalStyle.infos}>Type expedition: {expedition?.typeExpedition?.libelle}</Text>

                    </View>

                </View>

            </Collapsible>

            <TouchableOpacity onPress={expeditionProduitsCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Expedition produits</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isExpeditionProduitsCollapsed}>

                {expedition.expeditionProduits && expedition.expeditionProduits.length > 0 ? ( expedition.expeditionProduits.map((item, index) => (
                    <View key={index} style={globalStyle.itemCard}>
                        <View>
                            <Text style={globalStyle.infos}>Code : {item.code}</Text>
                            <Text style={globalStyle.infos}>Libelle : {item.libelle}</Text>
                            <Text style={globalStyle.infos}>Description : {item.description}</Text>
                            <Text style={globalStyle.infos}>Analyse chimique: {item?.analyseChimique?.libelle}</Text>
                            <Text style={globalStyle.infos}>Charte chimique: {item?.charteChimique?.libelle}</Text>

                        </View>
                    </View>
                    )) ) : (
                    <View style={globalStyle.itemCard}>
                        <Text style={globalStyle.infos}>No expedition produits</Text>
                    </View>
                )}

            </Collapsible>

        </ScrollView>

    </View>
);
};

export default ExpeditionAdminView;
