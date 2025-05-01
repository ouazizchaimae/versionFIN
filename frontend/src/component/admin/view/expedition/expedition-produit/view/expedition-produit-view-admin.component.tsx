import {Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';
import {RouteProp} from '@react-navigation/native';
import Collapsible from 'react-native-collapsible';
import {ScrollView} from 'react-native-gesture-handler';
import {globalStyle} from "../../../../../../shared/globalStyle";

import  {ExpeditionProduitDto}  from '../../../../../../controller/model/expedition/ExpeditionProduit.model';

type ExpeditionProduitViewScreenRouteProp = RouteProp<{ ExpeditionProduitDetails: { expeditionProduit : ExpeditionProduitDto } }, 'ExpeditionProduitDetails'>;

type Props = { route: ExpeditionProduitViewScreenRouteProp; };

const ExpeditionProduitAdminView: React.FC<Props> = ({ route }) => {

    const { expeditionProduit } = route.params;
    const [isExpeditionProduitCollapsed, setIsExpeditionProduitCollapsed] = useState(false);



    const expeditionProduitCollapsible = () => {
        setIsExpeditionProduitCollapsed(!isExpeditionProduitCollapsed);
    };

return(
    <View style={{ padding: 20 }}>

        <ScrollView>

            <TouchableOpacity onPress={expeditionProduitCollapsible} style={{ backgroundColor: '#ffd700', padding: 10, borderRadius: 10, marginVertical: 5 }}>
                <Text style={{ textAlign: 'center', fontWeight: 'bold', fontSize: 20 }}>Expedition produit</Text>
            </TouchableOpacity>

            <Collapsible collapsed={isExpeditionProduitCollapsed}>

                <View style={globalStyle.itemCard}>

                    <View>

                        <Text style={globalStyle.infos}>Id: {expeditionProduit.id}</Text>
                        <Text style={globalStyle.infos}>Code: {expeditionProduit.code}</Text>
                        <Text style={globalStyle.infos}>Libelle: {expeditionProduit.libelle}</Text>
                        <Text style={globalStyle.infos}>Description: {expeditionProduit.description}</Text>
                        <Text style={globalStyle.infos}>Analyse chimique: {expeditionProduit?.analyseChimique?.libelle}</Text>
                        <Text style={globalStyle.infos}>Charte chimique: {expeditionProduit?.charteChimique?.libelle}</Text>

                    </View>

                </View>

            </Collapsible>


        </ScrollView>

    </View>
);
};

export default ExpeditionProduitAdminView;
